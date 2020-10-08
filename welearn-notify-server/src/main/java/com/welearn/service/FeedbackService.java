package com.welearn.service;

import com.welearn.entity.po.notify.Feedback;
import com.welearn.entity.qo.notify.FeedbackQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : FeedbackService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface FeedbackService extends BaseService<Feedback, FeedbackQueryCondition>{

}