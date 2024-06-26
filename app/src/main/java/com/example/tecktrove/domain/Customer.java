package com.example.tecktrove.domain;


import com.example.tecktrove.contacts.Email;
import com.example.tecktrove.contacts.Telephone;
import com.example.tecktrove.util.Pair;

import java.util.ArrayList;

public class Customer extends User {

    private ArrayList<Synthesis> savedSynthesis;
    private ArrayList<OrderLine> cart;

    private ArrayList<Order> orderList;

    /**
     * Default Constructor
     */
    public Customer(){}

    /**
     * Helper Constructor
     *
     * @param id                the id of the customer
     * @param username          username of the customer
     * @param password          password of the customer
     * @param firstName         first name of the customer
     * @param lastName          last name of the customer
     * @param email             email address of the customer
     * @param telephone         telephone number of the customer
     * @param savedSynthesis    saved synthesis of the customer
     * @param cart              cart of the customer
     */
    public Customer( int id, String username, String password, String firstName, String lastName, Email email, Telephone telephone, ArrayList<Synthesis> savedSynthesis, ArrayList<OrderLine>cart){
        super(id,username,password, firstName,lastName,email,telephone);
        this.savedSynthesis=savedSynthesis;
        this.cart=cart;
        this.orderList = new ArrayList<Order>();
    }

    /**
     * Gets the saved sythesis of the customer
     *
     * @return an ArrayList of Synthesis objects
     */
    public ArrayList<Synthesis> getSavedSynthesis() {
        return this.savedSynthesis;
    }

    /**
     * Gets customer's cart
     *
     * @return an ArrayList of {@link Pair} objects (Product, quantity)
     */
    public ArrayList<OrderLine> getCart() {
        return this.cart;
    }

    /**
     * Sets the  customer's saved synthesis list
     *
     * @param savedSynthesis the customer's saved synthesis list
     */
    public void setSavedSynthesis(ArrayList<Synthesis> savedSynthesis) {
        this.savedSynthesis = savedSynthesis;
    }

    /**
     * Sets the customer's cart
     *
     * @param cart the cart of the customer
     */
    public void setCart(ArrayList<OrderLine>cart) {
        this.cart = cart;
    }

    /**
     * Adds a syntesis to customer's saves
     *
     * @param synthesis a synthesis object
     */
    public void addToSavedSynthesis(Synthesis synthesis){
        this.savedSynthesis.add(synthesis);
    }

    /**
     * Removes a synthesis from customer's saves
     *
     * @param synthesis a synthesis object
     */
    public void removeFromSaved(Synthesis synthesis){
        this.savedSynthesis.remove(synthesis);
    }

    /**
     * Adds to cart a product, with it's quantity
     *
     * @param line  a pair of (product, quantity)
     */
    public void addToCart(OrderLine line){
        Boolean found = false;
        for(OrderLine product : cart) {
            if ((product.getProductType()).equals(line.getProductType())) {
                found = true;
                int new_quantity = product.getQuantity() + line.getQuantity();
                product.setQuantity(new_quantity);
            }
        }
        if(!found){
            this.cart.add(line);
        }
    }

    /**
     * Removes from customer's cart a product
     *
     * @param product a product
     */
    public void removeFromCart(ProductType product){
        this.cart.remove(product);
    }

    /**
     * Gets a product from customer's cart
     *
     * @param ProductID the product's id
     * @return  a product or null
     */
    public OrderLine getProductFromCart(int ProductID){
        int i = this.cart.size() - 1;
        OrderLine p;
        while(i>=0){
            p = this.cart.get(i);
            if(p.getProductType().getModelNo() == ProductID){
                return p;
            }
            i-=1;
        }
        return null;
    }

    /**
     * Gets product from customer's saves
     *
     * @param ProductID the product's id
     * @return  a product object or null
     */
    public ProductType getProductFromSaved(int ProductID){

        int j = this.savedSynthesis.size()-1;
        ProductType p;

        while(j>=0){
            p = this.savedSynthesis.get(j);
            if(p.getModelNo() == ProductID){
                return p;
            }
            j-=1;
        }
        return null;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void addOrderList(Order order){
        this.orderList.add(order);
    }
}
