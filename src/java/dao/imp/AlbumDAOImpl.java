/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.imp;

/**
 *
 * @author Astersa
 */
import dao.AlbumDAO;
import model.Album;
import model.AlbumWithArtist;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAOImpl implements AlbumDAO {

    @Override
    public List<AlbumWithArtist> getAllAlbums() {
        List<AlbumWithArtist> albums = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT a.*, ar.name as artist_name FROM Albums a " +
                    "JOIN Artists ar ON a.artistId = ar.id " +
                    "ORDER BY a.likes DESC";
//                    + "LIMIT 5";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Album baseAlbum = extractAlbumFromResultSet(rs);
                String artistName = rs.getString("artist_name");
                AlbumWithArtist album = new AlbumWithArtist(baseAlbum, artistName);
                albums.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null)
                    DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return albums;
    }

    @Override
    public Album getAlbumById(int id) {
        Album album = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Albums WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                album = extractAlbumFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null)
                    DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return album;
    }

    @Override
    public List<Album> getAlbumsByArtist(int artistId) {
        List<Album> albums = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Album WHERE artistId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, artistId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Album album = extractAlbumFromResultSet(rs);
                albums.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null)
                    DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return albums;
    }

    @Override
    public boolean addAlbum(Album album) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO Album (name, artistId) VALUES (?, ?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, album.getName());
            ps.setInt(2, album.getArtistId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                album.setId(rs.getInt(1));
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null)
                    DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateAlbum(Album album) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE Album SET name = ?, artistId = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, album.getName());
            ps.setInt(2, album.getArtistId());
            ps.setInt(3, album.getId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean deleteAlbum(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();

            // Delete associated songs or update them would be handled by the database
            // due to foreign key constraints or should be handled in service layer

            String sql = "DELETE FROM Album WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to extract Album from ResultSet
    private Album extractAlbumFromResultSet(ResultSet rs) throws SQLException {
        Album album = new Album();
        album.setId(rs.getInt("id"));
        album.setName(rs.getString("name"));
        album.setArtistId(rs.getInt("artistId"));
        album.setAlbumUrl(rs.getString("albumUrl"));
        album.setLikes(rs.getInt("likes"));
        return album;
    }
}
