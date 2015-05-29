package it.uniroma3.model;

import org.apache.openejb.jee.jba.cmp.Strategy;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lorenzovalente on 27/03/15.
 */


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

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


    public User() {
    }

    public User(String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth,
                Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.registrationDate = Calendar.getInstance().getTime();

    }


    public Long getId() { return id; }

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("it.uniroma3.model.User");
        sb.append("{id=").append(id);
        sb.append(", firstName='").append(firstName);
        sb.append(", lastName='").append(lastName);
        sb.append("', email=").append(email);
        sb.append("', phoneNumber=").append(phoneNumber);
        sb.append(", dateOfBirth='").append(dateOfBirth);
        sb.append("', address='").append(address);
        sb.append("'}\n");
        return sb.toString();
    }

}

