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
import model.SongDTO;
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
    public List<SongDTO> getAllSongs() {
        List<SongDTO> songs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT s.*, a.name AS artist_name, NULL AS 'UserLikes', al.Name as albumName, NULL as likedAt\n"
                    + "                    FROM Songs s\n"
                    + "                    JOIN Artists a ON s.artistId = a.id\n"
                    + "                    JOIN Albums al ON s.AlbumId = al.Id\n"
                    + "					LEFT JOIN UserLikesSongs u ON s.Id = u.SongId\n"
                    + "                     ORDER BY s.streams DESC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Song baseSong = extractSongFromResultSet(rs);
                String artistName = rs.getString("artist_name");
                String albumName = rs.getString("albumName");
                String likeAt = rs.getString("likedAt");
                String likeStr = rs.getString("UserLikes");
                boolean like;
                if (likeStr == null) {
                    like = false;
                } else {
                    like = true;
                }
                SongDTO song = new SongDTO(baseSong, artistName, like, albumName, likeAt);
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return songs;
    }

    @Override
    public SongDTO getSongById(int id) {
        SongDTO song = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT s.*, a.name AS artist_name, al.Name as albumName, u.created_at as likedAt, u.UserId AS 'UserLikes'\n"
                    + "                    FROM Songs s\n"
                    + "                    JOIN Artists a ON s.artistId = a.id\n"
                    + "                    JOIN Albums al ON s.AlbumId = al.Id\n"
                    + "					LEFT JOIN UserLikesSongs u ON s.Id = u.SongId \n"
                    + "					WHERE s.id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Song baseSong = extractSongFromResultSet(rs);
                String artistName = rs.getString("artist_name");
                String albumName = rs.getString("albumName");
                String likeAt = rs.getString("likedAt");
                String likeStr = rs.getString("UserLikes");
                boolean like;
                if (likeStr == null) {
                    like = false;
                } else {
                    like = true;
                }
                song = new SongDTO(baseSong, artistName, like, albumName, likeAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return song;
    }

    @Override
    public List<SongDTO> getAllSongByUserId(int userId) {
        System.out.println("userId: " + userId);
        List<SongDTO> listSongByUID = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT s.*, a.name AS artist_name, u.UserId AS 'UserLikes', al.Name as albumName, u.created_at as likedAt\n"
                    + "                                     FROM Songs s\n"
                    + "                                     JOIN Artists a ON s.artistId = a.id\n"
                    + "                                     LEFT JOIN UserLikesSongs u ON s.Id = u.SongId AND u.UserId = ?\n"
                    + "                    			  LEFT JOIN Albums al ON s.AlbumId = al.Id\n"
                    + "                                   ORDER BY s.streams DESC;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Song baseSong = extractSongFromResultSet(rs);
                String artistName = rs.getString("artist_name");
                String albumName = rs.getString("albumName");
                String likeStr = rs.getString("UserLikes");
                String likeAt = rs.getString("likedAt");
                boolean like;
                if (likeStr == null) {
                    like = false;
                } else {
                    like = true;
                }
                SongDTO song = new SongDTO(baseSong, artistName, like, albumName, likeAt);
                listSongByUID.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listSongByUID;
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
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return songs;
    }

    @Override
    public List<SongDTO> getSongsByAlbumId(int albumId) {
        List<SongDTO> songs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT s.*, a.name AS artist_name, al.Name as albumName, u.created_at as likedAt, u.UserId AS 'UserLikes'\n"
                    + "FROM Songs s\n"
                    + "JOIN Artists a ON s.artistId = a.id\n"
                    + "JOIN Albums al ON s.AlbumId = al.Id\n"
                    + "LEFT JOIN UserLikesSongs u ON s.Id = u.SongId AND u.UserId = ?\n"
                    + "WHERE s.albumId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, albumId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Song baseSong = extractSongFromResultSet(rs);
                String artistName = rs.getString("artist_name");
                String albumName = rs.getString("albumName");
                String likeAt = rs.getString("likedAt");
                String likeStr = rs.getString("UserLikes");
                boolean like;
                if (likeStr == null) {
                    like = false;
                } else {
                    like = true;
                }
                SongDTO song = new SongDTO(baseSong, artistName, like, albumName, likeAt);
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
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
            String sql = "SELECT s.* FROM Song s "
                    + "JOIN Song_Genre sg ON s.id = sg.songId "
                    + "WHERE sg.genreId = ?";
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
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return songs;
    }

    // For ADMIN
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
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // For ADMIN
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
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // For ADMIN
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
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
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
            String sql = "UPDATE Songs SET Streams = Streams + 1 WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
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
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
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
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
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
            String sql = "SELECT g.* FROM Genre g "
                    + "JOIN Song_Genre sg ON g.id = sg.genreId "
                    + "WHERE sg.songId = ?";
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
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return genres;
    }

    @Override
    public boolean addLikedSong(int userId, int songId) {
        String checkUserQuery = "SELECT COUNT(*) FROM Users WHERE Id = ?";
        String checkSongQuery = "SELECT COUNT(*) FROM Songs WHERE Id = ?";
        String checkLikedQuery = "SELECT COUNT(*) FROM UserLikesSongs WHERE UserId = ? AND SongId = ?";
        String deleteQuery = "DELETE FROM UserLikesSongs WHERE UserId = ? AND SongId = ?";
        String insertQuery = "INSERT INTO UserLikesSongs (UserId, SongId) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement userStmt = conn.prepareStatement(checkUserQuery);
                PreparedStatement songStmt = conn.prepareStatement(checkSongQuery);
                PreparedStatement checkSongInLike = conn.prepareStatement(checkLikedQuery);
                PreparedStatement deleteSongInLike = conn.prepareStatement(deleteQuery);
                PreparedStatement insertSongInLike = conn.prepareStatement(insertQuery)) {

            // Check if user exists
            userStmt.setInt(1, userId);
            try (ResultSet userRs = userStmt.executeQuery()) {
                userRs.next();
                if (userRs.getInt(1) == 0) {
                    System.out.println("Error: User ID " + userId + " does not exist.");
                    return false;
                }
            }

            // Check if song exists
            songStmt.setInt(1, songId);
            try (ResultSet songRs = songStmt.executeQuery()) {
                songRs.next();
                if (songRs.getInt(1) == 0) {
                    System.out.println("Error: Song ID " + songId + " does not exist.");
                    return false;
                }
            }

            // Check if the song is already liked
            checkSongInLike.setInt(1, userId);
            checkSongInLike.setInt(2, songId);
            try (ResultSet rs = checkSongInLike.executeQuery()) {
                rs.next();
                boolean exists = rs.getInt(1) > 0;

                if (exists) {
                    deleteSongInLike.setInt(1, userId);
                    deleteSongInLike.setInt(2, songId);
                    deleteSongInLike.executeUpdate();
                    System.out.println("Song unliked.");
                    return false;
                } else {
                    insertSongInLike.setInt(1, userId);
                    insertSongInLike.setInt(2, songId);
                    insertSongInLike.executeUpdate();
                    System.out.println("Song liked.");
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // PhuongNDP - 21/3/2025
    @Override
    public List<SongDTO> getLikedSongs(int userId) {
        List<SongDTO> listLikedSongs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT s.*, a.name AS artist_name, u.UserId AS 'UserLikes', al.Name as albumName,  FORMAT(u.created_at, 'dd MMM yyyy', 'en-US') AS likedAt\n"
                    + "FROM Songs s\n"
                    + "JOIN Artists a ON s.artistId = a.id\n"
                    + "JOIN UserLikesSongs u ON s.Id = u.SongId AND u.UserId = ?\n"
                    + "JOIN Albums al ON s.AlbumId = al.Id\n"
                    + "ORDER BY s.streams DESC;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Song baseSong = extractSongFromResultSet(rs);
                String artistName = rs.getString("artist_name");
                String albumName = rs.getString("albumName");
                String likeStr = rs.getString("UserLikes");
                String likeAt = rs.getString("likedAt");
                boolean like;
                if (likeStr == null) {
                    like = false;
                } else {
                    like = true;
                }
                SongDTO song = new SongDTO(baseSong, artistName, like, albumName, likeAt);
                listLikedSongs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listLikedSongs;
    }

    @Override
    public List<SongDTO> searchSongs(String searchQuery) {
        List<SongDTO> songs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT s.*, a.name as artist_name, NULL AS 'UserLikes'\n"
                    + "FROM Songs s\n"
                    + "JOIN Artists a ON s.artistId = a.id\n"
                    + "WHERE s.name LIKE ?\n"
                    + "ORDER BY s.streams DESC;";

            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchQuery + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Song baseSong = extractSongFromResultSet(rs);
                String artistName = rs.getString("artist_name");
                String likeStr = rs.getString("UserLikes");
                boolean like;
                if (likeStr == null) {
                    like = false;
                } else {
                    like = true;
                }
                SongDTO song = new SongDTO(baseSong, artistName, like, "", null);
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    @Override
    public List<SongDTO> getQueue() {
        List<SongDTO> queue = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT s.*, a.name as artist_name, NULL AS 'UserLikes'\n"
                    + "FROM Songs s\n"
                    + "JOIN Artists a ON s.artistId = a.id\n"
                    + "ORDER BY RAND()";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Song baseSong = extractSongFromResultSet(rs);
                String artistName = rs.getString("artist_name");
                String likeStr = rs.getString("UserLikes");
                boolean like;
                if (likeStr == null) {
                    like = false;
                } else {
                    like = true;
                }
                SongDTO song = new SongDTO(baseSong, artistName, false, "", null);
                queue.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    DBUtil.closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return queue;
    }

    // END
    // For checking data getting from database
    public static void main(String[] args) {
        SongDAOImpl e = new SongDAOImpl();
        List<SongDTO> list = e.getLikedSongs(1);
        for (SongDTO songDTO : list) {
            System.out.println(songDTO.toString());
        }
    }
}
