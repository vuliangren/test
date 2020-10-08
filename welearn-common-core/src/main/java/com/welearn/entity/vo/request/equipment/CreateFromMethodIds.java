package com.welearn.entity.vo.request.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CreateFromMethodIds {
    @NotBlank
    private String equipmentId;
    @NotBlank
    private String engineerId;
    @NotBlank
    private String remark;
    @NotEmpty
    private List<String> methodIds;
}