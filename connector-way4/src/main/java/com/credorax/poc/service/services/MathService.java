package com.credorax.poc.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RefreshScope
public class MathService {

    @Value("${connector-way4.properties.booleanField}")
    public volatile Boolean booleanField;

    @Value("${connector-way4.properties.integerField}")
    public volatile Integer integerField;

    @Value("${connector-way4.properties.decimalField}")
    public volatile BigDecimal decimalField;

    @Value("${connector-way4.properties.stringField}")
    public volatile String stringField;

    private static final Logger LOGGER = LoggerFactory.getLogger(MathService.class);

    public Map<String, Object> getDataFromConsulConfig() {
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("booleanField", booleanField);
        result.put("integerField", integerField);
        result.put("decimalField", decimalField);
        result.put("stringField", stringField);

        return result;
    }

    public Integer sum(Integer arg1, Integer arg2) {
        LOGGER.info("Connector WAY4 MathService sum invocation with args: {}, {}", arg1, arg2);
        return arg1 + arg2;
    }

    public BigDecimal multiplication(BigDecimal arg1, BigDecimal arg2) {
        LOGGER.info("Connector WAY4 MathService multiplication invocation with args: {}, {}", arg1, arg2);
        return arg1.multiply(arg2);
    }

}
