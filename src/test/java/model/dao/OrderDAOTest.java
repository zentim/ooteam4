package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import main.java.model.bean.Order;
import main.java.model.bean.User;
import main.java.model.dao.OrderDAO;
import main.java.model.dao.UserDAO;

public class OrderDAOTest {
	public static UserDAO userdao = new UserDAO();
	public static User user = new User(); 
	public static int userId;
	
	public static OrderDAO orderdao = new OrderDAO();
	public static Order order = new Order();
	public static int orderId;
	
	@BeforeClass
	public static void testAdd() {
		// create user
		user.setEmail("abc@abc.com");
		user.setPassword("1234");
		userId = userdao.add(user);
		
		System.out.println("Test Start...");
		
		// create order
		order.setUser(userdao.get(userId));
		order.setDateOrdered(new Date());
		order.setDatePaid(new Date());
		order.setState(OrderDAO.waitPay);
		order.setTotal(1000);
		order.setDeliverMethod(0);
		order.setAddress("Yuntech");
		orderId = orderdao.add(order);
	}
	
	@Test
	public void testTotal() {
		int result = orderdao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
		// delete order
		orderdao.delete(orderId);
		
		System.out.println("Test End...");
		
		// delete user
		userdao.delete(userId);
	}

}
