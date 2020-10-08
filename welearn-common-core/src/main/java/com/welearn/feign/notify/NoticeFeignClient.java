package com.welearn.feign.notify;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.notify.Notice;
import com.welearn.entity.qo.notify.NoticeQueryCondition;
import com.welearn.entity.vo.request.notify.View;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.notify.NoticeInfo;
import java.util.List;

/**
 * Description : welearn-notify-service / com.welearn.controller.NoticeController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-notify-server", configuration = FeignConfiguration.class)
public interface NoticeFeignClient {

    @RequestMapping(value = "/notice/sendNotice")
    CommonResponse sendNotice(Notice notice);

    @RequestMapping(value = "/notice/view")
    CommonResponse view(View view);

    @RequestMapping(value = "/notice/searchInfo")
    CommonResponse<List<NoticeInfo>> searchInfo(NoticeQueryCondition noticeQueryCondition);

    @RequestMapping(value = "/notice/autoSendNotice")
    CommonResponse autoSendNotice();

    @RequestMapping(value = "/notice/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/notice/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/notice/update")
    CommonResponse update(Notice entity);

    @RequestMapping(value = "/notice/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/notice/create")
    CommonResponse<Notice> create(Notice entity);

    @RequestMapping(value = "/notice/search")
    CommonResponse<List<Notice>> search(NoticeQueryCondition queryCondition);

    @RequestMapping(value = "/notice/select")
    CommonResponse<Notice> select(String id);
}