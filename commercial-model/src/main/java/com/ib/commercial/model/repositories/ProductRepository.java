package com.ib.commercial.model.repositories;

import com.ib.commercial.model.Product;

/**
 * Created by justin on 13/10/2015.
 */
public interface ProductRepository {
    Product getProduct(Long id);
}
