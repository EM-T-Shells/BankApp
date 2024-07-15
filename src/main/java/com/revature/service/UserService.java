package com.revature.service;

import com.revature.entity.User;
import com.revature.exception.InvalidLength;
import com.revature.exception.LoginFail;
import com.revature.exception.UserAlreadyExists;
import com.revature.repository.UserDao;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public User validateNewCredentials(User newUserCredentials){
        if (checkUsernamePasswordLength(newUserCredentials)){
            if(checkUsernameIsUnique(newUserCredentials)){
                User createdUser = userDao.createUser(newUserCredentials);
                System.out.println("UserService: Created user with ID: " + createdUser.getId());
                return createdUser;
            } else {
                throw new UserAlreadyExists("Username already taken.");
            }
        }else {
            throw new InvalidLength("Username or password length invalid.");
        }
    }

    public User checkLoginCredentials(User credentials){
        for (User user : userDao.getAllUsers()){
            boolean usernameMatches = user.getUsername().equals(credentials.getUsername());
            boolean passwordMatches = user.getPassword().equals(credentials.getPassword());
            if (usernameMatches && passwordMatches){
                System.out.println("UserService: Logged in user with ID: " + user.getId());
                return user;
            }
        }
        throw new LoginFail("Credentials are invalid: please try again");
    }

    private boolean checkUsernamePasswordLength(User newUserCredentials){
        boolean usernameIsValid = newUserCredentials.getUsername().length() <= 30;
        boolean passwordIsValid = newUserCredentials.getPassword().length() <= 30;
        return usernameIsValid && passwordIsValid;
    }

    private boolean checkUsernameIsUnique(User newUserCredentials){
        boolean usernameIsUnique = true;
        List<User> users = userDao.getAllUsers();
        for(User user : users){
            if(newUserCredentials.getUsername().equals(user.getUsername())){
                usernameIsUnique = false;
                break;
            }
        }
        return usernameIsUnique;
    }

}