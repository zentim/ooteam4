package main.java.model.bean;

import java.util.Date;
import java.util.List;

import main.java.model.dao.PromotionDAO;

public class Promotion {
    
	private int id;
	private String name;
	private Date dateFrom;
	private Date dateTo;
	private int state;
	private int discountType;
	private String discountTypeDesc;
	private List<PromotionItem> promotionItems;
	
	
	public String getDiscountTypeDescription() {
		String desc;
		switch (discountType) {
		case PromotionDAO.NO_DISCOUNT:
			desc = "No Discount";
			break;
		case PromotionDAO.PRODUCT_SET:
			desc = "Product Set";
			break;
		case PromotionDAO.EACH_GROUP_OF_N:
			desc = "Each Group Of N";
			break;
		case PromotionDAO.BROUGHT_MORE_THAN_IN_LAST_YEAR:
			desc = "Brought More Than In Last Year";
			break;
		case PromotionDAO.BUY_X_GET_Y_FREE:
			desc = "Buy X Get Y Free";
			break;
		default:
			desc = "Unknow";
		}
		
		return desc;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getDiscountType() {
		return discountType;
	}

	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}

	public List<PromotionItem> getPromotionItems() {
		return promotionItems;
	}

	public void setPromotionItems(List<PromotionItem> promotionItems) {
		this.promotionItems = promotionItems;
	}

	public String getDiscountTypeDesc() {
		return discountTypeDesc;
	}

	public void setDiscountTypeDesc(String discountTypeDesc) {
		this.discountTypeDesc = discountTypeDesc;
	}

	
}
