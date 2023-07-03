package com.ldf.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author ldf
 * @create 2023/6/27 15:13
 */
@Service
public class PaymentService {
    /**
     * 正常访问
     *
     * @param id
     */
    public String paymentInfoOK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_OK，id：" + id + "\t";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"), // 使用线程池隔离
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //开启熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3")// 窗口时间内多少个请求失败了，会打开断路器
    })
    public String paymentInfoTimeOut(Integer id) {
        int age = 10 / 0;
        int number = 4;
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {


        }
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_TimeOut，id：" + id + "\t 耗时" + number + "秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池:  " + Thread.currentThread().getName() + "  8001系统繁忙或者运行报错，请稍后再试,id:  " + id + "\t" + "o(╥﹏╥)o";
    }

    //=====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }
}
