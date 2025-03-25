// Wait for the DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Get references to the elements
    const toggleEditButton = document.getElementById('toggleEditMode');
    const profileForm = document.getElementById('u-profileForm'); // Fixed ID to match HTML
    const cancelButton = document.getElementById('cancelEdit');
    const saveButton = document.getElementById('saveProfile');
    
    // Add click event listener to the Edit Profile button
    toggleEditButton.addEventListener('click', function() {
        // Show the profile form by changing its display style
        profileForm.style.display = 'grid'; // Using grid as in your first version
        toggleEditButton.style.display = 'none'; // Hide the edit button when form is shown
    });
    
    // Add click event listener to the Cancel button to hide the form
    cancelButton.addEventListener('click', function() {
        // Hide the profile form
        profileForm.style.display = 'none';
        toggleEditButton.style.display = 'block'; // Show edit button again
    });
    
    // Add save functionality
    saveButton.addEventListener('click', function() {
        // Simulate saving profile
        const username = document.getElementById('username').value;
        document.querySelector('.u-profile-info h1').textContent = username; // Updated selector to match HTML
        profileForm.style.display = 'none';
        toggleEditButton.style.display = 'block';
        // Show a success message
        alert('Profile updated successfully!');
    });
    
    // Add unfollow functionality
    const unfollowButtons = document.querySelectorAll('.u-unfollow-button'); // Updated selector to match HTML
    unfollowButtons.forEach(button => {
        button.addEventListener('click', (e) => {
            const artistCard = e.target.closest('.u-artist-card'); // Updated selector to match HTML
            const artistName = artistCard.querySelector('.u-artist-name').textContent; // Updated selector
            if (confirm(`Are you sure you want to unfollow ${artistName}?`)) {
                artistCard.style.opacity = '0.5';
                button.textContent = 'Unfollowed';
                button.disabled = true;
            }
        });
    });
    
    // Genre tab selection (if you have genre tabs in your HTML)
    const genreTabs = document.querySelectorAll('.genre-tab');
    genreTabs.forEach(tab => {
        tab.addEventListener('click', () => {
            genreTabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');
        });
    });
});