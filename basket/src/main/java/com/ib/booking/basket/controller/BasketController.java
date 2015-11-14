package com.ib.booking.basket.controller;

import com.ib.booking.basket.proxies.ProductRepositoryProxy;
import com.ib.booking.basket.repositories.BasketRepository;
import com.ib.commercial.model.Basket;
import com.ib.commercial.model.Product;
import com.ib.commercial.trace.InfoLineBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.http.HttpHeaders;
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

    ch.qos.logback.classic.Logger fastKafkaLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("fast-kafka");
    ch.qos.logback.classic.Logger fastKafkaErrorLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("fast-kafka-error");
    private static final Logger log = LoggerFactory.getLogger(BasketController.class);

    @Autowired
    private ProductRepositoryProxy productrepository;

    @Autowired
    private BasketRepository basketRepository;

    String[] keys = { "keycloak_name", "keycloak_email", "x-forwarded-for", "keycloak_username", "keycloak_subject" };

    @RequestMapping(value = "/create/{basketId}", method = RequestMethod.PUT)
    ResponseEntity<?> create(@PathVariable String basketId, @RequestHeader HttpHeaders headers) {

        String[] args = { BasketController.class.getName(), "create", "basket", basketId };
        fastKafkaLogger.debug(InfoLineBuilder.getLine(args, headers, keys));

        log.debug("Create");

        //log.debug("ProductApi: User={}, Auth={}, called with productId={}", currentUser.getName(), authorizationHeader, basketId);
        basketRepository.insert(new Basket(basketId));
        Basket basket = basketRepository.findOne(basketId);
        return new ResponseEntity<>(basket, null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/remove/{basketId}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable String basketId, @RequestHeader HttpHeaders headers)    {

        String[] args = { BasketController.class.getName(), "remove", "basket", basketId };
        fastKafkaLogger.debug(InfoLineBuilder.getLine(args, headers, keys));

        log.debug("Remove Basket#"+basketId);
        basketRepository.delete(basketId);
        return new ResponseEntity<>(null, null, HttpStatus.GONE);
    }

    @RequestMapping(value = "/clearall", method = RequestMethod.DELETE)
    ResponseEntity<?> clearall(@RequestHeader HttpHeaders headers)    {

        String[] args = { BasketController.class.getName(), "clearall", "basket" };
        fastKafkaLogger.debug(InfoLineBuilder.getLine(args, headers, keys));

        log.debug("Clearing all Baskets");
        basketRepository.deleteAll();
        return new ResponseEntity<>(null, null, HttpStatus.GONE);
    }

    @RequestMapping(value = "/{basketId}/add/{productId}", method = RequestMethod.PUT)
    ResponseEntity<Basket> add(@PathVariable String basketId, @PathVariable String productId, @RequestHeader HttpHeaders headers) {


        String[] args = { BasketController.class.getName(), "add", "basket", basketId, productId };
        fastKafkaLogger.debug(InfoLineBuilder.getLine(args, headers, keys));

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
    ResponseEntity<Basket> remove(@PathVariable String basketId, @PathVariable String productId, @RequestHeader HttpHeaders headers) {

        String[] args = { BasketController.class.getName(), "remove", "basket", basketId, productId };
        fastKafkaLogger.debug(InfoLineBuilder.getLine(args, headers, keys));

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
    ResponseEntity<Basket> empty(@PathVariable String basketId, @RequestHeader HttpHeaders headers) {

        String[] args = { BasketController.class.getName(), "empty", "basket", basketId };
        fastKafkaLogger.debug(InfoLineBuilder.getLine(args, headers, keys));

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
    ResponseEntity<Basket>  get(@PathVariable String basketId, @RequestHeader HttpHeaders headers) {

        String[] args = { BasketController.class.getName(), "get", "basket", basketId };
        fastKafkaLogger.debug(InfoLineBuilder.getLine(args, headers, keys));


        log.debug("Get basket : "+basketId);

        Basket basket = basketRepository.findOne(basketId);

        return new ResponseEntity<>(basket, null, HttpStatus.OK);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    ResponseEntity<List<Basket>>  list(@RequestHeader HttpHeaders headers) {

        String[] args = { BasketController.class.getName(), "list", "basket" };
        fastKafkaLogger.debug(InfoLineBuilder.getLine(args, headers, keys));


        log.debug("List baskets");

        List<Basket> baskets = basketRepository.findAll();

        return new ResponseEntity<>(baskets, null, HttpStatus.OK);
    }

}
