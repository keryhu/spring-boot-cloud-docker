package com.ib.commercial.model;

import java.util.ArrayList;

/**
 * Created by justin on 13/10/2015.
 */
public class Product {

    private Long id = 0L;
    private String name = null;

    public Product() {}

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
