<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" info="會員登入"%>
<jsp:include page="/WEB-INF/subviews/header.jsp"/>
    <div id="article" style="height:75vh">
        <%
            List<String> errors = (List<String>)request.getAttribute("errors");
            if (errors!=null && errors.size()>0){
                out.println(errors);
            }
            
            Cookie[] cookies = request.getCookies();
            String uid="";
            String auto="";
            if(cookies!=null){
                for(int i=0;i<cookies.length;i++){
                    Cookie cookie = cookies[i];
                    if(cookie.getName().equals("userid")){
                        uid = cookie.getValue();                        
                    }else if(cookie.getName().equals("auto")){
                        auto = cookie.getValue();
                    }
                }
            }
        %>
        <form method="POST" action="login">
            <p>
                <label for="id">會員帳號:</label>
                <input type="text" id="id" name="id" placeholder="請輸入身分證號" 
                       value="<%= "GET".equalsIgnoreCase(request.getMethod())?uid:"" %>" required>
                <input id="auto" name="auto" type="checkbox" <%=auto%>><label for="auto">記住帳號</label>
            </p>
            <p>
                <label for="pwd">會員密碼:</label>
                <input type="password" id="pwd" name="password" placeholder="請輸入密碼" required>
            </p>
            <p>                
                <a href="javascript:refresh()"><image src="images/check.jpg" id="checkImg" title="點選即可更新圖片文字"></a><br>
                <label for="check_code">驗證碼:</label>
                <input type="text" id="check_code" name="checkCode" placeholder="請輸入驗證碼" required 
                       value="<%= "GET".equalsIgnoreCase(request.getMethod())?"":request.getParameter("checkCode")%>">
                <script>
                    function refresh(){
                        var checkImg = document.getElementById("checkImg");
                        checkImg.src="images/check.jpg?refresh=" + new Date();
                    }                    
                </script>
            </p>
            <input type="submit" value="確定登入">
        </form>
    </div>                       
<%@include  file="/WEB-INF/subviews/footer.jsp" %>
