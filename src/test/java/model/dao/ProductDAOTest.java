package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.*;

import main.java.model.bean.Product;
import main.java.model.bean.Category;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.CategoryDAO;

public class ProductDAOTest {
	public static ProductDAO productdao = new ProductDAO();
	public static Product product = new Product();
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category(); 
	
	@BeforeClass
	public static void testAdd() {
		// add category
		category.setName("hello");
		category.setName("Book");
		categorydao.add(category);
		
		System.out.println("Test Start...");
		product.setName("Harry Potter");
		product.setOrignalPrice(1000);
		product.setPromotePrice(800);
		product.setStock(5);
		product.setCategory(category);
		product.setCreateDate(new Date());
		
		productdao.add(product);
	}
	
	@Test
	public void testTotal() {
		int result = productdao.getTotal(category.getId());
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
		List<Product> ProductBeans = productdao.list();
		for (Product bean : ProductBeans) {
			productdao.delete(bean.getId());
		}
		
		System.out.println("Test End...");
		
		// remove category
		categorydao.delete(categorydao.get("Book").getId());
	}

}
