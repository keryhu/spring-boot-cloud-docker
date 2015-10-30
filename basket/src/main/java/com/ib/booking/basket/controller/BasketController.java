package com.ib.booking.basket.controller;

import com.ib.booking.basket.proxies.ProductRepositoryProxy;
import com.ib.booking.basket.repositories.BasketRepository;
import com.ib.commercial.model.Basket;
import com.ib.commercial.model.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/basket")
public class BasketController {

    private Log log = LogFactory.getLog(BasketController.class);

    @Autowired
    private ProductRepositoryProxy productrepository;

    @Autowired
    private BasketRepository basketRepository;

    @RequestMapping(value = "/create/{basketId}", method = RequestMethod.PUT)
    ResponseEntity<?> create(@PathVariable String basketId) {

        log.debug("Create");
        basketRepository.insert(new Basket(basketId));
        Basket basket = basketRepository.findOne(basketId);
        return new ResponseEntity<>(basket, null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{basketId}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable String basketId)    {
        log.debug("Remove Basket#"+basketId);
        basketRepository.delete(basketId);
        return new ResponseEntity<>(null, null, HttpStatus.GONE);
    }

    @RequestMapping(value = "/clearall", method = RequestMethod.DELETE)
    ResponseEntity<?> clearall()    {
        log.debug("Clearing all Baskets");
        basketRepository.deleteAll();
        return new ResponseEntity<>(null, null, HttpStatus.GONE);
    }

    @RequestMapping(value = "/{basketId}/add/{productId}", method = RequestMethod.PUT)
    ResponseEntity<Basket> add(@PathVariable String basketId, @PathVariable String productId) {

        log.debug("Basket #"+basketId+" Add Product#"+productId);

        Product product = productrepository.getProduct(productId);
        Basket basket = basketRepository.findOne(basketId);
        if (basket.getProducts() != null) {
            basket.getProducts().add(product);
        }
        else    {
            basket.setProducts(new ArrayList<>());
            basket.getProducts().add(product);
        }
        basketRepository.insert(basket);
        basket = basketRepository.findOne(basketId);
        return new ResponseEntity<>(basket, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{basketId}/remove/{productId}", method = RequestMethod.DELETE)
    ResponseEntity<Basket> remove(@PathVariable String basketId, @PathVariable String productId) {

        log.debug("Basket #"+basketId+" Add Product#"+productId);

        Product product = productrepository.getProduct(productId);
        Basket basket = basketRepository.findOne(basketId);
        if (basket.getProducts() != null) {
            basket.getProducts().remove(product);
        }
        basketRepository.insert(basket);
        basket = basketRepository.findOne(basketId);
        return new ResponseEntity<>(basket, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{basketId}/empty", method = RequestMethod.POST)
    ResponseEntity<Basket> empty(@PathVariable String basketId) {

        log.debug("Basket #"+basketId+" Emptying");

        Basket basket = basketRepository.findOne(basketId);
        if (basket.getProducts() != null) {
            basket.getProducts().clear();
        }
        basketRepository.insert(basket);
        basket = basketRepository.findOne(basketId);
        return new ResponseEntity<>(basket, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{basketId}", method = RequestMethod.GET)
    ResponseEntity<Basket>  get(@PathVariable String basketId) {

        log.debug("Get basket : "+basketId);

        Basket basket = basketRepository.findOne(basketId);

        return new ResponseEntity<>(basket, null, HttpStatus.OK);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    ResponseEntity<List<Basket>>  list() {

        log.debug("List baskets");

        List<Basket> baskets = basketRepository.findAll();

        return new ResponseEntity<>(baskets, null, HttpStatus.OK);
    }

}
