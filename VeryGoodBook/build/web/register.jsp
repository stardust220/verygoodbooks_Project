<%@page import="java.util.List"%>
<%@page import="vgb.domain.BloodType"%>
<%@page import="vgb.domain.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8" info="會員註冊"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>會員註冊</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>會員註冊</h1>
        <hr>
        <a href="index.html" title="首頁">首頁</a> |  
        <a href='/vgb/products_list.html'>產品清單</a> |      
        <hr>
        <%
            List<String> errors = (List<String>) request.getAttribute("errors");
            if (errors != null && errors.size() > 0) out.println(errors);            
        %>
        <form method="POST" action="register.do">
            <p>
                <label for="id">會員帳號:</label>
                <input type="text" id="id" name="id" pattern="[A-Za-z][12]\d{8}" 
                       value="${param.id}" placeholder="請輸入身分證號" required>
            </p>
            <p>
                <label for="name">會員姓名:</label>
                <input type="text" id="name" name="name" 
                       value="<%= "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("name") %>"
                       placeholder="請輸入姓名" required>
            </p>
            <p>
                <label for="pwd1">會員密碼:</label>
                <input type="password" id="pwd1" name="password1" placeholder="請輸入密碼" minlength="6" maxlength="20" required><br>
                <label for="pwd2">確認密碼:</label>
                <input type="password" id="pwd2" name="password2" placeholder="請確認密碼" minlength="6" maxlength="20" required>
            </p>
            <p>
                <label>會員性別:</label>
                <input type="radio" id="male" name="gender" required value="<%=Customer.MALE%>"
                       <%= "POST".equalsIgnoreCase(request.getMethod()) && 
                               String.valueOf(Customer.MALE).equals(request.getParameter("gender")) ?"checked": "" %>
                       > <label for="male">男</label>
                <input type="radio" id="female" name="gender" required value="<%=Customer.FEMALE%>"
                       <%= "POST".equalsIgnoreCase(request.getMethod()) && 
                               String.valueOf(Customer.FEMALE).equals(request.getParameter("gender")) ?"checked": "" %>
                       > <label for="female">女</label>
            </p>
            <p>
                <label for="email">電子郵件:</label>
                <input type="email" id="email" name="email" 
                       value="<%= "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("email") %>"
                       placeholder="請輸入電子郵件" required>
            </p>
            <p>
                <label for="birthday">出生日期:</label>
                <input type="date" id="birthday" name="birthday" 
                       value="<%= "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("birthday") %>">
            </p> 
            <!--        <p>
                            <label for="age">會員年齡:</label>
                            <input type="number" id="age" name="age" min="20" max="80">
                        </p> -->
            <p>
                <label for="address">聯絡地址:</label>
                <input type="text" id="address" name="address" placeholder="請輸入聯絡地址"
                       value="<%= "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("address") %>">
                <!--<textarea id="address" name="address" placeholder="請輸入聯絡地址"></textarea>-->
            </p>
            <p>
                <label for="phone">聯絡電話:</label>
                <input type="tel" id="phone" name="phone" placeholder="請輸入聯絡電話"
                       value="<%= "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("phone") %>">
            </p>
            <p>
                <label>婚姻狀況:</label>
                <input type="checkbox" id="married" name="married" 
                       <%=  "GET".equalsIgnoreCase(request.getMethod())?"": 
                               (request.getParameter("married")!=null?"checked":"") %>><label for="married">已婚</label>
            </p>
            <p>
                <label for='blood_type'>會員血型:</label>
                <select id ='blood_type' name='bloodType'>
                    <option value="">請選擇...</option>
                    <% for (BloodType bType : BloodType.values()) {%>
                    <option value="<%= bType.name()%>" 
                            <%= "POST".equalsIgnoreCase(request.getMethod()) && bType.name().equals(request.getParameter("bloodType")) ?"selected":"" %>><%= bType%></option>
                    <%}%>
                </select>
            </p>            
            <p>
                <a href="javascript:refresh()" title="點選圖片即可更新"><img id="checkImg" src="images/register_check.jpg"></a><br>
                <label for="check_code">驗證碼:</label>
                <input type="text" id="check_code" name="checkCode" placeholder="請輸入驗證碼" 
                       value="<%= "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("checkCode") %>">
                       required>
                <script>
                    function refresh() {
                        var checkImg = document.getElementById("checkImg");
                        checkImg.src = "images/RegisterImageCheckServlet?refresh=" + new Date();
                    }
                </script>                
            </p>
            <input type="submit" value="確定註冊">
        </form>
    </body>
</html>
