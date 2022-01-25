package com.credorax.poc.service.controllers;

import com.credorax.poc.service.services.MathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("math-operations")
public class MathController {

    private static Logger LOGGER = LoggerFactory.getLogger(MathController.class);

    @Autowired
    private MathService mathService;

    @GetMapping("getDataFromConsulConfig")
    public Map<String, Object> getDataFromConsulConfig() {
        return mathService.getDataFromConsulConfig();
    }

    @GetMapping("sum")
    public Integer sum(@RequestParam Integer arg1, @RequestParam Integer arg2) {
        LOGGER.info("Connector WAY4 Controller sum invocation with args: {}, {}", arg1, arg2);
        return mathService.sum(arg1, arg2);
    }

    @GetMapping("multiplication")
    public BigDecimal multiplication(@RequestParam BigDecimal arg1, @RequestParam BigDecimal arg2) {
        LOGGER.info("Connector WAY4 Controller multiplication invocation with args: {}, {}", arg1, arg2);
        return mathService.multiplication(arg1, arg2);
    }

}
