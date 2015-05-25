package it.uniroma3.model;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerFacade {

	@PersistenceContext(unitName = "jee-unit")
	private EntityManager em;
	
	public Order createOrder() {
		Order order = new Order();
		em.persist(order);
		return order;
	}
	
	public void aggiungiOrderLine(int quantity,Long idOrder, long idProduct) throws Exception {
		Order order = em.find(Order.class,idOrder);
		Product product = em.find(Product.class, idProduct);
		Float unitPrice = product.getPrice();
		OrderLine orderLine = new OrderLine(unitPrice, quantity, product);
		order.addOrderLine(orderLine);
		em.persist(orderLine);
	}
	
	

}
