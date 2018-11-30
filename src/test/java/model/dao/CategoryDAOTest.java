package test.java.model.dao;

import static org.junit.Assert.*;

import org.junit.*;

import main.java.model.bean.Category;
import main.java.model.bean.Segment;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.SegmentDAO;

public class CategoryDAOTest {
    public static SegmentDAO segmentdao = new SegmentDAO();
    public static Segment segment;
    public static int segmentId;

    public static CategoryDAO categorydao = new CategoryDAO();
    public static Category category;
    public static int categoryId;

    @BeforeClass
    public static void testAdd() throws Exception {
        // create segment
        segment = new Segment();
        segment.setName("SegmentTest");
        segmentId = segmentdao.add(segment);

        System.out.println("Test Start...");

        // create category
        category = new Category();
        category.setName("CategoryTest");
        category.setSegment((Segment) segmentdao.get(segmentId));
        categoryId = categorydao.add(category);
    }

    @Test
    public void testTotal() {
        int result = categorydao.getTotal();
        assertNotNull("should not be null", result);
    }

    @AfterClass
    public static void testDelete() throws Exception {
        // delete category
        categorydao.delete(categoryId);

        System.out.println("Test End...");

        // delete segment
        segmentdao.delete(segmentId);
    }

}
