package cying.service;

import cying.dao.ConfigDao;
import cying.entity.ConfigEntity;

/**
 * Created by samson on 17-4-20.
 */
public class ConfigService {
    public static final String BUDGET = "budget";
    public static final String MYSQL_PATH = "mysql_path";
    public static final String DEFAULT_BUDGET = "1000";
    private static ConfigDao dao = new ConfigDao();

    static {
        init();
    }

    private static void init() {
        init(BUDGET, DEFAULT_BUDGET);
        init(MYSQL_PATH, "");
    }
    private static void init(String name, String value) {
        ConfigEntity config = dao.getByName(name);

        if(config == null) {
            ConfigEntity tmp = new ConfigEntity();
            tmp.setName(name);
            tmp.setValue(value);
            dao.add(tmp);
        }
    }

    public static String getValue(String name) {
        return dao.getByName(name).getValue();
    }

    public static void update(String name, String value) {
        ConfigEntity config = dao.getByName(name);
        if(config != null) {
            config.setValue(value);
            dao.update(config);
        }
    }

    public static int getBudget() {
        return Integer.parseInt(getValue(BUDGET));
    }


}
