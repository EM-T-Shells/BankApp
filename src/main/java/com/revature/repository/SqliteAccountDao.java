package com.revature.repository;

import com.revature.entity.Account;
import com.revature.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class SqliteAccountDao implements AccountDao {

    @Override
    public Account createAccount(Account newAccount){
        String sql = "INSERT INTO Accounts (userId, balance) VALUES (?,?)";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, newAccount.getUserId());
            pstmt.setBigDecimal(2, newAccount.getBalance());
            pstmt.executeUpdate();
            return newAccount;

        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public  Set<Account> getAccountsByUserId(int userId){
        Set<Account> accounts = new HashSet<>();
        String sql = "SELECT * FROM Accounts WHERE userId = ?";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Account account = new Account();
                account.setAccountId(rs.getInt("accountId"));
                account.setUserId(rs.getInt("userId"));
                account.setBalance(rs.getBigDecimal("balance"));
                accounts.add(account);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return accounts;
    }


}
