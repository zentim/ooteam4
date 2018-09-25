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
	public static PropertyValueDAO propertyvaluedao = new PropertyValueDAO();
	public static PropertyValue propertyvalue = new PropertyValue();
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category(); 
	public static ProductDAO productdao = new ProductDAO();
	public static Product product = new Product(); 
	public static PropertyDAO propertydao = new PropertyDAO();
	public static Property property = new Property();
	
	@BeforeClass
	public static void testAdd() {
		// add category
		category.setName("Book");
		categorydao.add(category);
		
		// add product
		product.setName("Harry Potter");
		product.setOrignalPrice(1000);
		product.setPromotePrice(800);
		product.setStock(5);
		product.setCategory(category);
		product.setCreateDate(new Date());
		productdao.add(product);
		
		// add property
		property.setCategory(category);
		property.setName("author");
		propertydao.add(property);
		
		System.out.println("Test Start...");
		propertyvalue.setProperty(property);
		propertyvalue.setProduct(product);
		propertyvalue.setValue("J. K. Rowling");
		
		propertyvaluedao.add(propertyvalue);
	}
	
	@Test
	public void testTotal() {
		int result = propertyvaluedao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
		List<Product> PBeans = productdao.list();
		for (Product pbean : PBeans) {
			List<PropertyValue> PropertyValueBeans = propertyvaluedao.list(pbean.getId());
			for (PropertyValue pvbean : PropertyValueBeans) {
				propertyvaluedao.delete(pvbean.getId());
			}
		}
		
		
		System.out.println("Test End...");
		
		// remove
		List<Property> PropertyBeans = propertydao.list(categorydao.get("Book").getId());
		for (Property bean : PropertyBeans) {
			propertydao.delete(bean.getId());
		}
		List<Product> ProductBeans = productdao.list();
		for (Product bean : ProductBeans) {
			productdao.delete(bean.getId());
		}
		categorydao.delete(categorydao.get("Book").getId());
	}

}
