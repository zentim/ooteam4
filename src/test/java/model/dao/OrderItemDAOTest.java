package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

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
	public static OrderItemDAO orderitemdao = new OrderItemDAO();
	public static OrderItem orderitem = new OrderItem();
	public static UserDAO userdao = new UserDAO();
	public static User user = new User(); 
	public static ProductDAO productdao = new ProductDAO();
	public static Product product = new Product();
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category(); 
	public static OrderDAO orderdao = new OrderDAO();
	public static Order order = new Order(); 
	
	@BeforeClass
	public static void testAdd() {
		// add user
		user.setName("hello");
		user.setPassword("world");
		userdao.add(user);
		
		// add category
		category.setName("hello");
		category.setName("Book");
		categorydao.add(category);
		
		product.setName("The OO Design Pattern");
		product.setOrignalPrice(1000);
		product.setPromotePrice(800);
		product.setStock(5);
		product.setCategory(category);
		product.setCreateDate(new Date());
		
		productdao.add(product);
		
		// add order
		order.setOrderCode("1234");
		order.setAddress("Taiwan");
		order.setReceiver("Someone");
		order.setPhone("0912345678");
		order.setCreateDate(new Date());
		order.setPayDate(new Date());
		order.setUser(user);
		order.setStatus(OrderDAO.waitPay);
		orderdao.add(order);
		
		System.out.println("Test Start...");
		orderitem.setProduct(product);
		orderitem.setOrder(order);
		orderitem.setUser(user);
		orderitem.setNumber(1);
		orderitemdao.add(orderitem);
	}
	
	@Test
	public void testTotal() {
		int result = orderitemdao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
			
		System.out.println("Test End...");
		
		// remove
		List<Order> OrderBeans = orderdao.list();
		for (Order orderbean : OrderBeans) {
			List<OrderItem> OrderItemBeans = orderitemdao.listByOrder(orderbean.getId());
			for (OrderItem bean : OrderItemBeans) {
				orderitemdao.delete(bean.getId());
			}
			orderdao.delete(orderbean.getId());
		}
		List<Product> productBeans = productdao.list();
		for (Product bean : productBeans) {
			productdao.delete(bean.getId());
		}
		categorydao.delete(categorydao.get("Book").getId());
		userdao.delete(userdao.get("hello").getId());
	}

}
