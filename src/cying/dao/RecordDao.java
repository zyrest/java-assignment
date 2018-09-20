package cying.dao;

import cying.entity.RecordEntity;
import cying.util.DButil;
import cying.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by samson on 17-4-18.
 */
public class RecordDao {
    public int getTotalNum() {
        int num = 0;
        String sql = "SELECT COUNT(*) FROM record";

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet set = statement.executeQuery();

            if(set.next()) {
                num = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    public void add(RecordEntity record) {
        String sql = "INSERT INTO record VALUES(NULL,?,?,?,?)";

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, record.getSpend());
            statement.setInt(2, record.getCid());
            statement.setString(3, record.getComment());
            statement.setTimestamp(4, record.getDate());

            statement.execute();

            ResultSet set = statement.getGeneratedKeys();
            if(set.next()) {
                record.setId(set.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(RecordEntity record) {
        String sql = "UPDATE record SET spend=?,cid=?,comment=?,date=? WHERE id=?";

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, record.getSpend());
            statement.setInt(2, record.getCid());
            statement.setString(3, record.getComment());
            statement.setTimestamp(4, record.getDate());
            statement.setInt(5, record.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM record WHERE id=?";

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RecordEntity get(int id) {
        RecordEntity record = null;
        String sql = "SELECT * FROM record WHERE id=?";

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                record = new RecordEntity();
                record.setId(id);
                record.setSpend(set.getInt("spend"));
                record.setCid(set.getInt("cid"));
                record.setComment(set.getString("comment"));
                record.setDate(set.getTimestamp("date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return record;
    }

    public List<RecordEntity> list() {

        return list(0, Short.MAX_VALUE);
    }

    public List<RecordEntity> list(int start, int count) {
        List<RecordEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM record ORDER BY id DESC LIMIT ?,?";

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, start);
            statement.setInt(2, count);

            ResultSet set =statement.executeQuery();
            while (set.next()) {
                RecordEntity tmp = new RecordEntity();
                tmp.setId(set.getInt("id"));
                tmp.setSpend(set.getInt("spend"));
                tmp.setCid(set.getInt("cid"));
                tmp.setComment(set.getString("comment"));
                tmp.setDate(set.getTimestamp("date"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int listRecordNum(int cid) {
        String sql = "SELECT COUNT(*) FROM record WHERE cid=?";
        int recordNum = 0;

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, cid);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                recordNum = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recordNum;
    }

    public List<RecordEntity> list(int cid) {
        String sql = "SELECT * FROM record WHERE cid=?";
        List<RecordEntity> list = new ArrayList<>();

        try (Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, cid);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                RecordEntity tmp = new RecordEntity();
                tmp.setId(set.getInt("id"));
                tmp.setSpend(set.getInt("spend"));
                tmp.setCid(set.getInt("cid"));
                tmp.setComment(set.getString("comment"));
                tmp.setDate(set.getTimestamp("date"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<RecordEntity> listToday() {

        return list(DateUtil.today());
    }

    public List<RecordEntity> list(Date date) {
        List<RecordEntity> list = new ArrayList<>();
        Timestamp time = DateUtil.dateToTimestamp(date);

        String sql = "SELECT * FROM record WHERE date=?";

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, time);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                RecordEntity tmp = new RecordEntity();
                tmp.setId(set.getInt("id"));
                tmp.setSpend(set.getInt("spend"));
                tmp.setCid(set.getInt("cid"));
                tmp.setComment(set.getString("comment"));
                tmp.setDate(set.getTimestamp("date"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<RecordEntity> listMonth() {

        return list(DateUtil.monthBegin(), DateUtil.monthEnd());
    }

    public List<RecordEntity> list(Date start, Date end) {
        Timestamp timeStart = DateUtil.dateToTimestamp(start);
        Timestamp timeEnd = DateUtil.dateToTimestamp(end);
        List<RecordEntity> list = new ArrayList<>();

        String sql = "SELECT * FROM record WHERE date>=? AND date<=?";

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, timeStart);
            statement.setTimestamp(2, timeEnd);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                RecordEntity tmp = new RecordEntity();
                tmp.setId(set.getInt("id"));
                tmp.setSpend(set.getInt("spend"));
                tmp.setCid(set.getInt("cid"));
                tmp.setComment(set.getString("comment"));
                tmp.setDate(set.getTimestamp("date"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
