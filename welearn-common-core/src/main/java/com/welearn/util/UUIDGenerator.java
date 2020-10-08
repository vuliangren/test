package com.welearn.util;

import com.welearn.dictionary.common.PersistantConst;
import com.welearn.entity.po.finance.StockTask;
import com.welearn.error.exception.ProgramCheckFailedException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description : UUID 生成工具
 * TODO: 解决 如生成的间隔很接近时有可能出现重复的UUID
 * Created by Setsuna Jin on 2018/9/8.
 */
public class UUIDGenerator {
    private static final Map<String /* class.name */, String /* 4位的prefix */> PersistantPrefixIndex = getPersistantPrefixIndex();

    /**
     * 生成一般32位UUID
     * @return UUID(32位)
     */
    public static synchronized String get(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 生成带服务和实体编号的36位UUID
     *
     * @return %02x(2位)%02x(2位)UUID(32位)
     */
    public static synchronized String get(Class clazz){
        String prefix = PersistantPrefixIndex.get(clazz.getName());
        if (Objects.isNull(prefix))
            throw new ProgramCheckFailedException("class: %s 未在 PersistantConst 中注册", clazz.getName());
        return prefix + UUID.randomUUID().toString().replace("-","");
    }


    private static Map<String, String> getPersistantPrefixIndex(){
        return Arrays.stream(PersistantConst.values())
                .collect(Collectors.toMap(p -> p.getClazz().getName(), PersistantConst::getPrefix));
    }

}
