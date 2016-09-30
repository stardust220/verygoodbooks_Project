/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;

import vgb.domain.Book;
import vgb.model.BookService;

/**
 *
 * @author Administrator
 */
public class TestBookPassByValue {
    public static void main(String[] args) {
        Book book = new Book();
        book.setId(1);
        book.setName("Java SE 8技術手冊");
        book.setAutherName("林信良");
        book.setUnitPrice(620);
        
        System.out.println("book.getId() = " + book.getId());
        System.out.println("book.getName() = " + book.getName());
        System.out.println("book.getAutherName() = " + book.getAutherName());
        System.out.println("book.getUnitPrice() = " + book.getUnitPrice());//620
        
//        double price = book.getUnitPrice() + 100;
//        book.setUnitPrice(price);

        BookService service = new BookService();
        
        service.addPrice(book.getUnitPrice());        
        System.out.println("book.getUnitPrice() = " + book.getUnitPrice());//720
        
        service.addPrice(book);
        System.out.println("book.getUnitPrice() = " + book.getUnitPrice());//720
    }
}
