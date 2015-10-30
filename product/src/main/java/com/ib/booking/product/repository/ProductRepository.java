package com.ib.booking.product.repository;

import com.ib.commercial.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by justin on 13/10/2015.
 */
@Component("ProductRepository")
public interface ProductRepository extends MongoRepository<Product, String>{
    Product findById(String id);
}
