<%@page import="vgb.domain.*,java.util.*,vgb.model.BookService"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>產品清單</title>
        <!--<meta charset="BIG5">-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="decoration.css">
        <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>        
        <style>    
            body{font-family: Arial, "文泉驛正黑", "WenQuanYi Zen Hei", "儷黑 Pro", "LiHei Pro", 
                     "微軟正黑體", "Microsoft JhengHei", "標楷體", DFKai-SB, sans-serif;
                 font-size:14px;
            }
        </style>
        
        <script>
            function helloHandler(){
                alert("Hello!");
            }
//            function addCartHandler(bid){
//                alert("已放入購物車: No." + bid);
//            }
//            $('.star-rating input + i').click(changePhotoHandler);
//            function changePhotoHandler() {
//                // console.log($(this).index());
//                alert('dfsdfs');
//                $('.choice').text( $(this).val() + ' stars' );
//            }
            $("input").attr("radio").change(
              alert("dfsdf");
              function(){
                 
                $('.choice').text( $(this).val() + ' stars' );
              } 
            );
        </script>
    </head>
    <body>
        <div id="header">
            <h1>Very Good Book <span style="font-size:smaller;vertical-align: sub">產品清單</span></h1>
            <hr>
        </div>
        <div id="nav">
            <a href='./'>首頁</a> | 
            <a href='register.html'>註冊</a> | 
            <a href='login.html'>登入</a> | 
            <a href='member/update.html'>會員修改</a> |             
            <hr>
        </div>
        <div id="article" style="height:75vh;overflow: auto">
            <%
                request.setCharacterEncoding("UTF-8"); //HTTP GET, Tomcat預設為UTF-8, 其他的Web Server不一定
                String[] category = request.getParameterValues("category");
                int type = 0;
                if(category!=null){
                    for(int i=0;i<category.length;i++){
                        type+=Integer.parseInt(category[i]);
                    }
                }else{
                    type = 1;
                }
                String search = request.getParameter("search");                
                BookService service = new BookService();
                Collection<Book> list = null;
                if(search==null || (search=search.trim()).length()==0){
                    list = service.getAll();                    
                }else{                    
                    list = service.getBooks(search, type);
                }
            %>
            <%--<%= // list%>--%>
<%--            <p>
                <%= type%>
                <%= search%>
            </p>--%>
            <form method="POST">
                <input type="checkbox" name="category" id="cat1" value="1" <%=(type&1)>0?"checked":""%>><label for="cat1">書名</label>
                <input type="checkbox" name="category" id="cat2" value="2" <%=(type&2)>0?"checked":""%>><label for="cat1">作者</label>
                <input type="checkbox" name="category" id="cat3" value="4" <%=(type&4)>0?"checked":""%>><label for="cat1">出版商</label>
                    
                <input style="width:45%" type="search" id="search" name="search" 
                       value="<%= request.getParameter("search")==null?"":request.getParameter("search") %>" placeholder="請輸入書名/作者姓名/出版商名/...">
                <input type="submit" value="查詢">
            </form>
            <hr>
            <div>
                <ul>
                    <% for(Book book:list) {%>
                    <li style="display:inline-block;width:250px;height:350px;overflow: auto;box-shadow: 2px 2px 2px gray;padding:5px">
                        <h4><%= book.getName() %></h4>
                        <div style="width:160px;margin:auto;">
                            <img style="width:120px" src="<%= book.getPhotoUrl() %>">                        
                        </div>
                        <div><b>作者:<%= book.getAutherName() %></b> <span>出版: <%= book.getPublisher().getName() %> </span></div>
                        <div style="font-size:small;float:left;width:60%">                            
                            原價: <%= book.getListPrice() %> <br>
                            售價: <%= book.getUnitPrice() %> <br>
                            折扣: <%= 100-book.getDiscount() %>折<br>
                        </div>
                        <div style="float:left;width:40%">
                            <a href="javascript:addCartHandler(<%= book.getId() %>)"><img style="width:48px" src="images/cart.png" alt=""></a>
                         </div>    
                            <br>
                       <div class="star-rating">
                        <input type="radio" name="rating" value="1"><i class="rating"></i>
                        <input type="radio" name="rating" value="2"><i class="rating"></i>
                        <input type="radio" name="rating" value="3"><i class="rating"></i>
                        <input type="radio" name="rating" value="4"><i class="rating"></i>
                        <input type="radio" name="rating" value="5"><i class="rating"></i>
                       </div>
                       <br>
                        <strong class="choice">Choose a rating</strong>
                    </li>
                    <%}%>
               
                </ul>                
            </div>
        </div>
        <div id="footer">
            <hr>
            Copyright &COPY; VeryGoodBook Co.
        </div>
    </body>
</html>
