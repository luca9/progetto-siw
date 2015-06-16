package it.uniroma3.model;

import javax.persistence.*;


/**
 * Created by lorenzovalente on 27/03/15.
 */

@Embeddable
public class OrderLine {

	@Column(nullable = false)
	private Float unitPrice;

	@Column(nullable = false)
	private int quantity;

	@ManyToOne
	private Order order;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Product product;

	public OrderLine() {}

	public OrderLine (Float unitPrice, int quantity, Product product)  {
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.product = product;

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
				", unitPrice=" + unitPrice +
				", quantity=" + quantity +
				", product=" + product.toString() +
				'}';
	}
}
