package it.uniroma3.model;

import it.uniroma3.enums.UserGroup;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by lorenzovalente on 23/05/15.
 */

@Entity
@DiscriminatorValue("REGISTEREDUSER")
public class RegisteredUser extends User {


    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    private List<Order> orders;

    public RegisteredUser() {}

    public RegisteredUser
            (String username, String password, String firstName, String lastName, String email,
             String phoneNumber, Date dateOfBirth, Address address,
             List<Order> orders) {

        super(username, password, firstName, lastName, email, phoneNumber, dateOfBirth, address);
        setUserGroup(UserGroup.REGISTEREDUSER);
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order o) {
        this.orders.add(o);
    }

}
