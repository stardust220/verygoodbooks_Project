package vgb.test;


import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TestCustomerContructor {
    public static void main(String[] args) {
        try {
            Customer c = new Customer("A123456789", "123456", "張三豐",
                    "sammul.chang@gmail.com", 'M');
            
            c.setBirthday("1990/5/5");
            c.setPhone("0225149191");
            c.setAddress("台北市復興北路99號");
            
            System.out.println("c.getId() = " + c.getId());
            System.out.println("c.getPassword() = " + c.getPassword());
            System.out.println("c.getName() = " + c.getName());
            System.out.println("c.getEmail() = " + c.getEmail());
            System.out.println("c.getGender() = " + c.getGender());
        } catch (VGBException ex) {
            Logger.getLogger(TestCustomerContructor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
