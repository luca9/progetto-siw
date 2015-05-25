package it.uniroma3.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderFacade {
	
	@PersistenceContext(unitName = "jee-unit")
	private EntityManager em;
	
	public Order getOrder(long id) {
		Order order = em.find(Order.class, id);
		return order;
	}

}
