/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class UserDTO extends User{
    private int totalLikeSong;
    private int totalFollowArtist;

    public UserDTO(int totalLikeSong, int totalFollowArtist) {
        this.totalLikeSong = totalLikeSong;
        this.totalFollowArtist = totalFollowArtist;
    }

    public UserDTO(int id, Date dob, String name, String username, String password, int subId, String avatarUrl, String mail, int role, int totalLikeSong, int totalFollowArtist) {
        super(id, dob, name, username, password, subId, avatarUrl, mail, role);
        this.totalLikeSong = totalLikeSong;
        this.totalFollowArtist = totalFollowArtist;
    }

    public int getTotalLikeSong() {
        return totalLikeSong;
    }

    public void setTotalLikeSong(int totalLikeSong) {
        this.totalLikeSong = totalLikeSong;
    }

    public int getTotalFollowArtist() {
        return totalFollowArtist;
    }

    public void setTotalFollowArtist(int totalFollowArtist) {
        this.totalFollowArtist = totalFollowArtist;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "totalLikeSong=" + totalLikeSong + ", totalFollowArtist=" + totalFollowArtist + '}';
    }
    
    
}
