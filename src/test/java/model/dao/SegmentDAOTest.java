package test.java.model.dao;

import static org.junit.Assert.*;

import org.junit.*;

import main.java.model.bean.Segment;
import main.java.model.dao.SegmentDAO;

public class SegmentDAOTest {
    public static SegmentDAO segmentdao = new SegmentDAO();
    public static Segment segment;
    public static int segmentId;

    @BeforeClass
    public static void testAdd() throws Exception {
        System.out.println("Test Start...");

        // create segment
        segment = new Segment();
        segment.setName("SegmentTest");
        segmentId = segmentdao.add(segment);
    }

    @Test
    public void testTotal() {
        int result = segmentdao.getTotal();
        assertNotNull("should not be null", result);
    }

    @AfterClass
    public static void testDelete() throws Exception {
        // delete segment
        segmentdao.delete(segmentId);

        System.out.println("Test End...");
    }

}
