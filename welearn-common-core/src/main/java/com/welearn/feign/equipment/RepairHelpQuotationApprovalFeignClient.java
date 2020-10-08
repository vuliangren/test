package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.RepairHelpQuotationApproval;
import com.welearn.entity.qo.equipment.RepairHelpQuotationApprovalQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.RepairHelpQuotationApprovalController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface RepairHelpQuotationApprovalFeignClient {

    @RequestMapping(value = "/repairHelpQuotationApproval/afterApplicationReject")
    CommonResponse afterApplicationReject(String string);

    @RequestMapping(value = "/repairHelpQuotationApproval/afterApplicationPass")
    CommonResponse afterApplicationPass(String string);

    @RequestMapping(value = "/repairHelpQuotationApproval/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/repairHelpQuotationApproval/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/repairHelpQuotationApproval/update")
    CommonResponse update(RepairHelpQuotationApproval entity);

    @RequestMapping(value = "/repairHelpQuotationApproval/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/repairHelpQuotationApproval/create")
    CommonResponse<RepairHelpQuotationApproval> create(RepairHelpQuotationApproval entity);

    @RequestMapping(value = "/repairHelpQuotationApproval/search")
    CommonResponse<List<RepairHelpQuotationApproval>> search(RepairHelpQuotationApprovalQueryCondition queryCondition);

    @RequestMapping(value = "/repairHelpQuotationApproval/select")
    CommonResponse<RepairHelpQuotationApproval> select(String id);
}