package com.jd.framework.zuul;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@Controller
@EnableZuulProxy
public class ZuulApplication {


    public static void main(String[] args) {

        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);

    }
}
