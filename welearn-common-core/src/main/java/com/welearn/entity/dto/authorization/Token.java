package com.welearn.entity.dto.authorization;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.dictionary.authorization.TokenTypeConst;
import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.dictionary.common.UserTypeConst;
import com.welearn.entity.po.common.User;
import com.welearn.util.depreciation.StraightLineDepreciationCalculator;
import com.welearn.util.depreciation.UnitWorkloadDepreciationCalculator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/19.
 */
@Data
@EqualsAndHashCode
public class Token implements Serializable{
    private User user;
    private ServiceConst issuer;
    private ServiceConst audience;
    private Date expiredAt;
    private UserTypeConst userTypeConst;
    private ClientTypeConst clientTypeConst;
    private TokenTypeConst tokenType;
}
