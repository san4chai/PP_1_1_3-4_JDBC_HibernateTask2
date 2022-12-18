package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
//        userService.dropUsersTable();
//
//       userService.createUsersTable();

//        userService.saveUser("Ivan", "Petrov", (byte) 25);
//        userService.saveUser("Petr", "Ivanov", (byte) 28);
//        userService.saveUser("Oleg", "Sidorov", (byte) 30);
//        userService.saveUser("Fedor", "Petrov", (byte) 32);
//        userService.saveUser("User1", "LUser1", (byte) 50);
//        userService.saveUser("User2", "LUser2", (byte) 47);
        userService.removeUserById(2);
        userService.getAllUsers();
//
//          userService.cleanUsersTable();
    }
}
