package com.welearn.util.depreciation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Description : 工作量法折旧计算器
 * Created by Setsuna Jin on 2018/7/13.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UnitWorkloadDepreciationCalculator extends AbstractDepreciationCalculator<UnitWorkloadDepreciationCalculator> {
    // 预计总工作量
    @NotNull @Min(0)
    private BigDecimal sumWorkloadExpectancy;

    // 单位工作量折旧额
    private BigDecimal unitWorkloadDepreciation;

    // 获取单位工作量折旧额格式化后
    public BigDecimal getUnitWorkloadDepreciationFormat() {
        return setMoneyScale(unitWorkloadDepreciation);
    }

    // 获取工作量对应的折旧额
    public BigDecimal getWorkloadDepreciation(@NotNull Integer workload){
        return unitWorkloadDepreciation.multiply(new BigDecimal("" + workload));
    }

    // 获取工作量对应的折旧额格式化后
    public BigDecimal getWorkloadDepreciationFormat(@NotNull Integer workload){
        return setMoneyScale(getWorkloadDepreciation(workload));
    }

    /**
     * 工作量法折旧
     * @param originalValue 金额 字符串 如 "100.00"
     * @param salvageRate 预计净残值率 百分比 字符串
     * @param sumWorkloadExpectancy 预计总工作量
     */
    public UnitWorkloadDepreciationCalculator(@NotNull String originalValue, @NotNull String salvageRate, @NotNull Integer sumWorkloadExpectancy) {
        super(new BigDecimal(originalValue), new BigDecimal(salvageRate));
        this.sumWorkloadExpectancy = new BigDecimal("" + sumWorkloadExpectancy);
    }

    /**
     * 工作量法折旧
     * @param originalValue 金额 如 100.00
     * @param salvageRate 预计净残值率 百分比
     * @param sumWorkloadExpectancy 预计总工作量
     */
    public UnitWorkloadDepreciationCalculator(@NotNull Double originalValue, @NotNull Double salvageRate, @NotNull Integer sumWorkloadExpectancy) {
        super(new BigDecimal(originalValue), new BigDecimal(salvageRate));
        this.sumWorkloadExpectancy = new BigDecimal("" + sumWorkloadExpectancy);
    }

    /**
     * 计算折旧相关参数
     */
    @Override
    public UnitWorkloadDepreciationCalculator calculate() {
        this.setUnitWorkloadDepreciation(this.getOriginalValue()
                .divide(ONE.subtract(this.getSalvageRate()), 8, RoundingMode.HALF_UP)
                .divide(this.getSumWorkloadExpectancy(), 8, RoundingMode.HALF_UP));
        return this;
    }
}
