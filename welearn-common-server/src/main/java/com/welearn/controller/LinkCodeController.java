package com.welearn.controller;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.common.LinkCode;
import com.welearn.entity.qo.common.LinkCodeQueryCondition;
import com.welearn.entity.vo.request.common.BatchBindingIdTypeCreate;
import com.welearn.entity.vo.request.common.BatchUpdateCompanyId;
import com.welearn.entity.vo.request.common.Binding;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.generator.ControllerGenerator;
import com.welearn.service.LinkCodeService;
import com.welearn.service.ReRoutePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 关联编码管理
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/linkCode")
@Api(value = "linkCode 接口")
public class LinkCodeController extends BaseController<LinkCode, LinkCodeQueryCondition, LinkCodeService>{
    @RequestMapping(value = "/batchUpdateCompanyId")
    @ApiOperation(value = "批量授权关联编码给企业", httpMethod = "POST")
    public CommonResponse batchUpdateCompanyId(@RequestBody BatchUpdateCompanyId batchUpdateCompanyId) {
        service.batchUpdateCompanyId(batchUpdateCompanyId.getStartNumber(), batchUpdateCompanyId.getEndNumber(), batchUpdateCompanyId.getCompanyId());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/selectBySerialNumber")
    @ApiOperation(value = "根据序列号查找关联编码", httpMethod = "POST")
    public CommonResponse<LinkCode> selectBySerialNumber(@RequestBody String string) {
        LinkCode result = service.selectBySerialNumber(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/batchBindingIdTypeCreate")
    @ApiOperation(value = "批量创建关联ID的编码", httpMethod = "POST")
    public CommonResponse batchBindingIdTypeCreate(@RequestBody BatchBindingIdTypeCreate batchBindingIdTypeCreate) {
        service.batchBindingIdTypeCreate(batchBindingIdTypeCreate.getServicePrefix(), batchBindingIdTypeCreate.getPersistantPrefix(), batchBindingIdTypeCreate.getCount());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/binding")
    @ApiOperation(value = "关联编码绑定ID", httpMethod = "POST")
    public CommonResponse binding(@RequestBody Binding binding) {
        service.binding(binding.getNumber(), binding.getSerialNumber(), binding.getBindingId(), binding.getBindingPayload());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/unBinding")
    @ApiOperation(value = "关联编码解绑ID", httpMethod = "POST")
    public CommonResponse unBinding(@RequestBody String string) {
        service.unBinding(string);
        return CommonResponse.getSuccessResponse();
    }
}