package com.ices.ethereumevent;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class EthereumEventLisnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EthereumEventLisnerApplication.class, args);
	}

}
