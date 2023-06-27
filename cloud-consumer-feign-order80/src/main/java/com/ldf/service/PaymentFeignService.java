package com.ldf.service;

import entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import view.CommentResult;

/**
 * @author ldf
 * @create 2023/6/26 15:02
 */

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/{id}")
        CommentResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
