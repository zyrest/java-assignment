package cying.service;

import cying.dao.RecordDao;
import cying.entity.CategoryEntity;
import cying.entity.RecordEntity;

import java.sql.Timestamp;

/**
 * Created by samson on 17-4-21.
 */
public class RecordService {
    private static RecordDao recordDao = new RecordDao();

    public static void add(int spend, CategoryEntity category,
                           String comment, Timestamp now) {

        RecordEntity record = new RecordEntity();

        record.setSpend(spend);
        record.setCid(category.getId());
        record.setComment(comment);
        record.setDate(now);

        recordDao.add(record);
    }
}
