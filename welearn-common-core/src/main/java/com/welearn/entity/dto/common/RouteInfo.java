package com.welearn.entity.dto.common;

import com.welearn.entity.po.common.ReRouteHelp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/2.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RouteInfo implements Serializable {

    // 路由权限
    private List<String> authority;
    // 页面帮助
    private ReRouteHelp pageHelp;
}
