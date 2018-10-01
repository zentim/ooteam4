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
	public static UserDAO userdao = new UserDAO();
	public static User user = new User(); 
	public static int uid;
	
	public static CategoryDAO categorydao = new CategoryDAO();
	public static Category category = new Category();
	public static int cid;
	
	public static ProductDAO productdao = new ProductDAO();
	public static Product product = new Product();
	public static int pid;
	
	public static OrderDAO orderdao = new OrderDAO();
	public static Order order = new Order();
	public static int oid;
	
	public static OrderItemDAO orderitemdao = new OrderItemDAO();
	public static OrderItem orderitem = new OrderItem();
	public static int oiid;
	
	@BeforeClass
	public static void testAdd() {
		// create user
		user.setName("hello111");
		user.setPassword("world111");
		userdao.add(user);
		uid = userdao.get("hello111", "world111").getId();
		
		// create category
		category.setName("Book");
		cid = categorydao.add(category);
		
		// create product
		product.setName("Harry Potter");
		product.setOriginalPrice(1000);
		product.setPromotePrice(800);
		product.setStock(5);
		product.setCategory(categorydao.get(cid));
		product.setCreateDate(new Date());
		pid = productdao.add(product);
		
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
		
		System.out.println("Test Start...");
		
		// create orderitem
		orderitem.setProduct(productdao.get(pid));
		orderitem.setOrder(orderdao.get(oid));
		orderitem.setUser(userdao.get(uid));
		orderitem.setNumber(1);
		oiid = orderitemdao.add(orderitem);
	}
	
	@Test
	public void testTotal() {
		int result = orderitemdao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	
	@AfterClass
	public static void testDelete() {
		// delete orderitem
		orderitemdao.delete(oiid);
		
		System.out.println("Test End...");
		
		// delete order
		orderdao.delete(oid);
		
		// delete product
		productdao.delete(pid);
		
		// delete category
		categorydao.delete(cid);
		
		// delete user
		userdao.delete(uid);
	}

}
