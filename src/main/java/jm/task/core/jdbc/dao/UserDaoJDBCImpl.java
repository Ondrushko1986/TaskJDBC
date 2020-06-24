package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.executor.Executor;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection;
    private final Executor executor;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
        executor = new Executor(connection);
    }

    public Connection getConnection() {
        return connection;
    }


    public void createUsersTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists jdbc_task_users (id bigint auto_increment, name varchar(256), lastName varchar(256), age tinyint, primary key (id))");
        stmt.close();
    }

    public void dropUsersTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS jdbc_task_users");
        stmt.close();
    }

    public User getUserByLastName(String lastName) {
        try {
            return executor.execQuery("select * from jdbc_task_users where lastName='" + lastName + "'", result -> {
                result.next();
                return new User(result.getLong(1), result.getString(2),
                        result.getString(3), result.getByte(4));
            });
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        executor.execUpdate("INSERT INTO jdbc_task_users"
                + " (name, lastName, age) VALUES"
                + " ('" + name + "', '" + lastName + "', " + age + ")");
    }


    public void removeUserById(long id) throws SQLException {
        try (Statement stmt = connection.createStatement();) {
            stmt.execute("DELETE FROM jdbc_task_users WHERE id = id");
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM jdbc_task_users");
            while (result.next()) {
                list.add(new User(result.getLong(1), result.getString(2),
                        result.getString(3), result.getByte(4)));
            }
            return list;
        }

    }

    public void cleanUsersTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM jdbc_task_users");
        stmt.close();
    }
}
