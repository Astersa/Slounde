
// Mobile sidebar toggle
document.addEventListener('DOMContentLoaded', function () {
    const toggleBtn = document.createElement('button');
    toggleBtn.classList.add('btn', 'position-fixed', 'top-0', 'start-0', 'm-2', 'd-lg-none');
    toggleBtn.style.background = 'rgba(255, 255, 255, 0.1)';
    toggleBtn.style.backdropFilter = 'blur(5px)';
    toggleBtn.style.color = 'white';
    toggleBtn.style.borderRadius = '50%';
    toggleBtn.style.width = '40px';
    toggleBtn.style.height = '40px';
    toggleBtn.style.display = 'flex';
    toggleBtn.style.alignItems = 'center';
    toggleBtn.style.justifyContent = 'center';
    toggleBtn.innerHTML = '<i class="fas fa-bars"></i>';
    document.body.appendChild(toggleBtn);

    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('overlay');

    toggleBtn.addEventListener('click', function () {
        sidebar.classList.toggle('show');
        overlay.classList.toggle('show');
    });

    overlay.addEventListener('click', function () {
        sidebar.classList.remove('show');
        overlay.classList.remove('show');
    });

    // Play button toggle
    const playBtn = document.querySelector('.play-btn');
    playBtn.addEventListener('click', function () {
        const icon = playBtn.querySelector('i');
        if (icon.classList.contains('fa-play')) {
            icon.classList.remove('fa-play');
            icon.classList.add('fa-pause');

            // Add animation to now playing
            document.querySelector('.now-playing-image').classList.add('now-playing-animation');
        } else {
            icon.classList.remove('fa-pause');
            icon.classList.add('fa-play');

            // Remove animation
            document.querySelector('.now-playing-image').classList.remove('now-playing-animation');
        }
    });

    // Make music cards clickable
    const musicCards = document.querySelectorAll('.music-card');
    const audioPlayer = document.getElementById('audio-player');
    const playButton = document.querySelector('.play-btn i');
    const progressBar = document.querySelector('#seek_slider');
    const currentTimeDisplay = document.querySelector('.text-secondary.small:first-of-type');
    const durationDisplay = document.querySelector('.text-secondary.small:last-of-type');

    //Thanh tiến trình mặc định ở giá trị 0
    progressBar.value = 0;
    
    musicCards.forEach(card => {
        card.addEventListener('click', function () {
            const title = this.querySelector('h6').textContent;
            const artist = this.querySelector('p').textContent;
            const image = this.querySelector('img').src;
            const audioSrc = this.getAttribute('data-audio');

            // Cập nhật giao diện "Now Playing"
            document.querySelector('.now-playing-image').src = image;
            document.querySelector('.col-md-3 h6').textContent = title;
            document.querySelector('.col-md-3 p').textContent = artist;

            // Cập nhật nguồn nhạc và phát bài hát
            if (audioSrc != "#") {
                audioPlayer.src = audioSrc;
                
                audioPlayer.play();
            }

            // Cập nhật icon Play/Pause
            playButton.classList.remove('fa-play');
            playButton.classList.add('fa-pause');
        });
    });

    // Cập nhật thanh tiến trình khi bài hát phát
    audioPlayer.addEventListener('timeupdate', function () {
        const currentTime = audioPlayer.currentTime;
        const duration = audioPlayer.duration;
        if (!isNaN(duration)) {
            progressBar.style.width = `${100}%`;

            // Hiển thị thời gian hiện tại và tổng thời gian bài hát
            currentTimeDisplay.textContent = formatTime(currentTime);
            durationDisplay.textContent = formatTime(duration);
        } 
    });

    audioPlayer.addEventListener("play", () => {
        setInterval(() => {
            progressBar.value = (audioPlayer.currentTime / audioPlayer.duration) * 100;
        }, 500);
    });


    // Chuyển đổi thời gian (giây) thành định dạng mm:ss
    function formatTime(time) {
        const minutes = Math.floor(time / 60);
        const seconds = Math.floor(time % 60);
        return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
    }

    // Cho phép người dùng click vào thanh tiến trình để tua bài hát
    // Khi người dùng thay đổi thanh tiến trình
    progressBar.addEventListener("input", function () {
        const duration = audioPlayer.duration;
        if (!isNaN(duration) && duration > 0) {
            // Tính toán thời gian mới theo phần trăm
            const newTime = (progressBar.value / 100) * duration;
            audioPlayer.currentTime = newTime; // Cập nhật thời gian

            // Nếu bài hát đang phát, tiếp tục phát sau khi tua
            if (!audioPlayer.paused) {
                audioPlayer.play();
            }
        }
    });



    // Xử lý khi bấm nút Play/Pause
    document.querySelector('.play-btn').addEventListener('click', function () {
        if (audioPlayer.paused) {
            audioPlayer.play();
            playButton.classList.remove('fa-play');
            playButton.classList.add('fa-pause');
        } else {
            audioPlayer.pause();
            playButton.classList.remove('fa-pause');
            playButton.classList.add('fa-play');
        }
    });

    function togglePlay() {
        let playBtn = document.querySelector('.play-btn i');
        if (audio.paused) {
            audio.play();
            playBtn.className = "fas fa-pause";
        } else {
            audio.pause();
            playBtn.className = "fas fa-play";
        }
    }


    // Lấy phần tử audio player (Đảm bảo có thẻ <audio> trong HTML)
    const audio = document.getElementById("audio-player");

    // Lấy thanh điều chỉnh âm lượng
    const volumeSlider = document.querySelector(".volume_slider");
    const volumeIcon = document.querySelector(".fa-volume-up");

    // Hàm thay đổi âm lượng
    function setVolume() {
        let volume = volumeSlider.value / 100;
        audio.volume = volume;

        // Cập nhật icon volume
        if (volume === 0) {
            volumeIcon.className = "fas fa-volume-mute";
        } else if (volume < 0.5) {
            volumeIcon.className = "fas fa-volume-down";
        } else {
            volumeIcon.className = "fas fa-volume-up";
        }
    }

    // Gán sự kiện onchange cho thanh volume
    volumeSlider.addEventListener("input", setVolume);
});


function openPopup() {
    document.getElementById("overlay").style.display = "block";
    document.getElementById("popup").style.display = "block";
}

function closePopup() {
    document.getElementById("overlay").style.display = "none";
    document.getElementById("popup").style.display = "none";
}

// Gọi openPopup() khi cần hiển thị popup



