package com.welearn.validate.validator.common;

import com.welearn.entity.po.BasePersistant;
import com.welearn.validate.annotation.common.EntityCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

/**
 * 校验方法上的实体List类型参数
 * 验证参数非null, List非空, 元素非null, 元素id非null
 */
public class EntityListCheckValidator implements ConstraintValidator<EntityCheck, List<? extends BasePersistant>> {

    private EntityCheck entityCheck;

    @Override
    public void initialize(EntityCheck constraintAnnotation) {
        this.entityCheck = constraintAnnotation;
    }

    @Override
    public boolean isValid(List<? extends BasePersistant> value, ConstraintValidatorContext context) {
        String defaultMessage = context.getDefaultConstraintMessageTemplate();
        if (Objects.isNull(value)){
            return false;
        }
        context.disableDefaultConstraintViolation();
        if (value.size() == 0){
            context.buildConstraintViolationWithTemplate("不能为空").addConstraintViolation();
            return false;
        }
        for (int i = 0; i < value.size(); i++) {
            if (Objects.isNull(value.get(i))){
                context.buildConstraintViolationWithTemplate(defaultMessage)
                        .addPropertyNode("["+i+"]").addConstraintViolation();
                return false;
            }
            if (entityCheck.checkId() && Objects.isNull(value.get(i).getId())){
                context.buildConstraintViolationWithTemplate(defaultMessage)
                        .addPropertyNode("["+i+"].id").addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}