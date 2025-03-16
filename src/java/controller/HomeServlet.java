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
import model.Song;
import model.SongWithArtist;
import model.Album;
import model.AlbumWithArtist;
import java.io.IOException;
import java.util.List;
import util.AuthenticationUtil;

/**
 *
 * @author Astersa
 */
@WebServlet(name = "HomeServlet", urlPatterns = { "/home", "", "/likesong" })
public class HomeServlet extends HttpServlet {

    private SongDAO songDAO;
    private AlbumDAO albumDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        songDAO = new SongDAOImpl();
        albumDAO = new AlbumDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean auth = AuthenticationUtil.isAuthenticated(request);
        request.setAttribute("auth", auth);

        // Get the current servlet path to determine operation
        String operation = request.getServletPath();

        // Get top songs sorted by streams
        List<SongWithArtist> topSongs = songDAO.getAllSongs();

        // Get top albums sorted by likes
        List<AlbumWithArtist> topAlbums = albumDAO.getAllAlbums();

        // Set data as request attributes
        request.setAttribute("topSongs", topSongs);
        request.setAttribute("topAlbums", topAlbums);

        // Set the content JSP based on the operation
        String contentJsp;
        switch (operation) {
            case "/home":
                contentJsp = "/WEB-INF/views/home-content.jsp";
                break;
            case "":
                contentJsp = "/WEB-INF/views/home-content.jsp";
                break;
            case "/likesong":
                contentJsp = "/WEB-INF/views/like-song-content.jsp";
                break;
            default:
                contentJsp = "/WEB-INF/views/home-content.jsp"; // Default to home content
        }

        request.setAttribute("contentJsp", contentJsp);

        // Check if this is an AJAX request
        String isAjax = request.getParameter("ajax");
        if (isAjax != null && isAjax.equals("true")) {
            // For AJAX requests, forward directly to the content JSP
            request.getRequestDispatcher(contentJsp).forward(request, response);
        } else {
            // For regular requests, forward to the base template
            request.getRequestDispatcher("/WEB-INF/layout/base.jsp").forward(request, response);
        }
    }
}
