package com.ib.commercial.model.repositories;

import com.ib.commercial.model.Basket;

import java.util.HashMap;
import java.util.List;

/**
 * Created by justin on 13/10/2015.
 */
public interface BasketRepository {

    public Basket get(Long basketId);
    public Basket add(Basket basket);
    public void remove(Long basketId);
    public void update(Basket basket);
    public HashMap<String, Basket> list();
    public void clear();

}
