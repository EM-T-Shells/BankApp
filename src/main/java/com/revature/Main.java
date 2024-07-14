package com.revature;

import com.revature.controller.UserController;
import com.revature.controller.AccountController;
import com.revature.repository.SqliteUserDao;
import com.revature.repository.SqliteAccountDao;
import com.revature.service.UserService;
import com.revature.service.AccountService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService(new SqliteUserDao());
        AccountService accountService = new AccountService(new SqliteAccountDao());

        AccountController accountController = new AccountController(scanner, accountService);
        UserController userController = new UserController(scanner, userService, accountController);

        Map<String, String> controlMap = new HashMap<>();
        controlMap.put("Continue Loop", "true");

        while (Boolean.parseBoolean(controlMap.get("Continue Loop"))) {
            userController.promptUserForService(controlMap);
        }

        scanner.close();
    }
}
