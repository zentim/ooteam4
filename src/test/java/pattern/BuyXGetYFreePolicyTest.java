package test.java.pattern;

import junit.framework.TestCase;
import main.java.model.bean.*;
import main.java.model.dao.*;
import main.java.pattern.chainOfResponsibility.BuyXGetYFreePolicy;
import main.java.pattern.chainOfResponsibility.DiscountPolicy;
import main.java.pattern.chainOfResponsibility.DiscountRequest;

import org.junit.*;

import java.util.Date;
import java.util.List;

/** 
* BuyXGetYFreePolicy Tester. 
* (Need to connect mysql)
* 
*/ 
public class BuyXGetYFreePolicyTest extends TestCase {
    private static BrandDAO branddao = new BrandDAO();
    private static Brand brand;
    private static int brandId;

    private static ProductDAO productdao = new ProductDAO();
    private static Product productX;
    private static Product productY;

    private static UserDAO userdao = new UserDAO();
    private static User user;
    private static int userId;

    private static Order order;

    private static OrderItemDAO orderitemdao = new OrderItemDAO();
    private static OrderItem orderitem;

    private static PromotionDAO promotiondao = new PromotionDAO();
    private static Promotion promotion;

    private static PromotionItemDAO promotionitemdao = new PromotionItemDAO();
    private static PromotionItem promotionitem;

    private int productIdX;
    private int productIdY;

    private int promotionId;

    private int promotionItemIdX;
    private int promotionItemIdY;

    private int orderItemIdX;

    private int ExpectedOrderQuantity;

    // Init Chain
    private static DiscountPolicy buyXgetYfreeDiscount = new BuyXGetYFreePolicy();

    @Before
    public void setUp(){
    // create Brand and Category
    Category category = new Category();
    category.setName("Book");
    brand = new Brand();
    brand.setName("BookStore");
    category.setId(100);
    brand.setCategory(category);
    brandId = branddao.add(brand);

    // create user
    user = new User();
    user.setId(100);
    user.setEmail("test@abc.com");
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
    }

    @After
    public void tearDown(){
        /* Remove Test Data */
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
        //TODO: Test goes here...

        //Assume user order 1 productX
        ExpectedOrderQuantity = 1;
        System.out.println("Buy 1 Product X Test:");
        //Expected Result : 0 Free Product Y
        int ExpectedProductId = productY.getId();       // Id of Product Y
        int ExpectedQuantity = ExpectedOrderQuantity/2; // Quantity of Product Y

        // create orderItem with productX

        orderitem = new OrderItem();
        orderitem.setProduct(productdao.get(productIdX));
        orderitem.setQuantity(ExpectedOrderQuantity);   // Buy 10 product X
        orderitem.setUser(user);
        orderitem.setState(1);
        orderItemIdX = orderitemdao.add(orderitem);

        List<OrderItem> ois = orderitemdao.listCartByUser(userId);
        DiscountRequest dr = new DiscountRequest();
        dr.setOrderItems(ois);
        dr = buyXgetYfreeDiscount.handleDiscount(dr);
        dr.setNationalHoliday(true); // on national holidays

        System.out.println("Expected Result:\nDiscount:Buy X Get Y Free(National Day Discount)\nProductID:"+ExpectedProductId+"\nQuantity:"+ExpectedQuantity);
        System.out.println("Actual Result:"+dr.getDiscountMsg());

        //Assert Equal
        Assert.assertEquals("",dr.getDiscountMsg());
    }

    @Test
    public void testBuy10ProductX(){
        //TODO: Test goes here...

        //Assume user order 10 productX
        ExpectedOrderQuantity = 10;
        System.out.println("Buy 10 Product X Test:");

        //Expected Result : 5 Free Product Y
        int ExpectedProductId = productY.getId();       // Id of Product Y
        int ExpectedQuantity = ExpectedOrderQuantity/2; // Quantity of Product Y

        // create orderItem with productX
        orderitem = new OrderItem();
        orderitem.setProduct(productdao.get(productIdX));
        orderitem.setQuantity(ExpectedOrderQuantity);   // Buy 10 product X
        orderitem.setUser(user);
        orderitem.setState(1);
        orderItemIdX = orderitemdao.add(orderitem);

        List<OrderItem> ois = orderitemdao.listCartByUser(userId);
        DiscountRequest dr = new DiscountRequest();
        dr.setOrderItems(ois);
        dr = buyXgetYfreeDiscount.handleDiscount(dr);
        dr.setNationalHoliday(true); // on national holidays

        System.out.println("Expected Result:\nDiscount:Buy X Get Y Free(National Day Discount)\nProductID:"+ExpectedProductId+"\nQuantity:"+ExpectedQuantity);
        System.out.println("Actual Result:"+dr.getDiscountMsg());

        //Assert Equal
        Assert.assertEquals("(BuyXGetYFree Discount: Get Free [pid="+ExpectedProductId+", num="+ExpectedQuantity+"])",dr.getDiscountMsg());
    }

} 
