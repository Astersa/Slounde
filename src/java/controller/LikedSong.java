/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SongDAO;
import dao.imp.SongDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nanhp
 */
@WebServlet(name = "LikedSong", urlPatterns = { "/likedsong" })
public class LikedSong extends HttpServlet {

    private SongDAO songDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        songDAO = new SongDAOImpl();
        String songId = req.getParameter("songId");
        String isLiked = req.getParameter("isLiked");
        Integer userId = (Integer) session.getAttribute("userId");

        try {
            songDAO.addLikedSong(Integer.parseInt(songId), userId);
            System.out.println("success");
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);

            PrintWriter out = resp.getWriter();
            if (Boolean.parseBoolean(isLiked)) {
                out.write("Song liked successfully");
            } else {
                out.write("Song unliked successfully");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            PrintWriter out = resp.getWriter();
            out.write("Error liking song: " + e.getMessage());
        }
    }

}