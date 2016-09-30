/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.model;

import vgb.domain.Book;
import vgb.domain.Customer;
import vgb.domain.Product;
import vgb.domain.VIP;

/**
 *
 * @author Administrator
 */
public class OrderService {

    public double order(Customer c, Product p, int quantity) {
        //if (c != null && p != null) {
            double amount = p.getUnitPrice() * quantity;
            if (c instanceof VIP) { // && !(p instanceof Book)　
                VIP vip = (VIP) c; //casting, run-time error
                amount = amount * (100 - vip.getDiscount()) / 100;
            }

            return amount;
//        }else{
//            //TODO: throw
//            System.out.println("Error!");
//            return -100;
//        }
    }

}
