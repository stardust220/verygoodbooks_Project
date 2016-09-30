/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;

import vgb.domain.BloodType;
import vgb.domain.Customer;

/**
 *
 * @author Administrator
 */
public class TestCustomerBloodType {
    public static void main(String[] args) {
        Customer c = new Customer();
        
        String bloodTypeStr = BloodType.B.name();     //....   
        c.setBloodType(BloodType.valueOf("B")); //....
        
        System.out.println("c.getBloodType() = " + c.getBloodType());  
        
        System.out.println("<select>");
        BloodType[] bloodTypeArray = BloodType.values();
        for(int i=0;i<bloodTypeArray.length;i++){
            System.out.println("<option>"+bloodTypeArray[i]+"</option>");
        }
        System.out.println("</select>");
    }
}
