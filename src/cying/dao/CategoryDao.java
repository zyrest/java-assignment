package cying.dao;

import cying.entity.CategoryEntity;
import cying.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samson on 17-4-18.
 */
public class CategoryDao {

    public int getTotalNum() {
        int num = 0;
        String sql = "SELECT COUNT(*) FROM category";

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

    public void add(CategoryEntity category) {
        String sql = "INSERT INTO category VALUES(NULL,?)";

        try (Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql) ) {

            statement.setString(1, category.getName());
            statement.execute();

            ResultSet set = statement.getGeneratedKeys();
            if (set.next()) {
               category.setId( set.getInt(1) );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CategoryEntity category) {
        String sql = "UPDATE category SET name=? WHERE id=?";

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql) ) {

            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM category WHERE id=?";

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql) ) {

            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CategoryEntity get(int id) {
        CategoryEntity category = null;
        String sql = "SELECT * FROM category WHERE id=?";

        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql) ) {

            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                category = new CategoryEntity();
                category.setId(id);
                category.setName(set.getString("name"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    public List<CategoryEntity> list() {

        return list(0, Short.MAX_VALUE);
    }

    public List<CategoryEntity> list(int start, int count) {
        List<CategoryEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM category ORDER BY id DESC LIMIT ?,?";


        try (Connection connection = DButil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql) ) {

            statement.setInt(1, start);
            statement.setInt(2, count);

            ResultSet set = statement.executeQuery();
            while(set.next()) {
                CategoryEntity tmp = new CategoryEntity();
                tmp.setId(set.getInt("id"));
                tmp.setName(set.getString("name"));
                list.add(tmp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        CategoryDao dao = new CategoryDao();
        List<CategoryEntity> list = dao.list();

        System.out.println(list.size());
        System.out.println(list.get(0));
    }

}
