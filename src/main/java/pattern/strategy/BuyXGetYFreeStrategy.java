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
public class BuyXGetYFreeStrategy implements Strategy {

	public DiscountRequest Execute(DiscountRequest discountRequest) {
		List<OrderItem> orderItems = discountRequest.getOrderItems();

		PromotionDAO promotionDAO = new PromotionDAO();
		Promotion promotion;
		
		PromotionItemDAO promotionItemDAO = new PromotionItemDAO();
		PromotionItem promotionItem;
		
		// BuyXGetYFreeStrategy Algorithm
		// e.g. If a customer has purchased 2 units of Product X, he gets 1 unit of Product X (or Product 
		//      Y) free on national holidays.
		String freeProducts = "";
		for (OrderItem oi : orderItems) {
			promotionItem = promotionItemDAO.getByProduct(oi.getProduct().getId());
			promotion = promotionDAO.get(promotionItem.getPromotion().getId());
			
			if (promotion.getState() == 1) {
				// for verify one promotion's all of promotionItem condition
				List<PromotionItem> pis = promotionItemDAO.listByPromotion(promotion.getId());
				 
				// count variable is for calc free product quantity
				int count = oi.getQuantity() / promotionItem.getMinQuantity(); 
				for (PromotionItem pi : pis) {
					if (pi.getDiscountOf() == 0) {
						int temp = oi.getQuantity() / pi.getMinQuantity();
						
						if (count > temp) {
							count = temp;
						}
					}
				}
				
				if (count >= 1) {
					for (PromotionItem pi : pis) {
						if (pi.getDiscountOf() == 100) {
							// list all of free product, free product quantity is (free product minQuantity * count)
							freeProducts += "[pid=" + pi.getProduct().getId() + ", num=" + pi.getMinQuantity() * count + "]"; 
						}
					}
				}
			}
		}
		
		if (!freeProducts.equals("")) {
			discountRequest.setDiscountMsg(discountRequest.getDiscountMsg() + "(BuyXGetYFree Discount: Get Free " + freeProducts + ")");
		}
		
		return discountRequest;
	}

}
