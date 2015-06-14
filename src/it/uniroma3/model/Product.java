package it.uniroma3.model;

import javax.persistence.*;

@Entity
public class Product {

	@Id
	@Column(nullable = false)
	private String code;


	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Float price;

	@Column(nullable = false)
	private String description;


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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product)) return false;

		Product product = (Product) o;

		if (inStock != product.inStock) return false;
		if (code != null ? !code.equals(product.code) : product.code != null) return false;
		if (name != null ? !name.equals(product.name) : product.name != null) return false;
		if (price != null ? !price.equals(product.price) : product.price != null) return false;
		return !(description != null ? !description.equals(product.description) : product.description != null);

	}

	public int hashCode() {
		return this.code.hashCode();
	}

	@Override
	public String toString() {
		return "Product{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				", inStock=" + inStock +
				'}';
	}
}