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

    private final UserGroup userGroup = UserGroup.USER;

    public RegisteredUser() {
    }

    public RegisteredUser
            (String username, String password, String firstName, String lastName, String email,
             String phoneNumber, Date dateOfBirth, Address address,
             List<Order> orders) {

        super(username, password, firstName, lastName, email, phoneNumber, dateOfBirth, address);
        this.orders = orders;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    
}
