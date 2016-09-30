/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.model;

import vgb.domain.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vgb.domain.BloodType;
import vgb.domain.VGBException;
import vgb.domain.VIP;

/**
 *
 * @author Administrator
 */
public class CustomersDAO implements DAOInterface<String, Customer> {
    private static final String COLUMNS = "id, password, name, email, gender,"
            + "birthday,phone,address,married,blood_type,type,discount";
    private static final String TABLE = "customers";

    private static final String INSERT_SQL = "INSERT INTO " + TABLE + " ("
            + COLUMNS + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE_SQL = "UPDATE " + TABLE + " SET "
            + "password=?, name=?, email=?, gender=?,"
            + "birthday=?,phone=?,address=?,married=?,blood_type=?,"
            + "type=?,discount=? " + " WHERE id=?";

    private static final String DELETE_SQL = "DELETE FROM " + TABLE + " WHERE id=?";

    private static final String SELECT_SQL_BY_ID = "SELECT " + COLUMNS + " FROM " + TABLE
            + " WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT " + COLUMNS + " FROM " + TABLE;

    @Override
    public void insert(Customer c) throws VGBException {
        if (c == null) {
            throw new IllegalArgumentException("客戶新增失敗-客戶資料不得為null");
        }

        try (Connection connection = RDBConnection.getConnection();//1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL);//3.建立Statement物件
                ) {
            pstmt.setString(1, c.getId());
            pstmt.setString(2, c.getPassword());
            pstmt.setString(3, c.getName());
            pstmt.setString(4, c.getEmail());
            pstmt.setString(5, String.valueOf(c.getGender()));
            if (c.getBirthday() != null) {
                pstmt.setDate(6, new java.sql.Date(c.getBirthday().getTime()));
            } else {
                pstmt.setDate(6, null);
            }
            pstmt.setString(7, c.getPhone());
            pstmt.setString(8, c.getAddress());
            pstmt.setBoolean(9, c.isMarried());
            pstmt.setString(10, (c.getBloodType() != null ? c.getBloodType().name() : null));
            pstmt.setString(11, c.getClass().getSimpleName());
            if (c instanceof VIP) {
                pstmt.setInt(12, ((VIP) c).getDiscount());
            } else {
                pstmt.setInt(12, 0);
            }
            //4.執行指令
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "客戶新增失敗", ex);
            throw new VGBException("客戶新增失敗", ex);
        } catch (VGBException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "客戶新增失敗", ex);
            throw ex;
        }
    }

    @Override
    public void delete(Customer c) throws VGBException {
        if (c == null) {
            throw new IllegalArgumentException("客戶刪除失敗-客戶資料不得為null");
        }

        try (Connection connection = RDBConnection.getConnection();//1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(DELETE_SQL);//3.建立Statement物件
                ) {
            pstmt.setString(1, c.getId());
            //4.執行指令
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "客戶刪除失敗", ex);
            throw new VGBException("客戶刪除失敗", ex);
        }
    }

    @Override
    public void update(Customer c) throws VGBException {
        if (c != null) {
            //JDBC update
            try (Connection connection = RDBConnection.getConnection();//1. 2. 取得連線
                    PreparedStatement pstmt = connection.prepareStatement(UPDATE_SQL);//3.建立Statement物件
                    ) {
                pstmt.setString(1, c.getPassword());
                pstmt.setString(2, c.getName());
                pstmt.setString(3, c.getEmail());
                pstmt.setString(4, String.valueOf(c.getGender()));
                if (c.getBirthday() != null) {
                    pstmt.setDate(5, new java.sql.Date(c.getBirthday().getTime()));
                } else {
                    pstmt.setDate(5, null);
                }
                pstmt.setString(6, c.getPhone());
                pstmt.setString(7, c.getAddress());
                pstmt.setBoolean(8, c.isMarried());
                pstmt.setString(9, (c.getBloodType() != null ? c.getBloodType().name() : null));
                pstmt.setString(10, c.getClass().getSimpleName());
                if (c instanceof VIP) {
                    pstmt.setInt(11, ((VIP) c).getDiscount());
                } else {
                    pstmt.setInt(11, 0);
                }
                pstmt.setString(12, c.getId());

                //4.執行指令
                pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "客戶修改失敗", ex);
                throw new VGBException("客戶修改失敗", ex);
            }
        } else {
            throw new IllegalArgumentException("修改客戶資料時客戶不得為null");
        }
    }

    @Override
    public Customer get(String id) throws VGBException {
        Customer c = null;
        try (Connection connection = RDBConnection.getConnection(); //1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL_BY_ID);//3.建立Statement物件
                ) {
            pstmt.setString(1, id);

            //4.執行指令
            try (ResultSet rs = pstmt.executeQuery();) {
                //5. 處理rs
                while (rs.next()) {
                    String type = rs.getString("type");
                    if (type.equals("VIP")) {
                        c = new VIP();
                        ((VIP) c).setDiscount(rs.getInt("discount"));
                    } else {
                        c = new Customer();
                    }

                    c.setId(rs.getString("id"));
                    String password = rs.getString("password");
                    c.setPassword(password);
                    String name = rs.getString("name");
                    c.setName(name);
                    String email = rs.getString("email");
                    c.setEmail(email);
                    char gender = rs.getString("gender").charAt(0);
                    c.setGender(gender);
                    String phone = rs.getString("phone");
                    c.setPhone(phone);
                    String address = rs.getString("address");
                    c.setAddress(address);
                    boolean married = rs.getBoolean("married");
                    c.setMarried(married);

                    String bloodType = rs.getString("blood_type");
                    c.setBloodType(bloodType);

                    java.util.Date birthday = rs.getDate("birthday");
                    c.setBirthday(birthday);
                }
            }
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "查詢客戶失敗:" + id, ex);
            throw new VGBException("查詢客戶失敗!", ex);
        }

    }

    public List<Customer> getAll() throws VGBException {
        List<Customer> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection(); //1. 2. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_SQL);//3.建立Statement物件
                ResultSet rs = pstmt.executeQuery();//4.執行指令
                ) {
            //5. 處理rs
            while (rs.next()) {
                Customer c;
                String type = rs.getString("type");
                if (type.equals("VIP")) {
                    c = new VIP();
                    ((VIP) c).setDiscount(rs.getInt("discount"));
                } else {
                    c = new Customer();
                }
                
                c.setId(rs.getString("id"));
                String password = rs.getString("password");
                c.setPassword(password);
                String name = rs.getString("name");
                c.setName(name);
                String email = rs.getString("email");
                c.setEmail(email);
                char gender = rs.getString("gender").charAt(0);
                c.setGender(gender);
                String phone = rs.getString("phone");
                c.setPhone(phone);
                String address = rs.getString("address");
                c.setAddress(address);
                boolean married = rs.getBoolean("married");
                c.setMarried(married);

                String bloodType = rs.getString("blood_type");                
                c.setBloodType(bloodType);

                java.util.Date birthday = rs.getDate("birthday");
                c.setBirthday(birthday);
                
                list.add(c);
                
            }
            
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "查詢全部客戶失敗!", ex);
            throw new VGBException("查詢全部客戶失敗!", ex);
        }

    }

}
