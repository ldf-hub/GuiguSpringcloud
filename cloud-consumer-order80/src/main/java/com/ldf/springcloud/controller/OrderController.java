package com.ldf.springcloud.controller;

import entities.Payment;
import view.CommentResult;
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

    @PostMapping("payment")
    public CommentResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, CommentResult.class);
    }


    @GetMapping("payment/{id}")
    public CommentResult<Payment> create(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommentResult.class);
    }

}
