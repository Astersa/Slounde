<%-- 
    Document   : home-fix
    Created on : Mar 6, 2025, 12:51:46 PM
    Author     : admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Add your page-specific CSS -->
<link rel="stylesheet" href="css/likedsong.css" />


<div class="container-fluid p-0">
    <div class="row g-0">      
        <!-- Album Info Section -->
        <div class="album-info-section mb-4">
            <div class="row align-items-center">
                <div class="col-md-3">
                    <div class="album-cover">
                        <img src="${not empty album.albumUrl ? album.albumUrl : '#'}" alt="${album.name}" class="img-fluid rounded">
                    </div>
                </div>
                <div class="col-md-9">
                    <h1 class="album-title">${album.name}</h1>
                    <p class="album-artist">${album.artistName}</p>
                    <div class="album-meta">
                        <span class="song-count">${fn:length(albumSongs)} songs</span>
                    </div>
                </div>
            </div>
        </div>
        
        <h4 class="ls-section-title">Songs in ${album.name}</h4>
        <div class="ls-liked-songs">
            <!-- Column headers -->
            <div class="ls-music-header">
                <div class="ls-music-row">
                    <span class="ls-column-header">#</span>
                    <span class="ls-column-header">Name</span>
                    <span class="ls-column-header">Album</span>
                    <span class="ls-column-header">Date Added</span>
                    <span class="ls-column-header">Duration</span>
                </div>
            </div>

            <c:forEach var="song" items="${albumSongs}" varStatus="status">
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
                        <span class="ls-album"><a href="#">${song.albumName}</a></span>
                        <span class="ls-date">${song.likeAt}</span>
                        <span class="ls-duration">3:45</span>
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
<script src="js/script.js">
    console.log("${album}");

</script>



