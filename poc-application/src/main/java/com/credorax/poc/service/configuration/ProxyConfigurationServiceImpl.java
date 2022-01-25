package com.credorax.poc.service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProxyConfigurationServiceImpl extends ProxyConfigurationService {


    @Value("${com.credorax.hpp.proxy.enabled:false}")
    private Boolean shouldWeProxy;
    @Value("${com.credorax.hpp.proxy.routes:localhost:8080}")
    private String routes;

    @Override
    public Boolean getShouldWeProxy() {
        return shouldWeProxy;
    }

    @Override
    public String getNextHost() {
        return routes;
    }
}
