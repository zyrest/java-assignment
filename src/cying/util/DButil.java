package cying.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by samson on 17-4-18.
 */
public class DButil {
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "account";
    static String encoding = "UTF-8";

    static String user = "admin";
    static String password = "admin";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s",
                ip, port, database, encoding);

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        System.out.println(connection.getClass().getName());
    }
}
