package com.welearn.entity.vo.request.alink;

import java.lang.String;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class GetConfiguration {
    @NotBlank
    private String productKey;
    @NotBlank
    private String deviceName;
}