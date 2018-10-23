package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import main.java.model.bean.ProductImage;
import main.java.model.bean.User;
import main.java.model.bean.Brand;
import main.java.model.bean.Product;
import main.java.model.dao.ProductImageDAO;
import main.java.model.dao.UserDAO;
import main.java.model.dao.BrandDAO;
import main.java.model.dao.ProductDAO;

public class ProductImageDAOTest {
	public static BrandDAO branddao = new BrandDAO();
	public static Brand brand;
	public static int brandId;

	public static ProductDAO productdao = new ProductDAO();
	public static Product product;
	public static int productId;

	public static ProductImageDAO productimagedao = new ProductImageDAO();
	public static ProductImage productimage;
	public static int productImageId;

	@BeforeClass
	public static void testAdd() {
		// create brand
		brand = new Brand();
		brand.setName("Book");
		brandId = branddao.add(brand);

		// create product
		product = new Product();
		product.setName("Harry Potter");
		product.setInventory(5);
		product.setPrice(1000);
		product.setDateAdded(new Date());
		product.setBrand(branddao.get(brandId));
		productId = productdao.add(product);

		System.out.println("Test Start...");

		// create productimage
		productimage = new ProductImage();
		productimage.setProduct(productdao.get(productId));
		productimage.setType(ProductImageDAO.type_single);
		productImageId = productimagedao.add(productimage);
	}

	@Test
	public void testTotal() {
		int result = productimagedao.getTotal();
		assertNotNull("should not be null", result);
	}

	@AfterClass
	public static void testDelete() {
		// delete productimage
		productimagedao.delete(productImageId);

		System.out.println("Test End...");

		// delete product
		productdao.delete(productId);

		// delete brand
		branddao.delete(brandId);
	}

}
