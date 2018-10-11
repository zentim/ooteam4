package main.java.pattern.strategy;

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
public class BroughtMoreThanInLastYearStrategy implements Strategy {

	public DiscountRequest Execute(DiscountRequest discountRequest) {
		List<OrderItem> orderItems = discountRequest.getOrderItems();

		PromotionDAO promotionDAO = new PromotionDAO();
		Promotion promotion;
		
		PromotionItemDAO promotionItemDAO = new PromotionItemDAO();
		PromotionItem promotionItem;
		
		// SpentMoreThanInLastYearStrategy Algorithm
		// e.g. If a customer has brought in the last year more than $100K, he gets a 20% discount
		float discountAmount = 0;
		for (OrderItem oi : orderItems) {
			promotionItem = promotionItemDAO.getByProduct(oi.getProduct().getId());
			promotion = promotionDAO.get(promotionItem.getPromotion().getId());
			
			boolean isDiscountConditionPass = discountRequest.getLastYearAmount() >= promotionItem.getMinQuantity();
			if (promotion.getState() == 1 && isDiscountConditionPass) {
				discountAmount += oi.getQuantity() * oi.getProduct().getPrice() * ((float)promotionItem.getDiscountOf() / 100);
				discountAmount = Math.round(discountAmount);
			}
		}
		
		discountRequest.setTotalDiscount(discountRequest.getTotalDiscount() + discountAmount);
		discountRequest.setDiscountMsg(discountRequest.getDiscountMsg() + "(BroughtMoreThanInLastYear Discount: -" + discountAmount + ")");
		return discountRequest;
	}

}
