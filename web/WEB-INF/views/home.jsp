<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>WaveBeats - Music Streaming</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <div class="container-fluid p-0">
            <div class="row g-0">
                <!-- Sidebar -->
                <div class="col-lg-2 col-md-3 sidebar" id="sidebar">
                    <div class="d-flex justify-content-center mb-4">
                        <h4 class="logo">WaveBeats</h4>
                    </div>
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">
                                <i class="fas fa-home"></i> Home
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i class="fas fa-search"></i> Search
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i class="fas fa-bookmark"></i> Library
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i class="fas fa-heart"></i> Liked Songs
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i class="fas fa-podcast"></i> Podcasts
                            </a>
                        </li>
                    </ul>

                    <div class="mt-4">
                        <div class="d-flex justify-content-between align-items-center px-3 mb-2">
                            <span class="text-secondary">YOUR PLAYLISTS</span>
                            <button class="btn btn-link text-secondary p-0">
                                <i class="fas fa-plus"></i>
                            </button>
                        </div>
                        <div class="playlist-item">? Chill Vibes</div>
                        <div class="playlist-item">? Workout Mix</div>
                        <div class="playlist-item">? Road Trip</div>
                        <div class="playlist-item">? Focus Flow</div>
                        <div class="playlist-item">? Party Hits</div>
                        <div class="playlist-item">? 90s Nostalgia</div>
                        <div class="playlist-item">? Acoustic Sessions</div>
                    </div>
                </div>

                <!-- Main Content -->
                <div class="col-lg-10 col-md-9 ms-auto main-content">
                    <!-- Search Bar -->
                    <div class="search-container">
                        <i class="fas fa-search"></i>
                        <input type="text" class="search-input" placeholder="Search for artists, songs, or podcasts">
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
                                <img src="#" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Summer Nights</h6>
                                <p class="text-secondary mb-0">Tropical Beats</p>
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
                            <div class="music-card" data-audio="assets/audio/ThapRoiTuDo.mp3">
                                <span class="card-label">Updated</span>
                                <img src="assets/thumb/thaproitudo.jpg" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">Th�p r?i t? do</h6>
                                <p class="text-secondary mb-0">LIBI</p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="music-card" data-audio="assets/audio/TetOiTetA.mp3">
                                <img src="assets/thumb/tetoiteta.jpg" alt="Album Cover" class="track-image">
                                <h6 class="mb-1">T?t ?i T?t �</h6>
                                <p class="text-secondary mb-0">??c Ph�c</p>
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
                                    <div class="progress">
                                        <!--<div class="progress-bar" role="progressbar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>-->
                                        <input type="range" min="1" max="100" value="0" class="seek_slider" onchange="seekTo()">
                                    </div>
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

                        <input type="range" min="0" max="100" value="99" class="volume_slider" onchange="setVolume()">

                    </div>
                </div>
            </div>
        </div>

        <!-- Overlay for mobile -->
        <div class="overlay" id="overlay"></div>

        <!-- Th? <audio> ?? ph�t nh?c -->
        <audio id="audio-player" controls hidden></audio>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <<script src="js/script.js"></script>
    </body>
</html>