/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/*
Lớp Payment đại diện cho thông tin về một lần thanh toán, bao gồm mã thanh toán, tên khách hàng, mã sản phẩm, tên sản phẩm, tổng tiền, và ngày thanh toán.
*/
public class Payment {
    private int payid;
    private String cName;
    private String proId;
    private String pName; // Tên sản phẩm
    private double total; // Tổng tiền của lần thanh toán
    private String pdate; // Ngày thanh toán     
    private Cart cart; 
    
    // Phương thức lấy giá trị của pid
    public int getPayid() {
        return payid;
    }
    
    // Phương thức thiết lập giá trị cho pid
    public void setPayid(int payid) {
        this.payid = payid;
    }
    
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    
}
