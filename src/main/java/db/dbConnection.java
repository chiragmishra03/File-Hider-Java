package db;

import java.sql.*;

public class dbConnection {
    public static Connection connection;
    private static final String url = "jdbc:mysql://127.0.0.1:3306/fileHider";
    private static final String username = "";
    private static final String pass = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, pass);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return connection;
    }

    public static void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
