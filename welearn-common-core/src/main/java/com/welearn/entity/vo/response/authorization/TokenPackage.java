package com.welearn.entity.vo.response.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/19.
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TokenPackage implements Serializable{
    @NotBlank private String accessToken;
    private String refreshToken;
}
