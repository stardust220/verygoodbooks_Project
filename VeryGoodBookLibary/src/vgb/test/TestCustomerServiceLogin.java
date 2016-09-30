package vgb.test;


import java.util.logging.Level;
import java.util.logging.Logger;
import vgb.model.CustomerService;
import vgb.domain.Customer;
import vgb.domain.VGBException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class TestCustomerServiceLogin {
    public static void main(String[] args) {
        try {
            CustomerService service = new CustomerService();
            Customer c = service.login("A123456789", "12346");
            System.out.println("c = " + c);
            if(c==null){
                System.out.println("登入失敗");
            }else{
                System.out.println("歡迎光臨，" + c.getName());
            }
            service.register(null);
        } catch (VGBException ex) {
            Logger.getLogger(TestCustomerServiceLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
