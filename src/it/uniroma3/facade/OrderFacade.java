package it.uniroma3.facade;

import java.util.Date;
import java.util.List;

import it.uniroma3.enums.OrderState;
import it.uniroma3.model.Order;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "userController")
public class OrderFacade {

    @PersistenceContext(unitName = "products-unit")
    private EntityManager em;

    public OrderFacade() {

    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Order createOrder() {

    	Order order = new Order();
    	em.persist(order);
    	return order;
    }

    public Order getOrder(Long id) {
    	return em.find(Order.class, id);
    }

    public void updateOrder(Order o) {
    	em.merge(o);
    }

    public Order processOrder(Long orderID) {
        Order o = this.getOrder(orderID);
        for (OrderLine ol : o.getOrderLines().values()) {
            Product p = ol.getProduct();
            if (ol.getQuantity() > p.getInStock()) {
                em.refresh(o);
                return null;
            }
            p.setInStock(p.getInStock() - ol.getQuantity());
        }
        o.setOrderState(OrderState.PROCESSED);
        o.setClosed(new Date());
        em.merge(o);
        return o;
    }

    public List getClosedOrders() {
        Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.orderState = " + OrderState.CLOSED);
        return q.getResultList();
    }

    public List getOrdersState(OrderState state, Long customerID) {
        Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.orderState = " + state + " AND o.customer = " + customerID);
        return q.getResultList();
    }




}
