// Function to initialize user-related functionality
function initializeUserScripts() {
  console.log("Initializing user scripts...");
  const userProfileButton = document.getElementById("userProfileButton");
  const userDropdown = document.getElementById("userDropdown");

  if (userProfileButton && userDropdown) {
    // Remove existing event listeners (if any) to prevent duplicates
    const newUserProfileButton = userProfileButton.cloneNode(true);
    userProfileButton.parentNode.replaceChild(
      newUserProfileButton,
      userProfileButton
    );

    // Toggle dropdown when clicking the user profile button
    newUserProfileButton.addEventListener("click", function (e) {
      e.stopPropagation();
      userDropdown.style.display =
        userDropdown.style.display === "block" ? "none" : "block";
    });

    // Close dropdown when clicking outside
    document.addEventListener("click", function () {
      userDropdown.style.display = "none";
    });

    // Prevent dropdown from closing when clicking inside it
    userDropdown.addEventListener("click", function (e) {
      e.stopPropagation();
    });
  }
}

// Initialize on first load
document.addEventListener("DOMContentLoaded", initializeUserScripts);

// Export the initialization function for reuse
window.initializeUserScripts = initializeUserScripts;
