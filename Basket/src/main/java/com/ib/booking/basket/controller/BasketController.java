package com.ib.booking.basket.controller;

import com.ib.commercial.model.Basket;
import com.ib.commercial.model.Product;
import com.ib.commercial.model.repositories.BasketRepository;
import com.ib.commercial.model.repositories.ProductRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.logging.Logger;


@RestController
@RequestMapping("/basket")
public class BasketController {

    private Log log = LogFactory.getLog(BasketController.class);

    @Autowired
    private ProductRepository productrepository;

    @Autowired
    private BasketRepository basketRepository;

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    ResponseEntity<?> create() {

        log.debug("Create");

        Basket basket = basketRepository.add(new Basket());
        return new ResponseEntity<>(basket, null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{basketId}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long basketId)    {
        log.debug("Remove Basket#"+basketId);
        basketRepository.remove(basketId);
        return new ResponseEntity<>(null, null, HttpStatus.GONE);
    }

    @RequestMapping(value = "/{basketId}/add/{productId}", method = RequestMethod.POST)
    ResponseEntity<Basket> add(@PathVariable Long basketId, @PathVariable Long productId) {

        log.debug("Basket #"+basketId+" Add Product#"+productId);

        Product product = productrepository.getProduct(productId);
        Basket basket = basketRepository.get(basketId);
        basket.getProducts().add(product);
        basketRepository.update(basket);
        return new ResponseEntity<>(basket, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{basketId}/remove/{productId}", method = RequestMethod.POST)
    ResponseEntity<Basket> remove(@PathVariable Long basketId, @PathVariable Long productId) {

        log.debug("Basket #"+basketId+" Add Product#"+productId);

        Product product = productrepository.getProduct(productId);
        Basket basket = basketRepository.get(basketId);
        basket.getProducts().add(product);
        basketRepository.update(basket);
        return new ResponseEntity<>(basket, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{basketId}", method = RequestMethod.GET)
    ResponseEntity<Basket>  get(@PathVariable Long basketId) {

        log.debug("Get basket : "+basketId);

        Basket basket = basketRepository.get(basketId);

        return new ResponseEntity<>(basket, null, HttpStatus.OK);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    ResponseEntity<HashMap<String, Basket>>  list() {

        log.debug("List baskets");

        HashMap<String, Basket> baskets = basketRepository.list();

        return new ResponseEntity<>(baskets, null, HttpStatus.OK);
    }

}
