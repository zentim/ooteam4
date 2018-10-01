package test.java.model.dao;

import static org.junit.Assert.*;
import org.junit.*;

import main.java.model.bean.Property;
import main.java.model.bean.Category;
import main.java.model.dao.PropertyDAO;
import main.java.model.dao.CategoryDAO;

public class PropertyDAOTest {
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category();
	public static int cid;
	
	public static PropertyDAO propertydao = new PropertyDAO();
	public static Property property = new Property();
	public static int ppid;
	
	@BeforeClass
	public static void testAdd() {
		// create category
		category.setName("Book");
		cid = categorydao.add(category);
		
		System.out.println("Test Start...");
		
		// create property
		property.setCategory(categorydao.get(cid));
		property.setName("author");
		ppid = propertydao.add(property);
	}
	
	@Test
	public void testTotal() {
		int result = propertydao.getTotal(cid);
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
		// delete property
		propertydao.delete(ppid);
		
		System.out.println("Test End...");
		
		// delete category
		categorydao.delete(cid);
	}

}
