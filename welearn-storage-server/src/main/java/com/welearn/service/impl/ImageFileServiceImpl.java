package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.welearn.WelearnStorageServerApplication;
import com.welearn.entity.po.common.LinkCode;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.service.ImageFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Description :
 * Created by Setsuna Jin on 2019/5/10.
 */
@Slf4j
@Service
public class ImageFileServiceImpl implements ImageFileService {

    private static final String SAVE_PATH = "/home/link-code";

    private static final String CHARSET = "utf-8";
    private static final String FORMAT = "JPG";
    // 二维码尺寸  
    private static final int QRCODE_SIZE = 800;

    /**
     * 绘制二位码图片
     * @param content 二位码内容
     * @return 图片缓存
     */
    private BufferedImage drawQrCode(String content) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 5);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    /**
     *
     * @param qrCode 二位码图片
     * @param number 二位码编号
     * @param numberImagePath 数字图片路径 (0-9 需要手动取区域贴图)
     * @param frameImagePath 二位码边框图片路径
     */
    private void drawFrameAndNumber(BufferedImage qrCode, Long number, String numberImagePath, String frameImagePath) throws Exception {
        File frameImageFile = new File(frameImagePath);
        File numberImageFile = new File(numberImagePath);
        if (!frameImageFile.exists() || !numberImageFile.exists() ) {
            throw new BusinessVerifyFailedException("numberImage or frameImage file not found");
        }
        Image frame = ImageIO.read(frameImageFile);
        Image numbers = ImageIO.read(numberImageFile);
        // 创建二位码
        Graphics2D graph = qrCode.createGraphics();
        // 将边框覆盖在二位码上
        graph.drawImage(frame, 0, 0, QRCODE_SIZE, QRCODE_SIZE, null);
        // 从 numberImage 中扣取对应数字的图片 贴在边框底部
        int numberDrawPointX = 285;
        int numberDrawPointY = 693;
        int numberWidth = 38;
        int numberHeight = 44;
        String numberStr = String.format("%06d", number);
        for (int i = 0; i < numberStr.length(); i++) {
            int n = Integer.parseInt("" + numberStr.charAt(i));
            graph.drawImage(numbers, numberDrawPointX + i * numberWidth, numberDrawPointY, numberDrawPointX + (i+1) * numberWidth, numberDrawPointY + numberHeight,
                    n * numberWidth, 0, (n+1) * numberWidth, numberHeight, null);
        }
        graph.dispose();
    }

    
    private void generateLinkCodeImageFile(LinkCode linkCode, String numberImagePath, String frameImagePath, String savePath) throws Exception {
        BufferedImage qrCode = this.drawQrCode(String.format("https://shebei120.com/sn/%s", linkCode.getSerialNumber()));
        this.drawFrameAndNumber(qrCode, linkCode.getNumber(), numberImagePath, frameImagePath);
        File file = new File(savePath);
        if (!file.exists() && !file.isDirectory()) {
            if (!file.mkdirs())
                throw new BusinessVerifyFailedException("savePath: %s directory can not make", savePath);
        }
        ImageIO.write(qrCode, FORMAT, new File(getFileName(linkCode)));
    }

    private String getFileName(LinkCode linkCode){
        return SAVE_PATH + "/" + String.format("%06d", linkCode.getNumber()) + "." + FORMAT.toLowerCase();
    }

    /**
     * 关联编码的二位码图片生成
     *
     * @param linkCodes 关联编码列表
     */
    @Override
    public void generateLinkCodeQrCodeImage(List<LinkCode> linkCodes) {
        URL frameImageFile = WelearnStorageServerApplication.class.getClassLoader().getResource("image/link-code/frame.png");
        URL numberImageFile = WelearnStorageServerApplication.class.getClassLoader().getResource("image/link-code/number.png");

        if (Objects.isNull(frameImageFile) || Objects.isNull(numberImageFile))
            throw new BusinessVerifyFailedException("frameImageFile or numberImageFile url is error");
        linkCodes.forEach(linkCode -> {
            if (new File(getFileName(linkCode)).exists())
                return;
            try {
                this.generateLinkCodeImageFile(linkCode, numberImageFile.getPath(), frameImageFile.getPath(), SAVE_PATH);
            } catch (Exception e) {
                log.error("二位码图像生成失败", e);
            }
        });
    }
}
