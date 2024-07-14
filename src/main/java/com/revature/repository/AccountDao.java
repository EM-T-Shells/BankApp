package com.revature.repository;

import com.revature.entity.Account;

import java.util.Set;

public interface AccountDao {
    Account createAccount(Account newAccount); // Method to create a new account
    Set<Account> getAccountsByUserId(int userId); // Method to retrieve accounts by user ID
    Account getAccountById(int accountId); // Method to retrieve an account by its ID
    boolean updateAccount(Account account); // Method to update an account's details
}
