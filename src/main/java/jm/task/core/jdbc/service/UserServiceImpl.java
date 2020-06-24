package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
    }

    private static UserDaoJDBCImpl getUserDaoJDBCImpl() {
        return new UserDaoJDBCImpl(getMysqlConnection());
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("mytestdb?").          //db name
                    append("user=root&").          //login
                    append("password=root").      //password
                    append("&serverTimezone=UTC");   //setup server time


            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }


    public void createUsersTable() throws SQLException {
        UserDaoJDBCImpl dao = getUserDaoJDBCImpl();
        dao.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        UserDaoJDBCImpl dao = getUserDaoJDBCImpl();
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        UserDaoJDBCImpl dao = getUserDaoJDBCImpl();
        dao.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) throws SQLException {
        UserDaoJDBCImpl dao = getUserDaoJDBCImpl();
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return getUserDaoJDBCImpl().getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        UserDaoJDBCImpl dao = getUserDaoJDBCImpl();
        dao.cleanUsersTable();
    }
}
