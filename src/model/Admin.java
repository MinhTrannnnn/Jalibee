/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import frame.ForgotPasswordFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Admin {
    private int id; // Mã nhân viên đăng nhập 
    private String username; // Tên đăng nhập
    private String password; //Mật khẩu
    private String sQues; // Câu hỏi bảo mật (Trong trường hợp quên mật khẩu để lấy lại mật khẩu)
    private String ans; // Câu trả lời của câu hỏi bảo mật

    Connection con=MyConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    
    public int getMaxAdmin() throws SQLException{
        int row=0;
        try {
            ps=con.prepareStatement("select max(id) from admin");
            rs=ps.executeQuery(); // Thực hiện truy vấn SQL, trả kết quả về resultset
            while(rs.next()){
                row=rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return row+1;
    }
    
    public boolean insert(Admin admin){
        String sql="insert into admin(id,username,password,s_ques,ans) values (?,?,?,?,?)";
        try {
            ps=con.prepareStatement(sql); //gán các giá trị cho các placeholder 
            ps.setInt(1, admin.getId());
            ps.setString(2,admin.getUsername());
            ps.setString(3,admin.getPassword());
            ps.setString(4,admin.getsQues());
            ps.setString(5,admin.getAns());
            return ps.executeUpdate() >0; //thực thi câu lệnh insert
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean getSecurityQues(String username){
        try {
            String sql="select * from admin where username =?";
            ps=con.prepareStatement(sql);
            ps.setString(1, username);
            rs=ps.executeQuery();
            if(rs.next()){
                ForgotPasswordFrame.jTextField1.setText(rs.getString(4));
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean isAdminExist(String username){
        try {
            ps=con.prepareStatement("select * from admin where username=?");
            ps.setString(1, username); // Gán username cho placeholder vị trí 1 trong câu truy vấn
            rs=ps.executeQuery(); 
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean checkpass(String username,String password){
        try {
            ps=con.prepareStatement("select password from admin where username=?");
            ps.setString(1, username);
            rs=ps.executeQuery();
            if(rs.next()){
                String check=rs.getString(1);
                if(check.equals(password)){
                    return true;
                }
            }
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }
    
    public boolean checkAns(String username, String nAns){
        try {
            ps=con.prepareStatement("select ans from admin where username =?");
            ps.setString(1, username);
            rs=ps.executeQuery();
            if(rs.next()){
                String ans=rs.getString(1);
                if(ans.equals(nAns)){
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean setPassword(String username,String password){
        try {
            ps=con.prepareStatement("update admin set password=? where username=?");
            ps.setString(1, password);
            ps.setString(2, username);
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            return false;
        }   
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getsQues() {
        return sQues;
    }

    public void setsQues(String sQues) {
        this.sQues = sQues;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
