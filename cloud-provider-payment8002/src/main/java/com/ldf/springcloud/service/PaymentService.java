package com.ldf.springcloud.service;

import entities.Payment;

/**
 * @author ldf
 * @create 2023/1/11 15:41
 */

public interface PaymentService {
    int deleteByPrimaryKey(Long id);

    int insert(Payment record);

    int insertSelective(Payment record);

    Payment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);
}
