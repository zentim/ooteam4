package main.java.pattern.command;

import main.java.model.bean.OrderItem;
import main.java.model.dao.OrderItemDAO;

/**
 * 
 * Command Pattern - ConcreteCommand
 *
 */
public class DeleteOrderItem implements Command, Cloneable {
    OrderItem oi;
    int oldState;

    public DeleteOrderItem(OrderItem oi) {
        this.oi = oi;
        this.oldState = oi.getState();
    }

    @Override
    public void execute() {
        oi.setState(0);
        try {
            new OrderItemDAO().update(oi);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void unExecute() {
        oi.setState(oldState);
        try {
            new OrderItemDAO().update(oi);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public DeleteOrderItem clone() throws CloneNotSupportedException {
        DeleteOrderItem c = (DeleteOrderItem) super.clone();
        c.oi = oi.clone(); // deep clone
        return c;
    }

}
