
import java.util.HashMap;
import java.util.Map;
import vgb.domain.Customer;
import vgb.domain.Product;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class ShoppingCart {
    private Customer member;

    public Customer getMember() {
        return member;
    }

    public void setMember(Customer member) {
        this.member = member;
    }
    private Map<Product,Integer> cart;
    
    public ShoppingCart() {
        cart = new HashMap<>();
    }
    
    public void add(Product p){
        this.add(p,1);
    }
    
    public void add(Product p, int quantity){
        Integer oldQuantity = cart.get(p);
        if(oldQuantity!=null){
            quantity += oldQuantity;
        }
        cart.put(p, quantity);
    }
    
    public void update (Product p, int quantity){
        cart.put(p,quantity);
    }
    public void remove (Product p){
        cart.remove(p);
    }
    
}
