<%-- 
    Document   : page1
    Created on : 2016/9/23, 上午 10:31:54
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page1</title>
    </head>
    <body>
        <h1>Page1</h1>
    </body>
</html>
<% //out.flush(); %>
<jsp:forward page="page2.jsp" />
