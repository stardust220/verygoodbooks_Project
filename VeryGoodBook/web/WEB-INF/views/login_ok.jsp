<%-- 
    Document   : login_ok
    Created on : 2016/9/19, 下午 06:20:10
    Author     : Administrator
--%>

<%@page import="vgb.domain.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8" info="登入成功"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= this.getServletInfo() %></title>
    </head>
    <body>
        <%
            Customer c = 
                (Customer)request.getAttribute("customer");
        %>
        <h1>
            <%= this.getServletInfo() %>! 
            <%=  c!=null?c.getName():"" %>, 你好
        </h1>
    </body>
</html>
