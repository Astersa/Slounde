<%-- 
    Document   : home-fix
    Created on : Mar 6, 2025, 12:51:46 PM
    Author     : admin
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Search Bar -->
<div class="search-container">
    <i class="fas fa-search"></i>
    <input type="text" class="search-input" placeholder="Search for artists, songs, or podcasts">
    
    <% Integer userId = (Integer) session.getAttribute("userId");
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

<!-- Top Albums -->
<h4 class="section-title">Top Albums</h4>
<div class="row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4 mb-5">
    <c:forEach var="album" items="${topAlbums}">
        <div class="col">
            <div class="music-card" data-album-id="${album.id}">
                <span class="card-label">${album.likes} likes</span>
                <img src="${album.albumUrl != null ? album.albumUrl : '#'}" alt="Album Cover" class="track-image">
                <h6 class="mb-1">${album.name}</h6>
                <p class="text-secondary mb-0">${album.artistName}</p>
            </div>
        </div>
    </c:forEach>
</div>

<!-- Top Songs -->
<h4 class="section-title">Trendding Songs</h4>
<div class="row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4 mb-5">
    <c:forEach var="song" items="${topSongs}">
        <div class="col">
            <div class="music-card" 
                 data-song-id="${song.id}"
                 data-cover="${not empty song.thumbnailUrl ? song.thumbnailUrl : '#'}"
                 data-audio="${not empty song.songUrl ? song.songUrl : '#'}">
                <span class="card-label">${song.streams} streams</span>
                <img src="${not empty song.thumbnailUrl ? song.thumbnailUrl : '#'}" alt="Song Cover" class="track-image">
                <h6 class="mb-1">${song.name}</h6>
                <p class="text-secondary mb-0">${song.artistName}</p>
            </div>
        </div>
    </c:forEach>
</div>

