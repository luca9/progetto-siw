package it.uniroma3.controller;


import it.uniroma3.facade.OrderFacade;
import it.uniroma3.facade.ProductFacade;
import it.uniroma3.facade.UserFacade;
import it.uniroma3.model.Cart;
import it.uniroma3.model.RegisteredUser;
import it.uniroma3.model.Order;
import it.uniroma3.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class RegisteredUserController {
    @ManagedProperty(value = "#{login.user}")
    private RegisteredUser currentCustomer;
    private Order order;
    private Cart cart;
    private Order currentOrder;
    private List <Order> orders;

    @EJB(name = "order")
    private OrderFacade orderFacade;
    @EJB(name = "product")
    private ProductFacade productFacade;
    @EJB(name = "user")
    private UserFacade userFacade;

    private String productCode;
    private int quantity;
    @ManagedProperty(value = "#{login}")
    private Login login;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getCartSize() {
        int size = (cart == null) ? 0 : cart.getSize();
        return (size == 0) ? "" : "(" + size + ")";
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public OrderFacade getOrderFacade() {
        return orderFacade;
    }

    public void setOrderFacade(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public String dispatchOrder() throws Exception {
        this.currentOrder.dispatch();
        try {
            this.currentCustomer.addOrder(this.currentOrder);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.userFacade.updateUser(this.currentCustomer);
        }
        catch (Exception r) {
            r.printStackTrace();
        }
        return "confirmOrder";
    }

    public String getCustomerOrders () {
        this.orders = currentCustomer.getOrders();
        findOrder(1101L);
        System.out.println(order);
        return "myOrders";
    }

    public String findOrder (Long id) {
        this.order = orderFacade.getOrder(id);
        return "order";
    }

    public RegisteredUser getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(RegisteredUser currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }



    public String createOrder() {
        this.currentCustomer = login.getCustomer();
        this.currentOrder = new Order(currentCustomer);
        return "insertOrder";
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String addToCart(String productCode) throws Exception {
        if (!login.isLoggedIn())
            return "login";
        if (cart == null)
            this.cart = new Cart(currentCustomer);
        Product p = this.productFacade.getProduct(productCode);
        this.cart.addProduct(quantity, p);
        return "welcomeUser";
    }

    @PostConstruct
    private void forceGetC(){
        this.currentCustomer = login.getCustomer();
    }

    public void closeCart() throws Exception{
        this.currentOrder = cart;
        dispatchOrder();
    }

    public String addToOrder() throws Exception {
        Product p = this.productFacade.getProduct(productCode);
        if (p != null) {
            this.currentOrder.addProduct(quantity, p);
        }
            return "insertOrder";
    }
}