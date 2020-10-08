package com.welearn.entity.vo.request.apply;

import com.welearn.entity.po.BasePersistant;
import java.lang.String;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Modify<T extends BasePersistant> {
    @NotNull private String applicationId;
    @NotNull private T content;
    @NotNull private String applicantId;
}