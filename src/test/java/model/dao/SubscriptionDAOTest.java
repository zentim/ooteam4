package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import main.java.model.bean.Brand;
import main.java.model.bean.Product;
import main.java.model.bean.Subscription;
import main.java.model.bean.User;
import main.java.model.dao.BrandDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.SubscriptionDAO;
import main.java.model.dao.UserDAO;

public class SubscriptionDAOTest {
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

		// create brand
		brand = new Brand();
		brand.setName("Book");
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
		subscriptionId = subscriptiondao.add(subscription);
	}

	@Test
	public void testTotal() {
		int result = subscriptiondao.getTotal();
		assertNotNull("should not be null", result);
	}

	@AfterClass
	public static void testDelete() {
		// delete subscription
		subscriptiondao.delete(subscriptionId);

		System.out.println("Test End...");

		// delete user
		userdao.delete(userId);
		
		// delete product
		productdao.delete(productId);

		// delete brand
		branddao.delete(brandId);
	}

}
