<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Add CSS link -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/player-bar.css">

<!-- Player Bar -->
<div class="player-bar fixed-bottom">
    <div class="row align-items-center">
        <!-- Currently Playing -->
        
        <div class="col-md-3 d-flex align-items-center">
            <img src="#" alt="Now Playing" class="now-playing-image now-playing-animation">
            <div>
                <h6 class="mb-0">Waiting for song...</h6>
                <p class="text-secondary mb-0">Enjoy the music</p>
            </div>
            <button class="btn btn-link text-secondary ms-3 like-button">
                <i class="far fa-heart"></i>
            </button>
        </div>

        <!-- Player Controls -->
        <div class="col-md-6 slider-container">
            <div class="player-controls d-flex flex-column">
                <div class="d-flex align-items-center justify-content-center mb-2">
                    <!-- <button class="control-btn random-track">
                        <i class="fas fa-random"></i>
                    </button> -->
                    <button class="control-btn prev-track">
                        <i class="fas fa-step-backward"></i>
                    </button>
                    <button class="play-btn playpause-track">
                        <i class="fas fa-play"></i>
                    </button>
                    <button class="control-btn next-track">
                        <i class="fas fa-step-forward"></i>
                    </button>
                    <!-- <button class="control-btn repeat-track">
                        <i class="fas fa-redo-alt"></i>
                    </button> -->
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
            <div class="position-relative">
                <button class="control-btn queue-btn">
                    <i class="fas fa-list"></i>
                </button>
                <div class="queue-container" style="display: none;">
                    <ul class="queue-list">
                    </ul>
                </div>
            </div>
            <button class="control-btn volume-btn">
                <i class="fas fa-volume-up"></i>
            </button>
            <div>
                <input type="range" min="0" max="100" value="99" class="volume_slider">
            </div>
        </div>
    </div>
</div>