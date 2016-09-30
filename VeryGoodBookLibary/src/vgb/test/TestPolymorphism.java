/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;

import java.text.NumberFormat;
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
public class TestPolymorphism {
    public static void main(String[] args) {
        try {
            Object o1 = new Object();//General
            String s = "Hello";   //General
            System.out.println(s.length());
            
            Object o = s; //Polymorphism
            //System.out.println(o.length()); //compile-time error, length()是String的新方法
            System.out.println("o:"+o.toString());//toString()是Object的方法, 但String已經override了toString()
            System.out.println("o 參考到的實體物件是:" + o.getClass().getName());
            
            VIP vip = new VIP(); //General
            vip.setId("A123456789");
            vip.setName("張三");
            vip.setDiscount(15);
            
            Customer c = vip;//Polymorphism
            System.out.println(c.getId());
            o = vip; //Polymorphism
            System.out.println(o.toString());
            //System.out.println(o.getId()); //compile-time error, getId()是Customer的新方法
            System.out.println(o.toString());//toString()是Object的方法, 但Customer已經override了toString()
            
            //Book p = new Book(); //General
            Product p = new Book(); //Polymorphism
            p.setId(1);
            p.setName("Test");
            //p.setAutherName("");//compile-time error, setAutherName()是Book的新方法
            
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            System.out.println(nf.getClass().getName());
        } catch (VGBException ex) {
            Logger.getLogger(TestPolymorphism.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
