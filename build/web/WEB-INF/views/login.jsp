<%-- 
    Document   : login
    Created on : Mar 8, 2025, 1:35:31 PM
    Author     : Phuong
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            background: linear-gradient(to right, #0d0b27, #1b1440);
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            color: white;
        }
        .login-container {
            background: #251a58;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.6);
            text-align: center;
            width: 380px;
        }
        .login-container h2 {
            color: #e64aa9;
            font-size: 28px;
            margin-bottom: 25px;
            text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
        }
        .input-field {
            width: 100%;
            padding: 15px;
            margin: 15px 0;
            border: none;
            border-radius: 8px;
            background: #3d2c7c;
            color: white;
            font-size: 16px;
            box-sizing: border-box;
            transition: background 0.3s, transform 0.2s;
        }
        .input-field:focus {
            outline: none;
            background: #4e3b99;
            transform: scale(1.02);
        }
        .login-btn {
            background: linear-gradient(to right, #e64aa9, #c43b8a);
            color: white;
            padding: 15px;
            width: 100%;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: bold;
            font-size: 16px;
            margin-top: 10px;
            transition: all 0.3s;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .login-btn:hover {
            background: linear-gradient(to right, #c43b8a, #a52e70);
            transform: translateY(-2px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
        }
        .login-btn:active {
            transform: translateY(1px);
        }
        .register-link {
            margin-top: 20px;
            color: #b9a3e3;
            font-size: 15px;
        }
        .register-link a {
            color: #e64aa9;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s;
        }
        .register-link a:hover {
            color: #ff6ec4;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        
        <% if(request.getAttribute("error") != null) { %>
        <div class="error-message">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>
        
        <form id="loginForm" method="post" action="login">
            <input type="text" name="username" id="username" class="input-field" placeholder="Username" required>
            <input type="password" name="password" id="password" class="input-field" placeholder="Password" required>
            <button type="submit" class="login-btn">Login</button>
        </form>
        <p class="register-link">Don't have an account? <a href="register">Register here</a></p>
    </div>
    
    <script>
        document.getElementById("loginForm").addEventListener("submit", function(event) {
            string username = document.getElementById("username").value;
            string password = document.getElementById("password").value;
            
            if (!username || !password) {
                event.preventDefault();
                alert("Username and password are required");
                return false;
            }
        });
    </script>
</body>
</html>