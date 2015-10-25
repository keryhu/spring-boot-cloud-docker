package com.ib.booking.basket.proxies;

import com.ib.commercial.model.Product;
import com.ib.commercial.model.repositories.ProductRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;

/**
 * Created by justin on 13/10/2015.
 */
@Component("ProductRepository")
@EnableCircuitBreaker
public class ProductRepositoryProxy implements ProductRepository{

    private Log log = LogFactory.getLog(ProductRepositoryProxy.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultProduct")
    public Product getProduct(Long id) {
        getProductServices();

        ResponseEntity<Product> exchange =
                this.restTemplate.exchange(
                        "http://product/product/{id}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Product>() {},
                        id);

        Product resp = exchange.getBody();
        log.debug("Product Response : "+resp);
        return resp;
    }

    public Object getDefaultProduct() {
        return new Product(0L, "null product");
    }




    private void getProductServices()   {
        List<String> services = discoveryClient.getServices();
        Iterator it = services.iterator();
        while (it.hasNext())   {
            log.debug("Service : "+(String)it.next());
        }
        log.debug("Discovery Client description : " + discoveryClient.description());

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("product");

        it = serviceInstances.iterator();
        while (it.hasNext())   {
            ServiceInstance instance = (ServiceInstance)it.next();
            log.debug("ServiceInstance : "+instance);
            log.debug("ServiceInstance Host : "+instance.getHost());
            log.debug("ServiceInstance Port : "+instance.getPort());
            log.debug("ServiceInstance Uri : "+instance.getUri());
            log.debug("ServiceInstance id : "+instance.getServiceId());

        }

    }
}
