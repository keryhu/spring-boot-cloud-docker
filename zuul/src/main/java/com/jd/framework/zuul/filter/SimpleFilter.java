package com.jd.framework.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by justin on 03/12/2015.
 */
@Component("SimpleFilter")
public class SimpleFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);

    @Override
    public String filterType() {
        String type = "pre";
        log.debug("********* Filter Type : "+type);
        return type;
    }

    @Override
    public int filterOrder() {
        int order = 1;
        log.debug("********* Filter Order : "+order);
        return order;
    }

    @Override
    public boolean shouldFilter() {
        boolean filter = true;
        log.debug("********* Filter  : "+filter);
        return true;
    }

    @Override
    public Object run() {
        log.debug("********* run !");
        return null;
    }
}
