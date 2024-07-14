package com.revature.service;

import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.repository.UserDao;

import java.util.List;

public class UserService {

    private UserDao userDao;
    /*
        the service needs to facilitate data between the controller layer and the
        repository layer, so we need to provide a dao to the service so database
        operations can be performed after business and software requirements are
        verified
     */
    public UserService(UserDao userDao){
        /*
            this is a valid assignment whether we use the in-memory option or
            a sql implementation
         */
        this.userDao = userDao;
    }

    // this will be our entrypoint into the UserService registration functionality
    // to make the flow of the logic easier the code is encapsulated in helper methods
    public User validateNewCredentials(User newUserCredentials){
        // 1. check if lengths are correct
        if (checkUsernamePasswordLength(newUserCredentials)){
            // 2. check if username is unique
            if(checkUsernameIsUnique(newUserCredentials)){
                // 3.1 persist user data if valid, reject if not
                return userDao.createUser(newUserCredentials);
                // we return the credentials to the service layer because a new variable is used
                // to confirm the data actually passed into the repository layer and was returned
                // correctly
            }
        }
        // 3.2 inform user of results
        // we can use an exception to return an error message if the credentials are not persisted
        // TODO: handle this exception and make it a custom exception
        throw new RuntimeException("placeholder for custom exception");
    }

    public User checkLoginCredentials(User credentials){
        for (User user : userDao.getAllUsers()){
            // if username and password match return the credentials
            boolean usernameMatches = user.getUsername().equals(credentials.getUsername());
            boolean passwordMatches = user.getPassword().equals(credentials.getPassword());
            if (usernameMatches && passwordMatches){
                return credentials;
            }
        }
        // this exception holds our failure message for the user if their credentials are invalid
        throw new LoginFail("Credentials are invalid: please try again");
    }


    // we will use this method to check the length of the credentials
    private boolean checkUsernamePasswordLength(User newUserCredentials){
        boolean usernameIsValid = newUserCredentials.getUsername().length() <= 30;
        boolean passwordIsValid = newUserCredentials.getPassword().length() <= 30;
        return usernameIsValid && passwordIsValid;
    }

    // we will use this method to make sure the username is unique
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