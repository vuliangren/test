package com.welearn.entity.po.procurement;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_procurement : procurement
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/6 9:48:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Procurement extends BasePersistant
{
    /**
     * Description  : 采购项目名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:128]
     */
    @NotBlank
    private String name;
    
    /**
     * Description  : 采购预算
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    private Double budget;
    
    /**
     * Description  : 采购方式: 0-委托招标 1-自行招标 2-询价方式 3-单一来源 4-竞争性谈判
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:int][PRECISION:10]
     */
    @NotNull
    private Integer method;
    
    /**
     * Description  : 采购项目创建人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:128]
     */
    @NotBlank
    private String creatorId;
    
    /**
     * Description  : 采购流程文档记录, 根据不同的采购方式不同 JSON存储
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:text][SIZE:65535]
     */
    private String documentJson;
    
    /**
     * Description  : 中标人/成交供应商名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:varchar][SIZE:256]
     */
    private String sellerCompanyName;
    
    /**
     * Description  : 装箱内容物清单(包含商业验收相关参数)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:text][SIZE:65535]
     */
    private String contentsJson;
    
    /**
     * Description  : 货运包装及内容(包含商业验收相关参数)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:text][SIZE:65535]
     */
    private String packagesJson;
    
    /**
     * Description  : 采购方公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:varchar][SIZE:128]
     */
    @NotBlank
    private String purchaserCompanyId;
    
    /**
     * Description  : 采购项目状态: 0-选择待采购项 1-采购流程记录 2-双方合同签订 3-产品信息登记 4-商品货物配送 5-项目商业验收 6-项目技术验收 7-项目款项结算 8-采购项目结项
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:int][PRECISION:10]
     */
    private Integer status;
    
    /**
     * Description  : 采购项目类型: 0-医疗设备采购 1-医用耗材采购 2-设备配件采购
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:int][PRECISION:10]
     */
    private Integer type;
    
    /**
     * Description  : 商业验收状态: 0-待验收 1-验收通过 2-验收失败
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:int][PRECISION:10]
     */
    private Integer commercialCheckStatus;
    
    /**
     * Description  : 技术验收状态: 0-待验收 1-验收通过 2-验收失败
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:int][PRECISION:10]
     */
    private Integer technologyCheckStatus;
    
    /**
     * Description  : 验收记录(商业验收与技术验收相关参数)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:text][SIZE:65535]
     */
    private String checkRecordJson;
    
    /**
     * Description  : 验收报告签字盖章后 拍摄或扫描图片 上传
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:text][SIZE:65535]
     */
    private String checkReportPhotocopy;
    
    /**
     * Description  : 销售负责人姓名
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:varchar][SIZE:128]
     */
    private String salespersonName;
    
    /**
     * Description  : 销售负责人身份证
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:varchar][SIZE:512]
     */
    private String salespersonIDCard;
    
    /**
     * Description  : 销售负责人身份证复印件(加盖公章)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:text][SIZE:65535]
     */
    private String salespersonIDCardPhotocopy;
    
    /**
     * Description  : 是否已取消：0-正常，1-取消
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_procurement:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @NotNull
    private Boolean isCanceled;
    
    /**
     * Description  : 取消原因id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_procurement:varchar][SIZE:128]
     */
    private String cancellationReasonId;

    /**
     * 计算创建与更新的小时差值
     * @return 小时差
     */
    public Integer costHours(){
        DateTime begin = new DateTime(this.getCreatedAt());
        DateTime end = new DateTime(this.getUpdatedAt());
        Period p = new Period(begin, end, PeriodType.hours());
        return p.getHours();
    }
}
