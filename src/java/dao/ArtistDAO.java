/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Artist;

/**
 *
 * @author Astersa
 */
public interface ArtistDAO {
    List<Artist> getAllArtists();

    Artist getArtistById(int id);

    boolean addArtist(Artist artist);

    boolean updateArtist(Artist artist);

    boolean deleteArtist(int id);

    boolean incrementFollowers(int id);

    public List<Artist> getArtistLikedByUserId(int id);

    List<Artist> searchArtists(String searchQuery);
}
