package com.welearn.util.depreciation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Description : 抽象折旧计算器
 * Created by Setsuna Jin on 2018/7/14.
 */
@Data
@EqualsAndHashCode
abstract class AbstractDepreciationCalculator <T extends AbstractDepreciationCalculator>{
    protected static final BigDecimal ONE = new BigDecimal("1");
    protected static final BigDecimal TWO = new BigDecimal("2");
    protected static final BigDecimal TWELVE = new BigDecimal("12");

    // 原价 单位(分)
    @NotNull @Min(0)
    private BigDecimal originalValue;
    // 预计净残值率 (0-100)
    @NotNull @Min(0)
    private BigDecimal salvageRate;

    // 设置比率输出后保留4位小数
    public static BigDecimal setRateScale(BigDecimal value){
        return value.setScale(4, RoundingMode.HALF_UP);
    }

    // 设置费用输出后保留2位小数
    public static BigDecimal setMoneyScale(BigDecimal value){
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public AbstractDepreciationCalculator(BigDecimal originalValue) {
        this.originalValue = originalValue;
    }

    public AbstractDepreciationCalculator(BigDecimal originalValue, BigDecimal salvageRate) {
        this.originalValue = originalValue;
        this.salvageRate = salvageRate;
    }



    /**
     * 计算折旧相关参数
     */
    public abstract T calculate();
}
