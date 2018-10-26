package test.java.model.dao;

import static org.junit.Assert.*;

import org.junit.*;

import main.java.model.bean.Category;
import main.java.model.bean.Segment;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.SegmentDAO;
import main.java.pattern.composite.*;

public class CategoryDAOTest {
	public static SegmentDAO segmentdao = new SegmentDAO();
	public static Segment segment;
	public static int segmentId;
	
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category;
	public static int categoryId;

	@BeforeClass
	public static void testAdd() {
		// create segment
		segment = new Segment();
		segment.setName("SegmentTest");
		segmentId = segmentdao.add(segment);
		
		System.out.println("Test Start...");

		// create category
		category = new Category();
		category.setName("CategoryTest");
		category.setSegment(segmentdao.get(segmentId));
		categoryId = categorydao.add(category);
	}

	@Test
	public void testTotal() {
		int result = categorydao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	@Test
	public void testCompositePattern() {
		System.out.println("testCompositePattern: ");
		Component categoryComponent = new LinkedListCompositeComponent(categoryId);
		categoryComponent.operation();
	}

	@AfterClass
	public static void testDelete() {
		// delete category
		categorydao.delete(categoryId);

		System.out.println("Test End...");
		
		// delete segment
		segmentdao.delete(segmentId);
	}

}
