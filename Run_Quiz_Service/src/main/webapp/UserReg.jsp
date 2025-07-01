<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
        }
        .form-container {
            width: 300px;
            margin: auto;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 10px;
        }
        .form-container input {
            width: 100%;
            padding: 8px;
            margin-top: 8px;
            margin-bottom: 16px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
        .form-container button {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #0056b3;
        }
        #message {
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Register for Quiz</h2>
    <form id="quizForm">
        <input type="text" id="name" name="name" placeholder="Name" required>
        <input type="email" id="mail" name="mail" placeholder="Mail ID" required>
        <input type="text" id="quizId" name="quizId" placeholder="Quiz ID" required>
        <button type="submit">Submit</button>
    </form>
    <div id="message"></div>
</div>

<script>
document.getElementById("quizForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const userData = {
        name: document.getElementById("name").value,
        mail: document.getElementById("mail").value,
        quizId: document.getElementById("quizId").value
    };

    fetch("http://localhost:4040/api/quiz/SaveUser", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userData)
    })

    .then(response => {
        if (response.ok) {
            document.getElementById("message").innerText = "Successfully Registered!";
        } else {
            document.getElementById("message").innerText = "Registration Failed.";
        }
    })
    .catch(error => {
        console.error("Error:", error);
        document.getElementById("message").innerText = "Error occurred during registration.";
    });
});
</script>

</body>
</html>
