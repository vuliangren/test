package com.welearn.dictionary.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/23.
 */
@AllArgsConstructor
public enum  RequestHeaderConst {
    // 定位请求进入路由的时间
    REQUEST_IN_TIME("Request-In-Time"),
    // 负责处理请求的主要服务
    PROCESS_SERVER("Process-Server"),
    // 请求处理过程花费时间
    PROCESS_COST_TIME("Process-Cost-Time"),
    // 请求从到达路由到响应花费时间
    RESPONSE_COST_TIME("Response-Cost-Time"),
    // 用于记录请求 Feign Client 的服务
    REUQEST_FEIGN_APPLICATION("Request-Feign-Application"),
    // 用于 Feign Client 传递认证结果
    AUTH_REUSLT("Auth-Result"),
    // 标明一个请求的类型是 用户请求 还是 系统内部请求
    REQUEST_TYPE("Request-Type"),
    // 存放客户端请求携带的 ACCESS_TOKEN
    VEDA_TOKEN("Veda-AccessToken"),
    // 存放客户端请求携带的 当前用户 所选部门 id
    VEDA_DEPARTMENT_ID("Veda-DepartmentId"),
    // 分页-页码
    PAGINATE_PAGE("Paginate-Page"),
    // 分页-数量
    PAGINATE_SIZE("Paginate-Size"),
    // 分页-总数
    PAGINATE_TOTAL("Paginate-Total");

    @Getter
    private String headerName;
}
