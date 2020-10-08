package com.welearn.entity.vo.request.equipment;

import com.welearn.entity.po.procurement.Procurement;
import com.welearn.entity.po.procurement.ProcurementDetail;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import io.swagger.annotations.Api;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SparePartProcurementFinish {
    @NotNull private Procurement procurement;
    @NotNull private List<ProcurementDetail> details;
}