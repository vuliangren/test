package com.welearn.generator;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.dictionary.common.PersistantConst;
import com.welearn.util.PackageScannerUtil;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/10.
 */
public class PersistantConstGenerator {


//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        PersistantConstGenerator.generateAllServiceEntity();
//    }

    public static void generateAllServiceEntity() throws IOException, ClassNotFoundException {
        StringBuilder builder = new StringBuilder();
        for (ServiceConst service : ServiceConst.values()) {
            String packagePrefix = "com.welearn.entity.po."+service.getServiceName();
            Set<MetadataReader> entities = PackageScannerUtil.doScan(packagePrefix);
            List<PersistantConst> entityConstList = Arrays.stream(PersistantConst.values()).filter(p -> p.getService().equals(service)).collect(Collectors.toList());
            for (PersistantConst persistantConst : entityConstList) {
                String entityName = persistantConst.getClazz().getName().replace(packagePrefix+".", "");
                builder.append(String.format("%s(%s, %s.class, %d, \"%s\"),\n", entityName, service.name(), entityName, persistantConst.getUuid(), persistantConst.getDescription()));
            }
            // 根据现有的 PersistantConst 生成新的 PersistantConst (不考虑实体被删的情况, 只考虑实体增加后的追加)
            int count = 0;
            if (!entityConstList.isEmpty()){
                count = entityConstList.stream()
                        .max((a, b) -> a.getUuid() > b.getUuid() ? 1 : a.getUuid().equals(b.getUuid()) ? 0 : -1)
                        .get().getUuid() + 1;
            }
            for (MetadataReader entity : entities) {
                String entityClassName = entity.getClassMetadata().getClassName();
                if (entityClassName.contains("BasePersistant") || entityConstList.stream().anyMatch(e -> e.getClazz().getName().equals(entityClassName)))
                    continue;
                String entityName = entityClassName.replace(packagePrefix+".", "");
                builder.append(String.format("%s(%s, %s.class, %d, \"%s\"),\n", entityName, service.name(), entityName, count++, entityName));
            }
            if (entityConstList.size() + entities.size() > 0)
                builder.append("// ------------------------------------------------------------------\n");
        }
        System.out.println(builder.toString());
    }
}
