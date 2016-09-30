package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import vgb.domain.BloodType;
import vgb.domain.Customer;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  public String getServletInfo() {
    return "會員註冊";
  }

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
      out.write("        <title>會員註冊</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>會員註冊</h1>\n");
      out.write("        <hr>\n");
      out.write("        <a href=\"index.html\" title=\"首頁\">首頁</a> |  \n");
      out.write("        <a href='/vgb/products_list.html'>產品清單</a> |      \n");
      out.write("        <hr>\n");
      out.write("        ");

            List<String> errors = (List<String>) request.getAttribute("errors");
            if (errors != null && errors.size() > 0) out.println(errors);            
        
      out.write("\n");
      out.write("        <form method=\"POST\" action=\"register.do\">\n");
      out.write("            <p>\n");
      out.write("                <label for=\"id\">會員帳號:</label>\n");
      out.write("                <input type=\"text\" id=\"id\" name=\"id\" pattern=\"[A-Za-z][12]\\d{8}\" \n");
      out.write("                       value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" placeholder=\"請輸入身分證號\" required>\n");
      out.write("            </p>\n");
      out.write("            <p>\n");
      out.write("                <label for=\"name\">會員姓名:</label>\n");
      out.write("                <input type=\"text\" id=\"name\" name=\"name\" \n");
      out.write("                       value=\"");
      out.print( "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("name") );
      out.write("\"\n");
      out.write("                       placeholder=\"請輸入姓名\" required>\n");
      out.write("            </p>\n");
      out.write("            <p>\n");
      out.write("                <label for=\"pwd1\">會員密碼:</label>\n");
      out.write("                <input type=\"password\" id=\"pwd1\" name=\"password1\" placeholder=\"請輸入密碼\" minlength=\"6\" maxlength=\"20\" required><br>\n");
      out.write("                <label for=\"pwd2\">確認密碼:</label>\n");
      out.write("                <input type=\"password\" id=\"pwd2\" name=\"password2\" placeholder=\"請確認密碼\" minlength=\"6\" maxlength=\"20\" required>\n");
      out.write("            </p>\n");
      out.write("            <p>\n");
      out.write("                <label>會員性別:</label>\n");
      out.write("                <input type=\"radio\" id=\"male\" name=\"gender\" required value=\"");
      out.print(Customer.MALE);
      out.write("\"\n");
      out.write("                       ");
      out.print( "POST".equalsIgnoreCase(request.getMethod()) && 
                               String.valueOf(Customer.MALE).equals(request.getParameter("gender")) ?"checked": "" );
      out.write("\n");
      out.write("                       > <label for=\"male\">男</label>\n");
      out.write("                <input type=\"radio\" id=\"female\" name=\"gender\" required value=\"");
      out.print(Customer.FEMALE);
      out.write("\"\n");
      out.write("                       ");
      out.print( "POST".equalsIgnoreCase(request.getMethod()) && 
                               String.valueOf(Customer.FEMALE).equals(request.getParameter("gender")) ?"checked": "" );
      out.write("\n");
      out.write("                       > <label for=\"female\">女</label>\n");
      out.write("            </p>\n");
      out.write("            <p>\n");
      out.write("                <label for=\"email\">電子郵件:</label>\n");
      out.write("                <input type=\"email\" id=\"email\" name=\"email\" \n");
      out.write("                       value=\"");
      out.print( "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("email") );
      out.write("\"\n");
      out.write("                       placeholder=\"請輸入電子郵件\" required>\n");
      out.write("            </p>\n");
      out.write("            <p>\n");
      out.write("                <label for=\"birthday\">出生日期:</label>\n");
      out.write("                <input type=\"date\" id=\"birthday\" name=\"birthday\" \n");
      out.write("                       value=\"");
      out.print( "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("birthday") );
      out.write("\">\n");
      out.write("            </p> \n");
      out.write("            <!--        <p>\n");
      out.write("                            <label for=\"age\">會員年齡:</label>\n");
      out.write("                            <input type=\"number\" id=\"age\" name=\"age\" min=\"20\" max=\"80\">\n");
      out.write("                        </p> -->\n");
      out.write("            <p>\n");
      out.write("                <label for=\"address\">聯絡地址:</label>\n");
      out.write("                <input type=\"text\" id=\"address\" name=\"address\" placeholder=\"請輸入聯絡地址\"\n");
      out.write("                       value=\"");
      out.print( "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("address") );
      out.write("\">\n");
      out.write("                <!--<textarea id=\"address\" name=\"address\" placeholder=\"請輸入聯絡地址\"></textarea>-->\n");
      out.write("            </p>\n");
      out.write("            <p>\n");
      out.write("                <label for=\"phone\">聯絡電話:</label>\n");
      out.write("                <input type=\"tel\" id=\"phone\" name=\"phone\" placeholder=\"請輸入聯絡電話\"\n");
      out.write("                       value=\"");
      out.print( "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("phone") );
      out.write("\">\n");
      out.write("            </p>\n");
      out.write("            <p>\n");
      out.write("                <label>婚姻狀況:</label>\n");
      out.write("                <input type=\"checkbox\" id=\"married\" name=\"married\" \n");
      out.write("                       ");
      out.print(  "GET".equalsIgnoreCase(request.getMethod())?"": 
                               (request.getParameter("married")!=null?"checked":"") );
      out.write("><label for=\"married\">已婚</label>\n");
      out.write("            </p>\n");
      out.write("            <p>\n");
      out.write("                <label for='blood_type'>會員血型:</label>\n");
      out.write("                <select id ='blood_type' name='bloodType'>\n");
      out.write("                    <option value=\"\">請選擇...</option>\n");
      out.write("                    ");
 for (BloodType bType : BloodType.values()) {
      out.write("\n");
      out.write("                    <option value=\"");
      out.print( bType.name());
      out.write("\" \n");
      out.write("                            ");
      out.print( "POST".equalsIgnoreCase(request.getMethod()) && bType.name().equals(request.getParameter("bloodType")) ?"selected":"" );
      out.write('>');
      out.print( bType);
      out.write("</option>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                </select>\n");
      out.write("            </p>            \n");
      out.write("            <p>\n");
      out.write("                <a href=\"javascript:refresh()\" title=\"點選圖片即可更新\"><img id=\"checkImg\" src=\"images/register_check.jpg\"></a><br>\n");
      out.write("                <label for=\"check_code\">驗證碼:</label>\n");
      out.write("                <input type=\"text\" id=\"check_code\" name=\"checkCode\" placeholder=\"請輸入驗證碼\" \n");
      out.write("                       value=\"");
      out.print( "GET".equalsIgnoreCase(request.getMethod())?"": request.getParameter("checkCode") );
      out.write("\">\n");
      out.write("                       required>\n");
      out.write("                <script>\n");
      out.write("                    function refresh() {\n");
      out.write("                        var checkImg = document.getElementById(\"checkImg\");\n");
      out.write("                        checkImg.src = \"images/RegisterImageCheckServlet?refresh=\" + new Date();\n");
      out.write("                    }\n");
      out.write("                </script>                \n");
      out.write("            </p>\n");
      out.write("            <input type=\"submit\" value=\"確定註冊\">\n");
      out.write("        </form>\n");
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
