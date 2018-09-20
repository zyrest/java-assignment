package cying.service;

import cying.dao.CategoryDao;
import cying.dao.RecordDao;
import cying.entity.RecordEntity;
import cying.page.HomePage;
import cying.util.DateUtil;

import java.util.List;

/**
 * Created by samson on 17-4-21.
 */
public class HomeService {
    private static RecordDao recordDao = new RecordDao();
    private static CategoryDao categoryDao = new CategoryDao();

    public static HomePage getHomePage() {
        //本月数据
        List<RecordEntity> monthData = recordDao.listMonth();
        //今日数据
        List<RecordEntity> todayData = recordDao.listToday();
        //本月有多少天
        int totalDayOfThisMonth = DateUtil.totalDayOfThisMonth();
        //今天是这个月第几天
        int todayOfThisMonth = totalDayOfThisMonth-DateUtil.leftDayOfThisMonth();
        //每月的预算
        int budget = ConfigService.getBudget();

        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthAvailable = 0;
        int dayAvgAvailable = 0;
        int monthLeftDay = DateUtil.leftDayOfThisMonth();
        int spendPercentage = 0;

        //本月已用
        for (RecordEntity record : monthData)
            monthSpend += record.getSpend();
        //今天已用
        for (RecordEntity record : todayData)
            todaySpend += record.getSpend();
        //之前几天日均花费
        avgSpendPerDay = monthSpend / todayOfThisMonth;
        //本月剩余多少钱
        monthAvailable = budget - monthSpend;
        //之后几天日均花费应该为多少
        dayAvgAvailable = monthAvailable / monthLeftDay;
        //花了多少比例的钱
        spendPercentage = (monthSpend * 100) / budget ;

//        System.out.println(spendPercentage);

        HomePage page = new HomePage(monthSpend, todaySpend, avgSpendPerDay,
                                    monthAvailable, dayAvgAvailable, monthLeftDay,
                                    spendPercentage);

        return page;
    }
}
