package com.luobida.houserice.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.luobida.houserice.admin.dao.mapper")
public class HouseRiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseRiceAdminApplication.class, args);
    }

}
