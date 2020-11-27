package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    private Connection connection = getConnection();
    private Statement statement = null;
    private List<User> userList = new LinkedList();
    private static final String SQL_INSERT = "INSERT INTO usertable (name, lastName, age) VALUES (?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM usertable WHERE id=?";
    private Long id = 1L;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS usertable " +
                    "(id BIGINT UNSIGNED NOT NULL auto_increment, " +
                    " name VARCHAR(30), " +
                    " lastName VARCHAR(30), " +
                    " age TINYINT, " +
                    " PRIMARY KEY (id))";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS usertable ";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            int rows = preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, id);
            int row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try {
            userList.clear();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, lastName, age FROM usertable");
            while (rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                Byte age = rs.getByte("age");

                User user = new User(name, lastName, age);

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            statement = connection.createStatement();
            String sql = "TRUNCATE usertable ";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
