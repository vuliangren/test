package com.welearn.dictionary.equipment;

/**
 * Description : 维护任务分配后的状态
 * Created by Setsuna Jin on 2019/1/3.
 */
public enum MaintenanceTaskAssignmentStatusConst {
    // 0-待领取
    UN_RECEIVE,
    // 1-待处理
    UN_PROCESS,
    // 2-处理中
    PROCESSING,
    // 3-已处理
    PROCESSED,
    // 4-已取消
    CANCELED,
    // 5-部分完成
    PARTLY_PROCESSED,

}
