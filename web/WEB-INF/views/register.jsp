<%-- 
    Document   : register
    Created on : Mar 8, 2025, 9:55:32 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <style>
            body {
                background: linear-gradient(to right, #0d0b27, #1b1440);
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                margin: 0;
                color: white;
            }
            .register-container {
                background: #251a58;
                padding: 30px;
                border-radius: 15px;
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.6);
                text-align: center;
                width: 400px;
            }
            .register-container h2 {
                color: #e64aa9;
                font-size: 28px;
                margin-bottom: 25px;
                text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
            }
            .input-group {
                position: relative;
                margin: 15px 0;
            }
            .input-field {
                width: 100%;
                padding: 15px;
                padding-right: 40px;
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
            .toggle-password {
                position: absolute;
                right: 10px;
                top: 50%;
                transform: translateY(-50%);
                background: none;
                border: none;
                color: #b9a3e3;
                cursor: pointer;
                font-size: 18px;
            }
            .register-btn {
                background: linear-gradient(to right, #e64aa9, #c43b8a);
                color: white;
                padding: 15px;
                width: 100%;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                font-weight: bold;
                font-size: 16px;
                margin-top: 20px;
                transition: all 0.3s;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            .register-btn:hover {
                background: linear-gradient(to right, #c43b8a, #a52e70);
                transform: translateY(-2px);
                box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
            }
            .register-btn:active {
                transform: translateY(1px);
            }
            .login-link {
                margin-top: 20px;
                color: #b9a3e3;
                font-size: 15px;
            }
            .login-link a {
                color: #e64aa9;
                text-decoration: none;
                font-weight: bold;
                transition: color 0.3s;
            }
            .login-link a:hover {
                color: #ff6ec4;
                text-decoration: underline;
            }
            .form-label {
                display: block;
                text-align: left;
                margin-bottom: 6px;
                color: #b9a3e3;
                font-size: 14px;
            }
        </style>
    </head>
    <body>
        <div class="register-container">
            <h2>Create Account</h2>
            <form id="registerForm">
                <div class="input-group">
                    <label class="form-label" for="name">Full Name</label>
                    <input type="text" id="name" class="input-field" placeholder="Enter your full name" required>
                </div>

                <div class="input-group">
                    <label class="form-label" for="dob">Date of Birth</label>
                    <input type="date" id="dob" class="input-field" required>
                </div>

                <div class="input-group">
                    <label class="form-label" for="username">Username</label>
                    <input type="text" id="username" class="input-field" placeholder="Choose a username" required>
                </div>

                <div class="input-group">
                    <label class="form-label" for="password">Password</label>
                    <input type="password" id="password" class="input-field" placeholder="Create a password" required>
                    <button type="button" class="toggle-password" onclick="togglePasswordVisibility('password')">️</button>
                </div>

                <div class="input-group">
                    <label class="form-label" for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" class="input-field" placeholder="Confirm your password" required>
                    <button type="button" class="toggle-password" onclick="togglePasswordVisibility('confirmPassword')">️</button>
                </div>

                <button type="submit" class="register-btn">Register</button>
            </form>
            <p class="login-link">Already have an account? <a href="login">Login here</a></p>
        </div>

        <script>
            function togglePasswordVisibility(fieldId) {
                const passwordField = document.getElementById(fieldId);
                const toggleButton = passwordField.nextElementSibling;

                if (passwordField.type === "password") {
                    passwordField.type = "text";
                    toggleButton.innerHTML = "❌";
                } else {
                    passwordField.type = "password";
                    toggleButton.innerHTML = "👁️";
                }
            }

            document.getElementById("registerForm").addEventListener("submit", function (event) {
                event.preventDefault();

                const password = document.getElementById("password").value;
                const confirmPassword = document.getElementById("confirmPassword").value;

                if (password !== confirmPassword) {
                    alert("Passwords do not match. Please try again.");
                    return false;
                }

                // Trong thực tế, bạn sẽ gửi dữ liệu đến server tại đây
                console.log("Form submitted successfully");

                // Dữ liệu người dùng
                const userData = {
                    userId: null, // Thường được tạo bởi server
                    name: document.getElementById("name").value,
                    dob: document.getElementById("dob").value,
                    username: document.getElementById("username").value,
                    password: password
                };

                console.log("User data:", userData);
                alert("Registration successful!");
            });
        </script>
    </body>
</html>