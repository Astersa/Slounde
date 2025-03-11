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

/**
 *
 * @author Astersa
 */
import dao.SongDAO;
import dao.imp.SongDAOImpl;
import model.Song;

import java.io.IOException;
import java.util.List;
import util.AuthenticationUtil;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home", ""})
public class HomeServlet extends HttpServlet {
    
    private SongDAO songDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        songDAO = new SongDAOImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean auth = AuthenticationUtil.isAuthenticated(request);
        request.setAttribute("auth", auth);
        
        // Get latest songs for home page
        List<Song> latestSongs = songDAO.getAllSongs();
        
        // Set data as request attributes
        request.setAttribute("latestSongs", latestSongs);
        
        // Forward to home page
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }
}
