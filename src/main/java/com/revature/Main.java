package com.revature.main;

import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.repository.SqliteUserDao;
import com.revature.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService(new SqliteUserDao());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the BankApp");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (option == 1) {
                // Register
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                try {
                    User newUser = userService.validateNewCredentials(new User(username, password));
                    System.out.println("Registration successful: " + newUser);
                } catch (RuntimeException e) {
                    System.out.println("Registration failed: " + e.getMessage());
                }
            } else if (option == 2) {
                // Login
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                try {
                    User loggedInUser = userService.checkLoginCredentials(new User(username, password));
                    System.out.println("Login successful: " + loggedInUser);
                } catch (LoginFail e) {
                    System.out.println("Login failed: " + e.getMessage());
                }
            } else if (option == 3) {
                // Exit
                System.out.println("Exiting the application.");
                break;
            } else {
                System.out.println("Invalid option. Please choose again.");
            }
        }
        scanner.close();
    }
}
