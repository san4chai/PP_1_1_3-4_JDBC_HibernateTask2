package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        UserServiceImpl userService = new UserServiceImpl();
        util.getConnection();
//        userService.dropUsersTable();
//
//       userService.createUsersTable();
//
        userService.saveUser("Ivan", "Petrov", (byte) 25);
        userService.saveUser("Petr", "Ivanov", (byte) 28);
        userService.saveUser("Oleg", "Sidorov", (byte) 30);
        userService.saveUser("Fedor", "Petrov", (byte) 32);
//        userService.getAllUsers();
//        userService.removeUserById(4);
//          userService.cleanUsersTable();
    }
}
