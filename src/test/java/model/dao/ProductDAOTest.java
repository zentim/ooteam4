package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import main.java.model.bean.Product;
import main.java.model.bean.Category;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.CategoryDAO;

public class ProductDAOTest {
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category();
	public static int cid;
	
	public static ProductDAO productdao = new ProductDAO();
	public static Product product = new Product();
	public static int pid;
	
	@BeforeClass
	public static void testAdd() {
		// create category
		category.setName("Book");
		cid = categorydao.add(category);
		
		System.out.println("Test Start...");
		
		// create product
		product.setName("Harry Potter");
		product.setOriginalPrice(1000);
		product.setPromotePrice(800);
		product.setStock(5);
		product.setCategory(categorydao.get(cid));
		product.setCreateDate(new Date());
		pid = productdao.add(product);
	}
	
	@Test
	public void testTotal() {
		int result = productdao.getTotal(cid);
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
		// delete product
		productdao.delete(pid);
		
		System.out.println("Test End...");
		
		// delete category
		categorydao.delete(cid);
	}

}
