package com.welearn.mapper;

import com.welearn.entity.po.notify.Notice;
import com.welearn.entity.qo.notify.NoticeQueryCondition;
import com.welearn.entity.vo.response.notify.NoticeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Notice Mapper Interface : ryme_notify : notice
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/21 20:16:21
 * @see Notice
 */
@Mapper 
public interface NoticeMapper extends BaseMapper<Notice, NoticeQueryCondition> {
    /**
     * 查询通知详情(是否已读等)
     * 此方法仅限接收方用户查询其相关通知信息
     * @param condition 查询条件
     * @return 通知详情
     */
    List<NoticeInfo> selectInfoByCondition(NoticeQueryCondition condition);
}