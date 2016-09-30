package vgb.domain;

/**
 *
 * @author Administrator
 */
public class VIP extends Customer{
    private int discount=10;

    public VIP() {
    }    

    public VIP(String id, String pwd, String name) {
        super(id, pwd, name);
    }

    public VIP(String id, String password, String name, String email, char gender) {
        super(id, password, name, email, gender);
    }

    public VIP(String id, String pwd, String name, int discount) {        
        this(id, pwd, name);
        this.discount = discount;
    }



//    public VIP(){
//        //super();
//         System.out.println("VIP created...");
//    }
    
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return super.toString() + "{" + "discount=" + discount + '}';
    }
    
}
