/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.test;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vgb.domain.*;
import vgb.model.BooksDAO;
/**
 *
 * @author Administrator
 */
public class TestBooksDAO {
    public static void main(String[] args) {
        try {
            BooksDAO dao = new BooksDAO();
            
            Publisher publisher = new Publisher(1, "悅知文化");
            Book book = new Book();
//            book.setId(1);
//            book.setName("Microsoft SQL Server 2016資訊安全實戰");
//            book.setAutherName("許致學");
//            book.setSubtitle(null);
//            book.setPublisher(publisher);
//            book.setPublishDate("2016-07-08");
//            book.setUnitPrice(620);
//            book.setDiscount(21);
//            book.setStock(15);
//            book.setPhotoUrl("http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/001/072/13/0010721386.jpg&v=577a3ab9&w=348&h=348");
//            book.setIsbn("9789869316552");
//            dao.insert(book);
            
//            book.setId(2);
//            book.setPublisher(publisher);
//            book.setName("Information Architecture 100");
//            book.setSubtitle(":100個網站規劃必備的知識");
//            book.setAutherName("長谷川敦士");
//            book.setPublishDate("2013/11/29");
//            book.setUnitPrice(480);
//            book.setDiscount(21);
//            book.setStock(5);
//            book.setPhotoUrl("http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/001/061/88/0010618801.jpg&v=5295cb0b&w=348&h=348");
//            book.setIsbn("9789865912918");            
//            dao.insert(book);
//            
//            book.setId(0);
//            book.setPublisher(publisher);
//            book.setName("笑談軟體工程：敏捷開發法的逆襲");
//            book.setSubtitle("");
//            book.setAutherName("陳建村");
//            book.setPublishDate("2012-06-22");
//            book.setUnitPrice(550);
//            book.setDiscount(21);
//            book.setStock(12);
//            book.setPhotoUrl("http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/001/054/98/0010549884.jpg&v=4fe07522&w=348&h=348");
//            book.setIsbn("9789866072956");            
//            dao.insert(book);         
            
//            book.setId(0);
//            book.setPublisher(publisher);
//            book.setName("笑談軟體工程：敏捷開發法的逆襲");
//            book.setSubtitle("");
//            book.setAutherName("陳建村");
//            book.setPublishDate("2012-06-22");
//            book.setUnitPrice(550);
//            book.setDiscount(21);
//            book.setStock(12);
//            book.setPhotoUrl("http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/001/054/98/0010549884.jpg&v=4fe07522&w=348&h=348");
//            book.setIsbn("9789866072956");            
//            dao.insert(book); 
            
            book.setId(0);
            book.setPublisher(publisher);
            book.setName("笑談軟體工程：例外處理設計的逆襲");
            book.setSubtitle("");
            book.setAutherName("陳建村");
            book.setPublishDate("2014/05/20");
            book.setUnitPrice(580);
            book.setDiscount(21);
            book.setStock(5);
            book.setPhotoUrl("http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/001/063/75/0010637503.jpg&v=537dd241&w=348&h=348");
            book.setIsbn("9789865740382");            
            dao.insert(book); 

            System.out.println(dao.get(1));
            List<Book> list = dao.getAll();
            System.out.println(list);
            
            System.out.println(dao.getBooksByName("軟體工程"));
        } catch (VGBException ex) {
            Logger.getLogger(TestBooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
