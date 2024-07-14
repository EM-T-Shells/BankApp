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

    public Account openCheckingAccount(int userId, BigDecimal balance){
        Account newAccount = new Account(userId, balance);
        return accountDao.createAccount(newAccount);
    }


    public Set<Account> getUsersAccounts(int userId){
        return accountDao.getAccountsByUserId(userId);
    }
}
