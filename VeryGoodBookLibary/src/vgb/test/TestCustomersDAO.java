/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import vgb.domain.BloodType;
import vgb.domain.Customer;
import vgb.domain.VGBException;
import vgb.domain.VIP;
import vgb.model.CustomersDAO;

/**
 *
 * @author Administrator
 */
public class TestCustomersDAO {

    public static void main(String[] args) {
        if (Customer.checkId("A223456763")) {
            try {
                Customer c = new VIP("A223456763",
                        "123456", "李三娘");
                c.setGender(Customer.FEMALE);
                c.setEmail("fourth.lee.tw@gmail.com");
                c.setBirthday("1990/12/31");
                c.setPhone("0987654321");
                c.setAddress("台北市復興北路99號14F");
                c.setMarried(true);
                c.setBloodType(BloodType.AB);                   
                
                CustomersDAO dao = new CustomersDAO();
                
                //1. 新增
                dao.insert(c);

                //2. 查詢 by pkey
                Customer c2 = dao.get("A223456763");
                System.out.println(c2);
                if (c2 != null) {                    
                    c2.setAddress("台北市復興北路99號12F");                    
                    //3. 修改 by pkey
                    dao.update(c2);
                    
                    //4. 刪除 by pkey
                    System.out.println(c2=dao.get("A223456763"));                    
                    dao.delete(c2); 
                    System.out.println(dao.get("A223456763"));
                }
                
                //5. 查詢全部
                System.out.println(dao.getAll());
            } catch (VGBException ex) {
                Logger.getLogger(
                        TestCustomersDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        } else {
            System.out.println("id不正確");
        }
    }
}
