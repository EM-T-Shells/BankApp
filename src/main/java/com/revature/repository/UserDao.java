package com.revature.repository;

import com.revature.entity.User;

import java.util.List;

/*
    A Dao (Data Access Object) is a class that is meant to be used for facilitating direct
    interaction with your data persistence of choice. We will use this Dao to define
    what we want the User data persistence to look like, and then have implementation
    classes actually hold the logic of those actions
 */
public interface UserDao {

    /*
        Something to keep in mind: when coding to the interface (setting the type
        of an object to an interface it implements) the object that is created will
        have access to its custom fields but NOT its custom methods (non-override methods).
        If you want an action to be performed via method it must be declared in the interface
     */
    User createUser(User newUserCredentials);
    List<User> getAllUsers();
}
