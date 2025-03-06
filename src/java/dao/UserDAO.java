/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Song;
import model.User;

/**
 *
 * @author Astersa
 */
public interface UserDAO {
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByUsername(String username);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    boolean validateUser(String username, String password);
    void likeSong(int userId, int songId);
    void unlikeSong(int userId, int songId);
    List<Song> getLikedSongs(int userId);
}
