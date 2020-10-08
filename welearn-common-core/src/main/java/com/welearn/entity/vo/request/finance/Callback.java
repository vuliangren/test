package com.welearn.entity.vo.request.finance;

import com.welearn.entity.po.finance.Invoice;
import com.welearn.entity.po.finance.InvoicePayment;

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
public class Callback {
    @NotNull private Invoice invoice;
    @NotNull private InvoicePayment payment;
}