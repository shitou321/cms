package com.java.tian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Create By LiXiaoTian on 2019/12/17 10:46
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.java.tian"})
public class StartCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartCmsApplication.class);
        System.out.printf("===================启动完成===================");
    }
}
