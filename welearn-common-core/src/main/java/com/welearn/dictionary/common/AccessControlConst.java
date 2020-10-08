package com.welearn.dictionary.common;

/**
 * 跨域请求相关内容配置
 * 参考: https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Access_control_CORS
 */
public enum AccessControlConst {
    // 指定了允许访问该资源的外域 URI
    AccessControlAllowOrigin("Access-Control-Allow-Origin", "*"),

    // 指定了允许浏览器访问的响应头
    AccessControlExposeHeaders("Access-Control-Expose-Headers", "Process-Server, Process-Cost-Time"),

    // 指定了 preflight 请求的结果在多少秒内有效
    AccessControlMaxAge("Access-Control-Max-Age", "86400"),

    // 指定了当浏览器的credentials设置为true时是否允许浏览器读取response的内容
    AccessControlAllowCredentials("Access-Control-Allow-Credentials", "true"),

    // 指明了实际请求所允许使用的 HTTP 方法
    AccessControlAllowMethods("Access-Control-Allow-Methods", "GET, HEAD, PUT, POST, DELETE, OPTIONS, PATCH"),

    // 指明了实际请求中允许携带的首部字段
    AccessControlAllowHeaders("Access-Control-Allow-Headers", "content-type,paginate-Size,Paginate-Total,Paginate-Page,Veda-AccessToken,Veda-DepartmentId");

    private String headerName;
    private String headerValue;

    AccessControlConst(String headerName, String headerValue){
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    public String getHeaderName(){
        return this.headerName;
    }

    public String getHeaderValue(){
        return this.headerValue;
    }
}
