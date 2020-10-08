package com.welearn.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.welearn.dictionary.common.RequestAttributeConst;
import com.welearn.dictionary.common.RequestHeaderConst;
import com.welearn.entity.dto.common.Paginate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.authorization.RequestTypeConst.REQUEST_FROM_SYSTEM_INSIDE;
import static com.welearn.dictionary.common.RequestHeaderConst.REQUEST_TYPE;

/**
 * Description : 分页工具
 * Created by Setsuna Jin on 2018/9/12.
 */
@Slf4j
public class PaginateUtil {

    /**
     * 获取分页信息
     * 如返回 null 表示是内部请求
     * @return Paginate
     */
    public static Paginate getPaginate(HttpServletRequest request){
        if (REQUEST_FROM_SYSTEM_INSIDE.name().equals(request.getHeader(REQUEST_TYPE.getHeaderName())))
            return null;
        String page = request.getHeader(RequestHeaderConst.PAGINATE_PAGE.getHeaderName());
        String size = request.getHeader(RequestHeaderConst.PAGINATE_SIZE.getHeaderName());
        String total = request.getHeader(RequestHeaderConst.PAGINATE_TOTAL.getHeaderName());
        Paginate paginate = Paginate.getDefaultPaginate();
        if (StringUtils.isNotBlank(page))
            paginate.setPage(Integer.parseInt(page));
        if (StringUtils.isNotBlank(size))
            paginate.setSize(Integer.parseInt(size));
        if (StringUtils.isNotBlank(total))
            paginate.setTotal(Integer.parseInt(total));
        return paginate;
    }

    // 开始分页 服务间请求则不进行分页 用户请求有默认分页(page:1,size:10)
    public static void startPage(){
        Paginate paginate = getPaginate(GlobalContextUtil.getRequest());
        if (Objects.nonNull(paginate))
            PageHelper.startPage(paginate.getPage(), paginate.getSize());
    }

    // 关闭分页 服务间请求则不进行分页 用户请求有默认分页(page:1,size:10)
    public static <T> List<T> closePage(List<T> list){
        HttpServletRequest request = GlobalContextUtil.getRequest();
        if (REQUEST_FROM_SYSTEM_INSIDE.name().equals(request.getHeader(REQUEST_TYPE.getHeaderName())))
            return list;
        request.setAttribute(RequestAttributeConst.CACHE_PAGE_INFO.getName(), new PageInfo<>(list));
        PageHelper.clearPage();
        return list;
    }

    /**
     * 分页执行内容 Lambda 接口
     * @param <T>
     */
    public interface MapperSelect<T> {
        List<T> doSelect();
    }

    /**
     * 使用 Lambda 形式简化分页
     * @param selectFunction MapperSelect
     * @return List<T>
     */
    public static <T> List<T> page(MapperSelect<T> selectFunction){
        HttpServletRequest request = GlobalContextUtil.getRequest();
        Paginate paginate = getPaginate(request);
        if (Objects.nonNull(paginate))
            PageHelper.startPage(paginate.getPage(), paginate.getSize());
        List<T> result = selectFunction.doSelect();
        if (Objects.nonNull(paginate)){
            request.setAttribute(RequestAttributeConst.CACHE_PAGE_INFO.getName(), new PageInfo<>(result));
            PageHelper.clearPage();
        }
        return result;
    }
}
