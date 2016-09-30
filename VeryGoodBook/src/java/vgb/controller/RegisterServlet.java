/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register.do"})
public class RegisterServlet extends HttpServlet {

    private String inputPage = "/register.jsp"; //絕對路徑
    private String okPage = "/WEB-INF/views/register_ok.jsp"; //絕對路徑
    private boolean redirectFlag = false; //forward

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
        //1. 取得Form Data(), Check Data
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String checkCode = request.getParameter("checkCode");
        if (id == null || (id = id.trim()).length() == 0) {
            errors.add("會員帳號必須輸入");
        }

        if (name == null || (name = name.trim()).length() == 0) {
            errors.add("會員姓名必須輸入");
        }

        if (!(password1 != null && password2 != null && password1.equals(password2))) {
            errors.add("會員密碼與確認密碼必須輸入且內容一致");
        }

        if (gender == null
                || (gender.charAt(0) != Customer.MALE && gender.charAt(0) != Customer.FEMALE)) {
            errors.add("會員性別不正確");
        }

        if (email == null || (email = email.trim()).length() == 0) {
            errors.add("電子郵件必須輸入");
        }

        HttpSession session = request.getSession();
        if (checkCode == null || (checkCode = checkCode.trim()).length() == 0) {
            errors.add("驗證碼必須輸入");
        } else {
            //須加入驗證碼檢查            
            String oldCheckCode = (String) session.getAttribute("RegisterImageCheckServlet");
            if (!checkCode.equalsIgnoreCase(oldCheckCode)) {
                errors.add("驗證碼不正確");
            }
        }

        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String married = request.getParameter("married");
        String bloodType = request.getParameter("bloodType");

        if (errors.isEmpty()) {
            session.removeAttribute("RegisterImageCheckServlet");

            //2. 呼叫商業邏輯: 建立CustomerService，呼叫register(customer)
            try {
                //2.1建立Customer物件
                Customer c = new Customer();
                c.setId(id);
                c.setName(name);
                c.setPassword(password1);
                c.setGender(gender.charAt(0));
                c.setEmail(email);

                c.setBirthday(birthday);
                c.setAddress(address);
                c.setPhone(phone);
                c.setMarried(married != null);
                c.setBloodType(bloodType);

                //2.2 呼叫CustomerService的register(Customer)
                CustomerService service = new CustomerService();
                service.register(c);
                //3.1 當2為登入成功, forward到成功畫面(okPage)  
                request.setAttribute("customer", c);
                RequestDispatcher dispatcher = request.getRequestDispatcher(okPage);
                dispatcher.forward(request, response);

                return;
            } catch (VGBException ex) {
                if (ex.getCause() != null) {
                    errors.add(ex.toString() + ":" + ex.getCause().getMessage());
                } else {
                    errors.add(ex.toString());
                }
            } catch (Exception ex) {
                errors.add(ex.toString());
            }

        }

        //3.2 當2為註冊失敗, forward inputPage畫面...
        request.setAttribute("errors", errors);
        RequestDispatcher dispatcher = request.getRequestDispatcher(inputPage);
        dispatcher.forward(request, response);
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
