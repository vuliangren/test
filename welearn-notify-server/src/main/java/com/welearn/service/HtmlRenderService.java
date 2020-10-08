package com.welearn.service;

/**
 * Description : Velocity 渲染
 * Created by Setsuna Jin on 2018/11/27.
 */
public interface HtmlRenderService {

    /**
     * 更加模板类型 查找模板 根据参数渲染该模板
     * @param code 模板类型
     * @param args 参数
     * @return 渲染后的 html
     */
    String renderHtml(String code, Object args);
}
