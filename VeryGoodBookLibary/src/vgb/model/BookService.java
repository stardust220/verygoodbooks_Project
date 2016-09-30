/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import vgb.domain.Book;
import vgb.domain.VGBException;

/**
 *
 * @author Administrator
 */
public class BookService {

    private BooksDAO dao = new BooksDAO();

    //Delegate Methods from dao
    public void delete(Book b) throws VGBException {
        dao.delete(b);
    }

    public void insert(Book b) throws VGBException {
        dao.insert(b);
    }

    public void update(Book b) throws VGBException {
        dao.update(b);
    }

    public Book get(Integer id) throws VGBException {
        return dao.get(id);
    }

    public List<Book> getAll() throws VGBException {
        return dao.getAll();
    }

    public List<Book> getBooksByName(String name) throws VGBException {
        return dao.getBooksByName(name);
    }

    public List<Book> getBooksByAutherName(String name) throws VGBException {
        return dao.getBooksByAutherName(name);
    }

    public List<Book> getBooksByPublisherName(String name) throws VGBException {
        return dao.getBooksByPublisherName(name);
    }

    public Collection<Book> getBooks(String search, int type) throws VGBException {
        Set<Book> list = new HashSet<>();        
        if ((type & 1) > 0) {
            list.addAll(dao.getBooksByName(search));
        }

        if ((type & 2) > 0) {
            list.addAll(dao.getBooksByAutherName(search));
        }

        if ((type & 4) > 0) {
            list.addAll(dao.getBooksByPublisherName(search));
        }
        
        
        return list;
    }

    //Demo: Pass by Value
    public void addPrice(double price) {
        price = price + 100;
    }

//    public double addPrice(double price){
//        double rtn = price +100;        
//        return rtn;
//    }
    //Demo: Pass by Value
    public void addPrice(Book book) {
        double price = book.getUnitPrice() + 100;
        book.setUnitPrice(price);
    }

//    public double addPrice(Book book){
//        double price = book.getUnitPrice()+100;
//        return (price);
//    }
}
