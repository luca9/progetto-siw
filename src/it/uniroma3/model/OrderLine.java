package it.uniroma3.model;

import javax.persistence.*;


/**
 * Created by lorenzovalente on 27/03/15.
 */

@Embeddable
public class OrderLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Float unitPrice;

	@Column(nullable = false)
	private int quantity;

	@ManyToOne
	private Order order;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Product product;

	public OrderLine() {}

	public OrderLine (Float unitPrice, int quantity, Product product) throws Exception {
		if (quantity <=0)
			throw new Exception("Invalid input");
		else if (quantity > product.getInStock())
			throw new Exception("Not enough " + product.getName()+" in stock");
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.product = product;

	}

	public Long getId() {
		return id;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public float getSubTotal () {
		return this.quantity * this.unitPrice;
	}

	public Integer getQuantity() { return this.quantity;}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderLine{" +
				"id=" + id +
				", unitPrice=" + unitPrice +
				", quantity=" + quantity +
				", product=" + product.toString() +
				'}';
	}
}
