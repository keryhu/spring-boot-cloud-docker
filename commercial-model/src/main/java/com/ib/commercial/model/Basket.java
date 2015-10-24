package com.ib.commercial.model;

import java.util.ArrayList;

/**
 * Created by justin on 13/10/2015.
 */
public class Basket {

    private Long id = 0L;

    public Basket() {}

    private ArrayList<Product> products = new ArrayList<Product>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> items) {
        this.products = products;
    }
}
