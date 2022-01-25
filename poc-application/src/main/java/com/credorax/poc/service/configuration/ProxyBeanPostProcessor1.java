package com.credorax.poc.service.configuration;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ProxyBeanPostProcessor1 extends ProxyBeanPostProcessor {

    @Override
    boolean shouldBeProxied(HttpServletRequest request) {
        return true;
    }

}
