<%-- 
    Document   : user
    Created on : Mar 12, 2025, 11:25:04 PM
    Author     : phuongndp
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<link rel="stylesheet" href="css/user.css" />
<div class="u-profile-section">
    <div class="u-profile-header">
        <div class="u-avatar-container">
            <img src="assets/thumb/tetoiteta.jpg" class="avatar">
            <div class="u-avatar-edit">
                <i class="fas fa-camera"></i>
            </div>
        </div>
        <div class="u-profile-info">
            <h1>${user.name}</h1>
            <div class="u-profile-stats">
                <div class="u-stat-item">
                    <span>${user.totalFollowArtist}</span> Following
                </div>
                <div class="u-stat-item">
                    <span>${user.totalLikeSong}</span> Liked Songs
                </div>
            </div>
        </div>
        <!--<button class="u-edit-button" id="toggleEditMode">Edit Profile</button>-->
    </div>

    <!-- Profile form - initially hidden -->
    <div class="u-profile-form" id="u-profileForm" style="display: none;">
        <div class="u-form-group">
            <label for="name">Your name: </label>
            <input type="text" id="username" value="${user.name}">
        </div>
        <div class="u-form-group">
            <label for="email">Email: </label>
            <input type="email" id="email" value="${user.mail}">
        </div>
        <div class="u-form-group">
            <label for="dob">Date of Birth: </label>
            <input type="date" id="dob" value="${user.dob}">
        </div>
        <div class="u-form-actions">
            <button class="cancel-button" id="cancelEdit">Cancel</button>
            <button class="save-button" id="saveProfile">Save Changes</button>
        </div>
    </div>
</div>

<div class="u-section-title">
    <h2>Artists You Follow</h2>
    <a href="#" class="view-all">View all</a>
</div>

<div class="u-artist-grid">
    <c:forEach var="a" items="${artists}"> 
        <div class="u-artist-card">
            <img src="${a.avatarURL}" class="u-artist-image">
            <div class="u-artist-name">${a.name}</div>
            <div class="u-artist-genre">${a.followers} followers</div>
            <button class="u-unfollow-button">Unfollow</button>
        </div>
    </c:forEach>
</div>

<div class="u-section-title">
    <h2>Songs You Like</h2>
    <a href="likesong" data-spa="true" class="view-all">View all</a>
</div>

<div class="u-artist-grid">
    <c:forEach var="song" items="${likedSongs}" end="4"> 
        <div class="u-song-card" 
             data-song-id="${song.id}"
             data-title="${song.name}"
             data-artist="${song.artistName}"
             data-album="${song.albumName}"
             data-cover="${not empty song.thumbnailUrl ? song.thumbnailUrl : '#'}"
             data-audio="${not empty song.songUrl ? song.songUrl : '#'}"
             data-duration="n/a">
            <img src="${not empty song.thumbnailUrl ? song.thumbnailUrl : '#'}" class="u-artist-image">
            <div class="u-artist-name">${song.name}</div>
            <div class="u-artist-genre">${song.streams} streams</div>
        </div>
    </c:forEach>
</div>



