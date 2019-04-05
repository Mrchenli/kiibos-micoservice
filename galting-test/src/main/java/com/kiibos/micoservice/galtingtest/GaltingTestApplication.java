package com.kiibos.micoservice.galtingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author kiibos
 * @Description //
 * 管理后台重新开一个应用 不过也是调用这边的服务
 *
 * @Date 上午11:21 2019/3/22
 * @param
 * @return
 **/
@SpringBootApplication
@EnableTransactionManagement
public class GaltingTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaltingTestApplication.class, args);
	}

}
