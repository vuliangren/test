package com.welearn.generator;

import com.welearn.dictionary.api.ServiceConst;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/15.
 */
public class FeignClientGenerator {

    public static void generate(Class controllerClass, ServiceConst serviceConst){
        String feignClientTemplate = "package com.welearn.feign."+ serviceConst.getServiceName() +";\n" +
                "\n" +
                "import com.welearn.config.FeignConfiguration;\n" +
                "import org.springframework.cloud.netflix.feign.FeignClient;\n" +
                "import org.springframework.stereotype.Component;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "\n" +
                "{imports}" +
                "\n" +
                "/**\n" +
                " * Description : welearn-" + serviceConst.getServiceName() + "-service / " + controllerClass.getName() + "\n" +
                " * Feign Client Created by FeignClientGenerator\n" +
                " */\n" +
                "@Component\n" +
                "@FeignClient(value = \"welearn-" + serviceConst.getServiceName() + "-server\", configuration = FeignConfiguration.class)\n" +
                "public interface {PersistantName}FeignClient {\n" +
                "{methods}" +
                "{baseControllerMethods}\n" +
                "}";
        String methodTemplate = "\n" +
                "    @RequestMapping(value = \"/{persistantName}{requestMappingValue}\")\n" +
                "    {returnType} {methodName}({arguments});\n";
        String baseControllerMethodsTemplate = "\n" +
                "    @RequestMapping(value = \"/{persistantName}/disable\")\n" +
                "    CommonResponse disable(String id);\n" +
                "\n" +
                "    @RequestMapping(value = \"/{persistantName}/enable\")\n" +
                "    CommonResponse enable(String id);\n" +
                "\n" +
                "    @RequestMapping(value = \"/{persistantName}/update\")\n" +
                "    CommonResponse update({PersistantName} entity);\n" +
                "\n" +
                "    @RequestMapping(value = \"/{persistantName}/delete\")\n" +
                "    CommonResponse delete(String id);\n" +
                "\n" +
                "    @RequestMapping(value = \"/{persistantName}/create\")\n" +
                "    CommonResponse<{PersistantName}> create({PersistantName} entity);\n" +
                "\n" +
                "    @RequestMapping(value = \"/{persistantName}/search\")\n" +
                "    CommonResponse<List<{PersistantName}>> search({PersistantName}QueryCondition queryCondition);\n" +
                "\n" +
                "    @RequestMapping(value = \"/{persistantName}/select\")\n" +
                "    CommonResponse<{PersistantName}> select(String id);";
        String PersistantName = controllerClass.getSimpleName().replace("Controller","");
        String persistantName = ControllerGenerator.toLowCaseStr(PersistantName);
        Set<String> imports = new TreeSet<>();

        String methodsCode = "";
        Method[] methods = controllerClass.getDeclaredMethods();
        for (Method method : methods) {
            if (!method.isAnnotationPresent(RequestMapping.class))
                continue;
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            String requestMappingValue = requestMapping.value()[0];

            // 生成带泛型的参数列表
            String arguments = "";
            Parameter[] parameters = method.getParameters();
            Type[] genericTypes = method.getGenericParameterTypes();
            for (int i = 0; i < parameters.length; i++) {
                if (!parameters[i].isAnnotationPresent(RequestBody.class))
                    continue;
                arguments += ", " + getGenericTypeFullName(genericTypes[i].getTypeName(), imports) + " " + parameters[i].getName();
            }

            // 生成带泛型的返回值类型
            Type genericReturnType = method.getGenericReturnType();
            String returnType = getGenericTypeFullName(genericReturnType.getTypeName(), imports);

            imports.add("import " + "com.welearn.entity.po." + serviceConst.getServiceName() + "." + PersistantName + ";\n");
            imports.add("import " + "com.welearn.entity.qo." + serviceConst.getServiceName() + "." + PersistantName + "QueryCondition;\n");

            methodsCode += methodTemplate
                    .replace("{persistantName}", persistantName)
                    .replace("{requestMappingValue}", requestMappingValue)
                    .replace("{returnType}", returnType)
                    .replace("{methodName}", method.getName())
                    .replace("{arguments}", arguments.replaceFirst(", ", ""));
        }

        String importsCode = "";
        for (String importCode : imports) {
            importsCode += importCode;
        }

        String feignClientCode = feignClientTemplate.replace("{imports}", importsCode)
                .replace("{baseControllerMethods}", baseControllerMethodsTemplate)
                .replace("{PersistantName}", PersistantName)
                .replace("{persistantName}", persistantName)
                .replace("{methods}", methodsCode);

        ControllerGenerator.writeToFile(feignClientCode, ControllerGenerator.PATH + "welearn-common-core\\src\\main\\java\\com\\welearn\\feign\\" + serviceConst.getServiceName() + "\\" + PersistantName + "FeignClient.java");
    }

    public static String getGenericTypeFullName(String fullGenericType, Set<String> imports){
        String spliteStr = fullGenericType.replace(",","|").replace("<", "|").replace(">", "|").replace(" ", "");
        String[] classes = spliteStr.split("\\|");
        for (String clazz : classes) {
            if ("".equals(clazz))
                continue;
            imports.add("import " + clazz + ";\n");
            fullGenericType = fullGenericType.replace(clazz, clazz.substring(clazz.lastIndexOf(".")+1));
        }
        return fullGenericType;
    }
}
