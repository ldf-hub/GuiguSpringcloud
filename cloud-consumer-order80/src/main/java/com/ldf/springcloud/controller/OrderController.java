package com.ldf.springcloud.controller;

import com.ldf.springcloud.lb.LoadBalancer;
import entities.Payment;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import view.CommentResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

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
    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    DiscoveryClient discoveryClient;
    @PostMapping("payment")
    public CommentResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, CommentResult.class);
    }


    @GetMapping("payment/{id}")
    public CommentResult<Payment> create(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommentResult.class);
    }
    @GetMapping("payment/lb")
    public String getPaymentLB() {

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() <=0){
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);

    }
}
