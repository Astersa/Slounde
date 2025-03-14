<%-- 
    Document   : home-fix
    Created on : Mar 6, 2025, 12:51:46 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sloude - Music Streaming</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <div class="container-fluid p-0">
            <div class="row g-0">
                <% if (request.getAttribute("auth") != null) { %>
                <% Integer userId = (Integer) session.getAttribute("userId");
                
                %>
                <div>
                    <%= request.getAttribute("auth") %>
                    <%= userId %>
                </div>
                <% } %>  

                <!-- Sidebar -->
                <div class="col-lg-2 col-md-3 sidebar" id="sidebar">
                    <div class="d-flex justify-content-center mb-4">
                        <a href="home" style="text-decoration: none">
                            <h4 class="logo">SLOUDE</h4>
                        </a>
                    </div>
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">
                                <i class="fas fa-home"></i> Home
                            </a>
                        </li>
                        <!--                        <li class="nav-item">
                                                    <a class="nav-link" href="#">
                                                        <i class="fas fa-search"></i> Search
                                                    </a>
                                                </li>-->
                        <!--                        <li class="nav-item">
                                                    <a class="nav-link" href="#">
                                                        <i class="fas fa-bookmark"></i> Library
                                                    </a>
                                                </li>-->


                        <!--Nếu sau khi đăng nhập, có userId, khi chọn trang likedsong sẽ ko cần yêu cầu login-->
                        <% Integer userId = (Integer) session.getAttribute("userId");
                        if (userId == null) {%>
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
                            <a class="nav-link" href="likedsong">
                                <i class="fas fa-heart"></i> Liked Songs
                            </a>
                        </li>
                        <%}%>

                        <!--                        <li class="nav-item">
                                                    <a class="nav-link" href="#">
                                                        <i class="fas fa-podcast"></i> Podcasts
                                                    </a>
                                                </li>-->
                    </ul>

                    <div class="mt-4">
                        <div class="d-flex justify-content-between align-items-center px-3 mb-2">
                            <span class="text-secondary">YOUR PLAYLISTS</span>
                            <button class="btn btn-link text-secondary p-0">
                                <i class="fas fa-plus"></i>
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Main Content -->
                <div class="col-lg-10 col-md-9 ms-auto main-content">
                    <!-- Search Bar -->
                    <div class="search-container">
                        <i class="fas fa-search"></i>
                        <input type="text" class="search-input" placeholder="Search for artists, songs, or podcasts">
                        
                        
                        <%if (userId == null) {%>
                        <!-- User Profile Button -->
                        <div class="user-profile-container">
                            <div class="user-profile-button" id="userProfileButton">
                                <img src="#" alt="">
                            </div>
                            <!-- User Dropdown Menu (Hidden by default) -->
                            <div class="user-dropdown" id="userDropdown">
                                <a href="login">
                                    <div class="user-dropdown-item">
                                        Log in
                                    </div>
                                </a>

                                <a href="register">
                                    <div class="user-dropdown-item">
                                        Register
                                    </div>  
                                </a>

                            </div>
                        </div>
                        <%} else {%>
                        <!-- User Profile Button -->
                        <div class="user-profile-container">
                            <div class="user-profile-button" id="userProfileButton">
                                <img src="#" alt="">
                            </div>
                            <!-- User Dropdown Menu (Hidden by default) -->
                            <div class="user-dropdown" id="userDropdown">
                                <a href="user">
                                    <div class="user-dropdown-item">
                                        Profile
                                    </div>
                                </a>

                                <a href="logout">
                                    <div class="user-dropdown-item">
                                        Log out
                                    </div>  
                                </a>

                            </div>
                        </div>
                        <%}%>
                    </div>

                    <!-- Genre Pills -->
                    <div class="mb-4">
                        <span class="genre-pill">All</span>
                        <span class="genre-pill">Pop</span>
                        <span class="genre-pill">Rock</span>
                        <span class="genre-pill">Hip Hop</span>
                        <span class="genre-pill">Electronic</span>
                        <span class="genre-pill">Jazz</span>
                        <span class="genre-pill">R&B</span>
                        <span class="genre-pill">Classical</span>
                    </div>



                    <!-- Recently Played -->
                    <h4 class="section-title">Recently Played</h4>
                    <div class="row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4 mb-5">
                        <div class="col">
                            <div class="music-card" data-audio="#">
                                <span class="card-label">New</span>
                                <img src="https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/8/c/1/6/8c166e2b9a0e45ca9a6c7bef40a81f74.jpg
                                     " alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Mất Kết Nối</h6>
                                <p class="text-secondary mb-0">Dương Domic</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card featured" data-audio="#">
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Electric Dreams</h6>
                                <p class="text-secondary mb-0">Synthwave Masters</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card" data-audio="#">
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Acoustic Mornings</h6>
                                <p class="text-secondary mb-0">Folk Collective</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card" data-audio="#">
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Urban Jungle</h6>
                                <p class="text-secondary mb-0">Metro Beats</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card" data-audio="#">
                                <span class="card-label">Hot</span>
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Midnight Jazz</h6>
                                <p class="text-secondary mb-0">Smooth Quartet</p>
                            </div>
                        </div>
                    </div>

                    <!-- Made For You -->
                    <h4 class="section-title">Made For You</h4>
                    <div class="row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4 mb-5">
                        <div class="col">
                            <div class="music-card featured" data-audio="#">
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Daily Mix 1</h6>
                                <p class="text-secondary mb-0">Based on your listening</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card" data-audio="https://dl.dropboxusercontent.com/s/lq3h7ncyeix68zo/ThapRoiTuDo.mp3?st=7hg6thf3&dl=0">
                                <span class="card-label">Updated</span>
                                <img src="assets/thumb/thaproitudo.jpg" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Tháp Rơi Tự Do</h6>
                                <p class="text-secondary mb-0">LBI</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card" data-audio="https://dl.dropboxusercontent.com/s/nr5294u399kh7vg/TetOiTetA.mp3?st=pkcbktdk&dl=0">
                                <img src="assets/thumb/tetoiteta.jpg" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Tết Ơi Tết À</h6>
                                <p class="text-secondary mb-0">Đức Phúc</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card featured" data-audio="#">
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Mood Booster</h6>
                                <p class="text-secondary mb-0">Feel good playlist</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card" data-audio="#">
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Time Capsule</h6>
                                <p class="text-secondary mb-0">Songs from your past</p>
                            </div>
                        </div>
                    </div>

                    <!-- Trending Now -->
                    <h4 class="section-title">Trending Now</h4>
                    <div class="row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4">
                        <div class="col">
                            <div class="music-card featured" data-audio="#">
                                <span class="card-label">Trending</span>
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Top Hits 2025</h6>
                                <p class="text-secondary mb-0">Today's biggest hits</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card" data-audio="#">
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Dance Vibes</h6>
                                <p class="text-secondary mb-0">Electronic Dance Music</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card" data-audio="#">
                                <span class="card-label">Fresh</span>
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Hip Hop Central</h6>
                                <p class="text-secondary mb-0">Hottest rap tracks</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card" data-audio="#">
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Rock Classics</h6>
                                <p class="text-secondary mb-0">Timeless rock anthems</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card featured" data-audio="#">
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Indie Discoveries</h6>
                                <p class="text-secondary mb-0">Fresh indie selections</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Player Bar -->
            <div class="player-bar fixed-bottom">
                <div class="row align-items-center">
                    <!-- Currently Playing -->
                    <div class="col-md-3 d-flex align-items-center">
                        <img src="#" alt="Now Playing" class="now-playing-image now-playing-animation">
                        <div>
                            <h6 class="mb-0">Midnight City</h6>
                            <p class="text-secondary mb-0">M83</p>
                        </div>
                        <button class="btn btn-link text-secondary ms-3">
                            <i class="far fa-heart"></i>
                        </button>
                    </div>

                    <!-- Player Controls -->

                    <div class="col-md-6 slider-container">
                        <div class="player-controls d-flex flex-column">
                            <div class="d-flex align-items-center justify-content-center mb-2">

                                <button class="control-btn random-track">
                                    <i class="fas fa-random"></i>
                                </button>

                                <button class="control-btn prev-track">
                                    <i class="fas fa-step-backward"></i>
                                </button>

                                <button class="play-btn playpause-track">
                                    <i class="fas fa-play"></i>
                                </button>

                                <button class="control-btn next-track">
                                    <i class="fas fa-step-forward"></i>
                                </button>

                                <button class="control-btn repeat-track">
                                    <i class="fas fa-redo-alt"></i>
                                </button>
                            </div>
                            <div class="d-flex align-items-center">
                                <span class="text-secondary small">00:00</span>
                                <div class="progress-container">
                                    <input type="range" min="1" max="100" value="0" id="seek_slider">
                                </div>
                                <span class="text-secondary small">00:00</span>
                            </div>
                        </div>
                    </div>

                    <!-- Volume Controls -->
                    <div class="col-md-3 d-flex justify-content-end align-items-center slider-container">
                        <button class="control-btn">
                            <i class="fas fa-list"></i>
                        </button>
                        <button class="control-btn">
                            <i class="fas fa-volume-up"></i>
                        </button>
                        <div>
                            <input type="range" min="0" max="100" value="99" class="volume_slider">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Overlay for mobile -->
        <div class="overlay" id="overlay"></div>

        <!-- Thẻ <audio> để phát nhạc -->
        <audio id="audio-player" controls hidden></audio>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="js/script.js"></script>
        <script src="js/user-script.js"></script>
    </body>
</html>
