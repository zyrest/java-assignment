package cying.service;

import cying.dao.RecordDao;
import cying.entity.RecordEntity;
import cying.util.DateUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by samson on 17-4-21.
 */
public class ReportService {
    private static RecordDao recordDao = new RecordDao();

    /**
     *
     * @param day
     * @param rawMonthData
     * @return 返回某天的消费总额
     */
    public static int getDaySpend(Date day, List<RecordEntity> rawMonthData) {
        int daySpend = 0;
        Timestamp time = DateUtil.dateToTimestamp(day);

        for (RecordEntity record : rawMonthData) {
            if (record.getDate().equals(time))
                daySpend += record.getSpend();
        }

        return daySpend;
    }

    /**
     * 返回当前月份前几天的花费List
     * @return
     */

    public static List<RecordEntity> listMonthSpend() {
        List<RecordEntity> rawMonthData = recordDao.listMonth();
        List<RecordEntity> monthPerDay = new ArrayList<>();

        Date monthBegin = DateUtil.monthBegin();
        int daySpend = DateUtil.nowDayOfThisMonth()+1;
        Calendar calendar = Calendar.getInstance();

        for (int i = 0;i<daySpend;i++) {
            RecordEntity record = new RecordEntity();
            calendar.setTime(monthBegin);
            calendar.add(Calendar.DAY_OF_MONTH, i);

            int thisDaySpend = getDaySpend(calendar.getTime(), rawMonthData);
            record.setSpend(thisDaySpend);

            monthPerDay.add(record);
        }

        return monthPerDay;
    }
}
