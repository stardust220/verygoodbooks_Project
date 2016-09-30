/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import vgb.domain.Customer;
import vgb.domain.VGBException;

/**
 *
 * @author Administrator
 */
public class TestFinalVarCustomer {
    public static void main(String[] args) {
        try {
            final Customer c = new Customer("A123456789", "123456", "張三");
            System.out.println(c);
            c.setId("A223456781");
            System.out.println(c);
            //c = new Customer(); //c is a final variable
        } catch (VGBException ex) {
            Logger.getLogger(TestFinalVarCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
