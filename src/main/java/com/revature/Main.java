package com.revature;


import com.revature.database.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.connect();

    }
}