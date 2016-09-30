package vgb.model;
import java.util.List;
import vgb.domain.Customer;
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
public class CustomerService {
    private CustomersDAO customersDAO = new CustomersDAO();
    
    /**
     * 提供客戶登入的商業邏輯
     * @param uid: 客戶帳號
     * @param pwd: 客戶密碼
     * @return 登入成功的客戶物件，若登入失敗則回傳null
     */
    public vgb.domain.Customer login(String uid, String pwd) throws VGBException{
        vgb.domain.Customer c = null;
        if(uid!=null && (uid=uid.trim()).length()>0 
                && pwd!=null && (pwd=pwd.trim()).length()>0){            
            c = customersDAO.get(uid);            
            if(c!=null && pwd.equals(c.getPassword())){
                return c;
            }else{
                throw new VGBException("帳號密碼不正確,登入失敗");
            }            
        }else{
            throw new IllegalArgumentException("登入必須輸入帳號密碼");
        }
    }
    
    public void register(Customer c) throws VGBException{
        //TODO: replace by JDBC insert....
        customersDAO.insert(c);
    }

    //delegate methods
    public void delete(Customer c) throws VGBException {
        customersDAO.delete(c);
    }

    public void update(Customer c) throws VGBException {
        customersDAO.update(c);
    }

    public Customer get(String id) throws VGBException {
        return customersDAO.get(id);
    }

    public List<Customer> getAll() throws VGBException {
        return customersDAO.getAll();
    }
    
    
    
}
