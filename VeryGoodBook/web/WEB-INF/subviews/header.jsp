<%@page import="vgb.domain.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>首頁</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <!--<meta http-equiv="refresh" content="5;url=http://www.uuu.com.tw">-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body{font-family: Arial, "文泉驛正黑", "WenQuanYi Zen Hei", "儷黑 Pro", "LiHei Pro", 
                     "微軟正黑體", "Microsoft JhengHei", "標楷體", DFKai-SB, sans-serif;
            }
        </style>
        <script src="<%= request.getContextPath() %>/js/jquery.js" type="text/javascript"></script>
        <script>
            function logout(){
                $.ajax({url:'<%= request.getContextPath() %>/logout',
                        method:'POST',
                        dataType:"json"}
                ).done(doneHandler)
                 .fail(failHandler);
                 //.always(completeHandler);
                
            }
            
            function doneHandler(result){
                alert(result);
                
                var html = "<a href='<%= request.getContextPath() %>/register.html'>註冊</a> | " +
                        "<a href='<%= request.getContextPath() %>/login'>登入</a> | ";
                $("#memberSpan").html(html);
                $("#welcomeSpan").text("你好!");
                
            }
            
            function failHandler(xhr, textStatus){
                alert( "Request failed: " + textStatus );
            }
            
            function completeHandler(xhr, textStatus){
                alert("完成");
            }
        </script>            
    </head>
    <%        
        Customer member = (Customer)session.getAttribute("member");        
    %>
    <body>
        <div id="header">
            <h1>Very Good Book 
                <sub style="font-size:smaller">
                    <%= request.getParameter("banner_subtitle")==null?"歡迎光臨":request.getParameter("banner_subtitle")%>
                </sub>
            </h1>
            <hr>
        </div>
        <div id="nav">
            <a href='<%= request.getContextPath() %>/products_list.jsp'>產品清單</a> | 
            <% if(member==null) {%>
            <span id="userSpan">
                <a href='<%= request.getContextPath() %>/register.jsp'>註冊</a> | 
                <a href='<%= request.getContextPath() %>/login'>登入</a> | 
            </span>
            <%}else{  %>
            <span id="memberSpan">
                <a href='javascript:logout()'>登出(ajax)</a> |
                <a href='<%= request.getContextPath() %>/logout'>登出</a> | 
                <a href='<%= request.getContextPath() %>/member/update.jsp'>會員修改</a> | 
            </span>
            <%}  %>                        
            <span id="welcomeSpan"style="text-align: right;float:right">
<%--                你好<%= member!=null?"," + member.getName():"" %>--%>
                你好 ${not empty member?",":""} ${not empty member ? member.name :"!"}
            </span>
            <hr>
        </div>
