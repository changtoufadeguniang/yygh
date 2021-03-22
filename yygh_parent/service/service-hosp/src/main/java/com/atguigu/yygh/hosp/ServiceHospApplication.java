package com.atguigu.yygh.hosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

public class ServiceHospApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class,args);
    }
}
