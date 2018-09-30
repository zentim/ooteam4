package test.java.model.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.*;

import main.java.model.bean.Order;
import main.java.model.bean.User;
import main.java.model.dao.OrderDAO;
import main.java.model.dao.UserDAO;

public class OrderDAOTest {
	public static UserDAO userdao = new UserDAO();
	public static User user = new User(); 
	public static int uid;
	
	public static OrderDAO orderdao = new OrderDAO();
	public static Order order = new Order();
	public static int oid;
	
	@BeforeClass
	public static void testAdd() {
		// create user
		user.setName("hello");
		user.setPassword("world");
		uid = userdao.add(user);
		
		System.out.println("Test Start...");
		
		// create order
		order.setOrderCode("1234");
		order.setAddress("Taiwan");
		order.setReceiver("Someone");
		order.setPhone("0912345678");
		order.setCreateDate(new Date());
		order.setPayDate(new Date());
		order.setUser(userdao.get(uid));
		order.setStatus(OrderDAO.waitPay);
		oid = orderdao.add(order);
	}
	
	@Test
	public void testTotal() {
		int result = orderdao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
		// delete order
		orderdao.delete(oid);
		
		System.out.println("Test End...");
		
		// delete user
		userdao.delete(userdao.get("hello").getId());
	}

}
