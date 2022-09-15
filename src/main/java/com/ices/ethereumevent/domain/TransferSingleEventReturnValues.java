package com.ices.ethereumevent.domain;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferSingleEventReturnValues implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String operator;

    public String from;

    public String to;

    public BigInteger id;

    public BigInteger value;
}
