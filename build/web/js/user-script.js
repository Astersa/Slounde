// User Profile Dropdown Toggle
document.addEventListener('DOMContentLoaded', function() {
    const userProfileButton = document.getElementById('userProfileButton');
    const userDropdown = document.getElementById('userDropdown');
    
    // Toggle dropdown when clicking the user profile button
    userProfileButton.addEventListener('click', function(e) {
        e.stopPropagation();
        userDropdown.style.display = userDropdown.style.display === 'block' ? 'none' : 'block';
    });
    
    // Close dropdown when clicking outside
    document.addEventListener('click', function() {
        userDropdown.style.display = 'none';
    });
    
    // Prevent dropdown from closing when clicking inside it
    userDropdown.addEventListener('click', function(e) {
        e.stopPropagation();
    });
    
});