package com.ldf.springcloud.controller;

import entities.Payment;
import view.CommentResult;
import com.ldf.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @GetMapping(value = "/{id}")
    public CommentResult get(@PathVariable("id") Long id) {
        Payment payment = paymentService.selectByPrimaryKey(id);
        log.info("******查找结果：" + payment);
        if (payment != null) {
            return new CommentResult(200, "查找成功", payment);
        } else {
            return new CommentResult(444, "没有对应的记录，查找id："+id, null);

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
}
