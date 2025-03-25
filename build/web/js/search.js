document.addEventListener("DOMContentLoaded", function () {
  const searchInput = document.querySelector(".search-input");
  let searchTimeout;

  searchInput.addEventListener("keydown", function (e) {
    const searchQuery = e.target.value.trim();
    if (e.key === "Enter" && searchQuery.length > 0) {
      performSearch(searchQuery);
    }
  });

  function performSearch(query) {
    fetch(
      `/Slounde/search?searchQuery=${encodeURIComponent(query)}&ajax=true`,
      {
        method: "GET",
        headers: {
          "X-Requested-With": "XMLHttpRequest",
        },
      }
    )
      .then((response) => response.text())
      .then((html) => {
        const tempDiv = document.createElement("div");
        tempDiv.innerHTML = html;

        const containerFluid = tempDiv.querySelector(".container-fluid");
        if (containerFluid) {
          const cssLinks = tempDiv.querySelectorAll('link[rel="stylesheet"]');
          const dynamicCssContainer = document.getElementById("dynamic-css");

          dynamicCssContainer.innerHTML = "";

          cssLinks.forEach((link) => {
            if (
              !link.href.includes("bootstrap") &&
              !link.href.includes("font-awesome")
            ) {
              dynamicCssContainer.appendChild(link.cloneNode(true));
            }
          });

          const mainContent = document.querySelector("#dynamic-content");
          if (mainContent) {
            mainContent.innerHTML = containerFluid.outerHTML;

            if (typeof initializeUserScripts === "function") {
              initializeUserScripts();
            }
          }
        }
      })
      .catch((error) => {
        console.error("Error performing search:", error);
      });
  }
});
