<%-- 
    Document   : hello_el
    Created on : 2016/9/23, 下午 02:42:40
    Author     : Administrator
--%>

<%@page import="vgb.domain.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EL Demo</title>
    </head>
    <%
        int i =1;
        request.setAttribute("iAttr", i);
        
    %>
    <body>
        <h1>EL Demo</h1>
        <hr>
        <h2>Operator</h2>
        <p>i(${iAttr})+2: ${requestScope.iAttr + 2}</p>
        <p>i(<%= i %>)+2: <%= (Integer)request.getAttribute("iAttr") +2%></p>
        <p>1/2: ${1/2}</p>
        <p>1/2: <%=1/2%></p>
        <p>'1' + '2': ${'1'+'2'}</p>
        <p>'1' + '2': <%='1' + '2'%></p>
        <p>"1" + "2": ${"1" + "2"}</p>        
        <p>"1" + "2": <%="1" + "2"%></p>

        <p>'a' &gt; 'b': ${'a' gt 'b'}</p>
        <p>'a' > 'b': <%= 'a' > 'b'%></p>
        <p>1>2 & 2>3: ${1>2 && 2>3}</p>
        <p>1 & 2: <%=1 & 2%></p>
        <hr>
        <h2>隱含變數: pageContext</h2>
        <p>session id: <%= session.getId() %></p>
        <p>session id: <%= pageContext.getSession().getId() %></p>
        <p>session id: ${pageContext.session.id}</p>
        <p>ContextPath: ${pageContext.request.contextPath}</p>
        
        <hr>
        <h2>隱含變數</h2>
        <p>拜訪人次:<%= application.getAttribute("app.visitors.count")%></p>
        <p>拜訪人次:${applicationScope["app.visitors.count"]}</p>

        <p>會員:<%= session.getAttribute("member")==null ?"未登入":((Customer)session.getAttribute("member")).getName()%></p>
        <p>會員:${member.name}</p>

        <p>cookie userid ${cookie.userid.value} </p>
        
        <p>header user-agent: <%= request.getHeader("user-agent") %></p>
        <p>header user-agent: ${header["user-agent"]}</p>
        
        <p> parameter name:<%= request.getParameter("name") %></p>
        <p> parameter name:${param.name}</p>
        
        <p> parameter hoddy[0]:${paramValues.hobby[0]}</p>
    </body>
</html>
