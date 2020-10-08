package com.welearn.service.impl;

import com.welearn.dictionary.common.PersistantConst;
import com.welearn.entity.po.common.ReTagItem;
import com.welearn.entity.po.common.Tag;
import com.welearn.entity.qo.common.ReTagItemQueryCondition;
import com.welearn.entity.qo.common.TagQueryCondition;
import com.welearn.entity.vo.response.common.TagInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.ReTagItemMapper;
import com.welearn.mapper.TagMapper;
import com.welearn.service.TagService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Description : TagService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class TagServiceImpl extends BaseServiceImpl<Tag,TagQueryCondition,TagMapper>
        implements TagService{
    
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ReTagItemMapper reTagItemMapper;

    @Override
    TagMapper getMapper() {
        return tagMapper;
    }

    @Override
    public Tag create(Tag tag){
        TagQueryCondition condition = new TagQueryCondition();
        condition.setCompanyId(tag.getCompanyId());
        condition.setName(tag.getName());
        condition.setIsEnable(true);
        List<Tag> search = this.search(condition);
        if (search.isEmpty())
            return super.create(tag);
        else
            throw new BusinessVerifyFailedException("请勿重复添加相同的标签");
    }

    @Override
    public void delete(Tag tag){
        this.delete(tag.getId());
    }

    @Override
    @Transactional
    public void delete(String tagId){
        ReTagItemQueryCondition condition = new ReTagItemQueryCondition();
        condition.setIsEnable(true);
        condition.setTagId(tagId);
        List<ReTagItem> reTagItems = reTagItemMapper.selectByCondition(condition);
        reTagItems.forEach(reTagItem -> reTagItemMapper.deleteByPK(reTagItem.getId()));
        super.delete(tagId);
    }

    /**
     * 查询数据拥有的标签
     *
     * @param itemId 数据id
     * @return 标签
     */
    @Override
    public List<TagInfo> itemTags(String itemId) {
        return tagMapper.itemTags(itemId);
    }

    /**
     * 查询数据类型匹配的标签
     *
     * @param itemId    数据id
     * @param companyId 标签所属公司id
     * @return 标签
     */
    @Override
    public List<Tag> typeTags(String itemId, String companyId) {
        String type = itemId.substring(0, 4);
        if (type.length() < 4)
            throw new BusinessVerifyFailedException("itemId 类型非法");
        TagQueryCondition condition = new TagQueryCondition();
        condition.setIsEnable(true);
        condition.setItemType(type);
        if (StringUtils.isNotBlank(companyId))
            condition.setCompanyId(companyId);
        return this.search(condition);
    }

    /**
     * 获取标签关联的 数据id 列表
     *
     * @param tagIds 标签id 列表
     * @return 数据 id 列表
     */
    @Override
    public List<String> itemIds(List<String> tagIds) {
        return tagMapper.itemIds(tagIds);
    }
}
