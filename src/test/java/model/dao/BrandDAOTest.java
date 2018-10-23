package test.java.model.dao;

import static org.junit.Assert.*;

import org.junit.*;

import main.java.model.bean.Brand;
import main.java.model.dao.BrandDAO;

import main.java.pattern.composite.*;

public class BrandDAOTest {
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
	
	@Test
	public void testCompositePattern() {
		System.out.println("testCompositePattern: ");
		Component brandComponent = new LinkedListCompositeComponent(brandId);
		brandComponent.operation();
	}

	@AfterClass
	public static void testDelete() {
		// delete brand
		branddao.delete(brandId);

		System.out.println("Test End...");
	}

}
