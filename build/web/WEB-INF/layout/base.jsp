<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sloude - Music Streaming</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css" />
        <!-- Placeholder for dynamic CSS -->
    <div id="dynamic-css"></div>
</head>
<body>
    <div class="container-fluid p-0">
        <div class="row g-0">
            <!-- Include Sidebar -->
            <jsp:include page="/WEB-INF/common/sidebar.jsp" />

            <!-- Main Content -->
            <div class="col-lg-10 col-md-9 ms-auto main-content">
                <div id="dynamic-content">
                    <!-- Include initial page-specific content -->
                    <%--<jsp:include page="${param.content}" />--%>
                    <jsp:include page="${contentJsp}" />
                </div>
            </div>
        </div>

        <!-- Include Player Bar -->
        <jsp:include page="/WEB-INF/common/player-bar.jsp" />
    </div>

    <!-- Overlay for mobile -->
    <div class="overlay" id="overlay"></div>

    <!-- Thẻ <audio> để phát nhạc -->
    <audio id="audio-player" controls hidden></audio>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="js/player-handler.js"></script>
    <script src="js/script.js"></script>
    <script src="js/user-script.js"></script>

    <script>
        function extractAndLoadCSS(html) {
            const parser = new DOMParser();
            const doc = parser.parseFromString(html, 'text/html');
            const cssLinks = doc.querySelectorAll('link[rel="stylesheet"]');
            const dynamicCssContainer = document.getElementById('dynamic-css');

            // Remove existing dynamic CSS
            dynamicCssContainer.innerHTML = '';

            // Add new CSS links
            cssLinks.forEach(link => {
                if (!link.href.includes('bootstrap') && !link.href.includes('font-awesome')) {
                    dynamicCssContainer.appendChild(link.cloneNode(true));
                }
            });

            // Return the HTML without the CSS links
            cssLinks.forEach(link => link.remove());
            return doc.body.innerHTML;
        }

        function extractAndExecuteScripts(container) {
            const scripts = container.querySelectorAll("script");

            scripts.forEach(script => {
                const newScript = document.createElement("script");
                newScript.async = false; // Đảm bảo chạy theo đúng thứ tự

                if (script.src) {
                    newScript.src = script.src; // External script
                } else {
                    newScript.textContent = script.textContent; // Inline script
                }

                document.body.appendChild(newScript); // Thêm script vào body để thực thi
                script.remove(); // Xóa script cũ để tránh lặp lại
            });
        }

// Function to load content dynamically
        function loadContent(path) {
            fetch(path + '?ajax=true')
                    .then(response => response.text())
                    .then(html => {
                        // Extract and load CSS first
                        const contentHtml = extractAndLoadCSS(html);

                        // Update the content
                        const dynamicContent = document.getElementById('dynamic-content');
                        dynamicContent.innerHTML = contentHtml;

                        // Execute scripts in the loaded content
                        extractAndExecuteScripts(dynamicContent);
                        
                        // Reinitialize main scripts
                        if (typeof initializeUserScripts === 'function') {
                            initializeUserScripts();
                        }
                    })
                    .catch(error => console.error('Error loading content:', error));

            // Update URL without page reload
            window.history.pushState({path: path}, '', path);
        }

// Handle browser back/forward buttons
        window.onpopstate = function (event) {
            if (event.state && event.state.path) {
                loadContent(event.state.path);
            }
        };

// Intercept navigation links
        document.addEventListener('click', function (e) {
            const link = e.target.closest('a[data-spa="true"]');
            if (link) {
                e.preventDefault();
                loadContent(link.getAttribute('href'));
            }
        });

    </script>
</body>
</html>