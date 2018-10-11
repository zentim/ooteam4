package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import main.java.model.bean.Category;
import main.java.model.bean.Product;
import main.java.model.bean.Promotion;
import main.java.model.bean.PromotionItem;
import main.java.model.bean.User;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.PromotionDAO;
import main.java.model.dao.PromotionItemDAO;
import main.java.model.dao.UserDAO;

public class PromotionItemDAOTest {
  public static CategoryDAO categorydao = new CategoryDAO();
  public static Category category;
  public static int categoryId;

  public static ProductDAO productdao = new ProductDAO();
  public static Product product;
  public static int productId;
	
  public static PromotionDAO promotiondao = new PromotionDAO();
  public static Promotion promotion;
  public static int promotionId;

  public static PromotionItemDAO promotionitemdao = new PromotionItemDAO();
  public static PromotionItem promotionitem;
  public static int promotionItemId;

  @BeforeClass
  public static void testAdd() {
	// create category
    category = new Category();
    category.setName("Book");
    categoryId = categorydao.add(category);

    // create product
    product = new Product();
    product.setName("Harry Potter");
    product.setInventory(5);
    product.setPrice(1000);
    product.setDateAdded(new Date());
    product.setCategory(categorydao.get(categoryId));
    productId = productdao.add(product);
	  
    // create promotion
	promotion = new Promotion();
	promotion.setDiscountType(promotiondao.buyXGetYFree);
    promotion.setName("National Holiday");
    promotion.setDateFrom(new Date());
    promotion.setDateTo(new Date());
    promotion.setState(0);
    promotionId = promotiondao.add(promotion);

    System.out.println("Test Start...");

    // create promotionitem
    promotionitem = new PromotionItem();
    promotionitem.setPromotion(promotiondao.get(promotionId));
    promotionitem.setProduct(productdao.get(productId));
    promotionitem.setMinQuantity(100);
    promotionitem.setDiscountOf(15);
    promotionItemId = promotionitemdao.add(promotionitem);
  }

  @Test
  public void testTotal() {
    int result = promotionitemdao.getTotal();
    assertNotNull("should not be null", result);
  }

  @AfterClass
  public static void testDelete() {
    // delete promotionitem
    promotionitemdao.delete(promotionItemId);

    System.out.println("Test End...");

    // delete promotion
    promotiondao.delete(promotionId);
    
    // delete product
    productdao.delete(productId);

    // delete category
    categorydao.delete(categoryId);
  }

}
