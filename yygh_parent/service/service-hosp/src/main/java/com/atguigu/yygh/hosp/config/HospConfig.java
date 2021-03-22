package com.atguigu.yygh.hosp.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjie
 * @Title: HospConfig
 * @Description: 注入分页插件
 * @company: 西安石文软件有限公司
 * @date 2021/3/2120:02
 */
@Configuration
@MapperScan("com.atguigu.yygh.hosp.mapper")
public class HospConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
