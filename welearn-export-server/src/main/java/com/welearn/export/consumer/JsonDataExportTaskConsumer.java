package com.welearn.export.consumer;

import com.alibaba.fastjson.JSON;
import com.qiniu.util.Auth;
import com.welearn.entity.dto.export.JsonDataExportArgs;
import com.welearn.entity.dto.export.JsonDataExportSheet;
import com.welearn.entity.po.export.ExportTask;
import com.welearn.entity.po.storage.UploadRecord;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.export.ExportTaskConsumerHelper;
import com.welearn.util.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;
import java.util.Objects;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/11.
 */
@Slf4j
@Service
public class JsonDataExportTaskConsumer {

    @Autowired
    private ExportTaskConsumerHelper consumerHelper;

    @RabbitListener(queues = "json-export-task")
    public void consume(String exportTaskId) {
        ExportTask exportTask = consumerHelper.selectTask(exportTaskId);
        try {
            this.doExport(exportTask);
        } catch (Exception e) {
            log.error("json-export-task consume failed", e);
            consumerHelper.taskFailed(exportTask, e.getMessage());
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    private HSSFCellStyle generateCellBorderStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style=workbook.createCellStyle();
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        return style;
    }

    private void handleSheetExport(HSSFWorkbook workbook, HSSFSheet sheet, JsonDataExportSheet data) {
        int rowNum = 0;
        int colNum = data.getHeader().size();
        // 处理表头标题内容
        HSSFRow title = sheet.createRow(rowNum++);
        // 处理表头标题样式
        HSSFCellStyle titleStyle = generateCellBorderStyle(workbook);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short)12);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);

        for (int i = 0; i < colNum; i++) {
            HSSFCell cell = title.createCell(i);
            cell.setCellStyle(titleStyle);
        }
        // 合并标题的单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colNum - 1));
        title.getCell(0).setCellValue(data.getTitle());

        // 处理表头名称内容
        HSSFRow header = sheet.createRow(rowNum++);
        // 处理表头名称样式
        HSSFCellStyle headerStyle = generateCellBorderStyle(workbook);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setBorderTop(BorderStyle.DOUBLE);
        headerStyle.setBorderBottom(BorderStyle.DOUBLE);

        for (int i = 0; i < colNum; i++) {
            HSSFCell cell = header.createCell(i);
            cell.setCellValue(data.getHeader().get(i));
            cell.setCellStyle(headerStyle);
        }

        // 处理表格数据内容
        HSSFCellStyle dataStyle = this.generateCellBorderStyle(workbook);
        List<List<String>> tableData = data.getData();
        for (List<String> rowData : tableData) {
            HSSFRow row = sheet.createRow(rowNum++);
            for (int i = 0; i < colNum; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(rowData.get(i));
                cell.setCellStyle(dataStyle);
            }
        }
    }

    public void doExport(ExportTask exportTask) throws IOException {
        long startTimestamp = System.currentTimeMillis();
        // 确认接收任务
        consumerHelper.receiveTask(exportTask);
        // 验证请求参数
        JsonDataExportArgs jsonDataExportArgs = JSON.parseObject(exportTask.getArgsJson(), JsonDataExportArgs.class);
        if (Objects.isNull(jsonDataExportArgs) || jsonDataExportArgs.getSheets().isEmpty())
            throw new BusinessVerifyFailedException("请求参数格式非法");
        // POI 创建 Excel 文件
        String fileName = String.format("%s.xls", exportTask.getName());
        HSSFWorkbook workbook = new HSSFWorkbook();
        jsonDataExportArgs.getSheets().forEach((key, data) -> {
            HSSFSheet sheet = workbook.createSheet(key);
            // 处理每个 sheet 的导出
            this.handleSheetExport(workbook, sheet, data);
        });
        // 标记状态为上传中
        consumerHelper.uploadTask(exportTask);
        // 使用管道将 输出流 直接转换为 输入流
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        new Thread(()-> {
            try {
                workbook.write(out);
            } catch (IOException e) {
                log.error("HSSFWorkbook 输出流失败", e);
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("输出流关闭失败", e);
                }
            }
        }).start();
        // 上传文件到云
        String response = consumerHelper.uploadToQiniuFile(fileName, in, exportTask.getCreatorId(), ".xls", "application/vnd.ms-excel");
        // 更新任务状态
        consumerHelper.finishTask(exportTask, response, System.currentTimeMillis() - startTimestamp);
    }
}
