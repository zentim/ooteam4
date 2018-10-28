package test.java.pattern;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import main.java.pattern.chainOfResponsibility.DiscountPolicy;
import main.java.pattern.chainOfResponsibility.DiscountRequest;
import main.java.pattern.chainOfResponsibility.BroughtMoreThanInLastYearPolicy;
import main.java.model.bean.Brand;
import main.java.model.bean.Category;
import main.java.model.bean.OrderItem;
import main.java.model.bean.Product;
import main.java.model.bean.Promotion;
import main.java.model.bean.PromotionItem;
import main.java.model.bean.Segment;
import main.java.model.bean.User;
import main.java.model.dao.BrandDAO;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.OrderItemDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.PromotionDAO;
import main.java.model.dao.PromotionItemDAO;
import main.java.model.dao.SegmentDAO;
import main.java.model.dao.UserDAO;
import main.java.pattern.chainOfResponsibility.BuyXGetYFreePolicy;
import main.java.pattern.chainOfResponsibility.NoDiscountPolicy;
import main.java.pattern.chainOfResponsibility.EachGroupOfNPolicy;
import main.java.pattern.chainOfResponsibility.ProductSetPolicy;

/**
 * 
 * Test Chain Of Responsibility Pattern and Strategy Pattern
 *
 */
public class DiscountTest {
	// Init Test Data
    
    public static SegmentDAO segmentdao = new SegmentDAO();
    public static Segment segment;
    public static int segmentId;
    
    public static CategoryDAO categorydao = new CategoryDAO();
    public static Category category;
    public static int categoryId;
    
    public static BrandDAO branddao = new BrandDAO();
    public static Brand brand;
    public static int brandId;

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
	public static DiscountPolicy nationHolidayDiscount = new BuyXGetYFreePolicy();
	public static DiscountPolicy lastYear100KDiscount = new BroughtMoreThanInLastYearPolicy();
	public static DiscountPolicy eachGroupOf100Discount = new EachGroupOfNPolicy();
	public static DiscountPolicy xyzDiscount = new ProductSetPolicy();
	public static DiscountPolicy noDiscount = new NoDiscountPolicy();

	@BeforeClass
	public static void testStart() {
	    // create segment
        segment = new Segment();
        segment.setName("SegmentTest");
        segmentId = segmentdao.add(segment);

        // create category
        category = new Category();
        category.setName("CategoryTest");
        category.setSegment(segmentdao.get(segmentId));
        categoryId = categorydao.add(category);

        // create brand
        brand = new Brand();
        brand.setName("Book");
        brand.setCategory(categorydao.get(categoryId));
        brandId = branddao.add(brand);

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
		product.setBrand(branddao.get(brandId));
		int productId = productdao.add(product);
		
		// create promotion
	    promotion = new Promotion();
	    promotion.setDiscountType(PromotionDAO.BUY_X_GET_Y_FREE);
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
		orderitem.setQuantity(40);
		orderitem.setOrder(null);
		orderitem.setState(0);
		orderitem.setOriginalPrice(productdao.get(productId).getPrice());
		orderitem.setPromotionalPrice(productdao.get(productId).getPrice());
		int orderItemId = orderitemdao.add(orderitem);
		
		
		
		/* Prepare Pattern */
		List<OrderItem> ois = orderitemdao.listCartByUser(userId);
		
		DiscountRequest dr = new DiscountRequest();
		dr.setOrderItems(ois);
		dr.setNationHoliday(true); // on national holidays
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
		
		/* Prepare Test Data */
		// create product
		product = new Product();
		product.setName("Harry Potter");
		product.setInventory(200);
		product.setPrice(1000);
		product.setDateAdded(new Date());
		product.setBrand(branddao.get(brandId));
		int productId = productdao.add(product);
		
		// create promotion
	    promotion = new Promotion();
	    promotion.setDiscountType(PromotionDAO.BROUGHT_MORE_THAN_IN_LAST_YEAR);
	    promotion.setName("Brought More Than $100K In Last Year");
	    promotion.setDateFrom(new Date());
	    promotion.setDateTo(new Date());
	    promotion.setState(1);
	    int promotionId = promotiondao.add(promotion);
	    
    	// create promotionitem "has brought in the last year more than $100K, he gets a 20% discount"
	    promotionitem = new PromotionItem();
	    promotionitem.setPromotion(promotiondao.get(promotionId));
	    promotionitem.setProduct(productdao.get(productId));
	    promotionitem.setMinQuantity(100000);
	    promotionitem.setDiscountOf(20);
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
		List<OrderItem> ois = orderitemdao.listCartByUser(userId);
		
		DiscountRequest dr = new DiscountRequest();
		dr.setOrderItems(ois);
		dr.setNationHoliday(false);
		dr.setLastYearAmount(200000);
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
		product.setBrand(branddao.get(brandId));
		int productId = productdao.add(product);
		
		// create promotion
	    promotion = new Promotion();
	    promotion.setDiscountType(PromotionDAO.EACH_GROUP_OF_N);
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
		List<OrderItem> ois = orderitemdao.listCartByUser(userId);
		
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
		
		/* Prepare Test Data */
		// create product XYZ
		product = new Product();
		product.setName("Harry Potter");
		product.setInventory(200);
		product.setPrice(1000);
		product.setDateAdded(new Date());
		product.setBrand(branddao.get(brandId));
		int productIdX = productdao.add(product);
		int productIdY = productdao.add(product);
		int productIdZ = productdao.add(product);
		
		// create promotion
	    promotion = new Promotion();
	    promotion.setDiscountType(PromotionDAO.PRODUCT_SET);
	    promotion.setName("XYZ Discount");
	    promotion.setDateFrom(new Date());
	    promotion.setDateTo(new Date());
	    promotion.setState(1);
	    int promotionId = promotiondao.add(promotion);
	    
    	// create promotionitem "buys Product X, Product Y and Product Z he gets a discount of 5%"
	    promotionitem = new PromotionItem();
	    promotionitem.setPromotion(promotiondao.get(promotionId));
	    promotionitem.setMinQuantity(1);
	    promotionitem.setDiscountOf(5);
	    promotionitem.setProduct(productdao.get(productIdX));
	    int promotionItemIdX = promotionitemdao.add(promotionitem);
	    promotionitem.setProduct(productdao.get(productIdY));
	    int promotionItemIdY = promotionitemdao.add(promotionitem);
	    promotionitem.setProduct(productdao.get(productIdZ));
	    int promotionItemIdZ = promotionitemdao.add(promotionitem);
	    
		// create orderitem
		orderitem = new OrderItem();
		orderitem.setUser(userdao.get(userId));
		orderitem.setQuantity(1);
		orderitem.setOrder(null);
		orderitem.setState(0);
		
		orderitem.setOriginalPrice(productdao.get(productIdX).getPrice());
		orderitem.setPromotionalPrice(productdao.get(productIdX).getPrice());
		orderitem.setProduct(productdao.get(productIdX));
		int orderItemIdX = orderitemdao.add(orderitem);
		
		orderitem.setOriginalPrice(productdao.get(productIdY).getPrice());
		orderitem.setPromotionalPrice(productdao.get(productIdY).getPrice());
		orderitem.setProduct(productdao.get(productIdY));
		int orderItemIdY = orderitemdao.add(orderitem);
		
		orderitem.setOriginalPrice(productdao.get(productIdZ).getPrice());
		orderitem.setPromotionalPrice(productdao.get(productIdZ).getPrice());
		orderitem.setProduct(productdao.get(productIdZ));
		int orderItemIdZ = orderitemdao.add(orderitem);
		
		
		
		
		/* Prepare Pattern */
		List<OrderItem> ois = orderitemdao.listCartByUser(userId);
		
		DiscountRequest dr = new DiscountRequest();
		dr.setOrderItems(ois);
		dr.setNationHoliday(false);
		dr.setLastYearAmount(100);
		dr = nationHolidayDiscount.handleDiscount(dr);
		System.out.println("Msg: " + dr.getDiscountMsg());
		System.out.println("Total Discount Amount: " + dr.getTotalDiscount());
		
		
		
		/* Remove Test Data */
		// delete orderitem
		orderitemdao.delete(orderItemIdZ);
		orderitemdao.delete(orderItemIdY);
		orderitemdao.delete(orderItemIdX);
		
		// delete promotionitem
	    promotionitemdao.delete(promotionItemIdZ);
	    promotionitemdao.delete(promotionItemIdY);
	    promotionitemdao.delete(promotionItemIdX);
		
		// delete promotion
	    promotiondao.delete(promotionId);
		
		// delete product
		productdao.delete(productIdZ);
		productdao.delete(productIdY);
		productdao.delete(productIdX);
	}
	
	@AfterClass
	public static void testEnd() {
		
		System.out.println();
		System.out.println("Test End...");

		// delete user
		userdao.delete(userId);

		// delete brand
        branddao.delete(brandId);
        
        // delete category
        categorydao.delete(categoryId);
        
        // delete segment
        segmentdao.delete(segmentId);
	}

}
