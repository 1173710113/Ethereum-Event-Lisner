package com.ices.ethereumevent.lisner;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.DefaultBlockParameterName;

import com.alibaba.fastjson2.JSON;
import com.ices.ethereumevent.config.VotesContract;
import com.ices.ethereumevent.util.RabbitMQUtil;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class VotesContractLisner {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	
	@Autowired
	VotesContract votesContract;
	
	@Autowired
	RabbitMQUtil rabbitMQUtil;
	

	@PostConstruct
	public void listen() {
		log.info("start listening");
		votesContract.transferSingleEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST).subscribe(event -> {
			log.info(JSON.toJSONString(event));
			rabbitMQUtil.send("directExchange", "singleTransfer", JSON.toJSONString(event), event.log.getTransactionHash());

		});
	}
}
