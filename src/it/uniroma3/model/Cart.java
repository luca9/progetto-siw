package it.uniroma3.model;


public class Cart extends Order{

    public void addProduct(Product p) throws Exception {
        super.addProduct(1, p);
    }

    public Cart(RegisteredUser customer) {
        super(customer);
    }
}
