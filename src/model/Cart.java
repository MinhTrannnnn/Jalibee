/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;



/**
 * Lớp Cart đại diện cho thông tin của giỏ hàng, bao gồm mã giỏ hàng, mã sản phẩm, tên sản phẩm,số lượng, giá của sản phẩm và tổng tiền.
 */
public class Cart {
    private int cid; // Id của cart
    private int pid; // Mã sản phẩm
    private String pName; // Tên sản phẩm
    private int qty; //Số lượng
    private double price; //Giá sản phẩm
    private double total; //Tổng tiền = sản phẩm * số lượng
    
    
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
}
