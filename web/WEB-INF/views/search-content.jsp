<%-- 
    Document   : home-fix
    Created on : Mar 6, 2025, 12:51:46 PM
    Author     : admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Add your page-specific CSS -->
<link rel="stylesheet" href="css/likedsong.css" />

<div class="container-fluid p-0">
    <div class="row g-0">      
        <!--Liked Songs -->
        <div class="ls-liked-songs">
            <h4 class="ls-section-title">Albums</h4>

            <div class="row" style="margin-top: 1rem;">
                <c:forEach var="album" items="${searchAlbums}">
                    <div class="col-md-4" style="margin-bottom: 1rem;">
                        <div style="display: flex; align-items: center; gap: 1rem;">
                            <div style="width: 40px; height: 40px;">
                                <img src="${not empty album.albumUrl ? album.albumUrl : '#'}" alt="${album.name} Cover" style="width: 100%; height: 100%; object-fit: cover;">
                                <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); display: none;">
                                    <i class="fas fa-play"></i>
                                </div>
                            </div>
                            <div style="display: flex; flex-direction: column; gap: 0.5rem;">
                                <h6><a href="#" style="font-size: 1.2rem; font-weight: 700; color: #fff; text-decoration: none;">${album.name}</a></h6>
                                <p><a href="#" style="color: #b3b3b3; font-size: 0.9rem; text-decoration: none;">${album.artistName}</a></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="row ls-list">
                <h4 class="ls-section-title">Artists</h4>
                <c:forEach var="artist" items="${searchArtists}">
                    <div class="col-md-4" style="margin-bottom: 1rem;">
                        <div style="display: flex; align-items: center; gap: 1rem;">
                            <div style="width: 40px; height: 40px;">
                                <img src="${not empty artist.avatarURL ? artist.avatarURL : '#'}" alt="${artist.name} Avatar" style="width: 100%; height: 100%; object-fit: cover;">
                                <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); display: none;">
                                    <i class="fas fa-play"></i>
                                </div>
                            </div>
                            <div style="display: flex; flex-direction: column; gap: 0.5rem;">
                                <h6><a href="#" style="font-size: 1.2rem; font-weight: 700; color: #fff; text-decoration: none;">${artist.name}</a></h6>
                                <p><a href="#" style="color: #b3b3b3; font-size: 0.9rem; text-decoration: none;">Followers: ${artist.followers}</a></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>    
            </div>

            <!-- Song list -->
            <h4 class="ls-section-title">Songs</h4>
            <c:forEach var="song" items="${searchResults}" varStatus="status">
                <div class="ls-list">
                    <div class="ls-song-item" 
                         data-song-id="${song.id}"
                         data-title="${song.name}"
                         data-artist="${song.artistName}"
                         data-album="${song.albumName}"
                         data-cover="${not empty song.thumbnailUrl ? song.thumbnailUrl : '#'}"
                         data-audio="${not empty song.songUrl ? song.songUrl : '#'}"
                         data-duration="n/a">
                        <span class="ls-track-num">${status.index + 1}</span>
                        <div class="ls-song-info d-flex align-items-center">
                            <div class="ls-album-thumb">
                                <img src="${not empty song.thumbnailUrl ? song.thumbnailUrl : '#'}" alt="Album Cover" class="ls-track-img">
                                <div class="ls-play-overlay">
                                    <i class="fas fa-play"></i>
                                </div>
                            </div>
                            <div class="ls-track-details">
                                <h6><a href="#" class="ls-song-title">${song.name}</a></h6>
                                <p><a href="#" class="ls-artist">${song.artistName}</a></p>
                            </div>
                        </div>
                        <div class="ls-actions">
                            <i class="fas fa-heart"></i>
                            <i class="fas fa-list-ul"></i>
                        </div>
                        <span class="ls-like" style="display: none">${song.like}</span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>



