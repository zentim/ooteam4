package test.java.NationalDayDiscountTest;

import junit.framework.TestCase;
import main.java.model.bean.*;
import main.java.model.dao.*;
import main.java.pattern.chainOfResponsibility.BuyXGetYFreePolicy;
import main.java.pattern.chainOfResponsibility.DiscountPolicy;
import main.java.pattern.chainOfResponsibility.DiscountRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/** 
* BuyXGetYFreePolicy Tester. 
* (Need to connect mysql)
* 
*/ 
public class Buy1ProductXTest extends TestCase {
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

    @Before
    public void setUp(){
        // create brand
        Category category = new Category();
        category.setId(100);
        category.setName("Book");
        brand = new Brand();
        brand.setName("BookStore");
        brand.setCategory(category);
        brandId = branddao.add(brand);

        // create user
        user = new User();
        user.setId(100);
        user.setEmail("abc@abc.com");
        user.setPassword("1234");
        userId = userdao.add(user);

        // create product X
        productX = new Product();
        productX.setName("Harry Potter");
        productX.setInventory(200);
        productX.setPrice(1000);
        productX.setBrand(branddao.get(brandId));
        productIdX = productdao.add(productX);
        // create product Y
        productY = new Product();
        productY.setName("Star Wars");
        productY.setInventory(200);
        productY.setPrice(1000);
        productY.setBrand(branddao.get(brandId));
        productIdY = productdao.add(productY);

        // create promotion : Buy X Get Y Free discount (National Day Discount)
        promotion = new Promotion();
        promotion.setDiscountType(DiscountPolicy.BUY_X_GET_Y_FREE);
        promotion.setName("Buy X Get Y Free Discount");
        promotion.setState(1);
        promotionId = promotiondao.add(promotion);

        // create promotionItem with promotion :Buys 2 Product X,get 1 free Product Y
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
        orderitem.setUser(user);
        orderitem.setOrder(order);
        orderitem.setState(1);
        orderitem.setProduct(productX);
    }

    @After
    public void tearDown(){
        // delete orderItem
        orderitemdao.delete(orderItemIdX);

        // delete promotionItemX and Y
        promotionitemdao.delete(promotionItemIdX);
        promotionitemdao.delete(promotionItemIdY);

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


    @Test
    public void testBuy1ProductX(){
        System.out.println("Test: Buy 1 Product X");

        // user buy 1 productX
        ExpectedOrderQuantity = 1;
        orderitem.setQuantity(ExpectedOrderQuantity);
        orderItemIdX = orderitemdao.add(orderitem);

        List<OrderItem> ois = orderitemdao.listCartByUser(userId);
        DiscountRequest dr = new DiscountRequest();
        dr.setOrderItems(ois);
        dr = buyXgetYfreeDiscount.handleDiscount(dr);
        dr.setNationalHoliday(true); // on national holidays

        //Assert Equal
        // If the order doesn't have corresponding discount, discount msg should not have any content
        System.out.println("Excepted Result: (Blank)");
        System.out.println("Actual Result:"+dr.getDiscountMsg());
        Assert.assertEquals("",dr.getDiscountMsg());
    }
} 
