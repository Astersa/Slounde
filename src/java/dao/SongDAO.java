/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Song;

/**
 *
 * @author Astersa
 */
public interface SongDAO {
    List<Song> getAllSongs();
    Song getSongById(int id);
    List<Song> getSongsByArtist(int artistId);
    List<Song> getSongsByAlbum(int albumId);
    List<Song> getSongsByGenre(int genreId);
    boolean addSong(Song song);
    boolean updateSong(Song song);
    boolean deleteSong(int id);
    boolean incrementViews(int id);
    void addGenreToSong(int songId, int genreId);
    void removeGenreFromSong(int songId, int genreId);
}
