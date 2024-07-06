package com.revature.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:bankapp.db";

    public Connection connect(){
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established =]. )");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
