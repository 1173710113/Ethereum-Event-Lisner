package com.ices.ethereumevent.lisner;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.Contract;

import com.alibaba.fastjson2.JSON;
import com.ices.ethereumevent.config.EventFactory;
import com.ices.ethereumevent.config.EventFilter;
import com.ices.ethereumevent.config.EventFilterConfig;
import com.ices.ethereumevent.config.VotesContract;
import com.ices.ethereumevent.domain.BaseEventResponseBody;
import com.ices.ethereumevent.domain.TransferSingleEventReturnValues;
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
	Web3j web3j;

	@Autowired
	RabbitMQUtil rabbitMQUtil;

	@Autowired
	EventFilterConfig eventFilterConfig;

	public static final Event TRANSFERSINGLE_EVENT = new Event("TransferSingle",
			Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
			}, new TypeReference<Address>(true) {
			}, new TypeReference<Address>(true) {
			}, new TypeReference<Uint256>() {
			}, new TypeReference<Uint256>() {
			}));;

	public void listen() {
		log.info("start listening");
		votesContract.transferSingleEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST)
				.subscribe(event -> {
					TransferSingleEventReturnValues returnValues = new TransferSingleEventReturnValues();
					returnValues.setFrom(event.from);
					returnValues.setId(event.id);
					returnValues.setOperator(event.operator);
					returnValues.setTo(event.to);
					returnValues.setValue(event.value);
					BaseEventResponseBody<TransferSingleEventReturnValues> eventResponseBody = new BaseEventResponseBody<>(
							event.log, returnValues);
					rabbitMQUtil.send("directExchange", "singleTransfer", JSON.toJSONString(eventResponseBody),
							event.log.getTransactionHash());

				});
	}

	@PostConstruct
	public void listenTest() {

		eventFilterConfig.getEventFilters().stream().forEach(evetFilter -> {
			Event event = EventFactory.get(evetFilter.getEventSpecification().getEventName(),
					evetFilter.getEventSpecification().getParameterTypes());
			String hash = EventEncoder.encode(event);
			EthFilter filer = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST,
					evetFilter.getContractAddress()).addSingleTopic(hash);
			web3j.ethLogFlowable(filer).subscribe(EthLog -> {
				log.info(JSON.toJSONString(EthLog));
				EventValues values = Contract.staticExtractEventParameters(event, EthLog);
				log.info(JSON.toJSONString(values));

			});

		});
	}
}
