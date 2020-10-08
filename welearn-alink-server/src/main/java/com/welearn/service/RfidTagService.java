package com.welearn.service;

import com.welearn.entity.po.alink.ReRfidTagDevice;
import com.welearn.entity.po.alink.RfidTag;
import com.welearn.entity.qo.alink.RfidTagQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : RfidTagService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RfidTagService extends BaseService<RfidTag, RfidTagQueryCondition>{
    /**
     * 删除旧的关联, 并创建新关联
     * @param tagId 标签id
     * @param iotIds 设备iotIds列表
     * @return 关联关系
     */
    List<ReRfidTagDevice> deleteOldAndCreateNew(@NotBlank String tagId, @NotNull List<String> iotIds);
}