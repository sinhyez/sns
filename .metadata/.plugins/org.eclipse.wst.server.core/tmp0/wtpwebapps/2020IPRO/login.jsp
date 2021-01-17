<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="userModel.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%
    request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="userModel" class="userModel.User" scope="page"/>
<jsp:setProperty name="userModel" property="userID"/>
<jsp:setProperty name="userModel" property="userPW"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%


    UserDAO userDAO = new UserDAO();
    int result = userDAO.login(userModel.getUserEmail(), userModel.getUserPhone(), userModel.getUserPW());

    if (result == 1) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("location.href='main.jsp'");
        script.println("</script>");
    } else if (result == 0) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('Wrong Password.')");
        script.println("history.back()");
        script.println("</script>");
    } else if (result == -1) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('Wrong Id')");
        script.println("history.back()");
        script.println("</script>");
    } else if (result == -2) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('DB ERROR!!!!.')");
        script.println("history.back()");
        script.println("</script>");
    }

%>
</body>
</html>