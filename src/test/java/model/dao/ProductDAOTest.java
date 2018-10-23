package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.*;

import main.java.model.bean.Product;
import main.java.model.bean.Brand;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.BrandDAO;

import main.java.pattern.composite.*;

public class ProductDAOTest {
	public static BrandDAO branddao = new BrandDAO();
	public static Brand brand;
	public static int brandId;

	public static ProductDAO productdao = new ProductDAO();
	public static Product product;
	public static int productId;

	@BeforeClass
	public static void testAdd() {
		// create brand
		brand = new Brand();
		brand.setName("Book");
		brandId = branddao.add(brand);

		System.out.println("Test Start...");

		// create product
		product = new Product();
		product.setName("Harry Potter");
		product.setInventory(5);
		product.setPrice(1000);
		product.setDateAdded(new Date());
		product.setBrand(branddao.get(brandId));
		productId = productdao.add(product);
	}

	@Test
	public void testTotal() {
		int result = productdao.getTotal(brandId);
		assertNotNull("should not be null", result);
	}

	@Test
	public void testList() {
		System.out.println("=== testList");
		
		List<Product> products = productdao.list();
		if (products == null) {
			System.out.println("product list is null");
		} else {
			for (Product p : products) {
				System.out.println("product id: " + p.getId());
			}
		}
		
		System.out.println();
	}
	
	@Test
	public void testCompositePattern() {
		System.out.println("=== testCompositePattern");
		
		Component productComponent = new PrimitiveComponent(productId);
		Component brandComponent = new LinkedListCompositeComponent(brandId);
		brandComponent.add(productComponent);
		brandComponent.operation();
		
		System.out.println();
	}

	@AfterClass
	public static void testDelete() {
		// delete product
		productdao.delete(productId);

		System.out.println("Test End...");

		// delete brand
		branddao.delete(brandId);
	}

}
