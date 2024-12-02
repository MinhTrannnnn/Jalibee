/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class CustomerDataAccess {
    Connection con=MyConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    public void getAllProducts(JTable table){
        String sql="select * from product order by pid asc";
        try {
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            Object row[];
            while(rs.next()){
                row=new Object[4];
                row[0]=rs.getInt(1);
                row[1]=rs.getString(2);
                row[2]=rs.getDouble(3);
                row[3]=rs.getBytes(4);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getCartID(){
        int row=0;
        try {
            ps=con.prepareStatement("select max(cid) from cart");
            rs=ps.executeQuery();
            while(rs.next()){
                row=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row+1;
    }
    
    public boolean isProductExist(int cid, int pid){
        try {
            ps=con.prepareStatement("select * from cart where cid=? and pid=?");
            ps.setInt(1, cid);
            ps.setInt(2, pid);
            rs=ps.executeQuery();
            if(rs.next()){
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean insertCart(Cart cart){
        try {
            ps=con.prepareStatement("insert into cart (cid,pid,pName,qty,price,total) values (?,?,?,?,?,?)");
            ps.setInt(1, cart.getCid());
            ps.setInt(2, cart.getPid());
            ps.setString(3, cart.getpName());
            ps.setInt(4, cart.getQty());
            ps.setDouble(5, cart.getPrice());
            ps.setDouble(6, cart.getTotal());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public void getCart(JTable table){
        try {
            int cid=getCartID()-1;
            ps=con.prepareStatement("select * from cart where cid=?");
            ps.setInt(1, cid);
            rs=ps.executeQuery();
            DefaultTableModel model= (DefaultTableModel) table.getModel();
            Object row[];
            while(rs.next()){
                row=new Object[6];
                row[0]=rs.getInt(1);
                row[1]=rs.getInt(2);
                row[2]=rs.getString(3);
                row[3]=rs.getInt(4);
                row[4]=rs.getDouble(5);
                row[5]=rs.getDouble(6);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean DeleteFromCart(int cid,int pid){
        try {
            ps=con.prepareStatement("DELETE FROM cart WHERE cid = ? AND pid = ?");
            ps.setInt(1, cid);
            ps.setInt(2, pid);
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public double getTotalFromCart(int cid){
        double total = 0.0;
        try {
            ps=con.prepareStatement("select sum(total) from cart where cid=?");
            ps.setInt(1, cid);
            rs=ps.executeQuery();
            while(rs.next()){
                total= rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public int getPaymentId(){
        int paymentid=0;
        try {
            ps=con.prepareStatement("select max(payid) from payment");
            rs=ps.executeQuery();
            while(rs.next()){
                paymentid=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paymentid+1;
    }
    
    public boolean insertPayment(Payment payment){
        try {
            ps=con.prepareStatement("insert into payment (payid, cname, proid, pName, total, pDate) values (?,?,?,?,?,?)");
//            ps=con.prepareStatement("INSERT INTO payment (payid, cname, proid, pName, total, pDate)  VALUES (2, 'Nguyen Van A', 101, 'Product A', 500000, '2024-11-28');");
            ps.setInt(1, payment.getPayid());
            ps.setString(2, payment.getcName());
            ps.setString(3, payment.getProId());
            ps.setString(4, payment.getpName());
            ps.setDouble(5, payment.getTotal());
            ps.setString(6, payment.getPdate());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            ex.printStackTrace(); // Hiển thị lỗi trong console
            return false;
        }        
    }
    
    public boolean deleteCart (int cid){
        try {
            ps=con.prepareStatement("delete from cart where cid = ?");
            ps.setInt(1, cid);
            return ps.executeUpdate()>0;
        } catch (Exception ex) {
            return false;
        }
    }
    
}
