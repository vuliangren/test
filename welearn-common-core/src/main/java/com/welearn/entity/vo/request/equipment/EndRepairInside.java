package com.welearn.entity.vo.request.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class EndRepairInside {
    @NotNull private String dispatchId;
    @NotNull private Integer result;
    @NotNull private String reason;
}