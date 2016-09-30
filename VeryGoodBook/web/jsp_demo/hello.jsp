<%--  JSP 註解
    Document   : hello
    Created on : 2016/9/14, 下午 04:45:03
    Author     : Administrator
--%>
<jsp:directive.page import="java.util.Date,java.util.List" autoFlush="true" buffer="1kb" isThreadSafe="true" errorPage="/WEB-INF/error.jsp" />
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="true" info="HelloJSP"%>
<jsp:declaration>
    //copy 類別中, _jspService(...)方法外
    private String greeting="Hello";
    
    public void jspInit(){
        String greeting = this.getInitParameter("greeting");
        if(greeting!=null){
            this.greeting = greeting;
        }
    }
</jsp:declaration>>
<!-- HTML註解 -->
<!DOCTYPE html>
<html>
    <head>
        <style>
            /* CSS註解 */
        </style>
        <title><%= this.getServletInfo() %></title>
<script src="js/jquery.js" type="text/javascript">
            //.... JS註解
            /*
             * JS註解
             */
            
</script>
        
    </head>
    <body>
        <h1><%=  this.greeting %> World! This is <%= this.getServletInfo() %>!</h1>
        <p><%=  this.greeting %>，現在時間是:
        <%= new java.util.Date()%>    
        <%
           int i=1;//local variable
           out.println(i);      
            //java 註解          
        %>
        </p>
        <hr>
        <h2>request</h2>
        <p>hello:
        <%
            out.println(request.getParameter("hello"));
        %><br>
        name: <%= request.getParameter("name") %>
        </p>
        <p>
            user-agent: <%= request.getHeader("user-agent") %>
        </p>
        <p>
            context path: <%= request.getContextPath() %><br>
            Local name: <%= request.getLocalName() %><br>
            remote host: <%= request.getRemoteHost() %><br>
        </p>
        <hr>
        <h2>response</h2>
        Content Type: <%= response.getContentType() %>
        <hr>
        <h2>out</h2>       
        <%--<%= // out.print("Test")%>--%>
            application.setAttribute("app.visitors.count",2312);
        %> 
        <%  out.flush();
            Thread.sleep(300);
            out.print("Test"); 
        %><br>
        <%= "Test" %>
        <hr>
        <h2>session</h2>
        seesion id: <%= session.getId() %>
        <hr>
        <h2>application</h2>
        拜訪人次: <%= application.getAttribute("app.visitors.count") %>        
        <hr>
        <h2>config</h2>
        <p><%=  config.getInitParameter("greeting") %>，現在時間是: <%= new Date() %>
        <hr>
        <h2>pageContext</h2>
        seesion id: <%= pageContext.getSession().getId() %><br>
        拜訪人次: <%= pageContext.getServletContext().getAttribute("app.visitors.count") %>
        <hr>
        <h2>page</h2>
        page==this: <%= page==this %>
        <p>greeting: <%=  this.getInitParameter("greeting") %>
        <p>greeting: <%=  ((HttpServlet)page).getInitParameter("greeting") %>
        <p>page.toString(): <%= page.toString() %>
        <hr>
        <h2>expression</h2>
        一般JSP沒有宣告expression，只有error jsp
        <%--<%= // expression %>--%>
        <%
            
        %>


    </body>
</html>