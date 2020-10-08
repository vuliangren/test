package com.welearn.service.impl;

import com.welearn.dictionary.common.LinkCodeTypeConst;
import com.welearn.dictionary.common.PersistantConst;
import com.welearn.entity.po.common.LinkCode;
import com.welearn.entity.qo.common.LinkCodeQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.LinkCodeMapper;
import com.welearn.service.LinkCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * Description : LinkCodeService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class LinkCodeServiceImpl extends BaseServiceImpl<LinkCode,LinkCodeQueryCondition,LinkCodeMapper>
        implements LinkCodeService{
    
    @Autowired
    private LinkCodeMapper linkCodeMapper;
    
    @Override
    LinkCodeMapper getMapper() {
        return linkCodeMapper;
    }

    private void setSerialNumber(LinkCode linkCode, Long currentCount){
        Long number = currentCount + 1;
        linkCode.setNumber(number);
        linkCode.setSerialNumber(String.format("%s%s%012x", linkCode.getServicePrefix(), linkCode.getPersistantPrefix(), number));
    }

    @Override @Transactional
    public LinkCode create(LinkCode linkCode){
        if (StringUtils.isBlank(linkCode.getServicePrefix()) || linkCode.getServicePrefix().length() != 2 )
            linkCode.setServicePrefix("FF");
        if (StringUtils.isBlank(linkCode.getPersistantPrefix()) || linkCode.getPersistantPrefix().length() != 2 )
            linkCode.setPersistantPrefix("FF");
        long currentCount = linkCodeMapper.countAll();
        this.setSerialNumber(linkCode, currentCount);
        return super.create(linkCode);
    }

    /**
     * 批量创建 关联ID 类型 关联编码
     *
     * @param count 创建数量
     */
    @Override @Transactional
    public void batchBindingIdTypeCreate(String servicePrefix, String persistantPrefix, Integer count) {
        long currentCount = linkCodeMapper.countAll();
        if (StringUtils.isBlank(servicePrefix) || servicePrefix.length() != 2 )
            servicePrefix = "FF";
        if (StringUtils.isBlank(persistantPrefix) || persistantPrefix.length() != 2 )
            persistantPrefix = "FF";
        for (int i = 0; i < count; i++) {
            LinkCode linkCode = new LinkCode();
            linkCode.setType(LinkCodeTypeConst.BINDING_ID.ordinal());
            linkCode.setServicePrefix(servicePrefix);
            linkCode.setPersistantPrefix(persistantPrefix);
            linkCode.setIsBinding(false);
            this.setSerialNumber(linkCode, currentCount + i);
            super.create(linkCode);
        }
    }

    /**
     * 关联ID 类型 关联编码 绑定 ID
     *
     * @param number         编号 与 serialNumber 二选一填写
     * @param serialNumber   编码自身的序列号 与 number 二选一填写
     * @param bindingId      绑定ID
     * @param bindingPayload 绑定附带参数
     */
    @Override @Transactional
    public void binding(Long number, String serialNumber, String bindingId, String bindingPayload) {
        LinkCode linkCode = null;
        if (Objects.nonNull(number))
            linkCode = linkCodeMapper.selectByNumber(number);
        else if (StringUtils.isNotBlank(serialNumber))
            linkCode = selectBySerialNumber(serialNumber);

        // 验证关联编码是否可绑定
        if (Objects.isNull(linkCode))
            throw new BusinessVerifyFailedException("serialNumber 无效");
        if (linkCode.getIsBinding())
            throw new BusinessVerifyFailedException("serialNumber 已绑定 ID");
        if (LinkCodeTypeConst.BINDING_ID.ordinal() != linkCode.getType())
            throw new BusinessVerifyFailedException("serialNumber 类型不匹配");
        PersistantConst persistantConst = PersistantConst.getByUUID(bindingId);
        if (Objects.isNull(persistantConst))
            throw new BusinessVerifyFailedException("bindingId 非法 或 PersistantConst 中登记");
        // 检查 服务类型是否匹配
        if (!"FF".equals(linkCode.getServicePrefix())){
            if (!linkCode.getServicePrefix().equals(bindingId.substring(0, 2)))
                throw new BusinessVerifyFailedException("bindingId servicePrefix 不匹配");
        } else {
            linkCode.setServicePrefix(bindingId.substring(0, 2));
        }
        // 检查 实体类型是否匹配
        if (!"FF".equals(linkCode.getPersistantPrefix())){
            if (!linkCode.getPersistantPrefix().equals(bindingId.substring(2, 4)))
                throw new BusinessVerifyFailedException("bindingId persistantPrefix 不匹配");
        } else {
            linkCode.setPersistantPrefix(bindingId.substring(2, 4));
        }
        linkCode.setBindingId(bindingId);
        linkCode.setIsBinding(true);
        linkCode.setBindingAt(new Date());
        linkCode.setBindingPayload(bindingPayload);
        this.update(linkCode);
    }

    /**
     * 关联ID 类型 关联编码 解绑 ID
     *
     * @param serialNumber 序列号
     */
    @Override
    public void unBinding(String serialNumber) {
        LinkCode linkCode = selectBySerialNumber(serialNumber);
        if (Objects.isNull(linkCode))
            throw new BusinessVerifyFailedException("serialNumber 无效");
        if (!linkCode.getIsBinding())
            throw new BusinessVerifyFailedException("serialNumber 未绑定 ID");
        if (LinkCodeTypeConst.BINDING_ID.ordinal() != linkCode.getType())
            throw new BusinessVerifyFailedException("serialNumber 类型不匹配");
        linkCode.setBindingId(null);
        linkCode.setIsBinding(false);
        linkCode.setBindingAt(null);
        linkCode.setBindingPayload(null);
        linkCodeMapper.updateByPK(linkCode);
    }

    /**
     * 根据编码的序列号 获取 编码信息
     *
     * @param serialNumber 序列号
     * @return 编码信息
     */
    @Override
    public LinkCode selectBySerialNumber(String serialNumber) {
        return linkCodeMapper.selectBySerialNumber(serialNumber);
    }

    /**
     * 批量授权 关联编码到企业
     *
     * @param startNumber 起始编号(>=)
     * @param endNumber   结束编号(<=)
     * @param companyId   授权企业id
     */
    @Override
    public void batchUpdateCompanyId(Long startNumber, Long endNumber, String companyId) {
        if (endNumber < startNumber)
            throw new BusinessVerifyFailedException("endNumber 非法");
        linkCodeMapper.batchUpdateCompanyId(startNumber, endNumber, companyId);
    }

}
