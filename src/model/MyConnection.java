/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MyConnection {
    public static final String username="root"; // Khai báo tên người dùng cho cơ sở dữ liệu
    public static final String password="1234"; // Khai báo mật khẩu của người dùng cho cơ sở dữ liệu
    // Khai báo URL kết nối tới cơ sở dữ liệu, bao gồm tên cơ sở dữ liệu và các tham số bổ sung
    public static final String url="jdbc:mysql://localhost:3306/food2?autoReconnect=true&useSSL=false";
    public static Connection con =null; // Khởi tạo đối tượng Connection ban đầu là null
    
    public static Connection getConnection(){
        try {
            // Tải driver MySQL để sử dụng DriverManager
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tạo kết nối với cơ sở dữ liệu sử dụng url, username và password
            con=DriverManager.getConnection(url,username,password);
        } catch (Exception ex) {
            // Hiển thị thông báo lỗi nếu kết nối thất bại
            JOptionPane.showMessageDialog(null, ""+ex,"",JOptionPane.WARNING_MESSAGE);
        }
        // Trả về đối tượng Connection để sử dụng
        return con;
    }
}
