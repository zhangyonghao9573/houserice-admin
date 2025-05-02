package com.luobida.houserice.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.luobida.houserice.project.dao.mapper")
public class HouseRiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseRiceApplication.class, args);
    }

}
