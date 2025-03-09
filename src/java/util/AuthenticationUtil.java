/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Astersa
 */
public class AuthenticationUtil {
    /**
     * Kiểm tra xem người dùng đã đăng nhập chưa
     * @param request HttpServletRequest
     * @return true nếu người dùng đã đăng nhập, false nếu chưa
     */
    public static boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null && session.getAttribute("userId") != null);
    }
    
    /**
     * Lấy userId của người dùng đã đăng nhập
     * @param request HttpServletRequest
     * @return userId của người dùng đã đăng nhập, hoặc -1 nếu chưa đăng nhập
     */
    public static int getAuthenticatedUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            return (Integer) session.getAttribute("userId");
        }
        return -1;
    }
    
    /**
     * Đăng xuất người dùng
     * @param request HttpServletRequest
     */
    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
