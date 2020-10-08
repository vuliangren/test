package com.welearn.service;

import com.welearn.entity.po.common.LinkCode;

import java.util.List;

/**
 * Description : 图片文件相关服务
 * Created by Setsuna Jin on 2019/5/10.
 */
public interface ImageFileService {

    /**
     * 关联编码的二位码图片生成
     * @param linkCodes 关联编码列表
     */
    void generateLinkCodeQrCodeImage(List<LinkCode> linkCodes);
}
