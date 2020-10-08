package com.welearn.service;

import com.welearn.entity.po.common.LinkCode;
import com.welearn.entity.qo.common.LinkCodeQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Description : LinkCodeService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface LinkCodeService extends BaseService<LinkCode, LinkCodeQueryCondition>{

    /**
     * 批量创建 关联ID 类型 关联编码
     * @param count 创建数量
     */
    void batchBindingIdTypeCreate(String servicePrefix, String persistantPrefix, @NotNull @Min(1) Integer count);

    /**
     * 关联ID 类型 关联编码 绑定 ID
     *
     * @param number         编号 与 serialNumber 二选一填写
     * @param serialNumber   编码自身的序列号 与 number 二选一填写
     * @param bindingId      绑定ID
     * @param bindingPayload 绑定附带参数
     */
    void binding(Long number, String serialNumber, @NotBlank String bindingId, String bindingPayload);

    /**
     * 关联ID 类型 关联编码 解绑 ID
     * @param serialNumber 序列号
     */
    void unBinding(@NotBlank String serialNumber);

    /**
     * 根据编码的序列号 获取 编码信息
     * @param serialNumber 序列号
     * @return 编码信息
     */
    LinkCode selectBySerialNumber(@NotBlank String serialNumber);

    /**
     * 批量授权 关联编码到企业
     * @param startNumber 起始编号(>=)
     * @param endNumber 结束编号(<=)
     * @param companyId 授权企业id
     */
    void batchUpdateCompanyId(@NotNull @Min(1) Long startNumber, @NotNull Long endNumber, @NotBlank String companyId);
}