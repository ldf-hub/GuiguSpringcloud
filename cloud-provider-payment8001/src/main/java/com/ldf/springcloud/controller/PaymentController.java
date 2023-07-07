package com.ldf.springcloud.controller;

import com.ldf.springcloud.service.PaymentService;
import entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import view.CommentResult;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ldf
 * @create 2023/1/11 15:44
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    DiscoveryClient discoveryClient;

    /**
     * 服务发现
     *
     * @return
     */
    @GetMapping(value = "/discovery")
    public Object discovery() {
        // 获取所有的服务
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("****element：" + service);
        }

        // 获取服务的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info("****element：" + instance);
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/{id}")
    public CommentResult get(@PathVariable("id") Long id) {
        Payment payment = paymentService.selectByPrimaryKey(id);

        log.info("******查找结果：serverPort:" + serverPort + payment);
        if (payment != null) {
            return new CommentResult(200, "查找成功，serverPort:" + serverPort + payment);
        } else {
            return new CommentResult(444, "没有对应的记录，查找id：" + id, null);

        }
    }

    @PostMapping(value = "")
    public CommentResult insert(Payment payment) {
        int result = paymentService.insert(payment);
        log.info("******插入结果：" + result);
        if (result > 0) {
            return new CommentResult(200, "插入数据成功", result);
        } else {
            return new CommentResult(444, "插入数据失败", null);

        }
    }

    @PutMapping(value = "")
    public CommentResult update(Payment payment) {
        int result = paymentService.insert(payment);
        log.info("******插入结果：" + result);
        if (result > 0) {
            return new CommentResult(200, "更新数据成功", result);
        } else {
            return new CommentResult(444, "更新数据失败", null);

        }
    }

    @DeleteMapping(value = "/{id}")
    public CommentResult delete(@PathVariable("id") Long id) {
        int result = paymentService.deleteByPrimaryKey(id);
        log.info("******插入结果：" + result);
        if (result > 0) {
            return new CommentResult(200, "删除数据成功", result);
        } else {
            return new CommentResult(444, "删除数据失败", null);
        }
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
