package it.uniroma3.model;

import javax.persistence.*;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Float price;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String code;

	@Column(nullable = false)
	private int inStock;

	public Product() {
	}

	public Product(String name, Float price, String description, String code, int inStock) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.code = code;
		this.inStock = inStock;
	}

	public Long getId() {
		return id;
	}

	//          Getters & Setters        

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getInStock() { return inStock; }

	public void setInStock(int inStock) { this.inStock = inStock; }

	public boolean equals(Object obj) {
		Product product = (Product) obj;
		return this.getCode().equals(product.getCode());
	}

	public int hashCode() {
		return this.code.hashCode();
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("it.uniroma3.model.Product");
		sb.append("{id=").append(id);
		sb.append(", name='").append(name);
		sb.append("', price=").append(price);
		sb.append(", description='").append(description);
		sb.append("', code='").append(code);
		sb.append("'}\n");
		return sb.toString();
	}

}