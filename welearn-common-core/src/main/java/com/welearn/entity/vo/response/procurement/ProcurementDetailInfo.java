package com.welearn.entity.vo.response.procurement;

import com.welearn.entity.po.procurement.ProcurementDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 * Created by Setsuna Jin on 2018/11/27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcurementDetailInfo extends ProcurementDetail {
    private String productName;
    private String productModel;
    private String manufacturerName;
    private Integer managementLever;
    private Boolean isImportProduct;
    private Integer isLargeEquipment;
    private String productApplicationId;
}
