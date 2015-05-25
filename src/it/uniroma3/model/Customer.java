package it.uniroma3.model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lorenzovalente on 27/03/15.
 */


@Entity
@Table(name="tb_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date dateOfBirth;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date registrationDate;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;



    //             Constructors

    public Customer() {
    }

    public Customer (String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth,
                     Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.registrationDate = Calendar.getInstance().getTime();

    }


    //              Getters

    public Long getId() { return id; }
    public String getFirstName () { return this.firstName; }
    public String getLastName () { return this.lastName; }
    public String getEmail () { return this.email; }
    public String getPhoneNumber () { return this.phoneNumber; }
    public String getAddress () { return this.address.toString(); }


    //              Setters

    public void setFirstName (String firstName) { this.firstName = firstName; }
    public void setLastName (String lastName) { this.lastName = lastName; }
    public void setEmail (String email) { this.email = email; }
    public void setPhoneNumber (String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setDateOfBirth (Date dateOfBirth) {this.dateOfBirth = dateOfBirth; }
    public void setAddress (Address address) {this.address = address; }


    /**** Orders management ****/



    public boolean addOrder (Order order) {
        for (OrderLine orderLine: order.getOrderLines())
            orderLine.getProduct().setInStock(orderLine.getProduct().getInStock()-orderLine.getQuantity());
        order.close();
        this.orders.add(order);
        return true;
    }
    
    
    
    public List<Order> getOrders() {
    	return this.orders;
    }



    //              toString


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", registrationDate=" + registrationDate +
                ", address=" + address.toString() +
                ", orders=" + orders.toString() +
                '}';
    }

}

