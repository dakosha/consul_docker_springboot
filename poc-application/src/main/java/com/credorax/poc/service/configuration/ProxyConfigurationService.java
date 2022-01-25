package com.credorax.poc.service.configuration;

public abstract class ProxyConfigurationService {

    public abstract Boolean getShouldWeProxy();

    public abstract String getNextHost();

}
