package com.welearn.entity.vo.response.apply;

import com.welearn.entity.po.apply.ApprovalProcess;
import com.welearn.entity.po.apply.ApprovalProcessPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/10/9.
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalProcessInfo{
    private ApprovalProcess process;
    private List<ApprovalProcessPoint> points;
}
