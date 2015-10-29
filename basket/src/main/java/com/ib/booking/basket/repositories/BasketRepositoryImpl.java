package com.ib.booking.basket.repositories;

import com.ib.commercial.model.Basket;
import com.ib.commercial.model.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by justin on 13/10/2015.
 */

@Component("BasketRepository")
public class BasketRepositoryImpl implements com.ib.commercial.model.repositories.BasketRepository {

    HashMap<String, Basket> baskets = new HashMap<>();

    @Override
    public Basket get(String basketId) {
        return baskets.get(basketId);
    }

    @Override
    public Basket add(Basket basket) {
        String id = new Long(baskets.size()).toString();
        basket.setId(id);
        baskets.put(id.toString(), basket);
        return basket;
    }

    @Override
    public void remove(String basketId) {
        baskets.remove(basketId);
    }

    @Override
    public void update(Basket basket) {
        baskets.put(basket.getId(), basket);
    }

    public HashMap<String, Basket> list()  {
        return baskets;
    }

    @Override
    public void clear() {
        baskets = new HashMap<>();
    }
}
