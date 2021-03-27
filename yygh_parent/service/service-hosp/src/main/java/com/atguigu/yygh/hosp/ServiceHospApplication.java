package com.atguigu.yygh.hosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangjie
 * @Title: ServiceHospApplication
 * @Description: 医院设置服务
 * @company: 西安石文软件有限公司
 * @date 2021/3/2017:56
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu.yygh"})
@EnableDiscoveryClient //服务注册到nacos中
@EnableFeignClients(basePackages = "com.atguigu")
public class ServiceHospApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class,args);
    }
}
