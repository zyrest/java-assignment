package cying.dao;

import cying.entity.ConfigEntity;
import cying.util.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samson on 17-4-18.
 */
public class ConfigDao {

    public int getTotalNum() {
        int num = 0;
        String sql = "SELECT COUNT(*) FROM config";

        try(Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql) ) {

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                num = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    public void add(ConfigEntity config) {
        String sql = "INSERT INTO config VALUES(NULL,?,?)";

        try(Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql) ) {

            statement.setString(1, config.getName());
            statement.setString(2, config.getValue());

            statement.execute();

            ResultSet set = statement.getGeneratedKeys();
            if(set.next()) {
                int id = set.getInt(1);
                config.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ConfigEntity config) {
        String sql = "UPDATE config SET name= ?, value=? WHERE id = ?";

        try (Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql) ) {

            statement.setString(1, config.getName());
            statement.setString(2, config.getValue());
            statement.setInt(3, config.getId());

            statement.execute();

        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM config WHERE id=?";

        try (Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql) ) {

            statement.setInt(1, id);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ConfigEntity get(int id) {
        String sql = "SELECT * FROM config WHERE id=?";
        ConfigEntity config = null;

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                config = new ConfigEntity();
                config.setId(id);
                config.setName(set.getString("name"));
                config.setValue(set.getString("value"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return config;
    }

    public List<ConfigEntity> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<ConfigEntity> list(int start, int count) {
        String sql = "SELECT * FROM config ORDER BY id DESC LIMIT ?,?";
        List<ConfigEntity> list = new ArrayList<>();


        try (Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql) ) {

            statement.setInt(1, start);
            statement.setInt(2, count);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                ConfigEntity tmp = new ConfigEntity();
                tmp.setId(set.getInt("id"));
                tmp.setName(set.getString("name"));
                tmp.setValue(set.getString("value"));
                list.add(tmp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ConfigEntity getByName(String name) {
        String sql = "SELECT * FROM config WHERE name=?";
        ConfigEntity config = null;

        try (Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql) ) {

            statement.setString(1, name);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                config = new ConfigEntity();
                config.setId(set.getInt("id"));
                config.setName(name);
                config.setValue(set.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return config;
    }
}
