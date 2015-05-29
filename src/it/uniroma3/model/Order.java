package it.uniroma3.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import it.uniroma3.enums.OrderState;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<OrderLine> orderLines;


    public Order() {
        this.creationTime = Calendar.getInstance().getTime();
    }

    public Order (RegisteredUser customer) {
        this.creationTime = Calendar.getInstance().getTime();
        this.customer = customer;
        this.orderLines = new ArrayList<OrderLine>();
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

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void addOrderLine (OrderLine orderLine) {this.orderLines.add(orderLine);}


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