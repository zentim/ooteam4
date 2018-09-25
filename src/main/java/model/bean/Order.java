package main.java.model.bean;



import java.util.Date;
import java.util.List;

import main.java.model.dao.OrderDAO;

 
public class Order {
    private String orderCode;
    private String address;
    private String receiver;
    private String phone;
    private Date createDate;
    private Date payDate;
    private User user;
    private int id;
    private List<OrderItem> orderItems;
    private float total;
    private int totalNumber;
    private String status;
     
    public String getStatusDesc(){
        String desc ="未知";
        switch(status){
          case OrderDAO.waitPay:
              desc="待付款";
              break;
//          case OrderDAO.waitDelivery:
//              desc="待发货";
//              break;
//          case OrderDAO.waitConfirm:
//              desc="待收货";
//              break;
//          case OrderDAO.waitReview:
//              desc="待评价";
//              break;
          case OrderDAO.finish:
              desc="完成";
              break;
          case OrderDAO.delete:
              desc="刪除";
              break;
          default:
              desc="未知";
        }
        return desc;
    }
     
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getPayDate() {
        return payDate;
    }
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
 
    public String getReceiver() {
        return receiver;
    }
 
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
 
    public String getOrderCode() {
        return orderCode;
    }
 
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
 
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
 
    public float getTotal() {
        return total;
    }
 
    public void setTotal(float total) {
        this.total = total;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public int getTotalNumber() {
        return totalNumber;
    }
 
    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }
     
}