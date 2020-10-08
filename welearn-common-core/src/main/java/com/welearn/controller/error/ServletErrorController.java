package com.welearn.controller.error;

import com.welearn.dictionary.error.ErrorMessageConst;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class ServletErrorController extends AbstractErrorController {
    private static final String ErrorPath = "/error";

    @Value("${debug}")
    private Boolean isDebug;

    @Autowired
    private ErrorAttributes errorAttributes;

    public ServletErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping(path = ErrorPath)
    public CommonResponse error(HttpServletRequest request, HttpServletResponse response){
        Signature signature = new Signature(ErrorMessageConst.SYSTEM_ERROR);
        // 设置响应码
        response.setStatus(getStatus(request).value());
        // 设置错误详情
        Map<String, Object> detail = getErrorAttributes(request, isDebug);
        signature.setErrorMessage(detail.get("error").toString());
        signature.setErrorDetail(detail);
        return signature.toErrorResponse();
    }

    @Override
    public String getErrorPath() {
        return ErrorPath;
    }
}
