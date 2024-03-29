package com.ib.booking.product;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@EnableDiscoveryClient
@SpringBootApplication
@RestController
@Configuration
@EnableAutoConfiguration
@EnableHystrix
@EnableTurbine
public class ProductApplication {

    Logger fastKafkaLogger = (Logger) LoggerFactory.getLogger("fast-kafka");

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @RequestMapping("/")
    public String ping() {
        return ProductApplication.class+" UP\n";
    }
}
