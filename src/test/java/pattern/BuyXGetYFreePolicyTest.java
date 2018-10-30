package test.java.pattern;

import junit.framework.TestCase;
import main.java.model.bean.*;
import main.java.model.dao.*;
import main.java.pattern.chainOfResponsibility.BuyXGetYFreePolicy;
import main.java.pattern.chainOfResponsibility.DiscountPolicy;
import main.java.pattern.chainOfResponsibility.DiscountRequest;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/** 
* BuyXGetYFreePolicy Tester. 
* 
*/ 
public class BuyXGetYFreePolicyTest extends TestCase {
    public static BrandDAO branddao = new BrandDAO();
    public static Brand brand;
    public static int brandId;

    public static ProductDAO productdao = new ProductDAO();
    public static Product productX;
    public static Product productY;

    public static UserDAO userdao = new UserDAO();
    public static User user;
    public static int userId;

    public static Order order;

    public static OrderItemDAO orderitemdao = new OrderItemDAO();
    public static OrderItem orderitem;

    public static PromotionDAO promotiondao = new PromotionDAO();
    public static Promotion promotion;

    public static PromotionItemDAO promotionitemdao = new PromotionItemDAO();
    public static PromotionItem promotionitem;

    int productIdX;
    int productIdY;

    int promotionId;

    int promotionItemIdX;
    int promotionItemIdY;

    int orderItemIdX;

    int ExpectedOrderQuantity;

    // Init Chain
    public static DiscountPolicy buyXgetYfreeDiscount = new BuyXGetYFreePolicy();

/** 
* 
* Method: handleDiscount(DiscountRequest discountRequest)
* 
*/ 
@Test
public void testHandleDiscount() throws Exception {
//TODO: Test goes here...
    ExpectedOrderQuantity = 10;  //Assume user order 10 productX

    /* Prepare Test Data */
    // create brand
    brand = new Brand();
    brand.setName("Book");
    Category category = new Category();
    category.setId(100);
    brand.setCategory(category);
    brandId = branddao.add(brand);

    // create user
    user = new User();
    user.setId(100);
    user.setEmail("skin@skin.com");
    user.setPassword("skinskin");
    userId = userdao.add(user);

    // create product X
    productX = new Product();
    productX.setName("Harry Potter");
    productX.setInventory(200);
    productX.setPrice(1000);
    productX.setDateAdded(new Date());
    productX.setBrand(branddao.get(brandId));
    productIdX = productdao.add(productX);
    // create product Y
    productY = new Product();
    productY.setName("Don Quixote");
    productY.setInventory(200);
    productY.setPrice(1000);
    productY.setDateAdded(new Date());
    productY.setBrand(branddao.get(brandId));
    productIdY = productdao.add(productY);

    // create promotion : Buy X Get Y Free discount
    promotion = new Promotion();
    promotion.setDiscountType(PromotionDAO.BUY_X_GET_Y_FREE);
    promotion.setName("Buy X Get Y Free Discount");
    promotion.setDateFrom(new Date());
    promotion.setDateTo(new Date());
    promotion.setState(1);
    promotionId = promotiondao.add(promotion);

    // create promotionItem with promotion :Buys 2 Product X,get 1 Product Y  Free
    //promotionItem X
    promotionitem = new PromotionItem();
    promotionitem.setPromotion(promotiondao.get(promotionId));
    promotionitem.setMinQuantity(2);
    promotionitem.setDiscountOf(0);
    promotionitem.setProduct(productdao.get(productIdX));
    promotionItemIdX = promotionitemdao.add(promotionitem);
    //promotionItem Y
    promotionitem = new PromotionItem();
    promotionitem.setPromotion(promotiondao.get(promotionId));
    promotionitem.setMinQuantity(1);
    promotionitem.setDiscountOf(100);
    promotionitem.setProduct(productdao.get(productIdY));
    promotionItemIdY = promotionitemdao.add(promotionitem);

    // create order
    order = new Order();
    order.setId(-1);
    order.setUser(user);

    // create orderItem with productX
    orderitem = new OrderItem();
    orderitem.setUser(userdao.get(userId));
    orderitem.setQuantity(ExpectedOrderQuantity);
    orderitem.setOrder(order);
    orderitem.setState(1);

    orderitem.setOriginalPrice(productdao.get(productIdX).getPrice());
    orderitem.setPromotionalPrice(productdao.get(productIdX).getPrice());
    orderitem.setProduct(productdao.get(productIdX));
    orderItemIdX = orderitemdao.add(orderitem);

    System.out.println("testBuyXGetYFreeDiscount:");

    /* Prepare Pattern */
    List<OrderItem> ois = orderitemdao.listCartByUser(userId);

    DiscountRequest dr = new DiscountRequest();
    dr.setOrderItems(ois);
    dr = buyXgetYfreeDiscount.handleDiscount(dr);
    dr.setNationalHoliday(true); // on national holidays


    //Assert Equal
    System.out.println("Excepted Result:"+"(BuyXGetYFree Discount: Get Free [pid="+productY.getId()+", num="+ExpectedOrderQuantity/2+"])");
    System.out.println("Actual Result:"+dr.getDiscountMsg());
    Assert.assertEquals("(BuyXGetYFree Discount: Get Free [pid="+productY.getId()+", num="+ExpectedOrderQuantity/2+"])",dr.getDiscountMsg());




    /* Remove Test Data */
    // delete orderItem
    orderitemdao.delete(orderItemIdX);

    // delete promotionItemX and Y
    promotionitemdao.delete(promotionItemIdX);
    promotionitemdao.delete(productIdY);

    // delete promotion
    promotiondao.delete(promotionId);

    // delete productX and Y
    productdao.delete(productIdX);
    productdao.delete(productIdY);

    // delete user
    userdao.delete(userId);

    // delete brand
    branddao.delete(brandId);


}
} 
