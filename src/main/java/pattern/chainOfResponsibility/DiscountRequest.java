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
	private boolean isNationalHoliday = false;
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
	public boolean isNationalHoliday() {
		return isNationalHoliday;
	}
	public void setNationalHoliday(boolean isNationHoliday) {
		this.isNationalHoliday = isNationHoliday;
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
	@Override
	public String toString() {
		return "DiscountRequest [orderItems=" + orderItems + ", lastYearAmount=" + lastYearAmount + ", isNationHoliday="
				+ isNationalHoliday + ", discountMsg=" + discountMsg + ", totalDiscount=" + totalDiscount + "]";
	}
	

}
