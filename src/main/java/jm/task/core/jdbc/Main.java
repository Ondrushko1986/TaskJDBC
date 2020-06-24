package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class

Main {



    public static void main(String[] args) throws SQLException {

        String nameUser1 = "Vasja";
        String nameUser2 = "Kolja";
        String nameUser3 = "Nastja";
        String nameUser4 = "katja";

        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();

        service.saveUser(nameUser1, "Pupkin", (byte) 13);
        System.out.println(nameUser1 + " добавлен в базу данных");

        service.saveUser(nameUser2, "Pupkin", (byte) 14);
        System.out.println(nameUser2 + " добавлен в базу данных");

        service.saveUser(nameUser3, "Pupkin", (byte) 18);
        System.out.println(nameUser3 + " добавлен в базу данных");

        service.saveUser(nameUser4, "Pupkin", (byte) 18);
        System.out.println(nameUser4 + " добавлен в базу данных");

        System.out.println(service.getAllUsers().toString());

        service.cleanUsersTable();
        service.dropUsersTable();


    }
}
