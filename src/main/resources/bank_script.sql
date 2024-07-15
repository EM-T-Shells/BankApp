-- Drop existing tables if they exist
DROP TABLE IF EXISTS CheckingAccounts;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

CREATE TABLE CheckingAccounts (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    balance NUMERIC NOT NULL,
    FOREIGN KEY(user_id) REFERENCES Users(id) ON DELETE CASCADE
);


