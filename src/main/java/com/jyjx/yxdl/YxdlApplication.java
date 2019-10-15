package com.jyjx.yxdl;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(value = "com.jyjx.yxdl.mapper")
public class YxdlApplication {

	public static void main(String[] args) {
		SpringApplication.run(YxdlApplication.class, args);
	}

}
