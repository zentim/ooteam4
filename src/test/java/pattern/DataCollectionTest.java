package test.java.pattern;

import static org.junit.Assert.*;

import org.junit.*;

import main.java.model.bean.Brand;
import main.java.model.dao.BrandDAO;

public class DataCollectionTest {
	public static BrandDAO branddao = new BrandDAO();
	public static Brand brand;
	public static int brandId;

	@BeforeClass
	public static void testAdd() {
		System.out.println("Test Start...");

		// create brand
		brand = new Brand();
		brand.setName("Book");
		brandId = branddao.add(brand);
	}

	@Test
	public void testTotal() {
		int result = branddao.getTotal();
		assertNotNull("should not be null", result);
	}

	@AfterClass
	public static void testDelete() {
		// delete brand
		branddao.delete(brandId);

		System.out.println("Test End...");
	}

}
