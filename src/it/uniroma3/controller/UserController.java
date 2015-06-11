package it.uniroma3.controller;

import it.uniroma3.enums.UserGroup;
import it.uniroma3.facade.UserFacade;
import it.uniroma3.model.Address;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;

/**
 * Created by lorenzovalente on 24/05/15.
 */

@ManagedBean
@SessionScoped
public class UserController {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private Date registrationDate;
    private Address address;
    private String username;
    private String password;

    @EJB(beanName = "userController")
    private UserFacade userFacade;


    //addresses
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;


    public void main (String [] args) {
        System.out.println(userFacade.getUser(Long.parseLong("1")));
    }


    public String saveUser() {
        Address address = new Address(country, street, city, state, zipCode);
        userFacade.saveUser(username, password, firstName, lastName, email,
                phoneNumber, dateOfBirth, address);
        return "confirmRegistration";
    }

    public String login() {
         if (userFacade.getUser(username).getUserGroup() == UserGroup.USER)
             return "welcomeUser";
         else return "welcomeAdmin";
    }


    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
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
