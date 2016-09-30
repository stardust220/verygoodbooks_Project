<%-- 
    Document   : dump
    Created on : 2016/9/19, 上午 11:12:04
    Author     : Administrator
--%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dump</title>
    </head>
    <body>
        <div id="header">
            <h1>Very Good Book <sub>Dump</sub></h1>
            <hr>
        </div>
        <div id="nav">
            <a href='<%= request.getContextPath() %>'>首頁</a> | 
            <hr>
                Server Host:<%= request.getLocalName() %><br>
                Server Address:<%= request.getLocalAddr()%><br>
                Server Port:<%= request.getLocalPort() %><br>
                ContextPath:<%= application.getContextPath() %><br>

                Remote Host:<%= request.getRemoteHost() %><br>
                Remote Address:<%= request.getRemoteAddr()%><br>
                Remote Port:<%= request.getRemotePort() %><br>
                Request URL:<%= request.getRequestURL() %><br>
            <hr>
            <table width="100%" cellpadding="5" cellspacing="0" border="1">
                <caption>Headers Information</caption>
                <tbody>
                    <%  int index = 0;
                        Enumeration<String> headerNames = request.getHeaderNames();
                        while(headerNames.hasMoreElements()){
                            String name = headerNames.nextElement();
                            String value = request.getHeader(name);                            
                            index++;
                    %>
                    <tr><td><%= index %></td><td><%= name %></td><td><%= value %></td></tr>
                    <% } %>
                </tbody>
                <thead>
                    <tr><th colspan="2">Header Name(共<%= index %>筆)</th><th>Header Value</th></tr>
                </thead>
            </table>
            <hr>
            <table width="100%" cellpadding="5" cellspacing="0" border="1">
                <caption>Form Data</caption>
                <thead>
                    <tr><th colspan="1">Param Name</th><th>Param Value</th></tr>
                </thead>
                <tbody>
                    <% 
                        Enumeration<String> paramNames = request.getParameterNames();
                        while(paramNames.hasMoreElements()){
                            String name = paramNames.nextElement();
                            String[] values = request.getParameterValues(name);
                    %>
                    <tr><td><%= name %></td><td><%= Arrays.toString(values) %></td></tr>
                    <%  } %>
                </tbody>
            </table>
        </div>        
    </body>
</html>
