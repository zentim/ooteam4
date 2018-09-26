package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.*;

import main.java.model.bean.ProductImage;
import main.java.model.bean.Category;
import main.java.model.bean.Product;
import main.java.model.dao.ProductImageDAO;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.ProductDAO;

public class ProductImageDAOTest {
	public static ProductImageDAO productimagedao = new ProductImageDAO();
	public static ProductImage productimage = new ProductImage();
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category(); 
	public static ProductDAO productdao = new ProductDAO();
	public static Product product = new Product(); 
	
	@BeforeClass
	public static void testAdd() {
		// add category
		category.setName("hello");
		category.setName("Book");
		categorydao.add(category);
		
		// add product
		product.setName("Harry Potter");
		product.setOriginalPrice(1000);
		product.setPromotePrice(800);
		product.setStock(5);
		product.setCategory(category);
		product.setCreateDate(new Date());
		productdao.add(product);
		
		System.out.println("Test Start...");
		productimage.setProduct(product);
		productimage.setType(ProductImageDAO.type_single);
		
		productimagedao.add(productimage);
	}
	
	@Test
	public void testTotal() {
		int result = productimagedao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
		List<ProductImage> ProductImageBeans = productimagedao.list(product, ProductImageDAO.type_single);
		for (ProductImage bean : ProductImageBeans) {
			productimagedao.delete(bean.getId());
		}
		
		System.out.println("Test End...");
		
		// remove
		List<Product> ProductBeans = productdao.list();
		for (Product bean : ProductBeans) {
			productdao.delete(bean.getId());
		}
		categorydao.delete(categorydao.get("Book").getId());
	}

}
