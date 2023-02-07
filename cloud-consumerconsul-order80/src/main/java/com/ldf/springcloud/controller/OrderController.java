package com.ldf.springcloud.controller;

import entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ldf
 * @create 2023/1/12 16:41
 */
@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {

    @Value("${api.paymentUrl}")
    private String PAYMENT_URL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("payment")
    public String create() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/consul",String.class);
    }


}
