package main.java.model.bean;

import java.util.Date;
import java.util.List;

import main.java.model.dao.OrderDAO;

public class Order {
	private int id;
	private Date dateOrdered;
	private Date datePaid;
	private String state;
	private float total;
	private int deliverMethod;
	private String address;
	private User user;
	private List<OrderItem> orderItems;

	public String getStatusDesc() {
		String desc = "未知";
		switch (state) {
		case OrderDAO.waitPay:
			desc = "待付款";
			break;
		case OrderDAO.waitDelivery:
			desc = "待發貨";
			break;
		// case OrderDAO.waitConfirm:
		// desc="待收貨";
		// break;
		// case OrderDAO.waitReview:
		// desc="待評價";
		// break;
		case OrderDAO.finish:
			desc = "完成";
			break;
		case OrderDAO.delete:
			desc = "刪除";
			break;
		default:
			desc = "未知";
		}
		return desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public Date getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getDeliverMethod() {
		return deliverMethod;
	}

	public void setDeliverMethod(int deliverMethod) {
		this.deliverMethod = deliverMethod;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

}
