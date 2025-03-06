/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Album;

/**
 *
 * @author Astersa
 */
public interface AlbumDAO {
     List<Album> getAllAlbums();
    Album getAlbumById(int id);
    List<Album> getAlbumsByArtist(int artistId);
    boolean addAlbum(Album album);
    boolean updateAlbum(Album album);
    boolean deleteAlbum(int id);
}
