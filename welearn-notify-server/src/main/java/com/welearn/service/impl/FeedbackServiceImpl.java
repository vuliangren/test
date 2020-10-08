package com.welearn.service.impl;

import com.welearn.entity.po.notify.Feedback;
import com.welearn.entity.qo.notify.FeedbackQueryCondition;
import com.welearn.mapper.FeedbackMapper;
import com.welearn.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : FeedbackService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback,FeedbackQueryCondition,FeedbackMapper>
        implements FeedbackService{
    
    @Autowired
    private FeedbackMapper feedbackMapper;
    
    @Override
    FeedbackMapper getMapper() {
        return feedbackMapper;
    }

}
