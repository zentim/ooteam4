package main.java.pattern.command;

import main.java.model.bean.OrderItem;
import main.java.model.dao.OrderItemDAO;

/**
 * 
 * Command Pattern - ConcreteCommand
 *
 */
public class ChangeOrderItem implements Command, Cloneable {
    OrderItem oi;
    int oldNum;
    int oldState;
    int newNum;
    int newState;

    public ChangeOrderItem(OrderItem oi, int newNum, int newState) {
        this.oi = oi;
        this.oldNum = oi.getQuantity();
        this.oldState = oi.getState();
        this.newNum = newNum;
        this.newState = newState;
    }

    @Override
    public void execute() {
        oi.setQuantity(newNum);
        oi.setState(newState);
        try {
            new OrderItemDAO().update(oi);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void unExecute() {
        oi.setQuantity(oldNum);
        oi.setState(oldState);
        try {
            new OrderItemDAO().update(oi);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ChangeOrderItem clone() throws CloneNotSupportedException {
        ChangeOrderItem c = (ChangeOrderItem) super.clone();
        c.oi = oi.clone(); // deep clone
        return c;
    }
}
