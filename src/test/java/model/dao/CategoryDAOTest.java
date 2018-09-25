package test.java.model.dao;

import static org.junit.Assert.*;

import org.junit.*;

import main.java.model.bean.Category;
import main.java.model.dao.CategoryDAO;

public class CategoryDAOTest {
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category();
	
	@BeforeClass
	public static void testAdd() {
		System.out.println("Test Start...");
		category.setName("Book");
		categorydao.add(category);
	}
	
	@Test
	public void testTotal() {
		int result = categorydao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	@Test
	public void testGetByName() {
		Category result = categorydao.get("Book");
		assertNotNull("should not be null", result);
	}
	
	@AfterClass
	public static void testDelete() {
		categorydao.delete(categorydao.get("Book").getId());
		System.out.println("Test End...");
	}

}
