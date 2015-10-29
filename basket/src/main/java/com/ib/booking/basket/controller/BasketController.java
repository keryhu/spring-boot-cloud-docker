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
    ResponseEntity<?> delete(@PathVariable String basketId)    {
        log.debug("Remove Basket#"+basketId);
        basketRepository.remove(basketId);
        return new ResponseEntity<>(null, null, HttpStatus.GONE);
    }

    @RequestMapping(value = "/clearall", method = RequestMethod.DELETE)
    ResponseEntity<?> clearall()    {
        log.debug("Clearing all Baskets");
        basketRepository.clear();
        return new ResponseEntity<>(null, null, HttpStatus.GONE);
    }

    @RequestMapping(value = "/{basketId}/add/{productId}", method = RequestMethod.PUT)
    ResponseEntity<Basket> add(@PathVariable String basketId, @PathVariable String productId) {

        log.debug("Basket #"+basketId+" Add Product#"+productId);

        Product product = productrepository.getProduct(productId);
        Basket basket = basketRepository.get(basketId);
        basket.getProducts().add(product);
        basketRepository.update(basket);
        return new ResponseEntity<>(basket, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{basketId}/remove/{productId}", method = RequestMethod.DELETE)
    ResponseEntity<Basket> remove(@PathVariable String basketId, @PathVariable String productId) {

        log.debug("Basket #"+basketId+" Add Product#"+productId);

        Product product = productrepository.getProduct(productId);
        Basket basket = basketRepository.get(basketId);
        basket.getProducts().remove(product);
        basketRepository.update(basket);
        return new ResponseEntity<>(basket, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{basketId}/empty", method = RequestMethod.POST)
    ResponseEntity<Basket> empty(@PathVariable String basketId) {

        log.debug("Basket #"+basketId+" Emptying");

        Basket basket = basketRepository.get(basketId);
        basket.getProducts().clear();
        basketRepository.update(basket);
        return new ResponseEntity<>(basket, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{basketId}", method = RequestMethod.GET)
    ResponseEntity<Basket>  get(@PathVariable String basketId) {

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
