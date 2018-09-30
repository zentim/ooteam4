package test.java.model.dao;

import static org.junit.Assert.*;

import org.junit.*;

import main.java.model.bean.Category;
import main.java.model.dao.CategoryDAO;

public class CategoryDAOTest {
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category();
	public static int cid;
	
	@BeforeClass
	public static void testAdd() {
		System.out.println("Test Start...");
		
		// create category
		category.setName("Book");
		cid = categorydao.add(category);
	}
	
	@Test
	public void testTotal() {
		int result = categorydao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	@AfterClass
	public static void testDelete() {
		// delete category
		categorydao.delete(cid);
		
		System.out.println("Test End...");
	}

}
