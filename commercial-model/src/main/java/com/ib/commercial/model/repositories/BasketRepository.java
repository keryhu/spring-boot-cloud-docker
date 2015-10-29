package com.ib.commercial.model.repositories;

import com.ib.commercial.model.Basket;

import java.util.HashMap;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * Created by justin on 13/10/2015.
 */
public interface BasketRepository extends MongoRepository<Basket, String>{

    public Basket get(String basketId);
    public Basket add(Basket basket);
    public void remove(String basketId);
    public void update(Basket basket);
    public HashMap<String, Basket> list();
    public void clear();

}
