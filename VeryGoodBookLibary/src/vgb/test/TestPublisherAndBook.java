package vgb.test;


import vgb.domain.Book;
import vgb.domain.Publisher;
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class TestPublisherAndBook {
    public static void main(String[] args) {        
        Publisher pub = new Publisher();
        pub.setId(1);
        pub.setName("悅知文化");
        pub.setPhone("27198811");
        pub.setAddress("台北市松山區復興北路99號12樓");
        
        System.out.println("pub.id = " + pub.getId());
        System.out.println("pub.name = " + pub.getName());
        System.out.println("pub.phone = " + pub.getPhone());
        System.out.println("pub.address = " + pub.getAddress());
        System.out.println("pub = " + pub); 
        
        Book book = new Book();
        book.setId(1);
        book.setName("Microsoft SQL Server 2016資訊安全實戰");
        book.setAutherName("胡百敬");
        book.setPublishDate(new GregorianCalendar(2016,7-1,8).getTime());
        book.setUnitPrice(620);
        book.setDiscount(15);
        book.setStock(5);
        book.setPhotoUrl("http://www.delightpress.com.tw/images/books/cover/SKUD00027.jpg");
        book.setIsbn("978-986-93165-5-2");
        book.setPublisher(pub);
        
        System.out.println("book.id = " + book.getId());
        System.out.println("book.name = " + book.getName());
        System.out.println("book.subtitle = " + book.getSubtitle());
        System.out.println("book.autherName = " + book.getAutherName());
        System.out.println("book.publisherDate = " + book.getPublishDate());
        System.out.println("book.unitPrice = " + book.getUnitPrice());
        System.out.println("book.discount = " + book.getDiscount());
        System.out.println("book.stock = " + book.getStock());
        System.out.println("book.photoUrl = " + book.getPhotoUrl());
        System.out.println("book.isbn = " + book.getIsbn());
        System.out.println("book.publisher = " + book.getPublisher().getName());
        
    }
}
