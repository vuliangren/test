package com.welearn.mapper;

import com.welearn.entity.po.storage.RichText;
import com.welearn.entity.qo.storage.RichTextQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * RichText Mapper Interface : ryme_storage : rich_text
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/30 9:36:19
 * @see com.welearn.entity.po.storage.RichText
 */
@Mapper 
public interface RichTextMapper extends BaseMapper<RichText, RichTextQueryCondition> {
    
}