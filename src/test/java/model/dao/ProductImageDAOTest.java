package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import main.java.model.bean.ProductImage;
import main.java.model.bean.Category;
import main.java.model.bean.Product;
import main.java.model.dao.ProductImageDAO;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.ProductDAO;

public class ProductImageDAOTest {
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category();
	public static int cid;
	
	public static ProductDAO productdao = new ProductDAO();
	public static Product product = new Product();
	public static int pid;
	
	public static ProductImageDAO productimagedao = new ProductImageDAO();
	public static ProductImage productimage = new ProductImage();
	public static int piid;
	
	@BeforeClass
	public static void testAdd() {
		// create category
		category.setName("Book");
		cid = categorydao.add(category);
		
		// create product
		product.setName("Harry Potter");
		product.setOriginalPrice(1000);
		product.setPromotePrice(800);
		product.setStock(5);
		product.setCategory(categorydao.get(cid));
		product.setCreateDate(new Date());
		pid = productdao.add(product);
		
		System.out.println("Test Start...");
		
		// create productimage
		productimage.setProduct(productdao.get(pid));
		productimage.setType(ProductImageDAO.type_single);
		piid = productimagedao.add(productimage);
	}
	
	@Test
	public void testTotal() {
		int result = productimagedao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
		// delete productimage
		productimagedao.delete(piid);
		
		System.out.println("Test End...");
		
		// delete product
		productdao.delete(pid);
		
		// delete category
		categorydao.delete(cid);
	}

}
