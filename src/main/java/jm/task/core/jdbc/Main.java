package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Велком", "Миранчук", (byte)21);
        userService.saveUser("Джамбулат", "Миранчук", (byte)28);
        userService.saveUser("Алексей", "Миранчук", (byte)22);
        userService.saveUser("Антон", "Миранчук", (byte)26);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
