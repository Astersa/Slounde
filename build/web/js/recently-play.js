
// Add script to handle queue storage when clicking on music cards
document.addEventListener('DOMContentLoaded', function () {
    // Select all music cards
    const musicCards = document.querySelectorAll('.music-card');

    // Add click event to each card
    musicCards.forEach(card => {
        card.addEventListener('click', function () {
            // Get data from the clicked card
            const songData = {
                id: this.getAttribute('data-song-id'),
            };

            // Get current playlist from localStorage
            let playlist = JSON.parse(localStorage.getItem('playlist') || '[]');

            // Find position of the song in the list if it already exists
            const duplicateIndex = playlist.findIndex(song => song.id === songData.id);

            // If song already exists, remove it from the list
            if (duplicateIndex !== -1) {
                playlist.splice(duplicateIndex, 1);
            }

            // Add the new song to the beginning of the list
            playlist.unshift(songData);

            // Save the updated list to localStorage
            localStorage.setItem('playlist', JSON.stringify(playlist));

            // Save current song to localStorage for easy access
            localStorage.setItem('currentSong', JSON.stringify(songData));

            console.log('Added song to the beginning of playlist:', songData);

            sendSongIdsToServlet();
        });
    });

    // Find all login links (modify selector to match your specific login links)
    const loginLinks = document.querySelectorAll('a[href*="login"]');
    loginLinks.forEach(link => {
        link.addEventListener('click', function () {
            // Clear playlist data and current song when clicking login
            localStorage.removeItem('playlist');
            localStorage.removeItem('currentSong');
            console.log('Cleared playlist due to login link click');
        });
    });

    // Find all logout links (modify selector to match your specific logout links)
    const logoutLinks = document.querySelectorAll('a[href*="logout"]');
    logoutLinks.forEach(link => {
        link.addEventListener('click', function () {
            // Clear playlist data and current song when clicking logout
            localStorage.removeItem('playlist');
            localStorage.removeItem('currentSong');
            console.log('Cleared playlist due to logout link click');
        });
    });

    function getQueue() {
        return JSON.parse(localStorage.getItem('playlist') || '[]');
    }

    function getCurrentSong() {
        return JSON.parse(localStorage.getItem('currentSong'));
    }

    function clearQueue() {
        localStorage.removeItem('playlist');
        localStorage.removeItem('currentSong');
    }

});

// Send string array contains song id to servlet HomeServlet
function sendSongIdsToServlet() {
    let id = JSON.parse(localStorage.getItem('playlist')) || [];

    if (id.length === 0) {
        console.log("Không có bài hát nào trong danh sách.");
        return;
    }

    let firstSongId = id.length > 0 ? id[0].id : null;
    console.log(firstSongId); // 101 (nếu playlist không rỗng)


    console.log("Recent played:" + firstSongId);
    // Gửi đến Servlet qua phương thức POST
    fetch('/Slounde/home?operation=currentSong', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'id=' + encodeURIComponent(firstSongId)
    })
            .then(response => response.text())
            .then(data => console.log('Server response:', data))
            .catch(error => console.error('Error:', error));
}
