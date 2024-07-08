package com.revature.entity;

import java.io.Serializable;
import java.util.Objects;

/*
    This class is designed as a Java Bean: this is a design pattern that follows standard
    naming and development practices:
        - the class implements Serializable, which allows it to be turned into a byte stream
          by Java
        - all fields of the class are private and have public getter and setter methods
          to access and manipulate them
        - a no-args constructor is present
        - equals() and hashCode() are overridden from the base Object class
 */

public class User implements Serializable {

    private String username;
    private String password;

    public User(){}

    // technically Java Beans just have a no-args constructor, but we are including
    // this one for convenience
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
