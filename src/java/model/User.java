/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Astersa
 */
public class User {

    private int id;
    private Date dob;
    private String name;
    private String username;
    private String password;
    private int subId;
    private String avatarUrl;
    private String mail;
    private int role;

    // Constructor không tham số
    public User() {
    }

    // Constructor đầy đủ
    public User(int id, Date dob, String name, String username, String password, int subId, String avatarUrl, String mail, int role) {
        this.id = id;
        this.dob = dob;
        this.name = name;
        this.username = username;
        this.password = password;
        this.subId = subId;
        this.avatarUrl = avatarUrl;
        this.mail = mail;
        this.role = role;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", dob=" + dob
                + ", name='" + name + '\''
                + ", username='" + username + '\''
                + ", password='" + password + '\''
                + ", subId=" + subId
                + ", avatarUrl='" + avatarUrl + '\''
                + ", mail='" + mail + '\''
                + ", role=" + role
                + '}';
    }
}
