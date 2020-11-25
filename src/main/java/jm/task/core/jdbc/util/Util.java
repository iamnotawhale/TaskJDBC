package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost/userbase?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "C3por2d2c3po";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connection successful");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection not successful");
        }
        return connection;
    }
}
