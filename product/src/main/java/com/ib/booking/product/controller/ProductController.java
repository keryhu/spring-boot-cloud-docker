package com.ib.booking.product.controller;

import com.ib.booking.product.repository.ProductRepository;
import com.ib.commercial.model.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping("/product")
public class ProductController {

    private Log log = LogFactory.getLog(ProductController.class);

    @Autowired
    private ProductRepository repository;

    @PostConstruct
    public void init()  {

        log.debug("Setting up repository");

        if(repository.count() == 0) {
            repository.insert(new Product("1", "marmalade"));
            repository.insert(new Product("2", "milk"));
            repository.insert(new Product("3", "baked beans"));
            repository.insert(new Product("4", "bread"));
            repository.insert(new Product("5", "beef"));
            repository.insert(new Product("6", "chicken"));
            repository.insert(new Product("7", "coffee"));
            repository.insert(new Product("8", "tea"));
            repository.insert(new Product("9", "biscuits"));
            repository.insert(new Product("10", "cake"));
        }

    }




    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    Product get(@PathVariable String productId) {

        log.debug("Product get : "+productId);

        return repository.findOne(productId);
    }

}
