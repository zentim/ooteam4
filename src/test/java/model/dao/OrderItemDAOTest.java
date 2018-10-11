package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import main.java.model.bean.OrderItem;
import main.java.model.bean.Product;
import main.java.model.bean.User;
import main.java.model.bean.Category;
import main.java.model.bean.Order;
import main.java.model.dao.OrderItemDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.UserDAO;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.OrderDAO;

public class OrderItemDAOTest {
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category;
	public static int categoryId;

	public static ProductDAO productdao = new ProductDAO();
	public static Product product;
	public static int productId;
	
	public static UserDAO userdao = new UserDAO();
	public static User user;
	public static int userId;

	public static OrderDAO orderdao = new OrderDAO();
	public static Order order;
	public static int orderId;

	public static OrderItemDAO orderitemdao = new OrderItemDAO();
	public static OrderItem orderitem;
	public static int orderItemId;

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

		// create user
		user = new User();
		user.setEmail("abc@abc.com");
		user.setPassword("1234");
		userId = userdao.add(user);
		
		// create order
		order = new Order();
		order.setUser(userdao.get(userId));
		order.setDateOrdered(new Date());
		order.setDatePaid(new Date());
		order.setState(OrderDAO.waitPay);
		order.setTotal(1000);
		order.setDeliverMethod(0);
		order.setAddress("Yuntech");
		orderId = orderdao.add(order);

		System.out.println("Test Start...");

		// create orderitem
		orderitem = new OrderItem();
		orderitem.setUser(userdao.get(userId));
		orderitem.setProduct(productdao.get(productId));
		orderitem.setQuantity(100);
		orderitem.setOrder(orderdao.get(orderId));
		orderitem.setState(0);
		orderitem.setOriginalPrice(productdao.get(productId).getPrice());
		orderitem.setPromotionalPrice(productdao.get(productId).getPrice());
		orderItemId = orderitemdao.add(orderitem);
	}

	@Test
	public void testTotal() {
		int result = orderitemdao.getTotal();
		assertNotNull("should not be null", result);
	}

	@AfterClass
	public static void testDelete() {
		// delete orderitem
		orderitemdao.delete(orderItemId);

		System.out.println("Test End...");

		// delete order
		orderdao.delete(orderId);

		// delete user
		userdao.delete(userId);
		
		// delete product
		productdao.delete(productId);

		// delete category
		categorydao.delete(categoryId);
	}

}
