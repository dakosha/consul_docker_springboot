package com.credorax.poc.client.controllers;

import com.credorax.poc.client.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private static Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping("getSum")
    @Proxied(value = {"localhost:8085"})
    public Integer getSum(@RequestParam Integer arg1, @RequestParam Integer arg2) {
        LOGGER.info("Brain Controller Sum invocation with args: {}, {}", arg1, arg2);
        return clientService.getSum(arg1, arg2);
    }

    @GetMapping("getMultiplication")
    public Integer getMultiplication(@RequestParam Integer arg1, @RequestParam Integer arg2) {
        LOGGER.info("Brain Controller Multiplication invocation with args: {}, {}", arg1, arg2);
        return clientService.getMultiplication(arg1, arg2);
    }

}
