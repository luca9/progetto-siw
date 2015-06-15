package it.uniroma3.model;

import it.uniroma3.enums.OrderState;

import java.util.*;

import javax.persistence.*;

/**
 * Created by lorenzovalente on 27/03/15.
 */

@Entity
@Table(name = "CustomerOrder")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Date creationTime;

	@Column(nullable = false)
	private OrderState orderState;

	@ManyToOne
	private RegisteredUser customer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date placed;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dispatched;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "OrderLines")
	private Map<String, OrderLine> orderLines;


	public Order() {
		this.creationTime = Calendar.getInstance().getTime();
	}

	public Order(RegisteredUser customer) {
		this.creationTime = Calendar.getInstance().getTime();
		this.customer = customer;
		this.orderLines = new HashMap<>();
	}

	public Long getId() {
		return id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	public void placeOrder() {
		this.setOrderState(OrderState.PLACED);
	}

	public void dispatchOrder() {
		this.setOrderState(OrderState.DISPATCHED);
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(RegisteredUser customer) {
		this.customer = customer;
	}

	public Map<String, OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Map<String, OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public void addProduct(int quantity, Product product) throws Exception {
		OrderLine a = orderLines.get(product.getCode());
		if (a == null)
			a = new OrderLine(product.getPrice(), quantity, product);
		int s = a.getQuantity();
		a.setQuantity(s + quantity);
		orderLines.put(product.getCode(), a);
	}

	public float getTotal() {
		float total = 0;
		for(OrderLine orderLine: this.orderLines.values())
			total += orderLine.getSubTotal();
		return total;

	}

	public void dispatch(){
		this.dispatched = Calendar.getInstance().getTime();
		this.orderState = OrderState.PLACED;
	}

	public List<OrderLine> getItems(){
		return new ArrayList<>(orderLines.values());
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

	public int getSize() {
		return this.orderLines.size();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Order)) return false;

		Order order = (Order) o;

		if (id != null ? !id.equals(order.id) : order.id != null) return false;
		if (creationTime != null ? !creationTime.equals(order.creationTime) : order.creationTime != null) return false;
		if (orderState != order.orderState) return false;
		if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
		if (placed != null ? !placed.equals(order.placed) : order.placed != null) return false;
		if (dispatched != null ? !dispatched.equals(order.dispatched) : order.dispatched != null) return false;
		return !(orderLines != null ? !orderLines.equals(order.orderLines) : order.orderLines != null);

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
		result = 31 * result + (orderState != null ? orderState.hashCode() : 0);
		result = 31 * result + (customer != null ? customer.hashCode() : 0);
		result = 31 * result + (placed != null ? placed.hashCode() : 0);
		result = 31 * result + (dispatched != null ? dispatched.hashCode() : 0);
		result = 31 * result + (orderLines != null ? orderLines.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", creationTime=" + creationTime +
				", customer=" + customer.getLastName() +
				", orderLines=" + orderLines +
				'}';
	}
}