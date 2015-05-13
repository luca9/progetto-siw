package it.uniroma3.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lorenzovalente on 27/03/15.
 */

@Entity
@Table(name="tb_order")
public class Order {

    private final int OPEN = 0;
    private final int CLOSED = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date creationTime;

    @ManyToOne
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orders_id")
    private List<OrderLine> orderLines;

    private int state;

    public Order() {
        this.creationTime = Calendar.getInstance().getTime();
        this.state = 0;
    }

    public Order (Customer customer, List <OrderLine> orderLines) {
        this.creationTime = Calendar.getInstance().getTime();
        this.customer = customer;
        this.orderLines = orderLines;
    }

    public Long getId () {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public int getState () { return this.state; }

    public void setState (int state) {this.state = state;}

    public void open () {this.setState(OPEN);}
    public void close () {this.setState(CLOSED);}


    public void addOrderLine (OrderLine orderLine) {this.orderLines.add(orderLine);}

    public boolean isOpen () { return this.getState() == OPEN;}
    public boolean isClosed () { return this.getState() == CLOSED;}

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
                ", state=" + state +
                '}';
    }
}