package com.welearn.generator;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.common.User;
import com.welearn.util.PackageScannerUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Description : Controller 生成器
 * 根据 Service 接口生成 Controller 类，自动封装 RequestBody 参数生成 DTO 类
 * 使用前请确保 JDK 为 1.8 以上，编译时带参数 -parameters，IDEA 在 Java Complier > Additional command line parameters 下配置
 * Created by Setsuna Jin on 2018/4/8.
 */
public class ControllerGenerator {

    public static final String PATH = "C:\\Users\\hello\\Documents\\GitAliyun\\ryme-server\\";

//    /**
//     * 使用示例如下
//     * @param args
//     */
//    public static void main(String[] args) {
        //ControllerGenerator.generate(PermissionService.class, ServiceConst.COMMON_SERVER);
//    }

    public static void generateAllService(ServiceConst serviceConst) throws IOException, ClassNotFoundException {
        Set<MetadataReader> services = PackageScannerUtil.doScan("com.welearn.service");
        for (MetadataReader service : services) {
            String serviceInterfaceName = service.getClassMetadata().getClassName();
            if (serviceInterfaceName.contains("BaseService") || serviceInterfaceName.indexOf("Impl") == serviceInterfaceName.length() - 4)
                continue;
            System.out.println(serviceInterfaceName);
            ControllerGenerator.generate(Class.forName(service.getClassMetadata().getClassName()), serviceConst);
        }
    }

    public static void generate(Class serviceClass, ServiceConst serviceConst) {
        List<String> dtoClasses = new ArrayList<>();
        String dtoClassTemplate = "package com.welearn.entity.vo.request." + serviceConst.getServiceName()+";\n" +
                "\n" +
                "_IMPORTS_\n" +
                "import lombok.Data;\n" +
                "import lombok.EqualsAndHashCode;\n" +
                "import lombok.AllArgsConstructor;\n" +
                "import lombok.NoArgsConstructor;\n" +
                "\n" +
                "import io.swagger.annotations.Api;\n" +
                "\n" +
                "import javax.validation.constraints.NotNull;\n" +
                "\n" +
                "/**\n" +
                " * Description :\n" +
                " * Created by Setsuna Jin on 2018/4/10.\n" +
                " */\n" +
                "@Data\n" +
                "@EqualsAndHashCode\n" +
                "@NoArgsConstructor\n" +
                "@AllArgsConstructor\n" +
                "public class _ARG_NAME_UP_ {\n" +
                "_FIELDS_" +
                "}";
        String dtoClassFiledTemplate = "    @NotNull private _TYPE_ _NAME_;\n";
        String persistantName = serviceClass.getSimpleName().replaceFirst("Service","");
        String persistantNameLowerCase = persistantName.substring(0,1).toLowerCase() + persistantName.substring(1);

        boolean isUseBaseController = false;
        for (Class<?> c : serviceClass.getInterfaces()) {
            if (c.toString().contains("com.welearn.service.BaseService")){
                isUseBaseController = true;
            }
        }

        String controllerClassTemplate =
                "package com.welearn.controller;\n\n" +
                "import com.welearn.annotation.RequestUser;\n" +
                "import lombok.extern.slf4j.Slf4j;\n" +
                "import io.swagger.annotations.Api;\n" +
                "import org.springframework.validation.annotation.Validated;\n" +
                "import org.springframework.web.bind.annotation.RequestBody;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n\n" +
                "_IMPORTS_\n" +
                "/**\n" +
                " * Description :\n" +
                " * Created by Setsuna Jin on 2018/1/3.\n" +
                " */\n" +
                "@Slf4j\n" +
                "@RestController\n" +
                "@Validated\n" +
                "@RequestMapping(value = \"/_PO_LC_NAME_\")\n" +
                "@Api(value = \"_PO_LC_NAME_ 接口\")\n" +
                "public class _PONAME_Controller extends BaseController<_PONAME_, _PONAME_QueryCondition, _PONAME_Service>{\n" +
                "_METHOD_" +
                "}";
        if (!isUseBaseController)
            controllerClassTemplate = controllerClassTemplate.replace("extends BaseController<_PONAME_, _PONAME_QueryCondition, _PONAME_Service>", "");
        controllerClassTemplate = controllerClassTemplate.replaceAll("_PONAME_", persistantName)
            .replaceAll("_PO_LC_NAME_", persistantNameLowerCase);

        String userArg = "@RequestUser User user";
        String dtoArg = "@RequestBody _ARG_NAME_UP_ _ARG_NAME_";
        String resultDeclare = "_RESULT_TYPE_ result = ";
        String methodTemplate =
                "    @RequestMapping(value = \"/_METHOD_NAME_\")\n" +
                "    @ApiOperation(value = \"_METHOD_NAME_\", httpMethod = \"POST\")\n" +
                "    public _RETURN_TYPE_ _METHOD_NAME_(_DTO_ARG__USER_ARG_)_THROWS_ {\n" +
                "        _RESULT_DECLARE_service._METHOD_NAME_(_METHOD_ARGS_);\n" +
                "        return _RETURN_VALUE_;\n" +
                "    }\n\n";
        String methodCodeStr = "";
        Set<String> importDTOStr = new TreeSet<>();
        importDTOStr.add("import com.welearn.entity.po." + serviceConst.getServiceName() + "." + persistantName + ";\n");
        importDTOStr.add("import com.welearn.entity.qo." + serviceConst.getServiceName() + "." + persistantName + "QueryCondition;\n");
        importDTOStr.add("import com.welearn.service." + persistantName + "Service;\n");
        importDTOStr.add("import com.welearn.entity.vo.response.CommonResponse;\n");



        for (Method method : isUseBaseController ? serviceClass.getDeclaredMethods() : serviceClass.getMethods()) {
            Class[] paramTypes = method.getParameterTypes();
            String methodName = method.getName();
            String methodNameUpCase = toUpCaseStr(methodName);
            String argName = methodName;
            String argNameUpCase = methodNameUpCase;

            boolean isParamHasUser = false;
            List<Class> dtoParams = new ArrayList<>();
            for (Class clazz : paramTypes) {
                if (clazz.equals(User.class))
                    isParamHasUser = true;
                else
                    dtoParams.add(clazz);
            }
            String code = methodTemplate.replaceFirst("_USER_ARG_", (dtoParams.size() == 0 || !isParamHasUser ? "":", ") + (isParamHasUser ? userArg : ""))
                    .replaceFirst("_DTO_ARG_", dtoParams.size() == 0 ? "" : dtoArg);
            String returnType = method.getReturnType().getSimpleName();
            boolean haveReturnValue = !returnType.equals("void");

            Type genericReturnType = method.getGenericReturnType();
            if (genericReturnType instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType)genericReturnType).getActualTypeArguments();
                String returnTypeGenericStr = "";
                for (Type type : actualTypeArguments) {
                    importDTOStr.add("import " + type.getTypeName() + ";\n");
                    returnTypeGenericStr += ", " + type.getTypeName().substring(type.getTypeName().lastIndexOf(".")+1);
                }
                if (actualTypeArguments.length > 0){
                    returnType += "<" + returnTypeGenericStr.replaceFirst(", ", "") + ">";
                }
            }

            code = code.replaceAll("_RESULT_DECLARE_", haveReturnValue ? resultDeclare : "")
                .replaceAll("_RETURN_TYPE_", haveReturnValue ? "CommonResponse<" + returnType + ">" : "CommonResponse")
                .replaceAll("_RESULT_TYPE_", returnType)
                .replaceAll("_RETURN_VALUE_", haveReturnValue ? "new CommonResponse<>(result)" : "CommonResponse.getSuccessResponse()");

            String methodArgs = "";
            boolean needGenerateDto = dtoParams.size() > 1;
            if (!needGenerateDto && dtoParams.size() > 0){
                argNameUpCase = dtoParams.get(0).getSimpleName();
                argName = toLowCaseStr(argNameUpCase);
            }
            code = code.replaceFirst("_ARG_NAME_UP_", argNameUpCase).replaceFirst("_ARG_NAME_", argName);

            code = code.replaceAll("_METHOD_NAME_", methodName)
                    .replaceAll("_METHOD_NAME_UP_", methodNameUpCase);

            if (needGenerateDto)
                importDTOStr.add("import com.welearn.entity.vo.request." + serviceConst.getServiceName() + "." + argNameUpCase + ";\n");
            if (haveReturnValue)
                importDTOStr.add("import " + method.getReturnType().getName() + ";\n");

            String dtoClass = "";
            Set<String> dtoImport = new TreeSet<>();
            String dtoFieldstr = "";
            if (needGenerateDto)
                dtoClass = dtoClassTemplate.replace("_ARG_NAME_UP_", argNameUpCase);

            Parameter[] params = method.getParameters();
            Type[] genericTypes = method.getGenericParameterTypes();
            for (int i=0;i<paramTypes.length;i++) {
                Class c = paramTypes[i];
                importDTOStr.add("import " + c.getName() + ";\n");

                Parameter p = params[i];
                if (c.getSimpleName().equals("User"))
                    methodArgs += ", user";
                else if (!needGenerateDto){
                    methodArgs += ", " + argName;
                }
                else {
                    Type genericType = genericTypes[i];
                    String fieldType = c.getSimpleName();
                    if (genericType instanceof ParameterizedType){
                        Type[] actualTypeArguments = ((ParameterizedType)genericType).getActualTypeArguments();
                        String returnTypeGenericStr = "";
                        for (Type type : actualTypeArguments) {
                            dtoImport.add("import " + type.getTypeName() + ";\n");
                            returnTypeGenericStr += ", " + type.getTypeName().substring(type.getTypeName().lastIndexOf(".")+1);
                        }
                        if (actualTypeArguments.length > 0){
                            fieldType += "<" + returnTypeGenericStr.replaceFirst(", ", "") + ">";
                        }
                    }
                    dtoFieldstr += dtoClassFiledTemplate.replace("_TYPE_", fieldType).replace("_NAME_", p.getName());
                    dtoImport.add("import " + c.getName() + ";\n");
                    methodArgs += ", " + argName + ".get" + toUpCaseStr(p.getName()) + "()";
                }
            }
            String dtoImportsStr = "";
            for (String s:dtoImport) {
                dtoImportsStr += s;
            }
            if (needGenerateDto){
                dtoClass = dtoClass.replace("_FIELDS_",dtoFieldstr).replace("_IMPORTS_", dtoImportsStr);
                dtoClasses.add(dtoClass);
                writeToFile(dtoClass, PATH + "welearn-common-core\\src\\main\\java\\com\\welearn\\entity\\vo\\request\\" + serviceConst.getServiceName() + "\\" + argNameUpCase + ".java");
            }
            code = code.replaceFirst("_METHOD_ARGS_", methodArgs.replaceFirst(", ", ""));

            Class[] throwsEx = method.getExceptionTypes();
            String throwExStr = " ";
            if (throwsEx.length > 0)
                throwExStr += "throws ";
            for (Class ex: throwsEx) {
                throwExStr += ", " + ex.getSimpleName();
                importDTOStr.add("import " + ex.getName() + ";\n");
            }
            code = code.replaceFirst("_THROWS_", throwExStr.replaceFirst(", ", ""));
            methodCodeStr += code;
        }

        String importStr = "";
        for (String im:importDTOStr) {
            importStr += im;
        }
        controllerClassTemplate = controllerClassTemplate.replaceAll("_METHOD_", methodCodeStr)
            .replace("_IMPORTS_", importStr);
        writeToFile(controllerClassTemplate, PATH + "welearn-" + serviceConst.getServiceName() + "-server\\src\\main\\java\\com\\welearn\\controller\\" + persistantName + "Controller.java");
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    public static String toUpCaseStr(String name){
        String firstChar = name.substring(0,1);
        return name.replaceFirst(firstChar, firstChar.toUpperCase());
    }

    public static String toLowCaseStr(String name){
        String firstChar = name.substring(0,1);
        return name.replaceFirst(firstChar, firstChar.toLowerCase());
    }

    public static void writeToFile(String content, String path){
        File file = new File(path);
        if (file.exists()){
            System.out.println("FILE IS EXIST PATH:" + path);
            System.out.println("@@@\n" + content + "\n@@@\n");
            return;
        }
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                writer.close();
                fw.close();
                System.out.println("*** " + path);
                System.out.println("+++\n" + content + "\n+++\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
