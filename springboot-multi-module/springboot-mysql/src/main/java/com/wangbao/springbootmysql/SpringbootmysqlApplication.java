package com.wangbao.springbootmysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
@MapperScan("com.wangbao.springbootmysql.mapper")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
public class SpringbootmysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmysqlApplication.class, args);
    }

}
