package com.shanvin.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    private static final Logger logger = LoggerFactory.getLogger("ApplicationLogger");

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon", method = RequestMethod.GET)
    public String ribbon() {
        logger.info("ServiceInstance: {}", discoveryClient.getInstances("spring-boot-eureka-producer"));
        return restTemplate.getForEntity("http://spring-boot-eureka-producer/actuator/info", String.class).getBody();
    }

}
