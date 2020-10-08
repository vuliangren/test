package com.welearn.service.impl;

import com.welearn.entity.po.storage.RichText;
import com.welearn.entity.qo.storage.RichTextQueryCondition;
import com.welearn.mapper.RichTextMapper;
import com.welearn.service.RichTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : RichTextService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RichTextServiceImpl extends BaseServiceImpl<RichText,RichTextQueryCondition,RichTextMapper>
        implements RichTextService{
    
    @Autowired
    private RichTextMapper richTextMapper;
    
    @Override
    RichTextMapper getMapper() {
        return richTextMapper;
    }

}
