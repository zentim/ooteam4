package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import main.java.model.bean.Promotion;
import main.java.model.dao.PromotionDAO;
import main.java.pattern.chainOfResponsibility.DiscountPolicy;

public class PromotionDAOTest {
  public static PromotionDAO promotiondao = new PromotionDAO();
  public static Promotion promotion;
  public static int promotionId;

  @BeforeClass
  public static void testAdd() throws Exception {
    System.out.println("Test Start...");

    // create promotion
    promotion = new Promotion();
    promotion.setDiscountType(DiscountPolicy.BUY_X_GET_Y_FREE);
    promotion.setName("National Holiday");
    promotion.setDateFrom(new Date());
    promotion.setDateTo(new Date());
    promotion.setState(0);
    promotionId = promotiondao.add(promotion);
  }

  @Test
  public void testTotal() {
    int result = promotiondao.getTotal();
    assertNotNull("should not be null", result);
  }

  @AfterClass
  public static void testDelete() throws Exception {
    // delete promotion
    promotiondao.delete(promotionId);

    System.out.println("Test End...");
  }

}
