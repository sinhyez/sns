<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<header class="header">
    <div class="top">
        <h1 class="top1">
            <a href="#" class="top2">Join Page</a>
        </h1>
    </div>
</header>
<form action="joinA.jsp" method="post">
    <input type="text" name="userID" placeholder="ID">
    <input type="password" name="userPW" placeholder="password">
    <input type="text" name="userName" placeholder="name">
    <input type="radio" name="userGender" autocomplete="off" value="men" checked>men
    <input type="radio" name="userGender" autocomplete="off" value="women" checked>women
    <input type="email" name="userEmail" placeholder="email">
    <input type="text" name="userPhone" placeholder="phone">
    <input type="submit" value="join">
</form>
</body>
</html>