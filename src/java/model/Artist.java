/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Astersa
 */
public class Artist {
    private int id;
    private String name;
    private int followers;
    private String avatarURL;

    // Constructors
    public Artist() {
    }

    public Artist(int id, String name, int followers, String avatarURL) {
        this.id = id;
        this.name = name;
        this.followers = followers;
        this.avatarURL = avatarURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    @Override
    public String toString() {
        return "Artist{" + "id=" + id + ", name=" + name + ", followers=" + followers + ", avatarURL=" + avatarURL
                + '}';
    }

}
