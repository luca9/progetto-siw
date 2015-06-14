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
	private Map <Long, OrderLine> orderLines;


	public Order() {
		this.creationTime = Calendar.getInstance().getTime();
	}

	public Order (RegisteredUser customer) {
		this.creationTime = Calendar.getInstance().getTime();
		this.customer = customer;
		this.orderLines = new HashMap<Long,OrderLine>();
	}

	public Long getId () {
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

	public void placeOrder () {
		this.setOrderState(OrderState.PLACED);
	}

	public void dispatchOrder () {
		this.setOrderState(OrderState.DISPATCHED);
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(RegisteredUser customer) {
		this.customer = customer;
	}

	public Map<Long, OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Map<Long, OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
	public void addProductToOrder(int quantity, Product product) throws Exception {
		OrderLine a = this.orderLines.get(product.getId());
		if (a==null) {
			a = orderLines.put(product.getId(),new OrderLine(product.getPrice(),0,product));
		}
		a.setQuantity(a.getQuantity() + quantity);
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
		Order order = (Order) o;
		return this.getId() == order.getId();
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