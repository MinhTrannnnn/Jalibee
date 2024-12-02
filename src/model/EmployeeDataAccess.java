/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Admin
 */
public class EmployeeDataAccess {
    Connection con=MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    public int getMaxProductID(){
        int pid=0;
        try {
            ps=con.prepareStatement("select max(pid) from product");
            rs=ps.executeQuery();
            while(rs.next()){
                pid=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pid+1;
    }
    
    public boolean insertProduct(Product p){
        String sql="insert into product(pid,pname,price,image) values (?,?,?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, p.getPid());
            ps.setString(2, p.getPname());
            ps.setDouble(3, p.getPrice());
            ps.setBytes(4, p.getImage());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public void getAllProducts(JTable table){
        String sql="select pname, price, image from product order by pid desc";
        
        try {
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            
            Object[] row;
            while(rs.next()){
                row=new Object[3];
                row[0]=rs.getString(1);
                row[1]=rs.getDouble(2);
                row[2]=rs.getBytes(3);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getManageProducts(JTable table){
        String sql="select * from product order by pid asc";
        
        try {
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            
            Object[] row;
            while(rs.next()){
                row=new Object[4];
                row[0]=rs.getInt(1);
                row[1]=rs.getString(2);
                row[2]=rs.getDouble(3);
                row[3]=rs.getBytes(4);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    
    public boolean update(Product product){
        try {
            ps=con.prepareStatement("update product set pname =? , price = ? where pid=?");
            ps.setString(1, product.getPname());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getPid());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            return false;
        }       
    }
    
    public boolean delete(Product product){
        try {
            ps=con.prepareStatement("delete from product where pid=?");
            ps.setInt(1, product.getPid());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public void getPaymentDetail(JTable table){
        try {
            ps=con.prepareStatement("select * from payment");
            rs=ps.executeQuery();
            Object row[];
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            while(rs.next()){
                row=new Object[6];
                row[0]=rs.getInt(1);
                row[1]=rs.getString(2);
                row[2]=rs.getString(3);
                row[3]=rs.getString(4);
                row[4]=rs.getDouble(5);
                row[5]=rs.getString(6);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getTotalProducts(){
        int count=0;
        try {
            ps=con.prepareStatement("select count(pid) from product");
            rs=ps.executeQuery();
            while(rs.next()){
                 count=rs.getInt(1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
    public double getTodayRevenue(String date){
        double revenue=0.0;
        try {
            ps=con.prepareStatement("select sum(total) from payment where pDate =?");
            ps.setString(1, date);
            rs=ps.executeQuery();
            while(rs.next()){
                revenue=rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return revenue;
    }
    
    public double getTotalRevenue(){
        double revenue=0.0;
        try {
            ps=con.prepareStatement("select sum(total) from payment");
            rs=ps.executeQuery();
            while(rs.next()){
                revenue=rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return revenue;
    }
}
