package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Arrays;
import java.util.Enumeration;

public final class dump_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");

    request.setCharacterEncoding("UTF-8");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Dump</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"header\">\n");
      out.write("            <h1>Very Good Book <sub>Dump</sub></h1>\n");
      out.write("            <hr>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"nav\">\n");
      out.write("            <a href='");
      out.print( request.getContextPath() );
      out.write("'>首頁</a> | \n");
      out.write("            <hr>\n");
      out.write("                Server Host:");
      out.print( request.getLocalName() );
      out.write("<br>\n");
      out.write("                Server Address:");
      out.print( request.getLocalAddr());
      out.write("<br>\n");
      out.write("                Server Port:");
      out.print( request.getLocalPort() );
      out.write("<br>\n");
      out.write("                ContextPath:");
      out.print( application.getContextPath() );
      out.write("<br>\n");
      out.write("\n");
      out.write("                Remote Host:");
      out.print( request.getRemoteHost() );
      out.write("<br>\n");
      out.write("                Remote Address:");
      out.print( request.getRemoteAddr());
      out.write("<br>\n");
      out.write("                Remote Port:");
      out.print( request.getRemotePort() );
      out.write("<br>\n");
      out.write("                Request URL:");
      out.print( request.getRequestURL() );
      out.write("<br>\n");
      out.write("            <hr>\n");
      out.write("            <table width=\"100%\" cellpadding=\"5\" cellspacing=\"0\" border=\"1\">\n");
      out.write("                <caption>Headers Information</caption>\n");
      out.write("                <tbody>\n");
      out.write("                    ");
  int index = 0;
                        Enumeration<String> headerNames = request.getHeaderNames();
                        while(headerNames.hasMoreElements()){
                            String name = headerNames.nextElement();
                            String value = request.getHeader(name);                            
                            index++;
                    
      out.write("\n");
      out.write("                    <tr><td>");
      out.print( index );
      out.write("</td><td>");
      out.print( name );
      out.write("</td><td>");
      out.print( value );
      out.write("</td></tr>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("                <thead>\n");
      out.write("                    <tr><th colspan=\"2\">Header Name(共");
      out.print( index );
      out.write("筆)</th><th>Header Value</th></tr>\n");
      out.write("                </thead>\n");
      out.write("            </table>\n");
      out.write("            <hr>\n");
      out.write("            <table width=\"100%\" cellpadding=\"5\" cellspacing=\"0\" border=\"1\">\n");
      out.write("                <caption>Form Data</caption>\n");
      out.write("                <thead>\n");
      out.write("                    <tr><th colspan=\"1\">Param Name</th><th>Param Value</th></tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");
 
                        Enumeration<String> paramNames = request.getParameterNames();
                        while(paramNames.hasMoreElements()){
                            String name = paramNames.nextElement();
                            String[] values = request.getParameterValues(name);
                    
      out.write("\n");
      out.write("                    <tr><td>");
      out.print( name );
      out.write("</td><td>");
      out.print( Arrays.toString(values) );
      out.write("</td></tr>\n");
      out.write("                    ");
  } 
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("        </div>        \n");
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
