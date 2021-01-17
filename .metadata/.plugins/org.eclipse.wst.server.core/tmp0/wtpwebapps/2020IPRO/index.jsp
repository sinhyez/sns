<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="login.css">
    <title>2020ipro</title>
</head>
<body>
<header class="header">
    <div class="top">
        <h1>login page</h1>
    </div>
</header>
<div class="container">
    <form action="login.jsp" method="post" class="login">
        <label class="account">
                <span class="id">
                    <input type="text" name="userEmail" name="userPhone" class="id1"
                           placeholder="Email address or Cell phone number">
                </span>
        </label>
        <label class="pass">
                <span class="pw">
                    <input type="password" class="pw1" name="userPW" placeholder="Password">
                </span>
        </label>
        <div class="button">
            <input type="submit" value="login">
        </div>
    </form>
    <div class="find">
        <a href="#" class="fina">Find your account</a>
    </div>
    <div class="find">
        <a href="#" class="finp">Find your password</a>
    </div>
    <div class="box1">
        <div class="creata">
            <p class="text1">sign up now? <a href="join.jsp" class="sign">sign up</a></p>
        </div>
    </div>
</div>
<footer class="footer">
    <div class="ft">
            <span class="copy">
                @COPYRIGHT 2020IPRO FROM JANG YOONSUNG
            </span>
    </div>
</footer>
</body>
</html>