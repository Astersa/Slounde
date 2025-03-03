/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Genre;

/**
 *
 * @author Astersa
 */
public interface GenreDAO {
    List<Genre> getAllGenres();
    Genre getGenreById(int id);
    boolean addGenre(Genre genre);
    boolean updateGenre(Genre genre);
    boolean deleteGenre(int id);
    List<Genre> getGenresBySong(int songId);
}
