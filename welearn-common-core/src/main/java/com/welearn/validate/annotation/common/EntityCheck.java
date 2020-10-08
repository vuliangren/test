package com.welearn.validate.annotation.common;


import com.welearn.validate.validator.common.EntityCheckValidator;
import com.welearn.validate.validator.common.EntityListCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 实体参数检查
 * 可对 BasePersistant 的子类使用
 * @see EntityCheckValidator
 * 可对 List<? extends BasePersistant> 的列表使用
 * @see EntityListCheckValidator
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EntityCheckValidator.class, EntityListCheckValidator.class})
@Documented
public @interface EntityCheck {
    String message() default "不能为null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // 是否检查实体的id字段是否为空
    boolean checkId() default false;
}