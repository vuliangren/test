package com.welearn.util.depreciation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Description : 双倍余额递减法
 * Created by Setsuna Jin on 2018/7/15.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DoubleDecliningBalanceDepreciationCalculator extends AbstractDepreciationCalculator<DoubleDecliningBalanceDepreciationCalculator> {
    // 预计使用寿命 (1-10)
    @NotNull
    @Min(1) @Max(10)
    private BigDecimal lifeExpectancy;

    // 折旧年限到期前两年内 年折旧率
    private BigDecimal yearlyDepreciationRate;
    // 折旧年限到期前两年内 月折旧率
    private BigDecimal monthlyDepreciationRate;
    // 年初固定资产净值
    private ArrayList<BigDecimal> everyYearsDepreciationValueList = new ArrayList<>();

//    public static void main(String[] args){
//        int life = 8;
//        DoubleDecliningBalanceDepreciationCalculator depreciationCalculator = new DoubleDecliningBalanceDepreciationCalculator("550000","0.05",life).calculate();
//        for (int i = 0; i < life; i++) {
//            System.out.println(depreciationCalculator.getYearlyDepreciationFormat(i+1));
//        }
//    }

    /**
     * 双倍余额递减法折旧
     * @param originalValue 金额 字符串 如"100.00"
     * @param salvageRate 百分百 字符串
     * @param lifeExpectancy 寿命年限
     */
    public DoubleDecliningBalanceDepreciationCalculator(@NotNull String originalValue, @NotNull String salvageRate, @NotNull Integer lifeExpectancy) {
        super(new BigDecimal(originalValue), new BigDecimal(salvageRate));
        this.lifeExpectancy = new BigDecimal("" + lifeExpectancy);
    }

    /**
     * 双倍余额递减法折旧
     * @param originalValue 金额 字符串 如"100.00"
     * @param salvageRate 百分百 字符串
     * @param lifeExpectancy 寿命年限
     */
    public DoubleDecliningBalanceDepreciationCalculator(@NotNull Double originalValue, @NotNull Double salvageRate, @NotNull Integer lifeExpectancy) {
        super(new BigDecimal(originalValue), new BigDecimal(salvageRate));
        this.lifeExpectancy = new BigDecimal("" + lifeExpectancy);
    }

    public BigDecimal getYearlyDepreciation(@NotNull @Min(1) Integer yearCount){
        return everyYearsDepreciationValueList.get(yearCount - 1);
    }

    public BigDecimal getYearlyDepreciationFormat(@NotNull @Min(1) Integer yearCount){
        return setMoneyScale(getYearlyDepreciation(yearCount));
    }

    public BigDecimal getMonthlyDepreciation(@NotNull @Min(1) Integer monthCount){
        return everyYearsDepreciationValueList.get(monthCount / 12).divide(TWELVE, 8, RoundingMode.HALF_UP);
    }

    public BigDecimal getMonthlyDepreciationFormat(@NotNull @Min(1) Integer monthCount){
        return setMoneyScale(getMonthlyDepreciation(monthCount));
    }

    public BigDecimal getYearlyDepreciationRateFormat(){
        return setRateScale(getYearlyDepreciationRate());
    }

    public BigDecimal getMonthlyDepreciationRateFormat(){
        return setRateScale(getMonthlyDepreciationRate());
    }

    /**
     * 计算折旧相关参数
     */
    @Override
    public DoubleDecliningBalanceDepreciationCalculator calculate() {
        this.setYearlyDepreciationRate(TWO.divide(this.getLifeExpectancy(), 8, RoundingMode.HALF_UP));
        this.setMonthlyDepreciationRate(this.getYearlyDepreciationRate().divide(TWELVE, 8, RoundingMode.HALF_UP));
        BigDecimal currentValue = this.getOriginalValue().multiply(ONE);
        int life = this.getLifeExpectancy().intValue();
        for (int i = 0; i < life -2; i++){
            BigDecimal depreciationValue = currentValue.multiply(this.getYearlyDepreciationRate());
            currentValue = currentValue.subtract(depreciationValue);
            everyYearsDepreciationValueList.add(depreciationValue);
        }
        currentValue = currentValue.subtract(this.getOriginalValue().multiply(this.getSalvageRate())).divide(TWO, 8, RoundingMode.HALF_UP);
        everyYearsDepreciationValueList.add(currentValue);
        everyYearsDepreciationValueList.add(currentValue.multiply(ONE));
        return this;
    }
}
