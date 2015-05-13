package it.uniroma3.model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lorenzovalente on 01/04/15.
 */

public class CustomerFacade {

    private EntityManagerFactory emf;
    private EntityManager em;

    public CustomerFacade() {
    }

    public void openEntityManager() {
        this.emf = Persistence.createEntityManagerFactory("products-unit");
        this.em = emf.createEntityManager();
    }

    public void closeEntityManager() {
        this.em.close();
        this.emf.close();
    }


    public Customer createCustomer
            (String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth, Address address) {
        openEntityManager();
        Customer customer = new Customer(firstName, lastName, email, phoneNumber, dateOfBirth, address);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(customer);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            customer = null;
        } finally {
            closeEntityManager();
        }
        return customer;
    }

    public boolean addOrder (Order order) {
        return true;
    }


    public List<Customer> getCustomerList() {
        this.openEntityManager();

        TypedQuery<Customer> customersTypedQuery =
                em.createQuery("select p from Customer p", Customer.class);
        List<Customer> customersList;
        customersList = customersTypedQuery.getResultList();

        this.closeEntityManager();

        return customersList;
    }
}







