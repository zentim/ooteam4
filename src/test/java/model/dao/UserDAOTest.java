package test.java.model.dao;

import static org.junit.Assert.*;

import org.junit.*;

import main.java.model.bean.User;
import main.java.model.dao.UserDAO;

public class UserDAOTest {
	public static UserDAO userdao = new UserDAO();
	public static User user = new User(); 
	public static int uid;
	
	@BeforeClass
	public static void testAdd() {
		System.out.println("Test Start...");
		
		// create user
		user.setName("hello");
		user.setPassword("world");
		uid = userdao.add(user);
	}
	
	@Test
	public void testTotal() {
		int result = userdao.getTotal();
		assertNotNull("should not be null", result);
	}
	
	@Test 
	public void testIsExist() {
		boolean result = userdao.isExist("hello");
		assertFalse("failure - should be false", !result);
	}
	
	@Test
	public void testGetByName() {
		User result = userdao.get("hello");
		assertNotNull("should not be null", result);
	}
	
	@Test
	public void testUpdate() {
		user.setPassword("worldtest");
		userdao.update(user);
		assertEquals("failure - strings are not equal", "worldtest", userdao.get(uid).getPassword());
	}
	
	@AfterClass
	public static void testDelete() {
		// delete user
		userdao.delete(userdao.get("hello").getId());
		
		System.out.println("Test End...");
	}

}
