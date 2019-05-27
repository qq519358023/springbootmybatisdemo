package com.beijia.example.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.beijia.example")//设置扫描路径
//@EnableCaching   //开启缓存功能
//@EnableScheduling   // 开启定时任务注解
@ImportResource(locations = { "classpath:druid/druid-bean.xml" })//导入Druid配置文件
@EnableTransactionManagement //开启事务
@MapperScan("com.beijia.example.mapper")//mybatis的包扫描路径，或@Mapper注解
public class SpringmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmybatisApplication.class, args);
    }

}
