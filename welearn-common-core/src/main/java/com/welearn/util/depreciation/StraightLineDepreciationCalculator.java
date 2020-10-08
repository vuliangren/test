package com.welearn.util.depreciation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Description : 年限平均法折旧计算器
 * Created by Setsuna Jin on 2018/7/13.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StraightLineDepreciationCalculator extends AbstractDepreciationCalculator<StraightLineDepreciationCalculator> {
    // 预计使用寿命 (1-10)
    @NotNull @Min(1) @Max(10)
    private BigDecimal lifeExpectancy;

    // 年折旧率
    private BigDecimal yearlyDepreciationRate;
    // 月折旧率
    private BigDecimal monthlyDepreciationRate;

    // 获取年折旧率格式化后
    public BigDecimal getYearlyDepreciationRateFormat() {
        return setRateScale(yearlyDepreciationRate);
    }

    // 获取月折旧率格式化后
    public BigDecimal getMonthlyDepreciationRateFormat() {
        return setRateScale(monthlyDepreciationRate);
    }

    // 年折旧额格式化后
    public BigDecimal getYearlyDepreciationFormat() {
        return setMoneyScale(this.getOriginalValue().multiply(yearlyDepreciationRate));
    }
    // 月折旧额格式化后
    public BigDecimal getMonthlyDepreciationFormat() {
        return setMoneyScale(this.getOriginalValue().multiply(monthlyDepreciationRate));
    }

    /**
     * 年限平均法折旧
     * @param originalValue 金额 字符串 如"100.00"
     * @param salvageRate 百分百 字符串
     * @param lifeExpectancy 寿命年限
     */
    public StraightLineDepreciationCalculator(@NotNull String originalValue, @NotNull String salvageRate, @NotNull Integer lifeExpectancy) {
        super(new BigDecimal(originalValue), new BigDecimal(salvageRate));
        this.lifeExpectancy = new BigDecimal("" + lifeExpectancy);
    }

    /**
     * 年限平均法折旧
     * @param originalValue 金额 字符串 如"100.00"
     * @param salvageRate 百分百 字符串
     * @param lifeExpectancy 寿命年限
     */
    public StraightLineDepreciationCalculator(@NotNull Double originalValue, @NotNull Double salvageRate, @NotNull Integer lifeExpectancy) {
        super(new BigDecimal(originalValue), new BigDecimal(salvageRate));
        this.lifeExpectancy = new BigDecimal("" + lifeExpectancy);
    }

    /**
     * 计算折旧相关参数
     */
    @Override
    public StraightLineDepreciationCalculator calculate() {
        this.setYearlyDepreciationRate(ONE.subtract(this.getSalvageRate())
                .divide(this.getLifeExpectancy(), 8, RoundingMode.HALF_UP));
        this.setMonthlyDepreciationRate(this.getYearlyDepreciationRate().divide(TWELVE, 8, RoundingMode.HALF_UP));
        return this;
    }
}
