package com.revature;


import com.revature.utils.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        DatabaseManager databaseManager = new DatabaseManager();
        DatabaseManager.connect();

    }
}