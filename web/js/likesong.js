document.addEventListener("DOMContentLoaded", function () {
  document.querySelector(".like-button").addEventListener("click", function () {
    let icon = this.querySelector("i");

    if (icon.classList.contains("far")) {
      let songId = currentSong.id;
      let isLiked = true;
      fetch(
        `/Slounde/home?operation=likesong&songId=${songId}&isLiked=${isLiked}`,
        {
          method: "POST",
          credentials: "include",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        }
      )
        .then((response) => {
          if (response.status === 401) {
            window.location.href = "/Slounde/login"; // Redirect to login if not authenticated
            return;
          }
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.text();
        })
        .then((data) => {
          console.log(data);
          icon.classList.remove("far");
          icon.classList.add("fas");
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    } else {
      let songId = currentSong.id;
      let isLiked = false;
      fetch(
        `/Slounde/home?operation=likesong&songId=${songId}&isLiked=${isLiked}`,
        {
          method: "POST",
          credentials: "include",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        }
      )
        .then((response) => {
          if (response.status === 401) {
            window.location.href = "/Slounde/login"; // Redirect to login if not authenticated
            return;
          }
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.text();
        })
        .then((data) => {
          console.log(data);
          icon.classList.remove("fas");
          icon.classList.add("far");
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    }
  });
});
