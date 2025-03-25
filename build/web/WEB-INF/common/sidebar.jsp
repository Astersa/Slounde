<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Sidebar -->
<div class="col-lg-2 col-md-3 sidebar" id="sidebar">
    <div class="d-flex justify-content-center mb-4 logo-zone">
        <a href="home" data-spa="true" style="text-decoration: none">
            <h4 class="logo">SLOUDE</h4>
        </a>
    </div>

    <ul class="nav flex-column">
        <% Integer userId = (Integer) session.getAttribute("userId");
           if (userId == null) {%>
        <li class="nav-item">
            <a class="nav-link" href="login">
                <i class="fas fa-user"></i> Login Here
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="home" data-spa="true">
                <i class="fas fa-home"></i> Home
            </a>
        </li>

        <!--Nếu sau khi đăng nhập, có userId, khi chọn trang likedsong sẽ ko cần yêu cầu login-->
        <% if (userId == null) {%>
        <li class="nav-item" onclick="openPopup()"> <!-- Kiểm tra khi click -->
            <a class="nav-link" href="#">
                <i class="fas fa-heart"></i> Liked Songs
            </a>
        </li>

        <!-- Popup -->
        <div class="overlay" id="overlay"></div>
        <div class="popup" id="popup">
            <h5>You must login to see your liked songs list.</h5>
            <div class="popup-buttons">
                <a href="login" class="btn-login">Login</a>
                <a href="#" class="btn-cancel" onclick="closePopup()">Cancel</a>
            </div>
        </div>
        <%} else {%>
        <li class="nav-item"> 
            <a class="nav-link" href="likesong" data-spa="true">
                <i class="fas fa-heart"></i> Liked Songs
            </a>
        </li>

        <div class="mt-4 playlist-zone">
            <div class="d-flex justify-content-between align-items-center px-3 mb-2">
                <span class="text-secondary">YOUR PLAYLISTS</span>
                <button class="btn btn-link text-secondary p-0">
                    <i class="fas fa-plus"></i>
                </button>
            </div>
            <div class="playlist-item">✨ Chill Vibes</div>
            <div class="playlist-item">🔥 Workout Mix</div>
            <div class="playlist-item">🚗 Road Trip</div>
            <div class="playlist-item">💻 Focus Flow</div>
            <div class="playlist-item">🎉 Party Hits</div>
            <div class="playlist-item">🎸 90s Nostalgia</div>
            <div class="playlist-item">🎻 Acoustic Sessions</div>
        </div>
        <%}%>

        <%} else {%>
        <li class="nav-item">
            <a class="nav-link" href="user" data-spa="true">
                <i class="fas fa-user"></i> Profile
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="home" data-spa="true">
                <i class="fas fa-home"></i> Home
            </a>
        </li>

        <li class="nav-item"> 
            <a class="nav-link" href="likesong" data-spa="true">
                <i class="fas fa-heart"></i> Liked Songs
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="logout">
                <i class="fas fa-sign-out-alt"></i> Log Out
            </a>
        </li>

        <%}%>
    </ul>

</div>