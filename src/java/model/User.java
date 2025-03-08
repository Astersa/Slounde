/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;

/**
 *
 * @author Astersa
 */


public class User {
    private int userId;
    private Date dob; 
    private String name;
    private String username;
    private String password;
    private int subId;

    // Constructor
    public User(int userId, Date dob, String name, String username, String password, int subId) {
        this.userId = userId;
        this.dob = dob;
        this.name = name;
        this.username = username;
        this.password = password;
        this.subId = subId;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    // ToString Method
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", dob=" + dob +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", subId=" + subId +
                '}';
    }
}


