package it.uniroma3.controller;

import it.uniroma3.facade.CustomerFacade;
import it.uniroma3.model.Address;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.OrderFacade;
import it.uniroma3.model.OrderLine;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class CustomerController {

    @ManagedProperty(value="#{param.id}")
    private Long id;
    private String firstName;
    private String lastName;   
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private Date registrationDate;
    private Customer customer;
    private List<Customer> customers;
    private List<Order> orders;
    private Order order;
    private OrderLine orderLine;
    private Address address;
    private int quantity;
    private long idOrder;
    private long idProduct;    

    @EJB
    private OrderFacade orderFacade;
    private CustomerFacade customerFacade;
    
    public String createOrder() {
    	this.order = customerFacade.createOrder();
    	return "order";
    }
    
    public String addOrderLine() throws Exception {
		this.orderLine = customerFacade.aggiungiOrderLine(quantity, idOrder, idProduct);
		return "orderLine"; 
    }
     
    public OrderLine getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(OrderLine orderLine) {
		this.orderLine = orderLine;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String listOrders() {
    	this.orders = this.customer.getOrders();
    	return "orders";
    }
    
    public String findOrder(Long id) {
    	this.order = this.orderFacade.getOrder(id);
    	return "order";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    
    public Address getAddress() {
    	return address;
    }
    
    public void setAddress(Address address) {
    	this.address = address;
    }
    
    public Order getOrder() {
    	return order;
    }
    
    public void setOrder(Order order) {
    	this.order = order;
    }
    
   
}


