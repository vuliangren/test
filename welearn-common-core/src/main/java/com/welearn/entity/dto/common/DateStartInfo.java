package com.welearn.entity.dto.common;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/12.
 */
@Data
@AllArgsConstructor
public class DateStartInfo implements Serializable {
    private Date thisDayStart;
    private Date thisYearStart;
    private Date thisWeekStart;
    private Date lastWeekStart;
    private Date thisMonthStart;
    private Date lastMonthStart;

    public Date getSearchStartAt() {
        return thisYearStart.getTime() > lastMonthStart.getTime() ? lastMonthStart : thisYearStart;
    }

    public static DateStartInfo getDateRange() {
        DateTime dateTime = new DateTime().withMillisOfDay(0);
        DateTime thisWeekStart = dateTime.withDayOfWeek(1);
        DateTime thisMonthStart = dateTime.withDayOfMonth(1);
        return new DateStartInfo(
                dateTime.toDate(),
                dateTime.withDayOfYear(1).toDate(),
                thisWeekStart.toDate(),
                thisWeekStart.minusWeeks(1).toDate(),
                thisMonthStart.toDate(),
                thisMonthStart.minusMonths(1).toDate());
    }
}