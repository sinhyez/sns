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
<jsp:setProperty name="userModel" property="userName"/>
<jsp:setProperty name="userModel" property="userGender"/>
<jsp:setProperty name="userModel" property="userEmail"/>
<jsp:setProperty name="userModel" property="userPhone"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    if (userModel.getUserID() == null || userModel.getUserPW() == null ||
            userModel.getUserName() == null || userModel.getUserGender() == null ||
            userModel.getUserEmail() == null || userModel.getUserPhone() == null) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('입력안된사항있음')");
        script.println("history.back()");
        script.println("</script>");
    } else {
        UserDAO userDAO = new UserDAO();
        int result = userDAO.join(userModel);

        if (result == -1) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('이미있는아이디')");
            script.println("history.back()");
            script.println("</script>");
        } else {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("location.href='main.jsp'");
            script.println("</script>");
        }
    }

%>
</body>
</html>