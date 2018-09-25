package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;

import main.java.model.bean.Property;
import main.java.model.bean.Category;
import main.java.model.dao.PropertyDAO;
import main.java.model.dao.CategoryDAO;

public class PropertyDAOTest {
	public static PropertyDAO propertydao = new PropertyDAO();
	public static Property property = new Property();
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category(); 
	
	@BeforeClass
	public static void testAdd() {
		// add category
		category.setName("Book");
		categorydao.add(category);
		
		System.out.println("Test Start...");
		property.setCategory(category);
		property.setName("author");
		
		propertydao.add(property);
	}
	
	@Test
	public void testTotal() {
		int result = propertydao.getTotal(categorydao.get("Book").getId());
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
		List<Property> PropertyBeans = propertydao.list(categorydao.get("Book").getId());
		for (Property bean : PropertyBeans) {
			propertydao.delete(bean.getId());
		}
		
		System.out.println("Test End...");
		
		// remove
		categorydao.delete(categorydao.get("Book").getId());
	}

}
