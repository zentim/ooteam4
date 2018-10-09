package test.java.model.dao;

import static org.junit.Assert.*;

import org.junit.*;

import main.java.model.bean.User;
import main.java.model.dao.UserDAO;

public class UserDAOTest {
	public static UserDAO userdao = new UserDAO();
	public static User user = new User();
	public static int userId;

	@BeforeClass
	public static void testAdd() {
		System.out.println("Test Start...");

		// create user
		user.setEmail("abc@abc.com");
		user.setPassword("1234");
		userId = userdao.add(user);
	}

	@Test
	public void testTotal() {
		int result = userdao.getTotal();
		assertNotNull("should not be null", result);
	}

	@Test
	public void testIsExist() {
		boolean result = userdao.isExist("abc@abc.com");
		assertTrue("success - should be true", result);
	}

	@Test
	public void testGetByName() {
		User result = userdao.get("abc@abc.com");
		assertNotNull("should not be null", result);
	}

	@Test
	public void testUpdate() {
		user.setPassword("abcd");
		userdao.update(user);
		assertEquals("failure - strings are not equal", "abcd", userdao.get(userId).getPassword());
	}

	@AfterClass
	public static void testDelete() {
		// delete user
		userdao.delete(userId);

		System.out.println("Test End...");
	}

}
