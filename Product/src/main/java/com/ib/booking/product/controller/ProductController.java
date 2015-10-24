package com.ib.booking.product.controller;

import com.ib.commercial.model.Product;
import com.ib.commercial.model.repositories.ProductRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product")
public class ProductController {

    private Log log = LogFactory.getLog(ProductController.class);

    @Autowired
    private ProductRepository repository;


    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    Product get(@PathVariable Long productId) {

        log.debug("Product get : "+productId);

        return repository.getProduct(productId);
    }

}
