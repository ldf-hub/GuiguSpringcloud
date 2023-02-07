package com.ldf.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author ldf
 * @create 2023/1/11 15:44
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/consul")
    public String paymentConsul() {
        return "consul：" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
