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
import vgb.model.OrderService;

/**
 *
 * @author Administrator
 */
public class TestOrderService {
    public static void main(String[] args) {
        try {
            Customer c =
                    new Customer("A123456789", "123456","張三");
            
            
            Product p = new Product();
            p.setId(100);
            p.setName("色鉛筆");
            p.setUnitPrice(620);
            
            Book book = new Book();
            book.setId(1);
            book.setName("Java SE 8技術手冊");
            book.setUnitPrice(620);
            book.setDiscount(10);
            
            OrderService service = new OrderService();
            System.out.println(service.order(c, p, 2));//1240
            
            double amount = service.order(c, book, 2);
            System.out.println("amount = " + amount);//1240-124
            
            
            VIP vip = new VIP();
            vip.setId("A223456781");
            vip.setName("林梅莉");
            vip.setDiscount(15);
            System.out.println(service.order(vip, p, 2));//1240*.85
            double amount2 = service.order(vip, book, 2);
            System.out.println("amount = " + amount2);//(1240-124)*.85
        } catch (VGBException ex) {
            Logger.getLogger(TestOrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
