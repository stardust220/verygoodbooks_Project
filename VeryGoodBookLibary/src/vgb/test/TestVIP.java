/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import vgb.domain.VGBException;
import vgb.domain.VIP;

/**
 *
 * @author Administrator
 */
public class TestVIP {
    public static void main(String[] args) {
        try {
            VIP vip = new VIP();
            vip.setId("A123456789");
            System.out.println(vip.getId());
            
            vip.setBirthday(1992, 9, 8);
            System.out.println("vip.getAge() = " + vip.getAge());
            
            vip.setDiscount(15);
            System.out.println("vip.getDiscount() = " + vip.getDiscount());
            
            System.out.println(vip);
        } catch (VGBException ex) {
            Logger.getLogger(TestVIP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
