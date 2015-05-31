package it.uniroma3.facade;

import it.uniroma3.model.Order;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "userController")
public class OrderFacade {

    @PersistenceContext(unitName = "products-unit")
    private EntityManager em;
    
    public OrderFacade() {
    	
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
    
      
   
    
        
}
