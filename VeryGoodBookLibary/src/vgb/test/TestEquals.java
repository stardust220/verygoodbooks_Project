/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import vgb.domain.Book;
import vgb.domain.Customer;
import vgb.domain.Product;
import vgb.domain.VGBException;
import vgb.domain.VIP;

/**
 *
 * @author Administrator
 */
public class TestEquals {

    public static void main(String[] args) {
        try {
            // ==, !=
            int i = 1;
            double j = 1;
            System.out.println(i == j);//true

            Customer c1 = new Customer("A123456789", "123456", "張三");
            Customer c2 = new VIP();
            c2.setId("A123456789");
            c2.setName("張三");
            c2.setPassword("123456");
            System.out.println(c1 == c2);//false
            System.out.println(c1.equals(c2)); //true

//        c2 = c1;
//        System.out.println(c1 == c2); //true
//        System.out.println(c1.equals(c2));//true
            Product p1 = new Product();
            p1.setId(1);
            p1.setName("色鉛筆");
            p1.setUnitPrice(620);

            Book b1 = new Book();
            b1.setId(1);
            b1.setName("色鉛筆");
            b1.setUnitPrice(620);

            Product p2 = b1;
            System.out.println("p1==p2 : " + (p1 == p2));//false
            System.out.println("p1.equals(p2): " + p1.equals(p2));//false

            System.out.println(p1);
            System.out.println(p2);
            String s1 = "Hello";
            String s2 = new String("Hello");
            System.out.println(s1 == s2); //false
            System.out.println(s1.equals(s2));//true

            s1 = "hello";
            System.out.println(s1 == s2); //false
            System.out.println(s1.equals(s2));//false
            System.out.println(s1.equalsIgnoreCase(s2));//true
        } catch (VGBException ex) {
            Logger.getLogger(TestEquals.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
