package main.java.model.bean;

import main.java.pattern.observer.Observer;
import main.java.pattern.observer.Subject;

/**
 * 
 * Observer Pattern - concrete observer
 * Factory Method Pattern - ConcreteObject
 *
 */
public class User implements Observer {

    private int id;
    private String email;
    private String password;

    /* Observer Method */
    @Override
    public void update(Subject subject) {
        Product product = (Product) subject;

        /**
         * If you want to send an email to observer, you need to uncomment the following
         * code.
         * 
         * Then setting the sender's gmail address and password in
         * "main.java.model.util.MailUtil" java file.
         */
//        Mailer b = new Mailer("");
//        String mail = email;
//        b.sendMail(mail, product.getId(), product.getName());
//		
        System.out.println("UserId " + id + " get a notify " + "from ProductId " + product.getId() + ": ");
        System.out.println("Product is available now you can buy it. ");
        System.out.println("(Inventory Quantity: " + product.getInventory() + ")");
    }

    /* Getter and Setter */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
