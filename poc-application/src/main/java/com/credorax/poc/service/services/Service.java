package com.credorax.poc.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

@org.springframework.stereotype.Service
public class Service {

    private static final Random RND = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

    public String verification() {
        boolean flag = true;
        while (flag) {
            if (flag) {
                LOGGER.info("Some message: " + RND.nextInt(1000));
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        return "Verification is NOT passed";
    }

}
