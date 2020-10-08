package com.welearn.validate.validator.common;

import com.welearn.entity.po.BasePersistant;
import com.welearn.validate.annotation.common.EntityCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * 校验方法上的实体类型参数
 * 验证参数非null, id非null
 */
public class EntityCheckValidator implements ConstraintValidator<EntityCheck, BasePersistant> {

    private EntityCheck entityCheck;

    @Override
    public void initialize(EntityCheck constraintAnnotation) {
        this.entityCheck = constraintAnnotation;
    }

    @Override
    public boolean isValid(BasePersistant value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)){
            return false;
        }
        if (entityCheck.checkId() && Objects.isNull(value.getId())){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("id").addConstraintViolation();
            return false;
        }
        return true;
    }
}