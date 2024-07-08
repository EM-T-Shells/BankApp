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
                    // Create our connection object in our try-with-resources block
                    Connection connection = DatabaseManager.connect();
                    // Create a Stream that has our SQL lines saved as String data
                    Stream<String> lines = Files.lines(sqlPath)
            ) {
                // By setting auto commit to false we can execute multiple statements
                // and then commit them all together, ensuring all data or no data
                // is updated
                connection.setAutoCommit(false);
                StringBuilder sqlBuilder = new StringBuilder();
                // Loop through our stream and append each line of the file
                // to the StringBuilder object
                lines.forEach(line -> sqlBuilder.append(line).append("\n"));
                String sql = sqlBuilder.toString();
                // Split the SQL into individual statements
                // The \\R character is a more robust newline indicator
                // This means new line, carriage return, and one or two other
                // characters are referenced by it
                String[] statements = sql.split(";\\R");

                // Execute each statement
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
