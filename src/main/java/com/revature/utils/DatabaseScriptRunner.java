package com.revature.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public class DatabaseScriptRunner {

    public static void main(String[] args) {
        // Path to the SQL script file
        Path sqlPath = Paths.get("src/main/resources/bank_setup_reset_script.sql");

        try {
            try (
                    Connection connection = DatabaseManager.connect();
                    Stream<String> lines = Files.lines(sqlPath)
            ) {
                connection.setAutoCommit(false);
                StringBuilder sqlBuilder = new StringBuilder();
                lines.forEach(line -> sqlBuilder.append(line).append("\n"));
                String sql = sqlBuilder.toString();
                String[] statements = sql.split(";\\R");

                for (String statement : statements) {
                    if (!statement.trim().isEmpty()) {
                        try (Statement stmt = connection.createStatement()) {
                            stmt.executeUpdate(statement);
                        }
                    }
                }
                connection.commit();
                System.out.println("Database script executed successfully.");
            }
        } catch (SQLException | IOException exception) {
            System.out.println("Failed to execute database script: " + exception.getMessage());
        }
    }
}
