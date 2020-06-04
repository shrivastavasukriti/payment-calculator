package com.feecalculator.test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.feecalculator.calculator.FeeCalculator;
import com.feecalculator.model.TransactionDto;

public class FeeCalculatorTest {

	FeeCalculator feeCalculator = new FeeCalculator();

	TransactionDto transactionDto = new TransactionDto();

	TransactionDto transactionDto2= new TransactionDto();
	
	@Test
	public void testCalculateFeesOK(){
		transactionDto.setClientId("ClientId1");
		transactionDto.setExternalTransactionID("TransactionId1");
		transactionDto.setMarketValue(new BigDecimal(100.2));
		transactionDto.setPriority(Boolean.FALSE);
		transactionDto.setSecurityId("SecurityId1");
		transactionDto.setTransactionDate(Date.from(Instant.now()));
		transactionDto.setTransactionType("BUY");
		
		
		transactionDto2.setClientId("ClientId2");
		transactionDto2.setExternalTransactionID("TransactionId2");
		transactionDto2.setMarketValue(new BigDecimal(10.39));
		transactionDto2.setPriority(Boolean.TRUE);
		transactionDto2.setSecurityId("SecurityId2");
		transactionDto2.setTransactionDate(Date.from(Instant.now()));
		transactionDto2.setTransactionType("SELL");
		
		List<TransactionDto> list = new ArrayList<>();
		list.add(transactionDto);
		list.add(transactionDto2);
		
		List<TransactionDto> listResult=feeCalculator.calculateFee(list);
		Assert.assertEquals(listResult.size(),2);
		Assert.assertEquals(new Double(50),listResult.get(0).getFees());
		Assert.assertEquals(new Double(500),listResult.get(1).getFees());
		
	}
}
