package com.revature.repository;

import com.revature.entity.User;
import com.revature.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDao implements UserDao {

    @Override
    public User createUser(User newUserCredentials){
        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, newUserCredentials.getUsername());
            pstmt.setString(2, newUserCredentials.getPassword());
            pstmt.executeUpdate();
            return newUserCredentials;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection connection = DatabaseManager.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}