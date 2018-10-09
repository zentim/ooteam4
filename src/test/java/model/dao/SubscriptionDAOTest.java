package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import main.java.model.bean.Category;
import main.java.model.bean.Product;
import main.java.model.bean.Subscription;
import main.java.model.bean.User;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.SubscriptionDAO;
import main.java.model.dao.UserDAO;

public class SubscriptionDAOTest {
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category();
	public static int categoryId;
	
	public static UserDAO userdao = new UserDAO();
	public static User user = new User(); 
	public static int userId;
	
	public static ProductDAO productdao = new ProductDAO();
	public static Product product = new Product();
	public static int productId;
	
	public static SubscriptionDAO subscriptiondao = new SubscriptionDAO();
	public static Subscription subscription = new Subscription();
	public static int subscriptionId;

  @BeforeClass
  public static void testAdd() {
       
    	// create category
 		category.setName("Book");
 		categoryId = categorydao.add(category);
 		
 		// create user
 		user.setEmail("abc@abc.com");
 		user.setPassword("1234");
 		userId = userdao.add(user);
 		
 		// create product
 		product.setName("Harry Potter");
 		product.setInventory(5);
 		product.setPrice(1000);
 		product.setDateAdded(new Date());
 		product.setCategory(categorydao.get(categoryId));
 		product.setSeller(userdao.get(userId));
 		productId = productdao.add(product);

 		System.out.println("Test Start...");

 		// create subscription
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
    
	    // delete product
 		productdao.delete(productId);
 		
 		// delete user
 		userdao.delete(userId);
 		
 		// delete category
 		categorydao.delete(categoryId);
  }

}
