package com.welearn.service;

import com.welearn.entity.po.storage.RichText;
import com.welearn.entity.qo.storage.RichTextQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : RichTextService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RichTextService extends BaseService<RichText, RichTextQueryCondition>{

}