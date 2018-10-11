package main.java.pattern.chainOfResponsibility;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.OrderItem;

/**
 * chain of responsibility - request
 */
public class DiscountRequest {

	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	private int lastYearAmount;
	private boolean isNationHoliday = false;
	private String discountMsg = ""; // (e.g. "eachGroupOfN: -100")
	private float totalDiscount = 0; // (e.g. 100.0)
	
	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public int getLastYearAmount() {
		return lastYearAmount;
	}
	public void setLastYearAmount(int lastYearAmount) {
		this.lastYearAmount = lastYearAmount;
	}
	public boolean isNationHoliday() {
		return isNationHoliday;
	}
	public void setNationHoliday(boolean isNationHoliday) {
		this.isNationHoliday = isNationHoliday;
	}
	public String getDiscountMsg() {
		return discountMsg;
	}
	public void setDiscountMsg(String discountMsg) {
		this.discountMsg = discountMsg;
	}
	public float getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(float totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

}
