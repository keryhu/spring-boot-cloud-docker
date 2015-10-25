package com.ib.commercial.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public int hashCode() {
        return new HashCodeBuilder(5, 11). // two randomly chosen prime numbers
                append(id).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Basket))
            return false;
        if (obj == this)
            return true;

        Basket rhs = (Basket) obj;
        return new EqualsBuilder().
                append(id, rhs.id).
                isEquals();
    }
}
