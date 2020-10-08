package com.welearn.feign.procurement;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.procurement.Procurement;
import com.welearn.entity.qo.procurement.ProcurementQueryCondition;
import com.welearn.entity.vo.request.procurement.Cancel;
import com.welearn.entity.vo.request.procurement.CommercialAcceptance;
import com.welearn.entity.vo.request.procurement.Distribution;
import com.welearn.entity.vo.request.procurement.ProcessRecord;
import com.welearn.entity.vo.request.procurement.Register;
import com.welearn.entity.vo.request.procurement.SelectItem;
import com.welearn.entity.vo.request.procurement.SettlementInvoice;
import com.welearn.entity.vo.request.procurement.SettlementPayment;
import com.welearn.entity.vo.request.procurement.SigningContract;
import com.welearn.entity.vo.request.procurement.TechnologyAcceptance;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.procurement.ProcurementDetailInfo;
import com.welearn.entity.vo.response.procurement.ProcurementInfo;
import java.lang.String;
import java.util.List;

/**
 * Description : welearn-procurement-service / com.welearn.controller.ProcurementController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-procurement-server", configuration = FeignConfiguration.class)
public interface ProcurementFeignClient {

    @RequestMapping(value = "/procurement/register")
    CommonResponse<Procurement> register(Register register);

    @RequestMapping(value = "/procurement/technologyAcceptance")
    CommonResponse<Procurement> technologyAcceptance(TechnologyAcceptance technologyAcceptance);

    @RequestMapping(value = "/procurement/settlementPayment")
    CommonResponse<Procurement> settlementPayment(String procurementId);

    @RequestMapping(value = "/procurement/technologyAcceptanceRetrial")
    CommonResponse<Procurement> technologyAcceptanceRetrial(String procurementId);

    @RequestMapping(value = "/procurement/commercialAcceptance")
    CommonResponse<Procurement> commercialAcceptance(CommercialAcceptance commercialAcceptance);

    @RequestMapping(value = "/procurement/settlementInvoice")
    CommonResponse<Procurement> settlementInvoice(SettlementInvoice settlementInvoice);

    @RequestMapping(value = "/procurement/searchInfo")
    CommonResponse<List<ProcurementInfo>> searchInfo(ProcurementQueryCondition condition);

    @RequestMapping(value = "/procurement/signingContract")
    CommonResponse<Procurement> signingContract(SigningContract signingContract);

    @RequestMapping(value = "/procurement/cancel")
    CommonResponse<Procurement> cancel(Cancel cancel);

    @RequestMapping(value = "/procurement/registerInfo")
    CommonResponse<List<ProcurementDetailInfo>> registerInfo(String procurementId);

    @RequestMapping(value = "/procurement/selectItem")
    CommonResponse<Procurement> selectItem(SelectItem selectItem);

    @RequestMapping(value = "/procurement/processRecord")
    CommonResponse<Procurement> processRecord(ProcessRecord processRecord);

    @RequestMapping(value = "/procurement/distribution")
    CommonResponse<Procurement> distribution(Distribution distribution);

    @RequestMapping(value = "/procurement/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/procurement/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/procurement/update")
    CommonResponse update(Procurement entity);

    @RequestMapping(value = "/procurement/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/procurement/create")
    CommonResponse<Procurement> create(Procurement entity);

    @RequestMapping(value = "/procurement/search")
    CommonResponse<List<Procurement>> search(ProcurementQueryCondition queryCondition);

    @RequestMapping(value = "/procurement/select")
    CommonResponse<Procurement> select(String id);
}