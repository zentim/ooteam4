package main.java.pattern.command;

import main.java.model.bean.OrderItem;
import main.java.model.dao.OrderItemDAO;

public class ChangeOrderItem implements Command {
    OrderItemDAO orderItemDAO;
    OrderItem oi;
    int oldNum;
    int oldState;
    int newNum;
    int newState;
    
    
    public ChangeOrderItem(OrderItemDAO orderItemDAO, OrderItem oi, int newNum, int newState) {
        this.orderItemDAO = orderItemDAO;
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
        orderItemDAO.update(oi);
    }

    @Override
    public void unExecute() {
        oi.setQuantity(oldNum);
        oi.setState(oldState);
        orderItemDAO.update(oi);
    }

}
