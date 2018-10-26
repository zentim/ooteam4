package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import main.java.model.bean.Brand;
import main.java.model.bean.Category;
import main.java.model.bean.Product;
import main.java.model.bean.Segment;
import main.java.model.bean.Subscription;
import main.java.model.bean.User;
import main.java.model.dao.BrandDAO;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.SegmentDAO;
import main.java.model.dao.SubscriptionDAO;
import main.java.model.dao.UserDAO;

public class SubscriptionDAOTest {
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
	public static int productId;
	
	public static UserDAO userdao = new UserDAO();
	public static User user;
	public static int userId;

	public static SubscriptionDAO subscriptiondao = new SubscriptionDAO();
	public static Subscription subscription;
	public static int subscriptionId;

	@BeforeClass
	public static void testAdd() {

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

		// create product
		product = new Product();
		product.setName("Harry Potter");
		product.setInventory(5);
		product.setPrice(1000);
		product.setDateAdded(new Date());
		product.setBrand(branddao.get(brandId));
		productId = productdao.add(product);
		
		// create user
		user = new User();
		user.setEmail("abc@abc.com");
		user.setPassword("1234");
		userId = userdao.add(user);

		System.out.println("Test Start...");

		// create subscription
		subscription = new Subscription();
		subscription.setUser(userdao.get(userId));
		subscription.setProduct(productdao.get(productId));
		subscriptionId = subscriptiondao.add(subscription.getProduct().getId(), subscription.getUser().getId());
	}

	@Test
	public void testTotal() {
		int result = subscriptiondao.getTotal();
		assertNotNull("should not be null", result);
	}

	@AfterClass
	public static void testDelete() {
		// delete subscription
		subscriptiondao.delete(subscription.getProduct().getId(), subscription.getUser().getId());

		System.out.println("Test End...");

		// delete user
		userdao.delete(userId);
		
		// delete product
		productdao.delete(productId);

		// delete brand
		branddao.delete(brandId);
		
		// delete category
		categorydao.delete(categoryId);
		
		// delete segment
		segmentdao.delete(segmentId);
	}

}
