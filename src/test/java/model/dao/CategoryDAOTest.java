package test.java.model.dao;

import static org.junit.Assert.*;

import org.junit.*;

import main.java.model.bean.Category;
import main.java.model.dao.CategoryDAO;

public class CategoryDAOTest {
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category;
	public static int categoryId;

	@BeforeClass
	public static void testAdd() {
		System.out.println("Test Start...");

		// create category
		category = new Category();
		category.setName("Book");
		categoryId = categorydao.add(category);
	}

	@Test
	public void testTotal() {
		int result = categorydao.getTotal();
		assertNotNull("should not be null", result);
	}

	@AfterClass
	public static void testDelete() {
		// delete category
		categorydao.delete(categoryId);

		System.out.println("Test End...");
	}

}
