package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import vgb.domain.*;
import java.util.*;
import vgb.model.BookService;

public final class products_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<!--\n");
      out.write("To change this license header, choose License Headers in Project Properties.\n");
      out.write("To change this template file, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>產品清單</title>\n");
      out.write("        <!--<meta charset=\"BIG5\">-->\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"decoration.css\">\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>        \n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js\"></script>        \n");
      out.write("        <style>    \n");
      out.write("            body{font-family: Arial, \"文泉驛正黑\", \"WenQuanYi Zen Hei\", \"儷黑 Pro\", \"LiHei Pro\", \n");
      out.write("                     \"微軟正黑體\", \"Microsoft JhengHei\", \"標楷體\", DFKai-SB, sans-serif;\n");
      out.write("                 font-size:14px;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("        \n");
      out.write("        <script>\n");
      out.write("            function helloHandler(){\n");
      out.write("                alert(\"Hello!\");\n");
      out.write("           \n");
      out.write("//            function addCartHandler(bid){\n");
      out.write("//                alert(\"已放入購物車: No.\" + bid);\n");
      out.write("//            }\n");
      out.write("//            $('.star-rating input + i').click(changePhotoHandler);\n");
      out.write("//            function changePhotoHandler() {\n");
      out.write("//                // console.log($(this).index());\n");
      out.write("//                alert('dfsdfs');\n");
      out.write("//                $('.choice').text( $(this).val() + ' stars' );\n");
      out.write("//            }\n");
      out.write("            $(\"input\").attr(\"radio\").change(\n");
      out.write("              alert(\"dfsdf\");\n");
      out.write("              function(){\n");
      out.write("                 \n");
      out.write("                $('.choice').text( $(this).val() + ' stars' );\n");
      out.write("              } \n");
      out.write("            );\n");
      out.write("     }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"header\">\n");
      out.write("            <h1>Very Good Book <span style=\"font-size:smaller;vertical-align: sub\">產品清單</span></h1>\n");
      out.write("            <hr>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"nav\">\n");
      out.write("            <a href='./'>首頁</a> | \n");
      out.write("            <a href='register.html'>註冊</a> | \n");
      out.write("            <a href='login.html'>登入</a> | \n");
      out.write("            <a href='member/update.html'>會員修改</a> |             \n");
      out.write("            <hr>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"article\" style=\"height:75vh;overflow: auto\">\n");
      out.write("            ");

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
            
      out.write("\n");
      out.write("            ");
      out.write('\n');
      out.write("\n");
      out.write("            <form method=\"POST\">\n");
      out.write("                <input type=\"checkbox\" name=\"category\" id=\"cat1\" value=\"1\" ");
      out.print((type&1)>0?"checked":"");
      out.write("><label for=\"cat1\">書名</label>\n");
      out.write("                <input type=\"checkbox\" name=\"category\" id=\"cat2\" value=\"2\" ");
      out.print((type&2)>0?"checked":"");
      out.write("><label for=\"cat1\">作者</label>\n");
      out.write("                <input type=\"checkbox\" name=\"category\" id=\"cat3\" value=\"4\" ");
      out.print((type&4)>0?"checked":"");
      out.write("><label for=\"cat1\">出版商</label>\n");
      out.write("                    \n");
      out.write("                <input style=\"width:45%\" type=\"search\" id=\"search\" name=\"search\" \n");
      out.write("                       value=\"");
      out.print( request.getParameter("search")==null?"":request.getParameter("search") );
      out.write("\" placeholder=\"請輸入書名/作者姓名/出版商名/...\">\n");
      out.write("                <input type=\"submit\" value=\"查詢\">\n");
      out.write("            </form>\n");
      out.write("            <hr>\n");
      out.write("            <div>\n");
      out.write("                <ul>\n");
      out.write("                    ");
 for(Book book:list) {
      out.write("\n");
      out.write("                    <li style=\"display:inline-block;width:250px;height:350px;overflow: auto;box-shadow: 2px 2px 2px gray;padding:5px\">\n");
      out.write("                        <h4>");
      out.print( book.getName() );
      out.write("</h4>\n");
      out.write("                        <div style=\"width:160px;margin:auto;\">\n");
      out.write("                            <img style=\"width:120px\" src=\"");
      out.print( book.getPhotoUrl() );
      out.write("\">                        \n");
      out.write("                        </div>\n");
      out.write("                        <div><b>作者:");
      out.print( book.getAutherName() );
      out.write("</b> <span>出版: ");
      out.print( book.getPublisher().getName() );
      out.write(" </span></div>\n");
      out.write("                        <div style=\"font-size:small;float:left;width:60%\">                            \n");
      out.write("                            原價: ");
      out.print( book.getListPrice() );
      out.write(" <br>\n");
      out.write("                            售價: ");
      out.print( book.getUnitPrice() );
      out.write(" <br>\n");
      out.write("                            折扣: ");
      out.print( 100-book.getDiscount() );
      out.write("折<br>\n");
      out.write("                        </div>\n");
      out.write("                        <div style=\"float:left;width:40%\">\n");
      out.write("                            <a href=\"javascript:addCartHandler(");
      out.print( book.getId() );
      out.write(")\"><img style=\"width:48px\" src=\"images/cart.png\" alt=\"\"></a>\n");
      out.write("                         </div>    \n");
      out.write("                            <br>\n");
      out.write("                       <div class=\"star-rating\">\n");
      out.write("                        <input type=\"radio\" name=\"rating\" value=\"1\"><i class=\"rating\"></i>\n");
      out.write("                        <input type=\"radio\" name=\"rating\" value=\"2\"><i class=\"rating\"></i>\n");
      out.write("                        <input type=\"radio\" name=\"rating\" value=\"3\"><i class=\"rating\"></i>\n");
      out.write("                        <input type=\"radio\" name=\"rating\" value=\"4\"><i class=\"rating\"></i>\n");
      out.write("                        <input type=\"radio\" name=\"rating\" value=\"5\"><i class=\"rating\"></i>\n");
      out.write("                       </div>\n");
      out.write("                       <br>\n");
      out.write("                        <strong class=\"choice\">Choose a rating</strong>\n");
      out.write("                    </li>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("               \n");
      out.write("                </ul>                \n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"footer\">\n");
      out.write("            <hr>\n");
      out.write("            Copyright &COPY; VeryGoodBook Co.\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
