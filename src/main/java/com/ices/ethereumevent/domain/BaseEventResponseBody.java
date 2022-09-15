package com.ices.ethereumevent.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.web3j.protocol.core.methods.response.Log;

import lombok.Data;

@Data
public class BaseEventResponseBody<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean removed;
	private String address;
	private String blockHash;
	private BigInteger blockNumber;
	private String data;
	private BigInteger logIndex;
	private List<String> topics;
	private String transactionHash;
	private BigInteger transactionIndex;
	private String type;
	private T returnValues;
	
	public BaseEventResponseBody (Log log, T returnValues) {
		this.removed = log.isRemoved();
		this.address = log.getAddress();
		this.blockHash = log.getBlockHash();
		this.blockNumber = log.getBlockNumber();
		this.data = log.getData();
		this.logIndex = log.getLogIndex();
		this.topics = log.getTopics();
		this.transactionHash = log.getTransactionHash();
		this.transactionIndex = log.getTransactionIndex();
		this.type = log.getType();
		this.returnValues = returnValues;
	}
}
