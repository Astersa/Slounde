/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.imp;

/**
 *
 * @author Astersa
 */

import dao.SongDAO;
import model.Song;
import model.SongWithArtist;
import model.Genre;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDAOImpl implements SongDAO {

    @Override
    public List<SongWithArtist> getAllSongs() {
        List<SongWithArtist> songs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT s.*, a.name as artist_name FROM Songs s " +
                    "JOIN Artists a ON s.artistId = a.id " +
                    "ORDER BY s.streams DESC ";
//                   + "LIMIT 5";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Song baseSong = extractSongFromResultSet(rs);
                String artistName = rs.getString("artist_name");
                SongWithArtist song = new SongWithArtist(baseSong, artistName);
                songs.add(song);
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

        return songs;
    }

    @Override
    public Song getSongById(int id) {
        Song song = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Songs WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                song = extractSongFromResultSet(rs);
                song.setGenres(getGenresForSong(conn, id));
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

        return song;
    }

    @Override
    public List<Song> getSongsByArtist(int artistId) {
        List<Song> songs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Song WHERE artistId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, artistId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Song song = extractSongFromResultSet(rs);
                songs.add(song);
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

        return songs;
    }

    @Override
    public List<Song> getSongsByAlbum(int albumId) {
        List<Song> songs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Song WHERE albumId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, albumId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Song song = extractSongFromResultSet(rs);
                songs.add(song);
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

        return songs;
    }

    @Override
    public List<Song> getSongsByGenre(int genreId) {
        List<Song> songs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT s.* FROM Song s " +
                    "JOIN Song_Genre sg ON s.id = sg.songId " +
                    "WHERE sg.genreId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, genreId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Song song = extractSongFromResultSet(rs);
                songs.add(song);
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

        return songs;
    }

    @Override
    public boolean addSong(Song song) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO Song (name, view, url, albumId, artistId) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, song.getName());
            // ps.setInt(2, song.getView());
            // ps.setString(3, song.getUrl());
            ps.setInt(4, song.getAlbumId());
            ps.setInt(5, song.getArtistId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                song.setId(rs.getInt(1));
            }

            // Add genres if available
            if (song.getGenres() != null && !song.getGenres().isEmpty()) {
                for (Genre genre : song.getGenres()) {
                    addGenreToSong(song.getId(), genre.getId());
                }
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
    public boolean updateSong(Song song) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE Song SET name = ?, view = ?, url = ?, albumId = ?, artistId = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, song.getName());
            ps.setInt(2, song.getLikes());
            ps.setString(3, song.getSongUrl());
            ps.setInt(4, song.getAlbumId());
            ps.setInt(5, song.getArtistId());
            ps.setInt(6, song.getId());

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
    public boolean deleteSong(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();

            // First delete from junction tables
            String sql1 = "DELETE FROM Song_Genre WHERE songId = ?";
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

            String sql2 = "DELETE FROM User_Like_Song WHERE songId = ?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

            // Then delete the song
            String sql3 = "DELETE FROM Song WHERE id = ?";
            ps = conn.prepareStatement(sql3);
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

    @Override
    public boolean incrementViews(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE Song SET view = view + 1 WHERE id = ?";
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

    @Override
    public void addGenreToSong(int songId, int genreId) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO Song_Genre (songId, genreId) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, songId);
            ps.setInt(2, genreId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
    public void removeGenreFromSong(int songId, int genreId) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM Song_Genre WHERE songId = ? AND genreId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, songId);
            ps.setInt(2, genreId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

    // Helper method to extract Song from ResultSet
    private Song extractSongFromResultSet(ResultSet rs) throws SQLException {
        Song song = new Song();
        song.setId(rs.getInt("id"));
        song.setName(rs.getString("name"));
        song.setStreams(rs.getInt("streams"));
        song.setLikes(rs.getInt("likes"));
        song.setSongUrl(rs.getString("songUrl"));
        song.setThumbnailUrl(rs.getString("thumbnailUrl"));
        song.setAlbumId(rs.getInt("albumId"));
        song.setArtistId(rs.getInt("artistId"));
        return song;
    }

    // Helper method to get genres for a song
    private List<Genre> getGenresForSong(Connection conn, int songId) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT g.* FROM Genre g " +
                    "JOIN Song_Genre sg ON g.id = sg.genreId " +
                    "WHERE sg.songId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, songId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getInt("id"));
                genre.setName(rs.getString("name"));
                genres.add(genre);
            }
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
        }

        return genres;
    }
}
