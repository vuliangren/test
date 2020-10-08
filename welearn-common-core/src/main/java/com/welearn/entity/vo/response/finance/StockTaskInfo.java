package com.welearn.entity.vo.response.finance;

import com.welearn.entity.po.finance.StockPackage;
import com.welearn.entity.po.finance.StockTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockTaskInfo extends StockTask {
    private List<StockPackage> stockPackages;
}
