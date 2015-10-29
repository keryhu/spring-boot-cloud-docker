package com.ib.commercial.model.repositories;

import com.ib.commercial.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * Created by justin on 13/10/2015.
 */
public interface ProductRepository extends MongoRepository<Product, String>{
    Product getProduct(String id);
}
