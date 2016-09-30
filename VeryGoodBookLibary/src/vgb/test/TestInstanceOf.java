/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;

import vgb.domain.*;

/**
 *
 * @author Administrator
 */
public class TestInstanceOf {
    public static void main(String[] args) {
        Object o = new Object();
        Customer c = new Customer();
        VIP vip = new VIP();
        
        System.out.println(o.getClass() == Object.class); //true
        //System.out.println(c.getClass() == Object.class); //compile-time error
        //System.out.println(vip.getClass() == Object.class); //compile-time error
        System.out.println(o instanceof Object); //true
        System.out.println(c instanceof Object); //true
        System.out.println(vip instanceof Object); //true

        System.out.println(o.getClass() == Customer.class); //false
        System.out.println(c.getClass() == Customer.class); //true
        //System.out.println(vip.getClass() == Customer.class); //compile-time error
        System.out.println(o instanceof Customer); //false
        System.out.println(c instanceof Customer); //true
        System.out.println(vip instanceof Customer); //true        
        
        System.out.println(o.getClass() == VIP.class); //false
        System.out.println(c.getClass() == VIP.class); //false
        System.out.println(vip.getClass() == VIP.class); //true
        System.out.println(o instanceof VIP); //false
        System.out.println(c instanceof VIP); //false
        System.out.println(vip instanceof VIP); //true        

        Object o1 = null;
        Customer c1 = null;
        VIP vip1 = null;
        System.out.println(o1 instanceof Object); //false
        System.out.println(c1 instanceof Object); //false
        System.out.println(vip1 instanceof Object); //false

        System.out.println(o1 instanceof Customer); //false
        System.out.println(c1 instanceof Customer); //false
        System.out.println(vip1 instanceof Customer); //false
        
        System.out.println(o1 instanceof VIP); //false
        System.out.println(c1 instanceof VIP); //false
        System.out.println(vip1 instanceof VIP); //false        
        
        
    }
}
