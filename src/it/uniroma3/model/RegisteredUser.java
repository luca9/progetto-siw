package it.uniroma3.model;

import it.uniroma3.enums.UserGroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by lorenzovalente on 23/05/15.
 */

@Entity
public class RegisteredUser extends User {


    @OneToMany
    private List<Order> orders;

    @Column (nullable = false)
    private String username;

    @Column (nullable = false)
    private String password;

    private final UserGroup userGroup = UserGroup.USER;

    public RegisteredUser() {
    }

    public RegisteredUser
            (String firstName, String lastName, String email,
             String phoneNumber, Date dateOfBirth, Address address,
             List<Order> orders, String username, String password) {

        super(firstName, lastName, email, phoneNumber, dateOfBirth, address);
        this.orders = orders;
        this.username = username;
        this.password = password;
    }

    public UserGroup getUserGroup() {
        return userGroup;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
