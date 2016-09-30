<%-- 
    Document   : register_ok
    Created on : 2016/9/20, 上午 11:55:48
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" info="註冊成功"%>
<!DOCTYPE html>
<html>
    <head>
        <title><%= this.getServletInfo() %></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div id="header">
            <h1><%= this.getServletInfo() %></h1>
            <hr>
        </div>
        <div id="nav">
            <a href='register.html'>註冊</a> | 
            <a href='login.html'>登入</a> | 
            <a href='member/update.html'>會員修改</a> | 
            <a href='products_list.html'>產品清單</a> | 
            <hr>
        </div>
        <div id="article" style="height:75vh">
            <p>註冊成功</p>
        </div>
        <div id="footer">
            <hr>
            Copyright &COPY; VeryGoodBook Co.
        </div>
    </body>
</html>
