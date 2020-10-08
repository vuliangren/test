package com.welearn.feign.common;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.request.common.Available;
import com.welearn.entity.vo.request.common.UserSearch;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.common.Position;
import com.welearn.entity.qo.common.PositionQueryCondition;
import com.welearn.entity.vo.request.common.Allot;
import com.welearn.entity.vo.request.common.TakeBack;
import com.welearn.entity.vo.response.CommonResponse;
import java.lang.String;
import java.util.List;

/**
 * Description : welearn-common-service / com.welearn.controller.PositionController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-common-server", configuration = FeignConfiguration.class)
public interface PositionFeignClient {

    @RequestMapping(value = "/position/available")
    CommonResponse<List<Position>> available(Available available);

    @RequestMapping(value = "/position/userPositions")
    CommonResponse<List<Position>> userPositions(String string);

    @RequestMapping(value = "/position/userSearch")
    CommonResponse<List<User>> userSearch(UserSearch userSearch);

    @RequestMapping(value = "/position/takeBack")
    CommonResponse takeBack(TakeBack takeBack);

    @RequestMapping(value = "/position/allot")
    CommonResponse allot(Allot allot);

    @RequestMapping(value = "/position/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/position/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/position/update")
    CommonResponse update(Position entity);

    @RequestMapping(value = "/position/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/position/create")
    CommonResponse create(Position entity);

    @RequestMapping(value = "/position/search")
    CommonResponse<List<Position>> search(PositionQueryCondition queryCondition);

    @RequestMapping(value = "/position/select")
    CommonResponse<Position> select(String id);
}