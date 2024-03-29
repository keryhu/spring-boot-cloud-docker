package com.ib.booking.basket.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

/**
 * Created by justin on 13/10/2015.
 */

@RestController
@RequestMapping("/basket/test")
public class TestController {

    private Log log = LogFactory.getLog(TestController.class);


    @RequestMapping(value = "/echo/{echoStr}", method = RequestMethod.GET)
    ResponseEntity<?> echo(@PathVariable String echoStr) {

        log.debug("Echo : "+echoStr);

        Response r = new Response();
        r.setDate(new Date().toString());
        //r.setPrincipal(currentUser.getName());
        r.setEchoing(echoStr);
        r.setMessage("Looks like everything is OK");

        return new ResponseEntity<>(r, null, HttpStatus.OK);
    }


    public class Response{

        String principal;

        public String getPrincipal() {
            return principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getEchoing() {
            return echoing;
        }

        public void setEchoing(String echoing) {
            this.echoing = echoing;
        }

        String date;
        String message;
        String echoing;

    }

}
