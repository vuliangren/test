package com.welearn.mapper;

import com.welearn.entity.po.notify.Feedback;
import com.welearn.entity.qo.notify.FeedbackQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * Feedback Mapper Interface : ryme_notify : feedback
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/21 20:16:20
 * @see Feedback
 */
@Mapper 
public interface FeedbackMapper extends BaseMapper<Feedback, FeedbackQueryCondition> {
    
}