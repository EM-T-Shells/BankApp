package com.revature.controller;

import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.service.UserService;

import java.util.Map;
import java.util.Scanner;

public class UserController {

    private final Scanner scanner;
    private final UserService userService;

    // Constructor to initialize scanner and userService
    public UserController(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    // Entry point method for the application
    public void promptUserForService(Map<String, String> controlMap) {
        System.out.println("Welcome to the BankApp");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("q. Exit");
        System.out.print("Choose an option: ");
        try {
            String userActionIndicated = scanner.nextLine();
            switch (userActionIndicated) {
                case "1":
                    registerNewUser();
                    break;
                case "2":
                    controlMap.put("User", login().getUsername());
                    break;
                case "q":
                    System.out.println("Goodbye!");
                    controlMap.put("Continue Loop", "false");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } catch (LoginFail exception) {
            System.out.println(exception.getMessage());
        }
    }

    // Method to handle new user registration
    public void registerNewUser() {
        User newCredentials = getUserCredentials();
        User newUser = userService.validateNewCredentials(newCredentials);
        System.out.printf("New account created: %s%n", newUser);
    }

    // Method to handle user login
    public User login() {
        return userService.checkLoginCredentials(getUserCredentials());
    }

    // Helper method to get user credentials from the console
    public User getUserCredentials() {
        System.out.print("Please enter a username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Please enter a password: ");
        String newPassword = scanner.nextLine();
        return new User(newUsername, newPassword);
    }
}
