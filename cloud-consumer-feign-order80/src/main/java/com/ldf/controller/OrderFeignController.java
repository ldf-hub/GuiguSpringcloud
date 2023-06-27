package com.ldf.controller;

import com.ldf.service.PaymentFeignService;
import entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import view.CommentResult;

import javax.annotation.Resource;

/**
 * @author ldf
 * @create 2023/6/26 15:02
 */
@RestController
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/{id}")
    public CommentResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }


}
