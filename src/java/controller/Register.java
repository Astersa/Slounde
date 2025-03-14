/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import dao.imp.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String name = req.getParameter("name");
        String dobString = req.getParameter("dob");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String mail = req.getParameter("mail");
        String confirmPassword = req.getParameter("confirmPassword");

        // Kiểm tra dữ liệu
        if (name == null || name.trim().isEmpty()
                || dobString == null || dobString.trim().isEmpty()
                || mail == null || mail.trim().isEmpty()
                || username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || confirmPassword == null || confirmPassword.trim().isEmpty()) {
            System.out.println(name + dobString + mail + username + password + confirmPassword);
            req.setAttribute("error", "All fields are required");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra xác nhận mật khẩu
        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Passwords do not match");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        try {
            // Chuyển đổi chuỗi ngày tháng thành đối tượng Date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = formatter.parse(dobString);

            // Kiểm tra username đã tồn tại chưa
            UserDAO userDAO = new UserDAOImpl(); // Tạo implementation của UserDAO
            User existingUser = userDAO.getUserByUsername(username);

            if (existingUser != null) {
                req.setAttribute("error", "Username already exists");
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
                return;
            }

            // Tạo đối tượng User mới
            // Gán userId = 0 vì sẽ được database tự tạo
            // SubId mặc định là 1 (như trong phương thức addUser)
            User newUser = new User(0, dob, name, username, password, 1, "#", mail, 1);

            // Thêm user vào database
            boolean success = userDAO.addUser(newUser);

            if (success) {
                // Đăng ký thành công, chuyển hướng đến trang đăng nhập
                resp.sendRedirect(req.getContextPath() + "/login");
            } else {
                // Đăng ký thất bại
                req.setAttribute("error", "Registration failed. Please try again.");
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            }

        } catch (ParseException e) {
            req.setAttribute("error", "Invalid date format");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred. Please try again.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }

}
