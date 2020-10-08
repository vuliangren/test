package com.welearn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.welearn.entity.po.equipment.EquipmentTypeDescription;
import com.welearn.entity.po.equipment.EquipmentTypeIndex;
import com.welearn.entity.po.equipment.EquipmentTypeItem;
import com.welearn.entity.po.equipment.ReEquipmentTypeItemDescription;
import com.welearn.service.*;
import org.apache.commons.lang.StringUtils;
import org.aspectj.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WelearnEquipmentServerApplication.class)
public class WelearnEquipmentServerApplicationTests {


    @Test
    public void contextLoads() {

    }


//    /**
//     * 添加 GBT14885 到 设备类型 中 代码
//     * @throws IOException
//     */
//
//    @Autowired
//    private EquipmentTypeItemService equipmentTypeItemService;
//
//    @Autowired
//    private EquipmentTypeDescriptionService equipmentTypeDescriptionService;
//
//    @Autowired
//    private ReEquipmentTypeItemDescriptionService reEquipmentTypeItemDescriptionService;
//
//    @Autowired
//    private EquipmentTypeIndexService equipmentTypeIndexService;
//
//    private void addGBT14885ToEquipmentType() throws IOException {
//        String json = FileUtil.readAsString(new File("C:\\Users\\hello\\Desktop\\GBT-14885(2010)\\GBT-14885(2010).json"));
//
//        JSONArray objects = JSON.parseArray(json);
//
//        Map<String, String> indexMap = new HashMap<>();
//        indexMap.put("A1", "土地、房屋及构筑物");
//        indexMap.put("A2", "通用设备");
//        indexMap.put("A3", "专用设备");
//        indexMap.put("A4", "文物和陈列品");
//        indexMap.put("A5", "图书、档案");
//        indexMap.put("A6", "家具、用具、装具及动植物");
//        for (String id : indexMap.keySet()) {
//            EquipmentTypeIndex index = new EquipmentTypeIndex();
//            index.setId(id);
//            index.setName(indexMap.get(id));
//            equipmentTypeIndexService.create(index);
//        }
//
//        for (int i = 0; i < objects.size(); i++) {
//            JSONObject jsonObject = objects.getJSONObject(i);
//            EquipmentTypeItem item = new EquipmentTypeItem();
//            item.setId(jsonObject.getString("id"));
//            item.setFirstId(jsonObject.getString("firstId"));
//            item.setFirstName(jsonObject.getString("firstName"));
//            item.setIndexId(jsonObject.getString("indexId"));
//            item.setIndexName(jsonObject.getString("indexName"));
//            item.setSecondId(jsonObject.getString("secondId"));
//            item.setSecondName(jsonObject.getString("secondName"));
//            item.setName(jsonObject.getString("name"));
//            item.setManagementCategory("*");
//            equipmentTypeItemService.create(item);
//
//            String unit = jsonObject.getString("unit");
//            String description = jsonObject.getString("description");
//            if (StringUtils.isNotBlank(unit) || StringUtils.isNotBlank(description)) {
//                String desc = "";
//                if (StringUtils.isNotBlank(unit)) {
//                    desc += String.format("单位: %s, ", unit);
//                }
//                if (StringUtils.isNotBlank(description)) {
//                    desc += String.format("描述: %s。", description);
//                }
//                EquipmentTypeDescription typeDescription = new EquipmentTypeDescription();
//                typeDescription.setDescription(desc);
//                typeDescription.setDSort("01");
//                typeDescription.setUSort("01");
//                equipmentTypeDescriptionService.create(typeDescription);
//
//                ReEquipmentTypeItemDescription re = new ReEquipmentTypeItemDescription();
//                re.setDescriptionId(typeDescription.getId());
//                re.setItemId(item.getId());
//                reEquipmentTypeItemDescriptionService.create(re);
//            }
//            System.out.println("-------------------------" + i);
//        }
//    }
}
