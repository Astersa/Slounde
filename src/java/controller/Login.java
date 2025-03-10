/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import dao.imp.UserDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra xem người dùng đã đăng nhập chưa
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            // Nếu đã đăng nhập, chuyển hướng đến trang chính
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Kiểm tra dữ liệu đầu vào
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            req.setAttribute("error", "Username and password are required");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }

        try {
            UserDAO userDAO = new UserDAOImpl();

            // Xác thực người dùng
            User user = userDAO.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = req.getSession(true);
                System.out.println(user.getUserId());
                session.setAttribute("userId", user.getUserId());
                System.out.println("Session created: " + session.getId());
                System.out.println("userId set: " + session.getAttribute("userId"));
//                session.setAttribute("username", user.getUsername());
//                session.setAttribute("name", user.getName());

                session.setMaxInactiveInterval(30 * 60);

                String requestedURL = (String) session.getAttribute("requestedURL");
                if (requestedURL != null) {
                    session.removeAttribute("requestedURL"); // Xóa thuộc tính sau khi sử dụng
                    resp.sendRedirect(requestedURL);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/home");
                }
            } else {
                req.setAttribute("error", "Invalid username or password");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
