package it.uniroma3.controller;

import it.uniroma3.facade.OrderFacade;
import it.uniroma3.facade.ProductFacade;
import it.uniroma3.facade.UserFacade;
import it.uniroma3.model.RegisteredUser;
import it.uniroma3.model.Order;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class AdminHandler {

    private Order order;
    private List orders;
    private RegisteredUser customer;
    @EJB(beanName = "user")
    private UserFacade uf;
    @EJB(beanName = "product")
    private ProductFacade pf;
    @EJB(beanName = "order")
    private OrderFacade of;

    @ManagedProperty(value = "#{login}")
    private Login login;

    public RegisteredUser getCustomer() {
        return customer;
    }

    public void setCustomer(RegisteredUser customer) {
        this.customer = customer;
    }

    public List getOrders() {
        return orders;
    }

    public void setOrders(List orders) {
        this.orders = orders;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public String getClosedOrders() {
        this.orders = this.of.getClosedOrders();
        return "openOrders";
    }

    public String getCustomerInfo(Long orderID) {
        this.customer = (RegisteredUser) this.of.getOrder(orderID).getCustomer();
        return "customerDetails";
    }

    public String viewOrderDetails(Long orderID) {
        this.order = this.of.getOrder(orderID);
        return "orderDetails";
    }


   /* public String processOrder(Long orderID) {
        Order current = this.of.getOrder(orderID);
        for (OrderLine ol : current.getOrderlines()) {
            Product p = ol.getProduct();
            if (ol.getQuantity() > p.getInStock())
                return "error";
            p.setInStock(p.getInStock() - ol.getQuantity());
            this.pf.updateProduct(p);
        }
        current.setState(OrderState.PROCESSED);
        return "success";
    }*/

    //TODO: da sistemare
    public String processOrder(Long orderID) {
        Order o = this.of.processOrder(orderID);
        if (o != null)
            return "success";
        else {
            return "orderDetails";
        }

    }

//    	Order current =  this.of.getOrder(orderID);
//    	Product p = //trovo il prodotto usando la ProfuctFacade, nella producti facade cerco il prodotto
//


}