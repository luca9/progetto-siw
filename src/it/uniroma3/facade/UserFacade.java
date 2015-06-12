package it.uniroma3.facade;

import java.util.Date;

import it.uniroma3.enums.UserGroup;
import it.uniroma3.model.*;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import javax.persistence.*;

@Stateless(name = "user")
public class UserFacade {

    @PersistenceContext(unitName = "products-unit")
    private EntityManager em;

    public UserFacade() {
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public RegisteredUser saveUser
            (String username, String password, String firstName, String lastName, String email,
             String phoneNumber, Date dateOfBirth, Address address) {
        RegisteredUser user = new RegisteredUser(username, password, firstName, lastName, email,
                phoneNumber, dateOfBirth, address, null);
        em.persist(user);
        return user;
    }

    public List <RegisteredUser> getAllRegisteredUsers() {
        return em.createQuery("select u from RegisteredUser u",
                RegisteredUser.class).getResultList();
    }

    public List <Order> getAllOrder() {
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    public User getUser (Long id) {
        return em.find(User.class, id);
    }


    public User getUser (String username) {
        return em.find(User.class, username);
    }
    
     public void updateUser (User user) {
        this.em.merge(user);
    }

    public void deleteUser (Long id) {
        User user = this.getUser(id);
        if (user!=null) this.em.remove(user);
    }

    public User findUser(String username, UserGroup group) {
        String subQuery = (group == null) ? "" : " AND u.group = " + group;
        User u = null;
        try {
            u = (User) this.em.createQuery("SELECT u FROM User u WHERE " +
                    "u.username = :email" + subQuery)
                    .setParameter("email", username)
                    .getSingleResult();
        } catch (Exception ignored) {
        }
        return u;
    }

    public User findUser(String email) {
        return findUser(email, null);
    }

    public RegisteredUser findCustomer(String email) {
        return (RegisteredUser) findUser(email, UserGroup.USER);
    }

    public Administrator findAdmin(String email) {
        return (Administrator) findUser(email, UserGroup.ADMINISTRATOR);
    }

}







