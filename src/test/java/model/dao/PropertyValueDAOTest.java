package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.*;

import main.java.model.bean.PropertyValue;
import main.java.model.bean.Category;
import main.java.model.bean.Product;
import main.java.model.bean.Property;
import main.java.model.dao.PropertyValueDAO;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.PropertyDAO;

public class PropertyValueDAOTest {
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category();
	public static int cid;
	
	public static PropertyDAO propertydao = new PropertyDAO();
	public static Property property = new Property();
	public static int ppid;
	
	public static ProductDAO productdao = new ProductDAO();
	public static Product product = new Product();
	public static int pid;
	
	public static PropertyValueDAO propertyvaluedao = new PropertyValueDAO();
	public static PropertyValue propertyvalue = new PropertyValue();
	public static int ppvid;
	
	@BeforeClass
	public static void testAdd() {
		// create category
		category.setName("Book");
		cid = categorydao.add(category);
		
		// create property
		property.setCategory(categorydao.get(cid));
		property.setName("author");
		ppid = propertydao.add(property);
		
		// create product
		product.setName("Harry Potter");
		product.setOriginalPrice(1000);
		product.setPromotePrice(800);
		product.setStock(5);
		product.setCategory(categorydao.get(cid));
		product.setCreateDate(new Date());
		pid = productdao.add(product);
		
		System.out.println("Test Start...");
		
		// create propertyvalue
		propertyvalue.setProperty(propertydao.get(ppid));
		propertyvalue.setProduct(productdao.get(pid));
		propertyvalue.setValue("J. K. Rowling");
		ppvid = propertyvaluedao.add(propertyvalue);
	}
	
	@Test
	public void testTotal() {
		int result = propertyvaluedao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
		// delete propertyvalue
		propertyvaluedao.delete(ppvid);
		
		System.out.println("Test End...");
		
		// delete product
		productdao.delete(pid);
		
		// delete property
		propertydao.delete(ppid);
		
		// delete category
		categorydao.delete(cid);
	}

}
