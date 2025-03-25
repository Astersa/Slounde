/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Song;
import model.SongDTO;

/**
 *
 * @author Astersa
 */
public interface SongDAO {
    List<SongDTO> getAllSongs();

    SongDTO getSongById(int id);

    List<SongDTO> getAllSongByUserId(int userId);

    List<Song> getSongsByArtist(int artistId);

    List<SongDTO> getSongsByAlbumId(int albumId);

    List<Song> getSongsByGenre(int genreId);

    boolean addSong(Song song);

    boolean addLikedSong(int userId, int songId);

    public List<SongDTO> getLikedSongs(int userId);

    boolean updateSong(Song song);

    boolean deleteSong(int id);

    boolean incrementViews(int id);

    void addGenreToSong(int songId, int genreId);

    void removeGenreFromSong(int songId, int genreId);

    List<SongDTO> searchSongs(String searchQuery);

    List<SongDTO> getQueue();
}
