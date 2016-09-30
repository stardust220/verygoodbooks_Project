/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jws.soap.InitParam;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vgb.domain.Customer;
import vgb.domain.VGBException;
import vgb.model.CustomerService;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 10,
        urlPatterns = {"/login", "/LOGIN"}) //,"/login.do" })
public class LoginServlet extends HttpServlet {
    private String inputPage = "/login.jsp"; //絕對路徑
    //private String okPage = "/WEB-INF/views/login_ok.jsp"; //絕對路徑
    private String okPage = "/"; //絕對路徑:Hmepage
    private boolean redirectFlag = true;
    
//    public LoginServlet(){
//        System.out.println("LoginServlet created...");
//    }

    public void init() {
        String inputPage = this.getInitParameter("input-page");
        if (inputPage != null) {
            this.inputPage = inputPage;
        }

        String okPage = this.getInitParameter("ok-page");
        if (okPage != null) {
            this.okPage = okPage;
        }

        String redirectFlag = this.getInitParameter("redirect");
        if (redirectFlag != null && Boolean.valueOf(redirectFlag)) {
            this.redirectFlag = true;
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList<>();

        //System.out.println(new Date(session.getCreationTime()));
        //1. 取得請求中的form data: id, password, checkCode, 檢查不得為null或空白字串
        //request.setCharacterEncoding("UTF-8"); //可省略
        String id = request.getParameter("id");
        if (id == null || (id = id.trim()).length() == 0) {
            errors.add("必須輸入帳號");
        }

        String password = request.getParameter("password");
        if (password == null || (password = password.trim()).length() == 0) {
            errors.add("必須輸入密碼");
        }

        HttpSession session = request.getSession(false);
        System.out.println(session != null ? (session.getId() + session.isNew()) : "沒有舊的session");
        if (session != null && !session.isNew()) {
            String checkCode = request.getParameter("checkCode"); //user輸入
            String oldCheckCode = (String)session.getAttribute("ImageCheckServlet");            
            if (checkCode == null || (checkCode = checkCode.trim()).length() == 0 || oldCheckCode==null) {
                errors.add("必須輸入驗證碼");
            } else {
                //TODO: 完成驗證碼檢查, chap15
                if(!checkCode.equalsIgnoreCase(oldCheckCode)){
                    errors.add("驗證碼不正確");
                }
            }
        }else{
            errors.add("登入驗證不正確，可能逾時，請重新登入");
        }

        if (errors.isEmpty()) {
            session.removeAttribute("ImageCheckServlet"); 
            //2. 建立CustomerService，呼叫login(id, password)
            try {
                CustomerService service = new CustomerService();
                Customer c = service.login(id, password);

                //3.1 當2為登入成功, redirect到Home畫面
                //3.1.1 optional: add cookie
                String auto = request.getParameter("auto");
                
                Cookie idCookie = new Cookie("userid", c.getId());
                if(auto!=null){
                    idCookie.setMaxAge(24*60*60*30);
                }else{
                    idCookie.setMaxAge(0);
                }                
                response.addCookie(idCookie);
                
                Cookie autoCookie = new Cookie("auto", "checked");
                if(auto!=null){
                    autoCookie.setMaxAge(24*60*60*30);
                }else{
                    autoCookie.setMaxAge(0);
                }                
                response.addCookie(autoCookie);                
                
                //request.setAttribute("customer", c);
                session.setAttribute("member", c);
                //session.removeAttribute("ImageCheckServlet");                 
                if(this.redirectFlag){
                    response.sendRedirect(request.getContextPath()+okPage);
                }else{
                    RequestDispatcher dispatcher= request.getRequestDispatcher(okPage);
                    dispatcher.forward(request, response);
                }
                System.out.println("session timeout in: "+ session.getMaxInactiveInterval() + " sec.");
                //session.setMaxInactiveInterval(5*60); //5分鐘
                return;
            } catch (VGBException ex) {
                errors.add(ex.getMessage());
            } catch (Exception ex) {
                this.log("發生非預期錯誤:" + ex.getMessage(), ex);
                errors.add("發生非預期錯誤:" + ex.getMessage());
            }
        }

        //3.2 當2為登入失敗, forward login.jsp畫面
        request.setAttribute("errors", errors);
        RequestDispatcher dispatcher = request.getRequestDispatcher(inputPage);
        dispatcher.forward(request, response);
        //return;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        request.getRequestDispatcher(inputPage).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
