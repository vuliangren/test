package com.welearn.entity.vo.request.procurement;

import com.welearn.entity.po.apply.Contract;
import com.welearn.entity.po.procurement.ProcurementDetail;
import java.lang.String;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
public class SigningContract {
    @NotNull private List<ProcurementDetail> details;
    @NotNull private Contract contract;
    @NotBlank private String procurementId;
    @NotBlank private String salespersonIDCard;
    @NotBlank private String salespersonName;
    @NotBlank private String salespersonIDCardPhotocopy;
}