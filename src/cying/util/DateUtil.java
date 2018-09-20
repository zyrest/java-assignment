package cying.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by samson on 17-4-18.
 */
public class DateUtil {
    public static long millisecondsOfOneDay = 1000*60*60*24;

    /**
     *
     * @param date 由日期控件得到的日期
     * @return
     *      存储在数据库里的timestamp
     */
    public static Timestamp dateToTimestamp(Date date) {

        return new Timestamp(date.getTime());
    }

    /**
     *
     * @return 获取今日日期 时分秒均为0
     */
    public static Date today() {
        Calendar today = Calendar.getInstance();

        today.setTime(new Date());
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        return today.getTime();
    }

    /**
     * 获得本月第一天
     * @return
     */
    public static Date monthBegin() {
        Calendar begin = Calendar.getInstance();

        begin.setTime(new Date());
        begin.set(Calendar.DAY_OF_MONTH, 1);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);

        return begin.getTime();
    }

    /**
     * 获得本月最后一天
     * @return
     */
    public static Date monthEnd() {
        Calendar end = Calendar.getInstance();

        end.setTime(new Date());
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);
        end.set(Calendar.DAY_OF_MONTH, 1);

        end.add(Calendar.MONTH, 1);
        end.add(Calendar.DAY_OF_MONTH, -1);

        return end.getTime();
    }

    /**
     * 计算当前月份有多少天
     * @return
     */
    public static int totalDayOfThisMonth() {
        long lastDayMilliSeconds = monthEnd().getTime();
        long firstDayMilliSeconds = monthBegin().getTime();

        int days = (int)
                ( (lastDayMilliSeconds-firstDayMilliSeconds)/millisecondsOfOneDay ) + 1 ;

        return days;
    }

    /**
     * 计算本月剩余多少天（包括今天）
     * @return
     */
    public static int leftDayOfThisMonth() {
        long lastDayMilliSeconds = monthEnd().getTime();
        long todayMilliSeconds = today().getTime();

        int days = (int)
                ( (lastDayMilliSeconds-todayMilliSeconds)/millisecondsOfOneDay ) + 1 ;

        return days;
    }

    /**
     * 几月今天是本月第几天
     * @return
     */
    public static int nowDayOfThisMonth() {

        return totalDayOfThisMonth() - leftDayOfThisMonth();
    }

    public static void main(String[] args) {
        System.out.println(monthBegin());
        System.out.println(monthEnd());
        System.out.println(today());
        System.out.println(totalDayOfThisMonth());
        System.out.println(leftDayOfThisMonth());
    }

}
