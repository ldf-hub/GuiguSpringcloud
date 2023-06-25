package com.ldf.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ldf
 * @create 2023/2/7 16:59
 */
@Configuration
public class MyselfRule {

    /**
     * 自定义规则
     *
     * @return
     */
    @Bean
    public IRule myRule() {
        return new RoundRobinRule();
    }
}
