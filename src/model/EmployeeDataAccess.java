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
    
    public boolean insertProduct(Product p){
        String sql="insert into product(pname,price,image) values (?,?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getPname());
            ps.setDouble(2, p.getPrice());
            ps.setBytes(3, p.getImage());
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
        String sql="select * from product order by pid desc";
        
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
}
