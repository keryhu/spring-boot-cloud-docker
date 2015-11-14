package com.ib.booking.product.controller;

import ch.qos.logback.classic.Logger;
import com.ib.booking.product.repository.ProductRepository;
import com.ib.commercial.model.Product;
import com.ib.commercial.trace.InfoLineBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;


@RestController
@RequestMapping("/product")
public class ProductController {

    Logger fastKafkaLogger = (Logger) LoggerFactory.getLogger("fast-kafka");

    private Log log = LogFactory.getLog(ProductController.class);

    @Autowired
    private ProductRepository repository;

    @PostConstruct
    public void init()  {

        fastKafkaLogger.debug("Setting up repository");

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
    Product get(@PathVariable String productId, @RequestHeader HttpHeaders headers) {

        String[] args = { ProductController.class.toGenericString(), "get", "product", productId };
        String[] keys = { "keycloak_name", "keycloak_email", "x-forwarded-for", "keycloak_username", "keycloak_subject" };

        fastKafkaLogger.debug("Product get : "+productId);
        fastKafkaLogger.debug(InfoLineBuilder.getLine(args, headers, keys));

        return repository.findOne(productId);
    }

}
