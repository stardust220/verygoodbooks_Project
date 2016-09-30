package vgb.test;

import vgb.domain.Customer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TestCustomerAge {

    public static void main(String[] args) throws Exception{
            Customer c = new Customer();
            System.out.println("c.birthday = " + c.getBirthday());

            c.setBirthday("1990/8/12");
            System.out.println("c.birthday = " + c.getBirthday());

            c.setBirthday(new GregorianCalendar(1990, 8 - 1, 13).getTime());
            System.out.println("c.birthday = " + c.getBirthday());

            c.setBirthday(1990, 8, 14);
            System.out.println("c.birthday = " + c.getBirthday());

            //計算生日
//        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
//        System.out.println("thisYear = " + thisYear);
//        
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(c.birthday);
//        
//        int birthYear = calendar.get(Calendar.YEAR);
//        
//        int age = thisYear-birthYear;
            System.out.println("c.age=" + c.getAge());

    }
}
