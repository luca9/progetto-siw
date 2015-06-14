package it.uniroma3.controller;

import it.uniroma3.facade.ProductFacade;
import it.uniroma3.model.Product;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ProductController {
	private static final long serialVersionUID = 1L;

	public ProductController(){
	}
	private String code;
	private String name;
	private Float price;
	private String description;
	private String searchTerm;
	private Product product;
	private int inStock;
	private int quantity;
	private List<Product> products;


	@EJB(beanName = "product")
	private ProductFacade productFacade;


	public String createProduct() {
		this.product = this.productFacade.createProduct(this.name, this.price, this.code, this.description, this.inStock);
		return "product";
	}

	public String listProducts() {
		this.products = this.productFacade.getAllProducts();
		return "products";
	}

	public String findProduct() {
		this.product = this.productFacade.getProduct(code);
		return "product";
	}

	public String findProduct(String code) {
		this.product = this.productFacade.getProduct(code);
		return "product";
	}

	public String findProducts() {
		this.products = this.productFacade.searchProduct(searchTerm);
		if (this.products.size() == 0) return "invalid";
		if (this.products.size() == 1) {
			this.product = this.products.get(0);
			return "product";
		}
		return "products";
	}

	public String increaseProductStock () {
		Product product = productFacade.getProduct(code);
		product.setInStock(product.getInStock()+quantity);
		productFacade.updateProduct(product);
		this.product = product;
		return "confirmAdd";
	}

	public String decreaseProductStock (String code, int quantity) {
		Product product = productFacade.getProduct(code);
		if (quantity>=product.getInStock()) product.setInStock(0);
		else product.setInStock(product.getInStock()-quantity);
		productFacade.updateProduct(product);
		this.product = product;
		return "confirmRemoval";
	}

	public String removeProduct () {
		product = productFacade.getProduct(code);
		productFacade.deleteProduct(code);
		return "confirmDeletion";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getQuantity () { return this.quantity; }

	public void setQuantity(int quantity) {this.quantity = quantity; }

	public int getInStock() {return inStock; }

	public void setInStock(int inStock) { this.inStock = inStock; }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSearchTerm() { return searchTerm; }

	public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}