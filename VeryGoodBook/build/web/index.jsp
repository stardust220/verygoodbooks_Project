<%@page contentType="text/html" pageEncoding="UTF-8" info="會員登入"%>
<%--
request.setCharacterEncoding("UTF-8");
--%>
<jsp:include page="/WEB-INF/subviews/header.jsp">
    <jsp:param  name="banner_subtitle" value="首頁" />
</jsp:include>
<div id="article" style="height:75vh">
    <p>拜訪人次:<%= application.getAttribute("app.visitors.count")%></p>
    <p>拜訪人次:${applicationScope["app.visitors.count"]}</p>

    <audio controls>
        <source src="http://www.w3schools.com/html/horse.ogg" type="audio/ogg">
        <source src="http://www.w3schools.com/html/horse.mp3" type="audio/mpeg">
        Your browser does not support the audio element.
    </audio>
</div>            
<%@include  file="/WEB-INF/subviews/footer.jsp" %>