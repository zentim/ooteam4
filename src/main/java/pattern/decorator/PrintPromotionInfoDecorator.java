package main.java.pattern.decorator;

import main.java.model.bean.Product;
import main.java.model.bean.Promotion;
import main.java.model.bean.PromotionItem;
import main.java.model.dao.PromotionItemDAO;
import main.java.pattern.chainOfResponsibility.DiscountPolicy;
import main.java.pattern.composite.Component;

/**
 * 
 * Decorator Pattern - ConcreteDecorator
 *
 */
public class PrintPromotionInfoDecorator extends Component{
    private Product product;

    public PrintPromotionInfoDecorator(Product product) {
        this.product = product;
    }

    @Override
    public void operation() {
        product.operation();
        fillPromotion(product);
    }
    
    public void fillPromotion(Product p) {
        PromotionItemDAO promotionItemDAO = new PromotionItemDAO();
        
        PromotionItem promotionItem = promotionItemDAO.getByProduct(p.getId()); 
        Promotion promotionByProduct = promotionItem.getPromotion();
        
        if (promotionByProduct != null 
                && !(promotionItem.getDiscountOf() == 100 
                && promotionByProduct.getDiscountType() == DiscountPolicy.BUY_X_GET_Y_FREE)) {
            
              String promotionName = "";
              if (promotionByProduct.getState() == 1) {
                  promotionName = promotionByProduct.getName();
              }
              
              String discountTypeName = promotionByProduct.getDiscountTypeDescription();
              p.setPromotionName(promotionName);
              p.setDiscountTypeName(discountTypeName);
              
              System.out.println("         * promotionName: " + product.getPromotionName());
        }
          
    }
    
    
}
