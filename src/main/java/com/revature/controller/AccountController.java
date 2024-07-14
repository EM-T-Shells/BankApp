package com.revature.controller;

import com.revature.entity.Account;
import com.revature.service.AccountService;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

public class AccountController {

    private final Scanner scanner;
    private final AccountService accountService;

    // Constructor to initialize Scanner and AccountService
    public AccountController(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    // Method to display account management options to the user
    public void accountMenu(int userId) {
        while (true) {
            System.out.println("Account Management Menu:");
            System.out.println("1. Open a new account");
            System.out.println("2. Close an existing account");
            System.out.println("3. View all accounts");
            System.out.println("q. Quit to main menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    openAccount(userId);
                    break;
                case "2":
                    closeAccount(userId);
                    break;
                case "3":
                    viewAccounts(userId);
                    break;
                case "q":
                    return; // Exit to main menu
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    // Method to open a new checking account
    private void openAccount(int userId) {
        Account newAccount = accountService.openCheckingAccount(userId);
        System.out.println("New account created: " + newAccount);
    }

    // Method to close an existing checking account
    private void closeAccount(int userId) {
        System.out.print("Enter the account ID to close: ");
        int accountId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Implement logic to close account (not provided in AccountService yet)
        // For now, we'll just print a placeholder message
        System.out.println("Account with ID " + accountId + " has been closed. (placeholder)");
    }

    // Method to view all accounts for the logged-in user
    private void viewAccounts(int userId) {
        Set<Account> accounts = accountService.getUserAccounts(userId);
        if (accounts.isEmpty()) {
            System.out.println("No accounts found for user ID " + userId);
        } else {
            accounts.forEach(account -> System.out.println(account));
            System.out.print("Enter the account ID to view details: ");
            int accountId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            viewAccountDetails(userId, accountId);
        }
    }

    // Method to view details of a specific account
    private void viewAccountDetails(int userId, int accountId) {
        while (true) {
            Account account = accountService.getAccountById(userId, accountId);
            if (account == null) {
                System.out.println("Account not found or does not belong to the user.");
                return;
            }
            System.out.println("Account Details:");
            System.out.println("Account ID: " + account.getAccountId());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("1. Deposit money");
            System.out.println("2. Withdraw money");
            System.out.println("3. Return to account menu");
            System.out.println("q. Quit to main menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    depositMoney(account);
                    break;
                case "2":
                    withdrawMoney(account);
                    break;
                case "3":
                    return; // Return to account menu
                case "q":
                    return; // Quit to main menu
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    // Method to deposit money into an account
    private void depositMoney(Account account) {
        System.out.print("Enter the amount to deposit: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline

        if (accountService.depositMoney(account.getAccountId(), amount)) {
            System.out.println("Deposited " + amount + " into account ID " + account.getAccountId() + ".");
        } else {
            System.out.println("Deposit failed.");
        }
    }

    // Method to withdraw money from an account
    private void withdrawMoney(Account account) {
        System.out.print("Enter the amount to withdraw: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline

        if (accountService.withdrawMoney(account.getAccountId(), amount)) {
            System.out.println("Withdrew " + amount + " from account ID " + account.getAccountId() + ".");
        } else {
            System.out.println("Withdrawal failed.");
        }
    }
}
