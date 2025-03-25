/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.SongDAO;
import dao.AlbumDAO;
import dao.imp.SongDAOImpl;
import dao.imp.AlbumDAOImpl;
import jakarta.servlet.http.HttpSession;
import model.SongDTO;
import model.AlbumWithArtist;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import util.AuthenticationUtil;
import com.google.gson.Gson;
import dao.ArtistDAO;
import dao.UserDAO;
import dao.imp.ArtistDAOImpl;
import dao.imp.UserDAOImpl;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import model.Artist;
import model.User;

/**
 *
 * @author Astersa
 */
@WebServlet(name = "HomeServlet", urlPatterns = { "/home", "/user", "/likesong", "/search", "/album" })
public class HomeServlet extends HttpServlet {

    private SongDAO songDAO;
    private AlbumDAO albumDAO;
    private ArtistDAO artistDAO;
    UserDAO userDAO = new UserDAOImpl();

    Queue<SongDTO> recentSongQueue = new LinkedList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        songDAO = new SongDAOImpl();
        albumDAO = new AlbumDAOImpl();
        artistDAO = new ArtistDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean auth = AuthenticationUtil.isAuthenticated(request);
        request.setAttribute("auth", auth);
        // Lay duong dan luu vao bien operation
        String operation = request.getServletPath();
        // Lay session tu request
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // PhuongNPD 21/2/2025
        List<SongDTO> likedSongs = new ArrayList<>();
        List<SongDTO> topSongs = new ArrayList<>();

        if (userId == null) {
            // Get top songs sorted by streams
            topSongs = songDAO.getAllSongs();
        } else {
            topSongs = songDAO.getAllSongByUserId(userId);
            likedSongs = songDAO.getLikedSongs(userId);
        }

        // Get top albums sorted by likes
        List<AlbumWithArtist> topAlbums = albumDAO.getAllAlbums();

        // Set data as request attributes
        request.setAttribute("topSongs", topSongs);
        request.setAttribute("topAlbums", topAlbums);
        request.setAttribute("likedSongs", likedSongs);

        List<SongDTO> recentSongList = new ArrayList<>();
        if (userId == null) {
            recentSongQueue.clear();
            request.setAttribute("loginToSeeContent", "Login to see content.");
        } else {
            recentSongList = new ArrayList<>(recentSongQueue);
            Collections.reverse(recentSongList);
        }
        request.setAttribute("recentlyPlayedSong", recentSongList);

        // Set the content JSP based on the operation
        String contentJsp;
        switch (operation) {
            case "/home":
                contentJsp = "/WEB-INF/views/home-content.jsp";
                break;
            case "/likesong":
                contentJsp = "/WEB-INF/views/like-song-content.jsp";
                break;
            case "/user":
                if (userId != null) {
                    User user = userDAO.getUserById(userId);
                    request.setAttribute("user", user);
                }

                if (userId != null) {
                    List<Artist> artistBeFollowed = artistDAO.getArtistLikedByUserId(userId);
                    request.setAttribute("artists", artistBeFollowed);
                }
                contentJsp = "/WEB-INF/views/user.jsp";
                break;
            case "/search":
                List<SongDTO> songs = search(request.getParameter("searchQuery"));
                List<AlbumWithArtist> albums = searchAlbum(request.getParameter("searchQuery"));
                List<Artist> artists = searchArtist(request.getParameter("searchQuery"));
                request.setAttribute("searchResults", songs);
                request.setAttribute("searchAlbums", albums);
                request.setAttribute("searchArtists", artists);
                System.out.println(artists);
                contentJsp = "/WEB-INF/views/search-content.jsp";
                break;
            case "/album":
                int albumId = Integer.parseInt(request.getParameter("albumId"));
                AlbumWithArtist album = albumDAO.getAlbumById(albumId);
                List<SongDTO> albumSongs = songDAO.getSongsByAlbumId(albumId);
                request.setAttribute("album", album);
                request.setAttribute("albumSongs", albumSongs);
                contentJsp = "/WEB-INF/views/album-content.jsp";
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/home");
                return;
        }

        request.setAttribute("contentJsp", contentJsp);

        String isAjax = request.getParameter("ajax");
        if (isAjax != null && isAjax.equals("true")) {
            request.getRequestDispatcher(contentJsp).forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/layout/base.jsp").forward(request, response);
        }
    }

    public List<SongDTO> search(String searchQuery)
            throws ServletException, IOException {
        List<SongDTO> songs = songDAO.searchSongs(searchQuery);
        return songs;
    }

    public List<AlbumWithArtist> searchAlbum(String searchQuery) {
        List<AlbumWithArtist> albums = albumDAO.searchAlbums(searchQuery);
        return albums;
    }

    public List<Artist> searchArtist(String searchQuery) {
        List<Artist> artists = artistDAO.searchArtists(searchQuery);
        return artists;
    }

    public void likeSong(String songId, String isLiked, Integer userId, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (userId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        boolean result = songDAO.addLikedSong(userId, Integer.parseInt(songId));
        out.write(String.valueOf(result));
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        if (Boolean.parseBoolean(isLiked)) {
            out.write("Song liked successfully");
        } else {
            out.write("Song unliked successfully");
        }
    }

    public void listen(String songId, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try {
            int id = Integer.parseInt(songId);
            boolean result = songDAO.incrementViews(id);
            if (result) {
                out.write("true");
            } else {
                out.write("false");
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("false");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        String operation = request.getParameter("operation");
        System.out.println(operation);
        switch (operation) {
            case "likesong":
                if (userId == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                String songId = request.getParameter("songId");
                String isLiked = request.getParameter("isLiked");

                likeSong(songId, isLiked, userId, response);
                break;
            case "listen":
                String Id = request.getParameter("songId");

                listen(Id, response);
                break;
            case "getSong":
                try {
                    int id = Integer.parseInt(request.getParameter("songId"));
                    SongDTO song = songDAO.getSongById(id);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    Gson gson = new Gson();
                    out.write(gson.toJson(song));
                    response.setStatus(HttpServletResponse.SC_OK);
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    PrintWriter out = response.getWriter();
                    out.write("Error fetching song");
                }
                break;
            case "getQueue":
                try {
                    List<SongDTO> queue = songDAO.getQueue();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    Gson gson = new Gson();
                    String json = gson.toJson(queue);
                    PrintWriter out = response.getWriter();
                    out.write(json);
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    PrintWriter out = response.getWriter();
                    out.write("Error fetching queue");
                }
                break;
            case "currentSong":
                String songIdsString = request.getParameter("id");

                if (songIdsString == null || songIdsString.isEmpty()) {
                    response.getWriter().write("No song available!");
                    return;
                }

                String[] songIds = songIdsString.split(",");

                for (String id : songIds) {
                    try {
                        int recSongId = Integer.parseInt(id.trim());
                        SongDTO song = songDAO.getSongById(recSongId);

                        if (song != null) {
                            recentSongQueue.removeIf(s -> s.getId() == song.getId());

                            recentSongQueue.add(song);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Lỗi parse ID: " + id);
                    }
                }

                // In ra danh sách bài hát trong queue
                for (SongDTO songDTO : recentSongQueue) {
                    System.out.println(songDTO);
                }
                break;
        }
    }

}
