package com.revature.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Account implements Serializable {
    private int accountId;
    private int userId;
    private BigDecimal balance;

    public Account(){}

    public Account(int userId, BigDecimal balance){
        this.userId = userId;
        this.balance = balance;
    }

    public Account(int userId) {
        this.userId = userId;
        this.balance = BigDecimal.ZERO;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getUserId() {
        return userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId &&
                userId == account.userId &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode(){
        return Objects.hash(accountId, userId, balance);
    }

    // toString method for readable representation of the object
    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", userId=" + userId +
                ", balance=" + balance +
                '}';
    }

}
