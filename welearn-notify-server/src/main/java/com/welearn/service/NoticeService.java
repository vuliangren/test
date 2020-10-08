package com.welearn.service;

import com.welearn.entity.po.notify.Notice;
import com.welearn.entity.qo.notify.NoticeQueryCondition;
import com.welearn.entity.vo.response.notify.NoticeInfo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : NoticeService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface NoticeService extends BaseService<Notice, NoticeQueryCondition>{

    /**
     * 发送通知提醒
     * @param notice 通知提醒
     */
    void sendNotice(@NotNull @Valid Notice notice);

    /**
     * 查询需要定时发送的通知提醒, 进行发送
     */
    void autoSendNotice();

    /**
     * 浏览通知
     * @param noticeId 通知id
     * @param userId 用户id
     */
    void view(String noticeId, String userId);

    /**
     * 查询通知详情
     */
    List<NoticeInfo> searchInfo(NoticeQueryCondition condition);
}