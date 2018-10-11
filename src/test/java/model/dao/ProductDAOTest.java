package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.*;

import main.java.model.bean.Product;
import main.java.model.bean.User;
import main.java.model.bean.Category;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.UserDAO;
import main.java.model.dao.CategoryDAO;

public class ProductDAOTest {
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category;
	public static int categoryId;

	public static ProductDAO productdao = new ProductDAO();
	public static Product product;
	public static int productId;

	@BeforeClass
	public static void testAdd() {
		// create category
		category = new Category();
		category.setName("Book");
		categoryId = categorydao.add(category);

		System.out.println("Test Start...");

		// create product
		product = new Product();
		product.setName("Harry Potter");
		product.setInventory(5);
		product.setPrice(1000);
		product.setDateAdded(new Date());
		product.setCategory(categorydao.get(categoryId));
		productId = productdao.add(product);
	}

	@Test
	public void testTotal() {
		int result = productdao.getTotal(categoryId);
		assertNotNull("should not be null", result);
	}

	@Test
	public void testList() {
		List<Product> products = productdao.list();
		if (products == null) {
			System.out.println("product list is null");
		} else {
			for (Product p : products) {
				System.out.println("product id: " + p.getId());
			}
		}
	}

	@AfterClass
	public static void testDelete() {
		// delete product
		productdao.delete(productId);

		System.out.println("Test End...");

		// delete category
		categorydao.delete(categoryId);
	}

}
