package com.welearn.entity.vo.request.alink;

import com.welearn.entity.po.BasePersistant;
import com.welearn.entity.po.alink.ReDeviceGroupDevice;
import java.lang.String;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import io.swagger.annotations.Api;

import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/10.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AllDeleteOldAndCreate<T extends BasePersistant> {
    @NotNull private String groupId;
    @NotNull private List<T> reItems;
}