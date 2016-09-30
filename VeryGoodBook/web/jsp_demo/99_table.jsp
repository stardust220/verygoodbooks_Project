<%-- 
    Document   : 99
    Created on : 2016/9/14, 下午 05:29:18
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>99乘法表</title>
    </head>
    <body>
        <h1>99乘法表</h1>
        <hr>
        <table cellpadding="5" cellspacing="0" border="1">
        <%for(int i=1;i<10;i++){%>
        <tr>
        <%for(int j=1;j<10;j++){%>
        <td><%=i%> * <%=j%> = <%=i*j%></td>
        <%}%>
        </tr>
        <%}%>
        </table>
    </body>
</html>
