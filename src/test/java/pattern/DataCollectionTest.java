package test.java.pattern;

import java.util.List;

import org.junit.*;

import main.java.model.bean.Product;
import main.java.model.bean.Segment;
import main.java.model.bean.Brand;
import main.java.model.bean.Category;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.SegmentDAO;
import main.java.model.dao.BrandDAO;
import main.java.model.dao.CategoryDAO;

/**
 * 
 * Test Composite Pattern and Iterator Pattern
 *
 */
public class DataCollectionTest {
    public static SegmentDAO segmentdao = new SegmentDAO();
    public static CategoryDAO categorydao = new CategoryDAO();
    public static BrandDAO branddao = new BrandDAO();
    public static ProductDAO productdao = new ProductDAO();

    @Test
    public void testCompositePattern() throws Exception {
        System.out.println("=== testCompositePattern (show products from the database)");

        // show products from the database
        List<Segment> segments = segmentdao.list();

        for (Segment s : segments) {
            List<Category> categorys = categorydao.list(s.getId());
            for (Category c : categorys) {
                s.add(c);
                List<Brand> brands = branddao.list(c.getId());
                for (Brand b : brands) {
                    c.add(b);
                    List<Product> products = productdao.list(b.getId());
                    for (Product p : products) {
                        b.add(p);
                    }
                }
            }

            s.operation();
        }

        System.out.println();
    }

}