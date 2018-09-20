package cying.page;

/**
 * Created by samson on 17-4-21.
 */
public class HomePage {
    //本月消费
    public String monthSpend;
    //今日消费
    public String todaySpend;
    //平均每日消费
    public String avgSpendPerDay;
    //当月剩余
    public String monthAvailable;
    //平均每日剩余
    public String dayAvgAvailable;
    //距离月底剩几天
    public String monthLeftDay;
    //使用比例
    public int spendPercentage;
    //是否超支
    public boolean isOverSpend;

    public HomePage(int monthSpend, int todaySpend, int avgSpendPerDay,
                    int monthAvailable, int dayAvgAvailable, int monthLeftDay,
                    int spendPercentage) {
        this.monthSpend = "￥" + monthSpend;
        this.todaySpend = "￥" + todaySpend;
        this.avgSpendPerDay = "￥" + avgSpendPerDay;

        if (monthAvailable < 0)
            isOverSpend = true;

        if (! isOverSpend) {
            this.monthAvailable = "￥" + monthAvailable;
            this.dayAvgAvailable = "￥" + dayAvgAvailable;
        } else {
            this.monthAvailable = "超支" + (-monthAvailable);
            this.dayAvgAvailable = "￥0";
        }

        this.monthLeftDay = monthLeftDay + "天";
        this.spendPercentage = spendPercentage;
    }

}
