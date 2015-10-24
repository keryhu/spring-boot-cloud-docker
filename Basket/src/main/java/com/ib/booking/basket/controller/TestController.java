package com.ib.booking.basket.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by justin on 13/10/2015.
 */

@RestController
@RequestMapping("/test")
public class TestController {

    private Log log = LogFactory.getLog(TestController.class);

    @Value("${prop.1}") private String prop1;

    @RequestMapping(value = "/echo/{echoStr}", method = RequestMethod.GET)
    ResponseEntity<?> echo(@PathVariable String echoStr) {

        log.debug("Echo : "+echoStr);
        log.debug("Prop : "+prop1);
        return new ResponseEntity<>(echoStr, null, HttpStatus.CREATED);
    }
}
