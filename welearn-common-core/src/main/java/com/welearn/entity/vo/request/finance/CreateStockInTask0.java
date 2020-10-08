package com.welearn.entity.vo.request.finance;

import com.welearn.entity.po.finance.StockPackage;
import com.welearn.entity.po.finance.StockType;
import java.lang.String;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
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
public class CreateStockInTask0 {
    @NotNull private StockType stockType;
    @NotNull private String taskDescription;
    @NotNull private String taskRemark;
    @NotNull private List<StockPackage> stockPackages;
    @NotNull private String userId;
}