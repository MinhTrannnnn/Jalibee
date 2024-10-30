/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class AdminDataAccess {
    // Thiết lập kết nối đến cơ sở dữ liệu bằng cách gọi phương thức getConnection() từ lớp MyConnection
    Connection con=MyConnection.getConnection();
    // Truy vấn tham số trong sql có chứa các biến ? để truyền tham số 
    PreparedStatement ps;
    // Khai báo đối tượng Statement để thực thi các câu lệnh SQL không có tham số cung cấp phương thức cho truy vấn khác nhau dùng để thực hiện các lệnh sql đơn giản 
    Statement st;
    // Khai báo đối tượng ResultSet để lưu trữ kết quả của câu truy vấn SQL (SELECT)
    ResultSet rs;
    
    public int getMaxAdmin() throws SQLException{
        int row=0;
        try {
            st=con.createStatement();  // Tạo đối tượng Statement để thực hiện các câu lệnh SQL
            rs=st.executeQuery("select max(id) from admin"); // Thực hiện truy vấn SQL, trả kết quả về resultset
            while(rs.next()){
                row=rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return row+1;
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
}
