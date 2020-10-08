package com.welearn.entity.vo.response.procurement;

import com.welearn.entity.po.procurement.Procurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description :
 * Created by Setsuna Jin on 2018/11/27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcurementInfo extends Procurement {
    private String contractNo;
    private String contractFirstPart;
    private String contractSecondPart;
    private Double contractAmount;
    private Integer contractDeliveryDays;
    private Integer contractAcceptanceDays;
    private Integer contractCreditDays;
    private Date effectiveDate;
    private String contractPhotocopy;
}
