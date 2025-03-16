<%-- 
    Document   : home-fix
    Created on : Mar 6, 2025, 12:51:46 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Add your page-specific CSS -->
<link rel="stylesheet" href="css/likedsong.css" />


<div class="container-fluid p-0">
    <div class="row g-0">
        <!-- Main Content -->
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
                    <div class="ls-song-item" 
                         data-song-id="1"
                         data-title="Summer Nights"
                         data-artist="Tropical Beats"
                         data-album="Summer Vibes"
                         data-cover="/api/placeholder/40/40"
                         data-audio="#"
                         data-duration="3:45">
                        <span class="ls-track-num">1</span>
                        <div class="ls-song-info d-flex align-items-center">
                            <img src="/api/placeholder/40/40" alt="Album Cover" class="ls-track-img">
                            <div class="ls-track-details">
                                <h6><a href="#" class="ls-song-title">Summer Nights</a></h6>
                                <p><a href="#" class="ls-artist">Tropical Beats</a></p>
                            </div>
                        </div>
                        <span class="ls-album"><a href="#">Summer Vibes</a></span>
                        <span class="ls-date">Jan 10, 2024</span>
                        <span class="ls-duration">3:45</span>
                    </div>

                    <div class="ls-song-item"
                         data-song-id="2"
                         data-title="Electric Dreams"
                         data-artist="Synthwave Masters"
                         data-album="Retro Future"
                         data-cover="/api/placeholder/40/40"
                         data-audio="#"
                         data-duration="4:20">
                        <span class="ls-track-num">2</span>
                        <div class="ls-song-info d-flex align-items-center">
                            <img src="/api/placeholder/40/40" alt="Album Cover" class="ls-track-img">
                            <div class="ls-track-details">
                                <h6><a href="#" class="ls-song-title">Electric Dreams</a></h6>
                                <p><a href="#" class="ls-artist">Synthwave Masters</a></p>
                            </div>
                        </div>
                        <span class="ls-album"><a href="#">Retro Future</a></span>
                        <span class="ls-date">Feb 5, 2024</span>
                        <span class="ls-duration">4:20</span>
                    </div>

                    <div class="ls-song-item"
                         data-song-id="3"
                         data-title="Acoustic Mornings"
                         data-artist="Folk Collective"
                         data-album="Calm Strings"
                         data-cover="/api/placeholder/40/40"
                         data-audio="#"
                         data-duration="2:55">
                        <span class="ls-track-num">3</span>
                        <div class="ls-song-info d-flex align-items-center">
                            <img src="/api/placeholder/40/40" alt="Album Cover" class="ls-track-img">
                            <div class="ls-track-details">
                                <h6><a href="#" class="ls-song-title">Acoustic Mornings</a></h6>
                                <p><a href="#" class="ls-artist">Folk Collective</a></p>
                            </div>
                        </div>
                        <span class="ls-album"><a href="#">Calm Strings</a></span>
                        <span class="ls-date">Mar 8, 2024</span>
                        <span class="ls-duration">2:55</span>
                    </div>

                    <div class="ls-song-item"
                         data-song-id="4"
                         data-title="Urban Jungle"
                         data-artist="Metro Beats"
                         data-album="City Sounds"
                         data-cover="/api/placeholder/40/40"
                         data-audio="#"
                         data-duration="3:30">
                        <span class="ls-track-num">4</span>
                        <div class="ls-song-info d-flex align-items-center">
                            <img src="/api/placeholder/40/40" alt="Album Cover" class="ls-track-img">
                            <div class="ls-track-details">
                                <h6><a href="#" class="ls-song-title">Urban Jungle</a></h6>
                                <p><a href="#" class="ls-artist">Metro Beats</a></p>
                            </div>
                        </div>
                        <span class="ls-album"><a href="#">City Sounds</a></span>
                        <span class="ls-date">Apr 12, 2024</span>
                        <span class="ls-duration">3:30</span>
                    </div>

                    <div class="ls-song-item"
                         data-song-id="5"
                         data-title="Midnight Jazz"
                         data-artist="Smooth Quartet"
                         data-album="Night Sessions"
                         data-cover="/api/placeholder/40/40"
                         data-audio="#"
                         data-duration="5:10">
                        <span class="ls-track-num">5</span>
                        <div class="ls-song-info d-flex align-items-center">
                            <img src="/api/placeholder/40/40" alt="Album Cover" class="ls-track-img">
                            <div class="ls-track-details">
                                <h6><a href="#" class="ls-song-title">Midnight Jazz</a></h6>
                                <p><a href="#" class="ls-artist">Smooth Quartet</a></p>
                            </div>
                        </div>
                        <span class="ls-album"><a href="#">Night Sessions</a></span>
                        <span class="ls-date">May 21, 2024</span>
                        <span class="ls-duration">5:10</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



