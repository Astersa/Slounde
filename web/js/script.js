
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
    const progressBar = document.querySelector('.progress');
    const currentTimeDisplay = document.querySelector('.text-secondary.small:first-of-type');
    const durationDisplay = document.querySelector('.text-secondary.small:last-of-type');

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
            if (audioSrc) {
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
            const progressPercent = (currentTime / duration) * 100;
            progressBar.style.width = `${progressPercent}%`;

            // Hiển thị thời gian hiện tại và tổng thời gian bài hát
            currentTimeDisplay.textContent = formatTime(currentTime);
            durationDisplay.textContent = formatTime(duration);
        }
    });

// Chuyển đổi thời gian (giây) thành định dạng mm:ss
    function formatTime(time) {
        const minutes = Math.floor(time / 60);
        const seconds = Math.floor(time % 60);
        return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
    }

// Cho phép người dùng click vào thanh tiến trình để tua bài hát
    document.querySelector('.progress-container').addEventListener('click', function (event) {
        const progressWidth = this.clientWidth;
        const clickX = event.offsetX;
        const duration = audioPlayer.duration;

        if (!isNaN(duration)) {
            audioPlayer.currentTime = (clickX / progressWidth) * duration;
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


    // Add hover effect to progress bar
    progressBar.addEventListener('mouseover', function () {
        this.style.height = '6px';
    });

    progressBar.addEventListener('mouseout', function () {
        this.style.height = '4px';
    });
});



