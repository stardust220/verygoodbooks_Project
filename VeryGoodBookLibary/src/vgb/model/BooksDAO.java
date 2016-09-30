/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vgb.domain.Book;
import vgb.domain.Publisher;
import vgb.domain.VGBException;

/**
 *
 * @author Administrator
 */
public class BooksDAO implements DAOInterface<Integer, Book> {

    private static final String COLUMNS = "books.name, subtitle, auther_name,"
            + "publisher_id,publish_date,list_price,discount,stock,photo_url,isbn";
    private static final String TABLE = "books";

    private static final String INSERT_SQL = "INSERT INTO " + TABLE + " ("
            + COLUMNS + ") VALUES(?,?,?,?,?,?,?,?,?,?)";

    private static final String INSERT_WITH_ID_SQL = "INSERT INTO " + TABLE + " ("
            + COLUMNS + ",id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE_SQL = "UPDATE " + TABLE + " SET "
            + "books.name=?, subtitle=?, auther_name=?,"
            + "publisher_id=?,publish_date=?,list_price=?,discount=?,stock=?,photo_url=?,isbn=?" + " WHERE id=?";

    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE id=?";

    private static final String SELECT_ALL_SQL = "SELECT books.id,publishers.name as pub_name," + COLUMNS + " FROM " + TABLE
            + " JOIN publishers ON books.publisher_id=publishers.id";

    private static final String SELECT_SQL_BY_ID = SELECT_ALL_SQL + " WHERE books.id=?";

    private static final String SELECT_SQL_BY_NAME = SELECT_ALL_SQL + " WHERE books.name LIKE ?";
    private static final String SELECT_SQL_BY_AUTHER_NAME = SELECT_ALL_SQL + " WHERE books.auther_name like ?";
    private static final String SELECT_SQL_BY_PUB_NAME = SELECT_ALL_SQL + " WHERE publishers.name like ?";

    @Override
    public void delete(Book b) throws VGBException {
        if (b == null) {
            throw new IllegalArgumentException("書籍刪除失敗-書籍資料不得為null");
        }

        try (Connection connection = RDBConnection.getConnection();//1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(DELETE_SQL);//3.建立Statement物件
                ) {
            pstmt.setInt(1, b.getId());

            //4.執行指令
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "書籍刪除失敗", ex);
            throw new VGBException("書籍刪除失敗", ex);
        } catch (VGBException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "書籍刪除失敗", ex);
            throw ex;
        }
    }

    @Override
    public void insert(Book b) throws VGBException {
        if (b == null) {
            throw new IllegalArgumentException("書籍新增失敗-書籍資料不得為null");
        }

        try (Connection connection = RDBConnection.getConnection();//1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(
                        b.getId() < 1 ? INSERT_SQL : INSERT_WITH_ID_SQL,
                        b.getId() < 1 ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS
                );//3.建立Statement物件
                ) {
            pstmt.setString(1, b.getName());
            pstmt.setString(2, b.getSubtitle());
            pstmt.setString(3, b.getAutherName());
            pstmt.setInt(4, b.getPublisher().getId());
            pstmt.setDate(5, b.getPublishDate() == null ? null : new java.sql.Date(b.getPublishDate().getTime()));
            pstmt.setDouble(6, b.getListPrice());
            pstmt.setInt(7, b.getDiscount());
            pstmt.setInt(8, b.getStock());
            pstmt.setString(9, b.getPhotoUrl());
            pstmt.setString(10, b.getIsbn());
            if (b.getId() > 0) {
                pstmt.setInt(11, b.getId());
            }

            //4.執行指令
            pstmt.executeUpdate();

            //5. 取得RETURN_GENERATED_KEYS
            if (b.getId() < 1) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    while (rs.next()) {
                        b.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "書籍新增失敗", ex);
            throw new VGBException("書籍新增失敗", ex);
        } catch (VGBException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "書籍新增失敗", ex);
            throw ex;
        }
    }

    @Override
    public void update(Book b) throws VGBException {
        if (b == null) {
            throw new IllegalArgumentException("書籍修改失敗-書籍資料不得為null");
        }

        try (Connection connection = RDBConnection.getConnection();//1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_SQL);//3.建立Statement物件
                ) {
            pstmt.setString(1, b.getName());
            pstmt.setString(2, b.getSubtitle());
            pstmt.setString(3, b.getAutherName());
            pstmt.setInt(4, b.getPublisher().getId());
            pstmt.setDate(5, b.getPublishDate() == null ? null : new java.sql.Date(b.getPublishDate().getTime()));
            pstmt.setDouble(6, b.getListPrice());
            pstmt.setInt(7, b.getDiscount());
            pstmt.setInt(8, b.getStock());
            pstmt.setString(9, b.getPhotoUrl());
            pstmt.setString(10, b.getIsbn());
            pstmt.setInt(11, b.getId());

            //4.執行指令
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "書籍修改失敗", ex);
            throw new VGBException("書籍修改失敗", ex);
        } catch (VGBException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "書籍修改失敗", ex);
            throw ex;
        }
    }

    @Override
    public Book get(Integer id) throws VGBException {
        try (Connection connection = RDBConnection.getConnection(); //1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL_BY_ID);//3.建立Statement物件                
                ) {
            pstmt.setInt(1, id);
            Book b = null;
            try (ResultSet rs = pstmt.executeQuery();) {//4.執行指令
                //5. 處理rs
                while (rs.next()) {
                    b = new Book();
                    b.setId(rs.getInt("id"));
                    String name = rs.getString("name");
                    b.setName(name);
                    String subtitle = rs.getString("subtitle");
                    b.setSubtitle(subtitle);
                    String autherName = rs.getString("auther_name");
                    b.setAutherName(autherName);
                    int pubId = rs.getInt("publisher_id");
                    String pubName = rs.getString("pub_name");
                    Publisher publisher = new Publisher(pubId, pubName);
                    b.setPublisher(publisher);
                    Date pubDate = rs.getDate("publish_date");
                    b.setPublishDate(pubDate);
                    b.setUnitPrice(rs.getDouble("list_price"));
                    b.setDiscount(rs.getInt("discount"));
                    b.setStock(rs.getInt("stock"));
                    b.setPhotoUrl(rs.getString("photo_url"));
                    b.setIsbn(rs.getString("isbn"));
                }
                return b;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "查詢全部書籍失敗!", ex);
            throw new VGBException("查詢全部書籍失敗!", ex);
        }
    }

    public List<Book> getAll() throws VGBException {
        List<Book> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection(); //1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_SQL);//3.建立Statement物件
                ResultSet rs = pstmt.executeQuery();//4.執行指令
                ) {
            //5. 處理rs
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("id"));
                String name = rs.getString("name");
                b.setName(name);
                String subtitle = rs.getString("subtitle");
                b.setSubtitle(subtitle);
                String autherName = rs.getString("auther_name");
                b.setAutherName(autherName);
                int pubId = rs.getInt("publisher_id");
                String pubName = rs.getString("pub_name");
                Publisher publisher = new Publisher(pubId, pubName);
                b.setPublisher(publisher);
                Date pubDate = rs.getDate("publish_date");
                b.setPublishDate(pubDate);
                b.setUnitPrice(rs.getDouble("list_price"));
                b.setDiscount(rs.getInt("discount"));
                b.setStock(rs.getInt("stock"));
                b.setPhotoUrl(rs.getString("photo_url"));
                b.setIsbn(rs.getString("isbn"));

                list.add(b);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "查詢全部書籍失敗!", ex);
            throw new VGBException("查詢全部書籍失敗!", ex);
        }
    }
    
    public List<Book> getBooksByAutherName(String name) throws VGBException {
        List<Book> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection(); //1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL_BY_AUTHER_NAME);//3.建立Statement物件                
                ) {
            pstmt.setString(1, "%"+name+"%");
//            pstmt.setString(2, "%"+name+"%");
            try (ResultSet rs = pstmt.executeQuery();) {//4.執行指令
                //5. 處理rs
                while (rs.next()) {
                    Book b = new Book();
                    b.setId(rs.getInt("id"));
                    b.setName(rs.getString("name"));
                    String subtitle = rs.getString("subtitle");
                    b.setSubtitle(subtitle);
                    String autherName = rs.getString("auther_name");
                    b.setAutherName(autherName);
                    int pubId = rs.getInt("publisher_id");
                    String pubName = rs.getString("pub_name");
                    Publisher publisher = new Publisher(pubId, pubName);
                    b.setPublisher(publisher);
                    Date pubDate = rs.getDate("publish_date");
                    b.setPublishDate(pubDate);
                    b.setUnitPrice(rs.getDouble("list_price"));
                    b.setDiscount(rs.getInt("discount"));
                    b.setStock(rs.getInt("stock"));
                    b.setPhotoUrl(rs.getString("photo_url"));
                    b.setIsbn(rs.getString("isbn"));

                    list.add(b);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "依作者姓名查詢書籍失敗!", ex);
            throw new VGBException("依作者姓名查詢書籍失敗!", ex);
        }        
    }
    
    public List<Book> getBooksByPublisherName(String name) throws VGBException {
        List<Book> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection(); //1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL_BY_PUB_NAME);//3.建立Statement物件                
                ) {
            pstmt.setString(1, "%"+name+"%");
//            pstmt.setString(2, "%"+name+"%");
            try (ResultSet rs = pstmt.executeQuery();) {//4.執行指令
                //5. 處理rs
                while (rs.next()) {
                    Book b = new Book();
                    b.setId(rs.getInt("id"));
                    b.setName(rs.getString("name"));
                    String subtitle = rs.getString("subtitle");
                    b.setSubtitle(subtitle);
                    String autherName = rs.getString("auther_name");
                    b.setAutherName(autherName);
                    int pubId = rs.getInt("publisher_id");
                    String pubName = rs.getString("pub_name");
                    Publisher publisher = new Publisher(pubId, pubName);
                    b.setPublisher(publisher);
                    Date pubDate = rs.getDate("publish_date");
                    b.setPublishDate(pubDate);
                    b.setUnitPrice(rs.getDouble("list_price"));
                    b.setDiscount(rs.getInt("discount"));
                    b.setStock(rs.getInt("stock"));
                    b.setPhotoUrl(rs.getString("photo_url"));
                    b.setIsbn(rs.getString("isbn"));

                    list.add(b);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "依出版商名稱查詢書籍失敗!", ex);
            throw new VGBException("依出版商名稱查詢書籍失敗!", ex);
        }
    }    
    
    
    //
    public List<Book> getBooksByName(String name) throws VGBException {
        List<Book> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection(); //1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL_BY_NAME);//3.建立Statement物件                
                ) {
            pstmt.setString(1, "%"+name+"%");
//            pstmt.setString(2, "%"+name+"%");
            try (ResultSet rs = pstmt.executeQuery();) {//4.執行指令
                //5. 處理rs
                while (rs.next()) {
                    Book b = new Book();
                    b.setId(rs.getInt("id"));
                    b.setName(rs.getString("name"));
                    String subtitle = rs.getString("subtitle");
                    b.setSubtitle(subtitle);
                    String autherName = rs.getString("auther_name");
                    b.setAutherName(autherName);
                    int pubId = rs.getInt("publisher_id");
                    String pubName = rs.getString("pub_name");
                    Publisher publisher = new Publisher(pubId, pubName);
                    b.setPublisher(publisher);
                    Date pubDate = rs.getDate("publish_date");
                    b.setPublishDate(pubDate);
                    b.setUnitPrice(rs.getDouble("list_price"));
                    b.setDiscount(rs.getInt("discount"));
                    b.setStock(rs.getInt("stock"));
                    b.setPhotoUrl(rs.getString("photo_url"));
                    b.setIsbn(rs.getString("isbn"));

                    list.add(b);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "依名稱查詢書籍失敗!", ex);
            throw new VGBException("依名稱查詢書籍失敗!", ex);
        }
    }
}
