package vgb.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Book extends Product {
    private String subtitle;
    private String autherName;
    private Date publishDate;
    private int discount = 10;
    private String isbn;
    private Publisher publisher;

    /**
     * @return the subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * @param subtitle the subtitle to set
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * @return the autherName
     */
    public String getAutherName() {
        return autherName;
    }

    /**
     * @param autherName the autherName to set
     */
    public void setAutherName(String autherName) {
        this.autherName = autherName;
    }

    /**
     * @return the publishDate
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate the publishDate to set
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    
    public void setPublishDate(String sDate) throws VGBException{
        if(sDate!=null){
            sDate = sDate.replace('/', '-');
            try{
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
                this.setPublishDate(date);
            }catch(ParseException ex){
                throw new VGBException("出版日期格式不正確!");
            }
        }else{
            throw new java.lang.IllegalArgumentException("出版日期不得為null");
        }
    }
    


    /**
     * @return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {        
        this.discount = discount;
    }


    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the publisher
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
    //改寫Products的getUnitPrice(), 即為Overriding Method
    @Override
    public double getUnitPrice() {//取得售價
        double price = super.getUnitPrice();
        //price = price * (100-discount) / 100;        
        price *= (100D-discount) / 100;        
        return price;
    }
    
    public double getListPrice(){//取得定價
        return super.getUnitPrice();
    }

    @Override
    public String toString() {
        return super.toString() + "{子標題=" + subtitle 
                + ",作者=" + autherName + ",出版日=" + publishDate + ",售價=" + getUnitPrice()
                + ",折扣=" + discount + "%,isbn=" + isbn + ",出版商=" + publisher + '}';
    }
    
    
}
