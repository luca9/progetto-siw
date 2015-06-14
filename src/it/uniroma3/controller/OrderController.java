package it.uniroma3.controller;

import it.uniroma3.enums.OrderState;
import it.uniroma3.facade.OrderFacade;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.RegisteredUser;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.Map;

/**
 * Created by lorenzovalente on 14/06/15.
 */

@SuppressWarnings("CdiUnproxyableBeanTypesInspection")
@ManagedBean
@SessionScoped
public class OrderController {
    private static final long serialVersionUID = 1L;

    public OrderController () {}

    private Date creationTime;
    private OrderState orderState;
    private RegisteredUser customer;
    private Date placed;
    private Date dispatched;
    private Map<Long, OrderLine> orderLines;

    @EJB(beanName = "order")
    private OrderFacade orderFacade;

    public OrderFacade getOrderFacade() {
        return orderFacade;
    }

    public void setOrderFacade(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public RegisteredUser getCustomer() {
        return customer;
    }

    public void setCustomer(RegisteredUser customer) {
        this.customer = customer;
    }

    public Date getPlaced() {
        return placed;
    }

    public void setPlaced(Date placed) {
        this.placed = placed;
    }

    public Date getDispatched() {
        return dispatched;
    }

    public void setDispatched(Date dispatched) {
        this.dispatched = dispatched;
    }

    public Map<Long, OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Map<Long, OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
