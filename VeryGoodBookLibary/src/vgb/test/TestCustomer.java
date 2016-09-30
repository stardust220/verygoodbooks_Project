package vgb.test;

import vgb.domain.Customer;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import vgb.domain.BloodType;

/**
 *
 * @author Administrator
 */
public class TestCustomer {

    public static void main(String[] args)throws Exception {
       try {
            int i = 1;
            int j = i;
            j = i + 1;
            System.out.println("i = " + i);//1
            System.out.println("j = " + j);//2

            Customer c1;

//        c1=new Customer();
//        c1.setId("A223456781");
//        c1.setPassword("123456");
//        c1.setName("張三丰");
            c1 = new Customer("A123456789", "123456", "張三丰");
            c1.setEmail("admin@uuu");
            char m = 77; //77是M的ascii字碼
            c1.setGender(m);//型別不具安全性
            c1.setGender(Customer.MALE);
            Date bDate = new GregorianCalendar(2015, 7 - 1, 1).getTime();
            c1.setBirthday(bDate);
            c1.setBloodType(BloodType.A);

            System.out.println("c1.id = " + c1.getId());
            System.out.println("c1.password = " + c1.getPassword());
            System.out.println("c1.name = " + c1.getName());
            System.out.println("c1.email = " + c1.getEmail());
            System.out.println("c1.gender = " + c1.getGender());//列印出的資料不具意義
            System.out.println("c1.gender = " + (c1.getGender() == Customer.FEMALE ? '女' : '男'));
            System.out.println("c1.birthday = " + c1.getBirthday());
            System.out.println("c1.married = " + c1.isMarried());
            System.out.println("c1.bloodType = " + c1.getBloodType());
            System.out.println("c1=" + c1);

            Customer c2 = new Customer();
            c2.setId(c1.getId());
            c2.setPassword("223456");
            c2.setName("林梅莉");
            c2.setEmail(c1.getEmail());
            c2.setGender(Customer.FEMALE);
            System.out.println("c2.id = " + c2.getId());
            System.out.println("c2.password = " + c2.getPassword());
            System.out.println("c2.name = " + c2.getName());
            System.out.println("c2.email = " + c2.getEmail());
            System.out.println("c2.gender = " + (c2.getGender() == Customer.FEMALE ? '女' : '男'));
            System.out.println("c2=" + c2);
        } catch (Exception ex) {
            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
