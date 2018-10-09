package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.*;

import main.java.model.bean.Product;
import main.java.model.bean.User;
import main.java.model.bean.Category;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.UserDAO;
import main.java.model.dao.CategoryDAO;

public class ProductDAOTest {
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category();
	public static int categoryId;
	
	public static UserDAO userdao = new UserDAO();
	public static User user = new User(); 
	public static int userId;
	
	public static ProductDAO productdao = new ProductDAO();
	public static Product product = new Product();
	public static int productId;
	
	@BeforeClass
	public static void testAdd() {
		// create category
		category.setName("Book");
		categoryId = categorydao.add(category);
		
		// create user
		user.setEmail("abc@abc.com");
		user.setPassword("1234");
		userId = userdao.add(user);
		
		System.out.println("Test Start...");
		
		// create product
		product.setName("Harry Potter");
		product.setInventory(5);
		product.setPrice(1000);
		product.setDateAdded(new Date());
		product.setCategory(categorydao.get(categoryId));
		product.setSeller(userdao.get(userId));
		productId = productdao.add(product);
	}
	
	@Test
	public void testTotal() {
		int result = productdao.getTotal(categoryId);
		assertNotNull("should not be null", result);
	}
	
	@Test
	public void testTotalBySeller() {
		int result = productdao.getTotalBySeller(userId, categoryId);
		assertNotNull("should not be null", result);
	}
	
	@Test
	public void testList() {
		List<Product> products = productdao.list();
		if (products == null) {
			System.out.println("product list is null");
		} else {
			for (Product p : products) {
				System.out.println("product id: " + p.getId());
			}
		}
	}
	
	
	@AfterClass
	public static void testDelete() {
		// delete product
		productdao.delete(productId);
		
		System.out.println("Test End...");
		
		// delete user
		userdao.delete(userId);
		
		// delete category
		categorydao.delete(categoryId);
	}

}
