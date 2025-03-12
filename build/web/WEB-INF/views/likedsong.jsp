<%-- 
    Document   : home-fix
    Created on : Mar 6, 2025, 12:51:46 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SLOUDE - Music Streaming</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/likedsong.css" />
    </head>
    <body>
        <div class="container-fluid p-0">
            <div class="row g-0">
                <!-- Sidebar -->
                <div class="col-lg-2 col-md-3 sidebar" id="sidebar">
                    <div class="d-flex justify-content-center mb-4">
                        <a href="home" style="text-decoration: none">
                            <h4 class="logo">SLOUDE</h4>
                        </a>
                    </div>
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link" href="home">
                                <i class="fas fa-home"></i> Home
                            </a>
                        </li>
                        <!--                        <li class="nav-item">
                                                    <a class="nav-link" href="#">
                                                        <i class="fas fa-search"></i> Search
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="#">
                                                        <i class="fas fa-bookmark"></i> Library
                                                    </a>
                                                </li>-->
                        <li class="nav-item">
                            <a class="nav-link active" href="likedsong">
                                <i class="fas fa-heart"></i> Liked Songs
                            </a>
                        </li>
                        <!--                        <li class="nav-item">
                                                    <a class="nav-link" href="#">
                                                        <i class="fas fa-podcast"></i> Podcasts
                                                    </a>
                                                </li>
                                            </ul>-->

                        <div class="mt-4">
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
                </div>

                <!-- Main Content -->
                <div class="col-lg-10 col-md-9 ms-auto main-content">
                    <!-- Search Bar -->
                    <div class="search-container">
                        <i class="fas fa-search"></i>
                        <input type="text" class="search-input" placeholder="Search for artists, songs, or podcasts">


                        <%Integer userId = (Integer) session.getAttribute("userId");
                        if (userId == null) {%>
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

                    <!--Liked Songs -->

                    <h4 class="section-title">Liked Songs</h4>
                    <div class="liked-songs">
                        <!-- Column headers -->
                        <div class="music-header">
                            <div class="music-row">
                                <span class="column-header">#</span>
                                <span class="column-header">Name</span>
                                <span class="column-header">Album</span>
                                <span class="column-header">Date Added</span>
                                <span class="column-header">Duration</span>
                            </div>
                        </div>

                        <!-- Song list -->
                        <div class="list">
                            <div class="music-card">
                                <span class="track-number">1</span>
                                <div class="d-flex align-items-center">
                                    <img src="/api/placeholder/40/40" alt="Album Cover" class="track-image">
                                    <div class="info">
                                        <h6><a href="#">Summer Nights</a></h6>
                                        <p><a href="#">Tropical Beats</a></p>
                                    </div>
                                </div>
                                <span class="album-name"><a href="#">Summer Vibes</a></span>
                                <span class="date-added">Jan 10, 2024</span>
                                <span class="duration">3:45</span>
                            </div>

                            <div class="music-card">
                                <span class="track-number">2</span>
                                <div class="d-flex align-items-center">
                                    <img src="/api/placeholder/40/40" alt="Album Cover" class="track-image">
                                    <div class="info">
                                        <h6><a href="#">Electric Dreams</a></h6>
                                        <p><a href="#">Synthwave Masters</a></p>
                                    </div>
                                </div>
                                <span class="album-name"><a href="#">Retro Future</a></span>
                                <span class="date-added">Feb 5, 2024</span>
                                <span class="duration">4:20</span>
                            </div>

                            <div class="music-card">
                                <span class="track-number">3</span>
                                <div class="d-flex align-items-center">
                                    <img src="/api/placeholder/40/40" alt="Album Cover" class="track-image">
                                    <div class="info">
                                        <h6><a href="#">Acoustic Mornings</a></h6>
                                        <p><a href="#">Folk Collective</a></p>
                                    </div>
                                </div>
                                <span class="album-name"><a href="#">Calm Strings</a></span>
                                <span class="date-added">Mar 8, 2024</span>
                                <span class="duration">2:55</span>
                            </div>

                            <div class="music-card">
                                <span class="track-number">4</span>
                                <div class="d-flex align-items-center">
                                    <img src="/api/placeholder/40/40" alt="Album Cover" class="track-image">
                                    <div class="info">
                                        <h6><a href="#">Urban Jungle</a></h6>
                                        <p><a href="#">Metro Beats</a></p>
                                    </div>
                                </div>
                                <span class="album-name"><a href="#">City Sounds</a></span>
                                <span class="date-added">Apr 12, 2024</span>
                                <span class="duration">3:30</span>
                            </div>

                            <div class="music-card">
                                <span class="track-number">5</span>
                                <div class="d-flex align-items-center">
                                    <img src="/api/placeholder/40/40" alt="Album Cover" class="track-image">
                                    <div class="info">
                                        <h6><a href="#">Midnight Jazz</a></h6>
                                        <p><a href="#">Smooth Quartet</a></p>
                                    </div>
                                </div>
                                <span class="album-name"><a href="#">Night Sessions</a></span>
                                <span class="date-added">May 21, 2024</span>
                                <span class="duration">5:10</span>
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
