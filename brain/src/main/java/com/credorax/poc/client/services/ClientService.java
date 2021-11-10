package com.credorax.poc.client.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {

    private static Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private RestTemplate restTemplate;

    public Integer getSum(Integer arg1, Integer arg2) {
        LOGGER.info("Brain Service Sum invocation with args: {}, {}", arg1, arg2);
        return restTemplate.getForObject("http://connector-way4/math-operations/sum?arg1=" + arg1 + "&arg2=" + arg2, Integer.class);
    }

    public Integer getMultiplication(Integer arg1, Integer arg2) {
        LOGGER.info("Brain Service Multiplication invocation with args: {}, {}", arg1, arg2);
        return restTemplate.getForObject("http://connector-way4/math-operations/multiplication?arg1=" + arg1 + "&arg2=" + arg2, Integer.class);
    }

}
