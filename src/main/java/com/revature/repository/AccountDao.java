package com.revature.repository;

import com.revature.entity.Account;

import java.util.Set;

public interface AccountDao {
    Account createAccount(Account newAccount);
    Set<Account> getAccountsByUserId(int userId);
    Account getAccountById(int accountId);
    boolean updateAccount(Account account);
    boolean deleteAccountById(int accountId);
}
