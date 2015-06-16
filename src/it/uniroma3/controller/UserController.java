package it.uniroma3.controller;

import it.uniroma3.enums.UserGroup;
import it.uniroma3.facade.ProductFacade;
import it.uniroma3.facade.UserFacade;
import it.uniroma3.model.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lorenzovalente on 24/05/15.
 */

@ManagedBean
@SessionScoped
public class UserController {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private Date registrationDate;
    private Address address;
    private String username;
    private String password;
    private Product product;
    private Login login;

    @EJB(beanName = "user")
    private UserFacade userFacade;


    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private List<Order> orders;


    public String saveUser() {
        Address address = new Address(country, street, city, state, zipCode);
        this.orders = new ArrayList<>();
        userFacade.saveUser(username, password, firstName, lastName, email,
                phoneNumber, dateOfBirth, address, orders);
        return "confirmRegistration";
    }

    public String listOrders() {
        this.orders = this.userFacade.getAllOrder();
        return "MyOrders";
    }


    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {return firstName;}

    public void setFirstName (String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName (String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail (String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public Date getDateOfBirth() {return dateOfBirth;}

    public void setDateOfBirth(Date dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public Date getRegistrationDate() {return registrationDate;}

    public Address getAddress() {return address;}

    public void setAddress(Address address) {this.address = address;}

    public String getUsername() {return username; }

    public void setUsername(String username) {this.username = username;}

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
