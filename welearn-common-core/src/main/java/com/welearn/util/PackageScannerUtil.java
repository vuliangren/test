package com.welearn.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description : 扫描包路径下的类
 * Created by Setsuna Jin on 2018/4/14.
 */
public class PackageScannerUtil {

    private static final String CLASSPATH_ALL_URL_PREFIX = "classpath*:";
    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
    public static Set<MetadataReader> doScan(String basePackage) throws IOException {
        Set<MetadataReader> candidates = new LinkedHashSet<>();
        String packageSearchPath = CLASSPATH_ALL_URL_PREFIX +
                basePackage.replace('.', '/') + "/" + DEFAULT_RESOURCE_PATTERN;
        PathMatchingResourcePatternResolver pmr = new PathMatchingResourcePatternResolver();
        Resource[] rs = pmr.getResources(packageSearchPath);
        CachingMetadataReaderFactory ss= new CachingMetadataReaderFactory();
        for(Resource r : rs){
            candidates.add(ss.getMetadataReader(r));
        }
        return candidates;
    }

    @Data
    @AllArgsConstructor
    public static class RequestProcessorItem<T> {
        private T processor;
        private Type[] types;
    }

    private <T> Map<String, RequestProcessorItem<T>> pathRequestProcessorScanner(Set<MetadataReader> controllerSet, Class<T> requestProcessorType){
        Map<String, RequestProcessorItem<T>> pathProcessorItemMap = new ConcurrentHashMap<>();
        Map<String, T> beanProcessorMap = new HashMap<>(); // SpringUtil.getBean...
        for (MetadataReader metadataReader : controllerSet) {
            String pathPrefix = "";
            // 解析该包下的类上标注的注解, 如果使用了 @Service 或 使用了 @Component 注解,
            // 且该注解的 value 是以 / 开头, 则使用该 value 作为 其内部 @Bean 注解标注的路由的前缀
            Class<? extends ClassMetadata> controllerMetadata = metadataReader.getClassMetadata().getClass();
            if (controllerMetadata.isAnnotationPresent(Service.class)) {
                Service annotation = controllerMetadata.getAnnotation(Service.class);
                if (annotation.value().startsWith("/")) {
                    pathPrefix = annotation.value();
                }
            } else if (controllerMetadata.isAnnotationPresent(Component.class)) {
                Component annotation = controllerMetadata.getAnnotation(Component.class);
                if (annotation.value().startsWith("/")) {
                    pathPrefix = annotation.value();
                }
            }
            Method[] declaredMethods = controllerMetadata.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                Type genericReturnType = declaredMethod.getGenericReturnType();
                // 只有返回值是指定类型的方法 才会继续后续流程
                if (!genericReturnType.getTypeName().equals(requestProcessorType.getName())) {
                    break;
                }
                // 解析返回值上的 泛型参数, 不同的请求处理类型可能不一样, 这里解析后存储起来
                // 如 JsonRequestProcessor 的第一个和第二个泛型 分别表示 请求体 类型 和 响应体 类型
                Type[] types = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                // 要求该方法上必须被 @Bean 注解标注, 且标注的名称是以 / 开头
                if (declaredMethod.isAnnotationPresent(Bean.class)) {
                    Bean annotation = declaredMethod.getAnnotation(Bean.class);
                    for (String value : annotation.value()) {
                        T requestProcess = beanProcessorMap.get(value);
                        if (Objects.nonNull(requestProcess) && value.startsWith("/")) {
                            pathProcessorItemMap.put(pathPrefix + value, new RequestProcessorItem<>(requestProcess, types));
                        }
                    }
                    for (String value : annotation.name()) {
                        T requestProcess = beanProcessorMap.get(value);
                        if (Objects.nonNull(requestProcess) && value.startsWith("/")) {
                            pathProcessorItemMap.put(pathPrefix + value, new RequestProcessorItem<>(requestProcess, types));
                        }
                    }
                }
            }
        }
        return pathProcessorItemMap;
    }

}