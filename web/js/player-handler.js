let currentSong = {
  id: null,
  name: "",
  artistName: "",
  albumName: "",
  thumbnailUrl: "",
  songUrl: "",
  like: false,
};

let songQueue = [];
let currentQueueIndex = 0;

function updatePlayerBar(songData) {
  const nowPlayingImage = document.querySelector(".now-playing-image");
  const nowPlayingTitle = document.querySelector(".col-md-3 h6");
  const nowPlayingArtist = document.querySelector(".col-md-3 p");
  const audioPlayer = document.getElementById("audio-player");
  const buttonLike = document.querySelector(".like-button i");

  if (nowPlayingImage) nowPlayingImage.src = songData.thumbnailUrl;
  if (nowPlayingTitle) nowPlayingTitle.textContent = songData.name;
  if (nowPlayingArtist) nowPlayingArtist.textContent = songData.artistName;
  if (buttonLike) {
    buttonLike.classList.remove("far", "fas", "fa-heart");
    if (currentSong.like) {
      console.log("Thêm trái tim đầy");
      buttonLike.classList.add("fas", "fa-heart");
    } else {
      console.log("Thêm trái tim rỗng");
      buttonLike.classList.add("far", "fa-heart");
    }
  }

  if (audioPlayer && songData.songUrl) {
    audioPlayer.pause();

    audioPlayer.src = songData.songUrl;

    audioPlayer.addEventListener("loadeddata", function playWhenLoaded() {
      audioPlayer.removeEventListener("loadeddata", playWhenLoaded);

      audioPlayer
        .play()
        .then(() => {
          const playButton = document.querySelector(".play-btn i");
          if (playButton) {
            playButton.classList.remove("fa-play");
            playButton.classList.add("fa-pause");
          }
          if (nowPlayingImage) {
            nowPlayingImage.classList.add("now-playing-animation");
          }
        })
        .catch((error) => {
          if (error.name === "AbortError") {
            console.log("Playback was interrupted by another load request");
          } else {
            console.error("Error playing audio:", error);
          }
        });
    });
  }

  sessionStorage.setItem("currentSong", JSON.stringify(songData));
}

function handleSongSelect(element) {
  const songId = {
    id: element.dataset.songId || "",
  };

  fetch(`/Slounde/home?operation=getSong&songId=${songId.id}`, {
    method: "POST",
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        console.log("Lấy thông tin bài hát thất bại!");
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((songData) => {
      currentSong = songData;
      updatePlayerBar(songData);
    })
    .catch((error) => {
      console.error("Error fetching song:", error);
    });

  fetch(`/Slounde/home?operation=listen&songId=${songId.id}`, {
    method: "POST",
    credentials: "include",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
  })
    .then((response) => {
      if (!response.ok) {
        console.log("Tăng lượt nghe thất bại!");
      }
      return response.text();
    })
    .then((data) => {
      if (data === "true") {
        console.log("Tăng lượt nghe thành công!");
      } else {
        console.log("Tăng lượt nghe thất bại!");
      }
    });

  fetch(`/Slounde/home?operation=getQueue`, {
    method: "POST",
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        console.log("Lấy danh sách bài hát thất bại!");
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((queue) => {
      songQueue = queue;

      const recentlyPlayed = JSON.parse(
        localStorage.getItem("playlist") || "[]"
      );

      const filteredRecentlyPlayed = recentlyPlayed
        .filter((song) => song.id !== currentSong.id)
        .reverse();

      if (filteredRecentlyPlayed.length > 0) {
        songQueue.unshift(...filteredRecentlyPlayed);
      } else {
        songQueue.unshift(currentSong);
      }

      currentQueueIndex = songQueue.findIndex(
        (song) => song.id === currentSong.id
      );
    })
    .catch((error) => {
      console.error("Error fetching queue:", error);
    });
}

function updateQueueDisplay() {
  const queueContainer = document.querySelector(".queue-container");
  const queueList = document.querySelector(".queue-list");
  let playlist = songQueue;

  queueList.innerHTML = "";

  playlist.forEach((song, index) => {
    const li = document.createElement("li");
    li.className = "queue-item";
    li.innerHTML = `
                <div class="queue-item-info">
                    <img src="${song.thumbnailUrl}" alt="Song Cover" class="queue-item-cover">
                    <div>
                        <h6 class="queue-item-title">${song.name}</h6>
                        <p class="queue-item-artist">${song.artistName}</p>
                    </div>
                </div>
            `;

    li.addEventListener("click", () => {
      const songData = {
        id: song.id,
        name: song.name,
        artistName: song.artistName,
        thumbnailUrl: song.thumbnailUrl,
        songUrl: song.songUrl,
      };
      currentSong = songData;
      updatePlayerBar(songData);
      queueContainer.style.display = "none";
    });

    queueList.appendChild(li);
  });
}

document.addEventListener("DOMContentLoaded", function () {
  const savedSong = sessionStorage.getItem("currentSong");
  if (savedSong) {
    updatePlayerBar(JSON.parse(savedSong));
  }

  const addSongClickHandlers = () => {
    document.querySelectorAll(".music-card").forEach((card) => {
      card.addEventListener("click", () => handleSongSelect(card));
    });

    document.querySelectorAll(".ls-song-item").forEach((item) => {
      item.addEventListener("click", () => handleSongSelect(item));
    });

    document.querySelectorAll(".u-song-card").forEach((item) => {
      item.addEventListener("click", () => handleSongSelect(item));
    });
  };

  addSongClickHandlers();

  const volumeBtn = document.querySelector(".volume-btn");
  const volumeSlider = document.querySelector(".volume_slider");
  const volumeIcon = document.querySelector(".volume-btn i");
  const audioPlayer = document.getElementById("audio-player");

  if (volumeBtn && volumeSlider && volumeIcon && audioPlayer) {
    audioPlayer.volume = volumeSlider.value / 100;

    volumeBtn.addEventListener("click", () => {
      if (audioPlayer.volume > 0) {
        volumeSlider.dataset.previousVolume = volumeSlider.value;
        volumeSlider.value = 0;
        audioPlayer.volume = 0;
        volumeIcon.classList.remove("fa-volume-up");
        volumeIcon.classList.add("fa-volume-mute");
      } else {
        const previousVolume = volumeSlider.dataset.previousVolume || 100;
        volumeSlider.value = previousVolume;
        audioPlayer.volume = previousVolume / 100;
        volumeIcon.classList.remove("fa-volume-mute");
        volumeIcon.classList.add("fa-volume-up");
      }
    });

    volumeSlider.addEventListener("input", () => {
      const volume = volumeSlider.value / 100;
      audioPlayer.volume = volume;

      if (volume === 0) {
        volumeIcon.classList.remove("fa-volume-up");
        volumeIcon.classList.add("fa-volume-mute");
      } else {
        volumeIcon.classList.remove("fa-volume-mute");
        volumeIcon.classList.add("fa-volume-up");
      }
    });
  }

  const queueBtn = document.querySelector(".queue-btn");
  const queueContainer = document.querySelector(".queue-container");

  queueBtn.addEventListener("click", () => {
    if (queueContainer.style.display === "block") {
      queueContainer.style.display = "none";
    } else {
      updateQueueDisplay();
      queueContainer.style.display = "block";
    }
  });

  document.addEventListener("click", (e) => {
    if (!queueContainer.contains(e.target) && !queueBtn.contains(e.target)) {
      queueContainer.style.display = "none";
    }
  });

  document.querySelector(".prev-track").addEventListener("click", (e) => {
    if (currentQueueIndex > 0) {
      currentQueueIndex--;
      updatePlayerBar(songQueue[currentQueueIndex]);
    }
  });

  document.querySelector(".next-track").addEventListener("click", (e) => {
    if (currentQueueIndex < songQueue.length - 1) {
      currentQueueIndex++;
      updatePlayerBar(songQueue[currentQueueIndex]);
    }
  });

  const observer = new MutationObserver((mutations) => {
    mutations.forEach((mutation) => {
      if (mutation.addedNodes.length) {
        addSongClickHandlers();
      }
    });
  });

  audioPlayer.addEventListener("ended", () => {
    if (currentQueueIndex < songQueue.length - 1) {
      currentQueueIndex++;
      updatePlayerBar(songQueue[currentQueueIndex]);
    }
  });

  const dynamicContent = document.getElementById("dynamic-content");
  if (dynamicContent) {
    observer.observe(dynamicContent, { childList: true, subtree: true });
  }
});
