package com.revature.service;

import com.revature.entity.Account;
import com.revature.repository.AccountDao;
import com.revature.repository.SqliteAccountDao;

import java.math.BigDecimal;
import java.util.Set;

public class AccountService {
    private final AccountDao accountDao;

    public AccountService() {
        this.accountDao = new SqliteAccountDao();
    }

    public Account openCheckingAccount(int userId) {
        Account newAccount = new Account(userId);
        return accountDao.createAccount(newAccount);
    }

    public Set<Account> getUserAccounts(int userId) {
        return accountDao.getAccountsByUserId(userId);
    }

    public Account getAccountById(int userId, int accountId) {
        Set<Account> accounts = getUserAccounts(userId);
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }

    public boolean depositMoney(int accountId, BigDecimal amount) {
        // Implement logic to deposit money
        // Placeholder logic
        Account account = accountDao.getAccountById(accountId);
        if (account != null) {
            account.setBalance(account.getBalance().add(amount));
            accountDao.updateAccount(account);
            return true;
        }
        return false;
    }

    public boolean withdrawMoney(int accountId, BigDecimal amount) {
        // Implement logic to withdraw money
        // Placeholder logic
        Account account = accountDao.getAccountById(accountId);
        if (account != null) {
            if (account.getBalance().compareTo(amount) >= 0) {
                account.setBalance(account.getBalance().subtract(amount));
                accountDao.updateAccount(account);
                return true;
            } else {
                System.out.println("Insufficient funds.");
            }
        }
        return false;
    }
}
