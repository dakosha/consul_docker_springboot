package com.credorax.poc.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@org.springframework.stereotype.Service
public class Service {

    private static final Random RND = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

    public String verification() {
        ExecutorService service = Executors.newFixedThreadPool(16);

        service.submit(() -> {
            for (int i=0; i<=50000; i++) {
                LOGGER.info("Verification Service was called: " + RND.nextInt());
            }
        });

        try {
            service.shutdown();
            if (service.awaitTermination(1000000000, TimeUnit.MILLISECONDS)) {
                return "Verification is passed";
            }
        } catch (InterruptedException e) {
        }
        return "Verification is NOT passed";
    }

}
