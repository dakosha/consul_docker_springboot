package com.credorax.poc.service.controllers;

import com.credorax.poc.service.services.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private static Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private Service service;

    @GetMapping("verification")
    public String verification() {
        LOGGER.info("Verification Controller was called");
        return service.verification();
    }

}
