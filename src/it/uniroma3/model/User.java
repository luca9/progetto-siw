package it.uniroma3.model;

import it.uniroma3.enums.UserGroup;
import org.apache.openejb.jee.jba.cmp.Strategy;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lorenzovalente on 27/03/15.
 */


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userGroup")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Basic(fetch = FetchType.EAGER)
    @Column(nullable = false, name = "userGroup")
    @Enumerated(value = EnumType.STRING)
    private UserGroup userGroup;

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

    public User(String username, String password, String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth,
                Address address) {
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
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

