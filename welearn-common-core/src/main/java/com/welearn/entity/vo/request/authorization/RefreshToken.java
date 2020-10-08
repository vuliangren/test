package com.welearn.entity.vo.request.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Description :
 * Created by Setsuna Jin on 2018/6/12.
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @NotBlank private String refreshToken;
}
