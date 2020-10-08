package com.welearn.entity.vo.response.finance;


import com.welearn.entity.po.finance.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInfo extends Stock {
    private Integer contentType;
    private String itemTypeId;
    private String itemTypeName;
    private String specification;
    private String description;
}
