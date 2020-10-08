package com.welearn.entity.vo.request.equipment;

import java.lang.String;
import java.util.List;

import com.welearn.entity.po.equipment.RepairDispatchOutside;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CreateDispatch {
    @NotBlank
    private String requestId;
    @NotNull
    private List<String> engineerIdList;
}