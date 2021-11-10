package com.credorax.poc.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@org.springframework.stereotype.Service
public class Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

    public String verification() {
        LOGGER.info("Verification Service was called");
        return "Verification is passed";
    }

}
