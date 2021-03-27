package com.atguigu.yygh.cmn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangjie
 * @Title: ServiceCmnApplication
 * @Description: 数据字典服务启动类
 * @company: 西安石文软件有限公司
 * @date 2021/3/2222:14
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu.yygh"}) //为了是可以扫到swagger的配置类
@EnableDiscoveryClient
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class, args);
    }

}
