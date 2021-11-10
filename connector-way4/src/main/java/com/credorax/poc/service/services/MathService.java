package com.credorax.poc.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MathService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MathService.class);

    public Integer sum(Integer arg1, Integer arg2) {
        LOGGER.info("Connector WAY4 MathService sum invocation with args: {}, {}", arg1, arg2);
        return arg1 + arg2;
    }

    public BigDecimal multiplication(BigDecimal arg1, BigDecimal arg2) {
        LOGGER.info("Connector WAY4 MathService multiplication invocation with args: {}, {}", arg1, arg2);
        return arg1.multiply(arg2);
    }

}
