package com.welearn.controller;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.procurement.Procurement;
import com.welearn.entity.qo.procurement.ProcurementQueryCondition;
import com.welearn.entity.vo.request.procurement.*;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.procurement.ProcurementDetailInfo;
import com.welearn.entity.vo.response.procurement.ProcurementInfo;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.ProcurementService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/procurement")
public class ProcurementController extends BaseController<Procurement, ProcurementQueryCondition, ProcurementService>{

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "根据查询条件查询采购项目(含合同相关信息)", httpMethod = "POST")
    public CommonResponse<List<ProcurementInfo>> searchInfo(@RequestBody ProcurementQueryCondition condition) {
        List<ProcurementInfo> result = service.searchInfo(condition);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/register")
    @ApiOperation(value = "采购设备供应商登记设备对应的产品", httpMethod = "POST")
    public CommonResponse<Procurement> register(@RequestBody Register register)  {
        Procurement result = service.register(register.getDetails(), register.getProcurementId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/registerInfo")
    @ApiOperation(value = "获取采购项及关联的产品信息", httpMethod = "POST")
    public CommonResponse<List<ProcurementDetailInfo>> registerInfo(@RequestBody String procurementId) {
        List<ProcurementDetailInfo> result = service.registerInfo(procurementId);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/cancel")
    @ApiOperation(value = "采购项目取消", httpMethod = "POST")
    public CommonResponse<Procurement> cancel(@RequestBody Cancel cancel) {
        Procurement result = service.cancel(cancel.getCancellationReasonId(), cancel.getProcurementId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/processRecord")
    @ApiOperation(value = "采购项目流程记录", httpMethod = "POST")
    public CommonResponse<Procurement> processRecord(@RequestBody ProcessRecord processRecord)  {
        Procurement result = service.processRecord(processRecord.getDocumentJson(), processRecord.getProcurementId(), processRecord.getIsNextStep());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/signingContract")
    @ApiOperation(value = "双方签订合同", httpMethod = "POST")
    public CommonResponse<Procurement> signingContract(@RequestBody SigningContract signingContract)  {
        Procurement result = service.signingContract(signingContract.getDetails(), signingContract.getContract(), signingContract.getProcurementId(),
                signingContract.getSalespersonIDCard(), signingContract.getSalespersonName(), signingContract.getSalespersonIDCardPhotocopy());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/distribution")
    @ApiOperation(value = "供应商对商品进行装箱与运输", httpMethod = "POST")
    public CommonResponse<Procurement> distribution(@RequestBody Distribution distribution)  {
        Procurement result = service.distribution(distribution.getContentJson(), distribution.getPackageJson(), distribution.getProcurementId(), distribution.getIsNextStep());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/selectItem")
    @ApiOperation(value = "选择待审批项创建采购项目", httpMethod = "POST")
    public CommonResponse<Procurement> selectItem(@RequestBody SelectItem selectItem)  {
        Procurement result = service.selectItem(selectItem.getName(), selectItem.getType(), selectItem.getBudget(), selectItem.getMethod(), selectItem.getDetailIds(), selectItem.getUserId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/technologyAcceptance")
    @ApiOperation(value = "院方对供应商的商品进行技术验收", httpMethod = "POST")
    public CommonResponse<Procurement> technologyAcceptance(@RequestBody TechnologyAcceptance technologyAcceptance)  {
        Procurement result = service.technologyAcceptance(technologyAcceptance.getCheckRecordJson(), technologyAcceptance.getCheckReportPhotocopy(), technologyAcceptance.getProcurementId(), technologyAcceptance.getIsNextStep());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/technologyAcceptanceRetrial")
    @ApiOperation(value = "供应商申请对项目技术验收进行重审", httpMethod = "POST")
    public CommonResponse<Procurement> technologyAcceptanceRetrial(@RequestBody String procurementId){
        Procurement result = service.technologyAcceptanceRetrial(procurementId);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/settlementInvoice")
    @ApiOperation(value = "采购项目款项结算 发票配置", httpMethod = "POST")
    public CommonResponse<Procurement> settlementInvoice(@RequestBody SettlementInvoice settlementInvoice)  {
        Procurement result = service.settlementInvoice(settlementInvoice.getProcurementId(), settlementInvoice.getInvoiceIds(), settlementInvoice.getIsNextStep());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/settlementPayment")
    @ApiOperation(value = "采购项目款项结算 支付凭证", httpMethod = "POST")
    public CommonResponse<Procurement> settlementPayment(@RequestBody String procurementId)  {
        Procurement result = service.settlementPayment(procurementId);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/commercialAcceptance")
    @ApiOperation(value = "院方对供应商运输来的商品进行商业验收", httpMethod = "POST")
    public CommonResponse<Procurement> commercialAcceptance(@RequestBody CommercialAcceptance commercialAcceptance)  {
        Procurement result = service.commercialAcceptance(commercialAcceptance.getContentJson(), commercialAcceptance.getPackageJson(), commercialAcceptance.getCheckRecordJson(), commercialAcceptance.getProcurementId(), commercialAcceptance.getIsNextStep());
        return new CommonResponse<>(result);
    }

}