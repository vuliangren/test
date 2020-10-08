package com.welearn.entity.vo.request.equipment;

import com.welearn.entity.po.apply.Contract;
import java.lang.String;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import io.swagger.annotations.Api;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SignContract {
    @NotNull private String dispatchOutsideId;
    @NotNull private Contract contract;
}