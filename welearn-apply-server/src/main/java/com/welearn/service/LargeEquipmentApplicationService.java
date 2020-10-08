package com.welearn.service;

import com.welearn.entity.po.apply.LargeEquipmentApplication;
import com.welearn.entity.qo.apply.LargeEquipmentApplicationQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : LargeEquipmentApplicationService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface LargeEquipmentApplicationService extends BaseService<LargeEquipmentApplication, LargeEquipmentApplicationQueryCondition>{

}