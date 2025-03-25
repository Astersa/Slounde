<%-- 
    Document   : home-fix
    Created on : Mar 6, 2025, 12:51:46 PM
    Author     : admin
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Recent Songs -->
<h4 class="section-title">Recent Songs</h4>
<div class="row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4 mb-5">
    <c:forEach var="song" items="${recentlyPlayedSong}" varStatus="status" end="4">
        <div class="col">
            <div class="music-card" 
                 data-song-id="${song.id}"
                 data-cover="${not empty song.thumbnailUrl ? song.thumbnailUrl : '#'}"
                 data-audio="${not empty song.songUrl ? song.songUrl : '#'}">
                <span class="card-label">${song.streams} streams</span>
                <img src="${not empty song.thumbnailUrl ? song.thumbnailUrl : '#'}" alt="Song Cover" class="track-image">
                <h6 class="mb-1">${song.name}</h6>
                <p class="text-secondary mb-0">${song.artistName}</p>
                <span class="islike" style="display: none">${song.like}</span>
            </div>
        </div>
    </c:forEach></br>
    <div style="margin-left: 30px">${loginToSeeContent}</div>
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
                <span class="islike" style="display: none">${song.like}</span>
            </div>
        </div>
    </c:forEach>
</div>

<!-- Top Albums -->
<h4 class="section-title">Top Albums</h4>
<div class="row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4 mb-5">
    <c:forEach var="album" items="${topAlbums}">
        <div class="col">
            <a href="album?albumId=${album.id}" data-spa="true" style="text-decoration: none; color: inherit;">
            <div class="album-card" data-album-id="${album.id}">
                <span class="card-label">${album.likes} likes</span>
                <img src="${album.albumUrl != null ? album.albumUrl : '#'}" alt="Album Cover" class="track-image">
                <h6 class="mb-1">${album.name}</h6>
                <p class="text-secondary mb-0">${album.artistName}</p>
            </div>
            </a>
        </div>
    </c:forEach>
</div>

