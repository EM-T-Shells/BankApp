package com.revature;

import com.revature.controller.UserController;
import com.revature.repository.SqliteUserDao;
import com.revature.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(new SqliteUserDao());
        UserController userController = new UserController(scanner, userService);
        Map<String, String> controlMap = new HashMap<>();
        controlMap.put("Continue Loop", "true");

        // Main loop to keep the application running
        while (Boolean.parseBoolean(controlMap.get("Continue Loop"))) {
            userController.promptUserForService(controlMap);
        }

        scanner.close();
    }
}
