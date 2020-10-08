package com.welearn.entity.vo.request.apply;

import com.welearn.entity.po.BasePersistant;
import java.lang.String;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Save<T extends BasePersistant> {
    @NotNull private T content;
    @NotNull private String applicantId;

    @NotNull @Range(min = 0, max = 1)
    private Integer type;

    private String linkId;
}