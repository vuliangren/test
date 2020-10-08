package com.welearn.entity.vo.request.equipment;

import java.io.Serializable;
import java.lang.String;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CancelBorrow implements Serializable {
    @NotBlank
    private String borrowId;
    @NotBlank
    private String reason;
}
