package com.welearn.entity.vo.request.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Description :
 * Created by Setsuna Jin on 2018/11/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    @NotBlank private String indexId;
    @NotBlank private String firstId;
    @NotBlank private String secondId;
}
