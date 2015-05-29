package it.uniroma3.facade;

import java.util.Date;

import it.uniroma3.model.RegisteredUser;
import it.uniroma3.model.User;
import it.uniroma3.model.Address;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless(name = "userController")
public class UserFacade {

    @PersistenceContext(unitName = "products-unit")
    private EntityManager em;

    public UserFacade() {
    }

    public User createUser
            (String firstName, String lastName, String email,
             String phoneNumber, Date dateOfBirth, Address address,
             String username, String password) {

        RegisteredUser user = new RegisteredUser(firstName, lastName, email,
                phoneNumber, dateOfBirth, address, null, username, password);
        em.persist(user);
        return user;
    }

    public List <RegisteredUser> getAllRegisteredUsers() {
        return em.createQuery("select u from RegisteredUser u",
                RegisteredUser.class).getResultList();
    }

    public User getUser (Long id) {
        User user = this.em.find(RegisteredUser.class, id);
        return user;
    }


    public RegisteredUser verifyUser (String username, String password) {
        RegisteredUser user = new RegisteredUser();
        List<RegisteredUser> users = em.createQuery
                ("select u from RegisteredUser u where u.username = :username",
                        RegisteredUser.class).getResultList();
        for (RegisteredUser u : users)
            if (u.getPassword().equals(password)) user = u;
        return user;
    }

    public void updateUser (User user) {
        this.em.merge(user);
    }

    public void deleteUser (Long id) {
        User user = this.getUser(id);
        if (user!=null) this.em.remove(user);
    }

}







