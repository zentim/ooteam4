package main.java.pattern.strategy;

import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.OrderItem;
import main.java.model.bean.Promotion;
import main.java.model.bean.PromotionItem;
import main.java.model.dao.PromotionDAO;
import main.java.model.dao.PromotionItemDAO;
import main.java.pattern.chainOfResponsibility.DiscountRequest;

/**
 * Strategy Pattern - concrete strategy
 */
public class ProductSetStrategy implements Strategy {

	public DiscountRequest Execute(DiscountRequest discountRequest) {
		List<OrderItem> orderItems = discountRequest.getOrderItems();

		PromotionDAO promotionDAO = new PromotionDAO();
		Promotion promotion;
		
		PromotionItemDAO promotionItemDAO = new PromotionItemDAO();
		PromotionItem promotionItem;
		
		// ProductSet Algorithm
		// e.g. If a customer buys Product X, Product Y and Product Z he gets a discount of 5%.
		float discountAmount = 0;
		
		
		
		for (OrderItem oi : orderItems) {
			promotionItem = promotionItemDAO.getByProduct(oi.getProduct().getId());
			promotion = promotionDAO.get(promotionItem.getPromotion().getId());
			
			if (promotion.getState() == 1) {
				
				// Get all promotionItem of the promotion 
				List<PromotionItem> pis = promotionItemDAO.listByPromotion(promotion.getId());
				
				// Record whether all PromotionItem is found in the shopping cart 
				List<OrderItem> foundOrderItems = new ArrayList<OrderItem>();
				for (PromotionItem pi : pis) {
					for (OrderItem oi2 : orderItems) {
						if (oi2.getProduct().getId() == pi.getProduct().getId()) {
							if (!foundOrderItems.contains(oi2) ) {
								foundOrderItems.add(oi2);
							}
						}
					}
				}
				
				// If some promotionItem were not found in the shopping cart,
				// this time will not have the discount
				if (foundOrderItems.size() == pis.size()) {
					discountAmount += oi.getQuantity() * oi.getProduct().getPrice() * ((float)promotionItem.getDiscountOf() / 100);
					discountAmount = Math.round(discountAmount);
					
					System.out.println("discount amount = " + discountAmount);
				}
			}
		}
		
		if (discountAmount > 0) {
			discountRequest.setTotalDiscount(discountRequest.getTotalDiscount() + discountAmount);
			discountRequest.setDiscountMsg(discountRequest.getDiscountMsg() + "(ProductSet Discount: -" + discountAmount + ")");
		}
		
		return discountRequest;
	}

}
