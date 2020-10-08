package com.welearn.entity.vo.response;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description :
 * Created by Setsuna Jin on 2018/9/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(description = "分页信息")
public class Pagination implements Serializable {
    @ApiModelProperty(value = "当前页码|默认为 1")
    private Integer current;
    @ApiModelProperty(value = "每页数量|当为 0 时表示获取全部数据")
    private Integer pageSize;
    @ApiModelProperty(value = "数据总数")
    private Long total;
//    private Integer size;
//    private Integer startRow;
//    private Integer endRow;
//    private Integer pages;
//    private Integer prePage;
//    private Integer nextPage;
//    private Boolean isFirstPage;
//    private Boolean isLastPage;
//    private Boolean hasPreviousPage;
//    private Boolean hasNextPage;
//    private Integer navigatePages;
//    private Integer navigateFirstPage;
//    private Integer navigateLastPage;

    Pagination(PageInfo pageInfo){
        this.setCurrent(pageInfo.getPageNum());
        this.setPageSize(pageInfo.getPageSize());
        this.setTotal(pageInfo.getTotal());
//        this.setSize(pageInfo.getSize());
//        this.setStartRow(pageInfo.getStartRow());
//        this.setEndRow(pageInfo.getEndRow());
//        this.setPages(pageInfo.getPages());
//        this.setPrePage(pageInfo.getPrePage());
//        this.setNextPage(pageInfo.getNextPage());
//        this.setIsFirstPage(pageInfo.isIsFirstPage());
//        this.setIsLastPage(pageInfo.isIsLastPage());
//        this.setHasPreviousPage(pageInfo.isHasPreviousPage());
//        this.setHasNextPage(pageInfo.isHasNextPage());
//        this.setNavigatePages(pageInfo.getNavigatePages());
//        this.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
//        this.setNavigateLastPage(pageInfo.getNavigateLastPage());
    }
}
