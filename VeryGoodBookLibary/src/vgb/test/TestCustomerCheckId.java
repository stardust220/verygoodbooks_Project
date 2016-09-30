/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;

import vgb.domain.Customer;

/**
 *
 * @author Administrator
 */
public class TestCustomerCheckId {
    public static void main(String[] args) {
        System.out.println(Customer.checkId("A223456781"));
        
        Customer c = new Customer();
        System.out.println(c.checkId("A123456789"));
    }
}
