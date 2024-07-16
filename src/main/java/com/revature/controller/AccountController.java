package com.revature.controller;

import com.revature.entity.Account;
import com.revature.service.AccountService;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

public class AccountController {

    private final Scanner scanner;
    private final AccountService accountService;

    public AccountController(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    public void accountMenu(int userId) {
        while (true) {
            System.out.println();
            System.out.println("Account Management Menu:");
            System.out.println();
            System.out.println("1. Open a new checking account");
            System.out.println("2. Close a checking account");
            System.out.println("3. View all accounts");
            System.out.println("q. Quit to main menu");
            System.out.println();
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
                    return;
                default:
                    System.out.println();
                    System.out.println("Invalid option. Please choose again.");
                    System.out.println();
            }
        }
    }

    private void openAccount(int userId) {
//        System.out.println("AccountController: Creating account for userId: " + userId);
        Account newAccount = accountService.openCheckingAccount(userId);
        System.out.println("New account created: " + newAccount);
        System.out.println();
    }

    private void closeAccount(int userId) {
        boolean validInput = false;
        while(!validInput){
            System.out.print("Enter the account ID to close: ");
            String input = scanner.nextLine();
            try{
                int accountId = Integer.parseInt(input);
                boolean isClosed = accountService.closeAccount(accountId);
                validInput=true;
                if (isClosed) {
                    System.out.println("Account with ID " + accountId + " has been closed.");
                    System.out.println();
                } else {
                    System.out.println("Failed to close account with ID " + accountId + ". Ensure the account exists and belongs to the user.");
                    System.out.println();
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid account ID. Please try again.");
            }
        }
    }

    private void viewAccounts(int userId) {
        Set<Account> accounts = accountService.getUserAccounts(userId);
        if (accounts.isEmpty()) {
            System.out.println("No accounts found for user ID " + userId);
        } else {
            accounts.forEach(account -> System.out.println(account));
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Enter the account ID to view details: ");
                try {
                    int accountId = Integer.parseInt(scanner.nextLine());
                    viewAccountDetails(userId, accountId);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid account ID. Please try again.");
                }
            }
        }
    }

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
            System.out.println();
            System.out.println("1. Deposit money");
            System.out.println("2. Withdraw money");
            System.out.println("3. Return to account menu");
//            System.out.println("q. Quit to main menu");
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
                    return;
//                case "q":
//                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void depositMoney(Account account) {
        System.out.print("Enter the amount to deposit: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();

        if (accountService.depositMoney(account.getAccountId(), amount)) {
            System.out.println("Deposited $" + amount + " into account ID " + account.getAccountId() + ".");
        } else {
            System.out.println("Deposit failed.");
        }
    }

    private void withdrawMoney(Account account) {
        System.out.print("Enter the amount to withdraw: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();

        if (accountService.withdrawMoney(account.getAccountId(), amount)) {
            System.out.println("Withdrew " + amount + " from account ID " + account.getAccountId() + ".");
        } else {
            System.out.println("Withdrawal failed.");
        }
    }
}
