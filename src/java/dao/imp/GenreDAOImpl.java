/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.imp;

/**
 *
 * @author Astersa
 */
import dao.GenreDAO;
import model.Genre;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenreDAOImpl implements GenreDAO {

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Genre";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Genre genre = extractGenreFromResultSet(rs);
                genres.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return genres;
    }

    @Override
    public Genre getGenreById(int id) {
        Genre genre = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Genre WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                genre = extractGenreFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return genre;
    }

    @Override
    public boolean addGenre(Genre genre) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO Genre (name) VALUES (?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, genre.getName());
            
            int affectedRows = ps.executeUpdate();
            
            if (affectedRows == 0) {
                return false;
            }
            
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                genre.setId(rs.getInt(1));
            }
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateGenre(Genre genre) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE Genre SET name = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, genre.getName());
            ps.setInt(2, genre.getId());
            
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean deleteGenre(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DBUtil.getConnection();
            
            // First delete from Song_Genre table
            String sql1 = "DELETE FROM Song_Genre WHERE genreId = ?";
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            
            // Then delete the genre
            String sql2 = "DELETE FROM Genre WHERE id = ?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Genre> getGenresBySong(int songId) {
        List<Genre> genres = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT g.* FROM Genre g " +
                         "JOIN Song_Genre sg ON g.id = sg.genreId " +
                         "WHERE sg.songId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, songId);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Genre genre = extractGenreFromResultSet(rs);
                genres.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return genres;
    }
    
    // Helper method to extract Genre from ResultSet
    private Genre extractGenreFromResultSet(ResultSet rs) throws SQLException {
        Genre genre = new Genre();
        genre.setId(rs.getInt("id"));
        genre.setName(rs.getString("name"));
        return genre;
    }
}
