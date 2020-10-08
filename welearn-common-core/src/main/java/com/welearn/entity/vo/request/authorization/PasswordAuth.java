package com.welearn.entity.vo.request.authorization;

import com.welearn.entity.vo.request.common.PasswordLogin;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/21.
 */
@Data
@EqualsAndHashCode
public class PasswordAuth {
    @NotNull private PasswordLogin passwordLogin;
    @NotNull private Integer clientType;
}
