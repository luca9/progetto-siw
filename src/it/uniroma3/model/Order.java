package it.uniroma3.model;

import it.uniroma3.enums.OrderState;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	private OrderState orderState;

	@ManyToOne
	private RegisteredUser customer;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date opened;
    @Temporal(TemporalType.TIMESTAMP)
    private Date closed;
    @Temporal(TemporalType.TIMESTAMP)
    private Date processed;

	@OneToMany
	@JoinColumn(name = "orders_id")
	private Map<Long, OrderLine> orderLines;


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

	public void setUser(User user) {
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
	
	public Date getOpened() {
		return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }
    
	public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
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