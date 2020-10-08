package com.welearn.entity.vo.request.equipment;

import com.welearn.entity.po.equipment.RepairDispatchOutside;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CreateDispatchOutside {
    @NotNull @Valid
    private RepairDispatchOutside dispatchOutside;
    @NotNull
    private List<String> engineerIdList;
}