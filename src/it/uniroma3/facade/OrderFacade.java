package it.uniroma3.facade;

import java.util.Date;
import java.util.List;

import it.uniroma3.enums.OrderState;
import it.uniroma3.model.Order;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Product;
import it.uniroma3.model.RegisteredUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "order")
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

    public List<Order> getCustomerOrders (RegisteredUser customer) throws Exception {
        List<Order> orders = null;
        try {
            orders = em.createQuery("select o from Order o " +
                    "where o.customer=:customer", Order.class)
                    .setParameter("customer", customer).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrder(Long id) {
    	return em.find(Order.class, id);
    }

    public void updateOrder(Order o) {
    	em.merge(o);
    }

//    public Order placeOrder(Long orderID) {
//        Order o = this.getOrder(orderID);
//        for (OrderLine ol : o.getOrderLines().values()) {
//            Product p = ol.getProduct();
//            if (ol.getQuantity() > p.getInStock()) {
//                em.refresh(o);
//                return null;
//            }
//            p.setInStock(p.getInStock() - ol.getQuantity());
//        }
//        o.setOrderState(OrderState.PLACED);
//        o.setDispatched(new Date());
//        em.merge(o);
//        return o;
//    }

    public List getDispatchedOrders() {
        Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.orderState = " + OrderState.DISPATCHED);
        return q.getResultList();
    }

    public List getOrdersState(OrderState state, Long customerID) {
        Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.orderState = " + state + " AND o.customer = " + customerID);
        return q.getResultList();
    }




}
