// Global state for currently playing song
let currentSong = {
  id: null,
  title: "",
  artist: "",
  album: "",
  cover: "",
  audioUrl: "",
  duration: "",
};

// Function to update player bar UI
function updatePlayerBar(songData) {
  const nowPlayingImage = document.querySelector(".now-playing-image");
  const nowPlayingTitle = document.querySelector(".col-md-3 h6");
  const nowPlayingArtist = document.querySelector(".col-md-3 p");
  const audioPlayer = document.getElementById("audio-player");

  if (nowPlayingImage) nowPlayingImage.src = songData.cover;
  if (nowPlayingTitle) nowPlayingTitle.textContent = songData.title;
  if (nowPlayingArtist) nowPlayingArtist.textContent = songData.artist;

  // Update audio source and play
  if (audioPlayer && songData.audioUrl) {
    audioPlayer.src = songData.audioUrl;
    audioPlayer
      .play()
      .then(() => {
        // Update play button icon
        const playButton = document.querySelector(".play-btn i");
        if (playButton) {
          playButton.classList.remove("fa-play");
          playButton.classList.add("fa-pause");
        }
        // Add animation to now playing image
        if (nowPlayingImage) {
          nowPlayingImage.classList.add("now-playing-animation");
        }
      })
      .catch((error) => console.error("Error playing audio:", error));
  }

  // Store current song in session storage
  sessionStorage.setItem("currentSong", JSON.stringify(songData));
}

// Function to handle song selection from any page
function handleSongSelect(element) {
  const songData = {
    id: element.dataset.songId || "",
    title:
      element.dataset.title ||
      element.querySelector(".ls-song-title, h6")?.textContent ||
      "",
    artist:
      element.dataset.artist ||
      element.querySelector(".ls-artist, p")?.textContent ||
      "",
    album:
      element.dataset.album ||
      element.querySelector(".ls-album")?.textContent ||
      "",
    cover: element.dataset.cover || element.querySelector("img")?.src || "",
    audioUrl: element.dataset.audio || "#",
    duration:
      element.dataset.duration ||
      element.querySelector(".ls-duration")?.textContent ||
      "",
  };

  // Update current song and player
  currentSong = songData;
  updatePlayerBar(songData);
}

// Initialize player with last played song
document.addEventListener("DOMContentLoaded", function () {
  const savedSong = sessionStorage.getItem("currentSong");
  if (savedSong) {
    updatePlayerBar(JSON.parse(savedSong));
  }

  // Add click handlers to all music cards
  const addSongClickHandlers = () => {
    // For home page music cards
    document.querySelectorAll(".music-card").forEach((card) => {
      card.addEventListener("click", () => handleSongSelect(card));
    });

    // For liked songs page song items
    document.querySelectorAll(".ls-song-item").forEach((item) => {
      item.addEventListener("click", () => handleSongSelect(item));
    });
  };

  // Initial setup
  addSongClickHandlers();

  // Setup observer for dynamic content changes
  const observer = new MutationObserver((mutations) => {
    mutations.forEach((mutation) => {
      if (mutation.addedNodes.length) {
        addSongClickHandlers();
      }
    });
  });

  // Observe dynamic content container
  const dynamicContent = document.getElementById("dynamic-content");
  if (dynamicContent) {
    observer.observe(dynamicContent, { childList: true, subtree: true });
  }
});
