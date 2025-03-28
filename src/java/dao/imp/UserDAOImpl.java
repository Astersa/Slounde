/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.imp;

/**
 *
 * @author Astersa
 */
import dao.UserDAO;
import model.Song;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.UserDTO;


public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Users";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                users.add(user);
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

        return users;
    }

     @Override
    public UserDTO getUserById(int id) {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT u.*, \n"
                    + "       COUNT(DISTINCT ul.songId) AS total_liked_songs, \n"
                    + "       COUNT(DISTINCT ufa.artistId) AS total_followed_artists\n"
                    + "FROM Users u\n"
                    + "LEFT JOIN UserLikesSongs ul ON u.id = ul.userId\n"
                    + "LEFT JOIN UserFollowsArtists ufa ON u.id = ufa.userId\n"
                    + "WHERE u.id = ?\n"
                    + "GROUP BY u.id,u.DoB, u.Name, u.Username, u.Password, u.AvatarUrl, u.Role, u.SubId, u.Mail";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = extractUserDTOFromResultSet(rs);
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

        return user;
    }


    @Override
    public User getUserByUsername(String username) {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Users WHERE username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = extractUserFromResultSet(rs);
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

        return user;
    }

    @Override
    public boolean addUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO Users (DoB, Name, Username, Password, SubId) "
                    + "VALUES (?, ?, ?, ?, 1);";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dobString = formatter.format(user.getDob());

            ps.setString(1, dobString);
            ps.setString(2, user.getName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                return false;
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

    @Override
    public boolean updateUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE Users SET name = ?, username = ?, password = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword()); // In production, use password hashing!
            ps.setInt(4, user.getId());

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
    public boolean deleteUser(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();

            // First delete from User_Like_Song table
            String sql1 = "DELETE FROM UserLikes WHERE userId = ?";
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

            // Then delete the user
            String sql2 = "DELETE FROM Users WHERE id = ?";
            ps = conn.prepareStatement(sql2);
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
    public boolean validateUser(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password); // In production, use password hashing!

            rs = ps.executeQuery();
            return rs.next(); // If a row is returned, credentials are valid
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

    @Override
    public void likeSong(int userId, int songId) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO UserLikes (userId, songId) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, songId);

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
    public void unlikeSong(int userId, int songId) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM UserLikes WHERE userId = ? AND songId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, songId);

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
    public List<Song> getLikedSongs(int userId) {
        List<Song> likedSongs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT s.* FROM Songs s "
                    + "JOIN UserLikes uls ON s.id = uls.songId "
                    + "WHERE uls.userId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Song song = new Song();
                song.setId(rs.getInt("id"));
                song.setName(rs.getString("name"));
                song.setStreams(rs.getInt("streams"));
                song.setSongUrl(rs.getString("songUrl"));
                song.setAlbumId(rs.getInt("albumId"));
                song.setArtistId(rs.getInt("artistId"));
                likedSongs.add(song);
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

        return likedSongs;
    }

    // Helper method to extract User from ResultSet
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User(rs.getInt("Id"),
                rs.getDate("DoB"), 
                rs.getString("Name"), 
                rs.getString("Username"), 
                rs.getString("Password"), 
                rs.getInt("SubId"),
                rs.getString("AvatarUrl"), 
                rs.getString("Mail"), 
                rs.getInt("Role"));
        return user;
    }
    
        private UserDTO extractUserDTOFromResultSet(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO(rs.getInt("Id"),
                rs.getDate("DoB"),
                rs.getString("Name"),
                rs.getString("Username"),
                rs.getString("Password"),
                rs.getInt("SubId"),
                rs.getString("AvatarUrl"),
                rs.getString("Mail"),
                rs.getInt("Role"),
                rs.getInt("total_liked_songs"),
                rs.getInt("total_followed_artists"));
        return user;
    }

    public static void main(String[] args) {
        UserDAO ud = new UserDAOImpl();
        User u = ud.getUserById(1);
        System.out.println(u.toString());
    }
}
