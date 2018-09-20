package cying.service;

import cying.dao.CategoryDao;
import cying.dao.RecordDao;
import cying.entity.CategoryEntity;

import java.util.Collections;
import java.util.List;

/**
 * Created by samson on 17-4-21.
 */
public class CategoryService {
    private static CategoryDao categoryDao = new CategoryDao();
    private static RecordDao recordDao = new RecordDao();

    public static List<CategoryEntity> list() {
        List<CategoryEntity> list = categoryDao.list();

        for(CategoryEntity entity : list) {
            int recordNum = recordDao.listRecordNum(entity.getId());
            entity.setRecordNum(recordNum);
        }

        Collections.sort(list, (c1,c2)->c2.getRecordNum()-c1.getRecordNum());

        return list;
    }

    public static void add(String name) {
        CategoryEntity category = new CategoryEntity();
        category.setName(name);
        categoryDao.add(category);
    }

    public static void delete(int id) {
        categoryDao.delete(id);
    }

    public static void update(int id, String name) {
        CategoryEntity category = new CategoryEntity();
        category.setId(id);
        category.setName(name);
        categoryDao.update(category);
    }


}
