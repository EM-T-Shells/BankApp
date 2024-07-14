package com.revature.repository;

import com.revature.entity.Account;
import com.revature.utils.DatabaseManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class SqliteAccountDao implements AccountDao {

    @Override
    public Account createAccount(Account newAccount) {
        String sql = "INSERT INTO CheckingAccounts (user_id, balance) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newAccount.getUserId());
            pstmt.setBigDecimal(2, newAccount.getBalance());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                newAccount.setAccountId(rs.getInt(1));
            }
            return newAccount;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Set<Account> getAccountsByUserId(int userId) {
        Set<Account> accounts = new HashSet<>();
        String sql = "SELECT * FROM CheckingAccounts WHERE user_id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt("id"));
                account.setUserId(rs.getInt("user_id"));
                account.setBalance(rs.getBigDecimal("balance"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return accounts;
    }

    @Override
    public Account getAccountById(int accountId) {
        String sql = "SELECT * FROM CheckingAccounts WHERE id = ?";
        Account account = null;

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getInt("id"));
                account.setUserId(rs.getInt("user_id"));
                account.setBalance(rs.getBigDecimal("balance"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return account;
    }

    @Override
    public boolean updateAccount(Account account) {
        String sql = "UPDATE CheckingAccounts SET balance = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBigDecimal(1, account.getBalance());
            pstmt.setInt(2, account.getAccountId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAccountById(int accountId) {
        String sql = "DELETE FROM CheckingAccounts WHERE id = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


}

