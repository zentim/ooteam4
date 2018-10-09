package test.java.model.dao;

import static org.junit.Assert.*;

import org.junit.*;

import main.java.model.bean.DiscountType;
import main.java.model.dao.DiscountTypeDAO;

public class DiscountTypeDAOTest {
  public static DiscountTypeDAO discounttypedao = new DiscountTypeDAO();
  public static DiscountType discounttype = new DiscountType();
  public static int discountTypeId;

  @BeforeClass
  public static void testAdd() {
    System.out.println("Test Start...");

    // create discounttype
    discounttype.setName("Buy X Get Y Free");
    discountTypeId = discounttypedao.add(discounttype);
  }

  @Test
  public void testTotal() {
    int result = discounttypedao.getTotal();
    assertNotNull("should not be null", result);
  }

  @AfterClass
  public static void testDelete() {
    // delete discounttype
    discounttypedao.delete(discountTypeId);

    System.out.println("Test End...");
  }

}
