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
