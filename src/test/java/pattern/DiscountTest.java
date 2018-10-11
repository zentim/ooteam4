package test.java.pattern;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import main.java.pattern.chainOfResponsibility.DiscountPolicy;
import main.java.pattern.chainOfResponsibility.DiscountRequest;
import main.java.pattern.chainOfResponsibility.SpentMoreThanInLastYearChain;
import main.java.model.bean.Category;
import main.java.model.bean.OrderItem;
import main.java.model.bean.Product;
import main.java.model.bean.Promotion;
import main.java.model.bean.PromotionItem;
import main.java.model.bean.User;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.OrderItemDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.PromotionDAO;
import main.java.model.dao.PromotionItemDAO;
import main.java.model.dao.UserDAO;
import main.java.pattern.chainOfResponsibility.BuyXGetYFreeChain;
import main.java.pattern.chainOfResponsibility.NoDiscountChain;
import main.java.pattern.chainOfResponsibility.EachGroupOfNChain;
import main.java.pattern.chainOfResponsibility.ProductSetChain;

public class DiscountTest {
	// Init Test Data
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category;
	public static int categoryId;

	public static ProductDAO productdao = new ProductDAO();
	public static Product product;
	
	public static UserDAO userdao = new UserDAO();
	public static User user;
	public static int userId;

	public static OrderItemDAO orderitemdao = new OrderItemDAO();
	public static OrderItem orderitem;
	
	public static PromotionDAO promotiondao = new PromotionDAO();
	public static Promotion promotion;
	
	public static PromotionItemDAO promotionitemdao = new PromotionItemDAO();
	public static PromotionItem promotionitem;
	  
	
	// Init Chain
	public static DiscountPolicy nationHolidayDiscount = new BuyXGetYFreeChain();
	public static DiscountPolicy lastYear100KDiscount = new SpentMoreThanInLastYearChain();
	public static DiscountPolicy eachGroupOf100Discount = new EachGroupOfNChain();
	public static DiscountPolicy xyzDiscount = new ProductSetChain();
	public static DiscountPolicy noDiscount = new NoDiscountChain();

	@BeforeClass
	public static void testStart() {
		// create category
		category = new Category();
		category.setName("Book");
		categoryId = categorydao.add(category);

		// create user
		user = new User();
		user.setEmail("abc@abc.com");
		user.setPassword("1234");
		userId = userdao.add(user);
		
		System.out.println("Test Start...");
		
		// Setting Chain Order
		nationHolidayDiscount.setNextDiscountPolicy(lastYear100KDiscount);
		lastYear100KDiscount.setNextDiscountPolicy(eachGroupOf100Discount);
		eachGroupOf100Discount.setNextDiscountPolicy(xyzDiscount);
		xyzDiscount.setNextDiscountPolicy(noDiscount);
	}
	
	@Test
	public void testNationHolidayDiscount() {
		System.out.println();
		System.out.println("testNationHolidayDiscount...");
		
		/* Prepare Test Data */
		// create product X
		product = new Product();
		product.setName("Harry Potter");
		product.setInventory(200);
		product.setPrice(1000);
		product.setDateAdded(new Date());
		product.setCategory(categorydao.get(categoryId));
		int productId = productdao.add(product);
		
		// create promotion
	    promotion = new Promotion();
	    promotion.setDiscountType(promotiondao.buyXGetYFree);
	    promotion.setName("Nation Holiday Discount");
	    promotion.setDateFrom(new Date());
	    promotion.setDateTo(new Date());
	    promotion.setState(1);
	    int promotionId = promotiondao.add(promotion);
	    
    	// create promotionitem "purchased 2 units of Product X"
	    promotionitem = new PromotionItem();
	    promotionitem.setPromotion(promotiondao.get(promotionId));
	    promotionitem.setProduct(productdao.get(productId));
	    promotionitem.setMinQuantity(2);
	    promotionitem.setDiscountOf(0);
	    int promotionItemIdX = promotionitemdao.add(promotionitem);
	    
    	// create promotionitem "gets 1 unit of Product X (or Product Y) free on national holidays"
	    promotionitem = new PromotionItem();
	    promotionitem.setPromotion(promotiondao.get(promotionId));
	    promotionitem.setProduct(productdao.get(productId));
	    promotionitem.setMinQuantity(1);
	    promotionitem.setDiscountOf(100);
	    int promotionItemIdY = promotionitemdao.add(promotionitem);
	    
		// create orderitem
		orderitem = new OrderItem();
		orderitem.setUser(userdao.get(userId));
		orderitem.setProduct(productdao.get(productId));
		orderitem.setQuantity(2);
		orderitem.setOrder(null);
		orderitem.setState(0);
		orderitem.setOriginalPrice(productdao.get(productId).getPrice());
		orderitem.setPromotionalPrice(productdao.get(productId).getPrice());
		int orderItemId = orderitemdao.add(orderitem);
		
		
		
		
		/* Prepare Pattern */
		List<OrderItem> ois = orderitemdao.listByUser(userId);
		
		DiscountRequest dr = new DiscountRequest();
		dr.setOrderItems(ois);
		dr.setNationHoliday(true);
		dr.setLastYearAmount(100);
		dr = nationHolidayDiscount.handleDiscount(dr);
		System.out.println("Msg: " + dr.getDiscountMsg());
		System.out.println("Total Discount Amount: " + dr.getTotalDiscount());
		
		
		
		/* Remove Test Data */
		// delete orderitem
		orderitemdao.delete(orderItemId);
		
		// delete promotionitem
		promotionitemdao.delete(promotionItemIdY);
	    promotionitemdao.delete(promotionItemIdX);
		
		// delete promotion
	    promotiondao.delete(promotionId);
		
		// delete product
		productdao.delete(productId);
	}
	
	@Test
	public void testLastYear100KDiscount() {
		System.out.println();
		System.out.println("testLastYear100KDiscount...");
	}
	
	@Test
	public void testEachGroupOf100Discount() {
		System.out.println();
		System.out.println("testEachGroupOf100Discount...");
		
		/* Prepare Test Data */
		// create product X
		product = new Product();
		product.setName("Harry Potter");
		product.setInventory(200);
		product.setPrice(1000);
		product.setDateAdded(new Date());
		product.setCategory(categorydao.get(categoryId));
		int productId = productdao.add(product);
		
		// create promotion
	    promotion = new Promotion();
	    promotion.setDiscountType(promotiondao.eachGroupOfN);
	    promotion.setName("Each Group Of 100 Discount");
	    promotion.setDateFrom(new Date());
	    promotion.setDateTo(new Date());
	    promotion.setState(1);
	    int promotionId = promotiondao.add(promotion);
	    
    	// create promotionitem "buys 100 units of Product X, he gets a discount of 15%"
	    promotionitem = new PromotionItem();
	    promotionitem.setPromotion(promotiondao.get(promotionId));
	    promotionitem.setProduct(productdao.get(productId));
	    promotionitem.setMinQuantity(100);
	    promotionitem.setDiscountOf(15);
	    int promotionItemIdX = promotionitemdao.add(promotionitem);
	    
		// create orderitem
		orderitem = new OrderItem();
		orderitem.setUser(userdao.get(userId));
		orderitem.setProduct(productdao.get(productId));
		orderitem.setQuantity(100);
		orderitem.setOrder(null);
		orderitem.setState(0);
		orderitem.setOriginalPrice(productdao.get(productId).getPrice());
		orderitem.setPromotionalPrice(productdao.get(productId).getPrice());
		int orderItemId = orderitemdao.add(orderitem);
		
		
		
		
		/* Prepare Pattern */
		List<OrderItem> ois = orderitemdao.listByUser(userId);
		
		DiscountRequest dr = new DiscountRequest();
		dr.setOrderItems(ois);
		dr.setNationHoliday(false);
		dr.setLastYearAmount(100);
		dr = nationHolidayDiscount.handleDiscount(dr);
		System.out.println("Msg: " + dr.getDiscountMsg());
		System.out.println("Total Discount Amount: " + dr.getTotalDiscount());
		
		
		
		/* Remove Test Data */
		// delete orderitem
		orderitemdao.delete(orderItemId);
		
		// delete promotionitem
	    promotionitemdao.delete(promotionItemIdX);
		
		// delete promotion
	    promotiondao.delete(promotionId);
		
		// delete product
		productdao.delete(productId);
	}
	
	@Test
	public void testXYZDiscount() {
		System.out.println();
		System.out.println("testXYZDiscount...");
	}
	
	@AfterClass
	public static void testEnd() {
		
		System.out.println();
		System.out.println("Test End...");

		// delete user
		userdao.delete(userId);

		// delete category
		categorydao.delete(categoryId);
	}

}
