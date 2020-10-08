package com.welearn.service;

import com.welearn.entity.po.common.Tag;
import com.welearn.entity.qo.common.TagQueryCondition;
import com.welearn.entity.vo.response.common.Option;
import com.welearn.entity.vo.response.common.TagInfo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Description : TagService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface TagService extends BaseService<Tag, TagQueryCondition>{

    /**
     * 查询数据拥有的标签
     * @param itemId 数据id
     * @return 标签
     */
    List<TagInfo> itemTags(@NotBlank String itemId);

    /**
     * 查询数据类型匹配的标签
     * @param itemId 数据id
     * @param companyId 标签所属公司id
     * @return 标签
     */
    List<Tag> typeTags(@NotBlank String itemId, String companyId);

    /**
     * 获取标签关联的 数据id 列表
     * @param tagIds 标签id 列表
     * @return 数据 id 列表
     */
    List<String> itemIds(@NotNull List<String> tagIds);
}