package com.ib.booking.basket.controller;

import com.ib.booking.basket.proxies.ProductRepositoryProxy;
import com.ib.booking.basket.repositories.BasketRepository;
import com.ib.commercial.model.Basket;
import com.ib.commercial.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/basket")
//@EnableOAuth2Resource
public class BasketController {

    private static final Logger log = LoggerFactory.getLogger(BasketController.class);

    @Autowired
    private ProductRepositoryProxy productrepository;

    @Autowired
    private BasketRepository basketRepository;

    @RequestMapping(value = "/create/{basketId}", method = RequestMethod.PUT)
    ResponseEntity<?> create(@PathVariable String basketId) {

        log.debug("Create");
        //log.debug("ProductApi: User={}, Auth={}, called with productId={}", currentUser.getName(), authorizationHeader, basketId);
        basketRepository.insert(new Basket(basketId));
        Basket basket = basketRepository.findOne(basketId);
        return new ResponseEntity<>(basket, null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/remove/{basketId}", method = RequestMethod.DELETE)
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
        basketRepository.save(basket);
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
        basketRepository.save(basket);
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
        basketRepository.save(basket);
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
