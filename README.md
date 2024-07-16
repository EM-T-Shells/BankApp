# Bank Application

## Overview

BankApp is a simple console-based bank application built with Java and SQLite. The application allows users to register, log in, and manage their checking accounts, including opening new accounts, viewing account details, depositing and withdrawing money, and closing accounts.

## Features

- **User Registration**: Users can register with a unique username and password.
- **User Login**: Existing users can securely log in with their credentials.
- **Account Management**:
   - Open a new checking account.
   - View all accounts associated with the logged-in user.
   - Deposit money into an account.
   - Withdraw money from an account.
   - Close an existing account.
- **Data Persistence**: All user and account information is stored in a SQLite database.

## Requirements

- Java Development Kit (JDK) 11 or higher
- SQLite
- Maven (for dependency management)
- IntelliJ IDEA or any other Java IDE

## Setup

1. **Clone the repository**:
   ```sh
   git clone https://github.com/em-t-shells/BankApp.git
   ```

2. **Navigate to the project directory**:
   ```sh
   cd BankApp
   ```

3. **Install dependencies**:
   ```sh
   mvn clean install
   ```

4. **Set up the database**:
   - The database schema will be initialized via the `DatabaseScriptRunner` class, which will run the SQL script `bank_setup_reset_script.sql` located in the `src/main/resources` directory.

5. **Run the application**:
   - **Using IntelliJ IDEA**:
      1. Open IntelliJ IDEA.
      2. Open the project by selecting the `BankApp` directory.
      3. In the Project view, navigate to `src/main/java/com/revature/Main.java`.
      4. Right-click on `Main.java` and select `Run 'Main.main()'`.
   - **Using Maven**:
     ```sh
     mvn exec:java -Dexec.mainClass="com.revature.Main"
     ```

## Usage

Once the application is running, you will see a main menu with options to register, log in, or exit the application.

1. **Register**:
   - Choose option `1` to register a new user.
   - Enter a unique username and password (both should not exceed 30 characters).

2. **Login**:
   - Choose option `2` to log in with existing credentials.
   - Enter your username and password.

3. **Account Management**:
   - After logging in, you will be presented with the account management menu.
   - Choose from options to open a new account, view all accounts, deposit money, withdraw money, or close an account.

4. **Exit**:
   - Choose option `q` to exit the application.

## Directory Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── revature/
│   │           ├── controller/
│   │           │   ├── AccountController.java
│   │           │   └── UserController.java
│   │           ├── entity/
│   │           │   ├── Account.java
│   │           │   └── User.java
│   │           ├── exception/
│   │           │   ├── InvalidLength.java
│   │           │   ├── LoginFail.java
│   │           │   └── UserAlreadyExists.java
│   │           ├── repository/
│   │           │   ├── AccountDao.java
│   │           │   ├── SqliteAccountDao.java
│   │           │   ├── SqliteUserDao.java
│   │           │   └── UserDao.java
│   │           ├── service/
│   │           │   ├── AccountService.java
│   │           │   └── UserService.java
│   │           ├── utils/
│   │           │   ├── DatabaseManager.java
│   │           │   └── DatabaseScriptRunner.java
│   │           └── Main.java
│   └── resources/
│       └── bank_setup_reset_script.sql
└── test/
    └── java/
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Create a new Pull Request

## License

This project is licensed under the MIT License. See the LICENSE file for details.