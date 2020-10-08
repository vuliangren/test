package com.welearn.dictionary.apply;

import com.welearn.entity.vo.response.apply.CertificateType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description :
 * Created by Setsuna Jin on 2018/10/24.
 */
@Getter
@AllArgsConstructor
public enum CertificateTypeConst {
    MEDICAL_DEVICE_REGISTRATION("医疗器械注册证","medical-device-registration","医疗器械注册，是指依照法定程序，对拟上市销售、使用的医疗器械的安全性、" +
            "有效性进行系统评价，以决定是否同意其销售、使用的过程。它分为境内医疗器械注册和境外医疗器械注册，境外的医疗器械不管是一类，二类，三类都要到北京国" +
            "家食品药品监督局办理：境内的一，二类医疗器械在当地的省或市食品药品监督局办理，三类的到国家食品药品监督局办理。医疗器械注册证是指医疗机械产品的合法身份证。"),
    MEDICAL_DEVICE_PRODUCTION("医疗器械生产企业许可证","medical-device-production","医疗器械生产许可证是医疗器械生产企业必须持有的证件，由当地药监局审核" +
            "颁发。开办医疗器械生产企业应当符合国家医疗器械行业发展规划和产业政策。"),
    BUSINESS_LICENSE("营业执照", "business-license","营业执照是工商行政管理机关发给工商企业、个体经营者的准许从事某项生产经营活动的凭证。"),
    LARGE_MEDICAL_EQUIPMENT_CONFIGURATION("大型医用设备配置许可证","large-medical-equipment-configuration","医疗机构获得《大型医用设备配置许可证》后，方可购置大型医用设备"),
    ;
    private String name;
    private String code;
    private String description;

    public static List<CertificateType> getCertificateTypeList(){
        return Arrays.stream(CertificateTypeConst.values()).map( type -> new CertificateType(type.name, type.code, type.description)).collect(Collectors.toList());
    }
}
