package main.java.pattern.command;

import main.java.model.bean.OrderItem;
import main.java.model.dao.OrderItemDAO;
/*
 * Command Pattern - ConcreteCommand
 */
public class DeleteOrderItem implements Command {
    OrderItemDAO orderItemDAO;
    OrderItem oi;
    int oldState;
    
    public DeleteOrderItem(OrderItemDAO orderItemDAO, OrderItem oi) {
        this.orderItemDAO = orderItemDAO;
        this.oi = oi;
        this.oldState = oi.getState();
    }

    @Override
    public void execute() {
        oi.setState(0);
        orderItemDAO.update(oi);
    }

    @Override
    public void unExecute() {
        oi.setState(oldState);
        orderItemDAO.update(oi);
    }

}
