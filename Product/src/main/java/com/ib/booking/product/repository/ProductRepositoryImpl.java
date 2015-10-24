package com.ib.booking.product.repository;

import com.ib.commercial.model.Product;
import com.ib.commercial.model.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by justin on 13/10/2015.
 */

@Component("ProductRepository")
public class ProductRepositoryImpl implements ProductRepository {

    HashMap<String, Product> products = new HashMap<>();

    public ProductRepositoryImpl() {

        products.put("1", new Product(1L, "marmalade"));
        products.put("2", new Product(2L, "milk"));
        products.put("3", new Product(3L, "baked beans"));
        products.put("4", new Product(4L, "bread"));
        products.put("5", new Product(5L, "beef"));
        products.put("6", new Product(6L, "chicken"));
        products.put("7", new Product(7L, "coffee"));
        products.put("8", new Product(8L, "tea"));
        products.put("9", new Product(9L, "biscuits"));
        products.put("10", new Product(10L, "cake"));

    }

    @Override
    public Product getProduct(Long id)  {
        String idStr = new Long(id).toString();
        return products.get(idStr);
    }

}
