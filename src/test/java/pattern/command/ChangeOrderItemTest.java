package test.java.pattern.command;

import main.java.model.bean.OrderItem;
import main.java.model.bean.Product;
import main.java.model.bean.User;
import main.java.model.dao.OrderItemDAO;
import main.java.pattern.command.ChangeOrderItem;
import main.java.pattern.command.Command;
import org.junit.*;

public class ChangeOrderItemTest {
    public static ChangeOrderItem changeOrderItemCommandTest;
    public static OrderItem oi= new OrderItem();
    public static int oiid;
    public static int oldNum;
    public static int oldState;
    public static int newNum;
    public static int newState;

@BeforeClass
public static void beforeClass() throws Exception {
    //create objects and data for the test
    OrderItemDAO orderItemDAO = new OrderItemDAO();
    oldNum = 10;
    oldState = 1;
    newNum = 20;
    newState = 0;
    oi.setState(oldState);
    oi.setQuantity(oldNum);
    oi.setUser(new User());
    oi.setProduct(new Product());
    oiid = orderItemDAO.add(oi);
    changeOrderItemCommandTest = new ChangeOrderItem(oi,newNum,newState);
}

@AfterClass
public static void afterClass() throws Exception {
    //delete object that created for the test
    OrderItemDAO orderItemDAO = new OrderItemDAO();
    orderItemDAO.delete(oiid);
} 
@Before
public void before() throws Exception{
    System.out.println("Test Start:\nOriginal State and Num of OrderItem:");
    System.out.println("Num:"+oi.getQuantity()+",State:"+oi.getState());
}
@After
public void after() throws Exception{
    System.out.println("New State and Num of OrderItem:");
    System.out.println("Num:"+oi.getQuantity()+",State:"+oi.getState()+"\nTest End\n");
}

@Test
public void testExecute() throws Exception {
    System.out.println("Execute Change OrderItem Command:");
    changeOrderItemCommandTest.execute();
    Assert.assertEquals(oi.getQuantity(),newNum);
    Assert.assertEquals(oi.getState(),newState);
} 

@Test
public void testUnExecute() throws Exception {
    System.out.println("unExecute Change OrderItem Command:");
    changeOrderItemCommandTest.unExecute();
    Assert.assertEquals(oi.getQuantity(),oldNum);
    Assert.assertEquals(oi.getState(),oldState);
} 

@Test
public void testClone() throws Exception {
    System.out.println("Clone ChangeOrderItem Command:");
    Command changeOrderItemClone= changeOrderItemCommandTest.clone();
    Assert.assertNotEquals(changeOrderItemCommandTest,changeOrderItemClone);
}

} 
