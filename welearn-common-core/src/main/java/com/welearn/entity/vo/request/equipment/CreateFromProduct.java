package com.welearn.entity.vo.request.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CreateFromProduct {
    @NotBlank
    private String productId;
    @NotNull
    private Integer initCount;

    private String procurementId;
    private String detailId;
    private String departmentId;

    @NotNull
    private Date guaranteeRepairExpiredAt;
}