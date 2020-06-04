package com.feecalculator.calculator;

import java.util.ArrayList;
import java.util.List;

import com.feecalculator.model.FEE;
import com.feecalculator.model.TransactionDto;

public class FeeCalculator {
	public List<TransactionDto> calculateFee(
			List<TransactionDto> listTransactionDto) {
		List<TransactionDto> listFee = new ArrayList<>();
		for (TransactionDto transactionDto : listTransactionDto) {
			if (isIntraDay(transactionDto, listFee)) {
				transactionDto.setFees(FEE.TEN.getFees());
			} else {
				if (transactionDto.getPriority()) {
					transactionDto.setFees(FEE.FIVE_HUNDRED.getFees());
				} else {
					if (transactionDto.getTransactionType().equals("SELL")
							|| transactionDto.getTransactionType().equals(
									"WITHDRAW")) {
						transactionDto.setFees(FEE.HUNDRED.getFees());
					} else if (transactionDto.getTransactionType()
							.equals("BUY")
							|| transactionDto.getTransactionType().equals(
									"DEPOSIT")) {
						transactionDto.setFees(FEE.FIFTY.getFees());
					}
				}
			}
			listFee.add(transactionDto);
		}
		return listFee;
	}

	private boolean isIntraDay(TransactionDto dto,
			List<TransactionDto> listTransactionDto) {
		boolean isIntraDay = false;
		if (!listTransactionDto.isEmpty()) {
			for (TransactionDto transactionDto : listTransactionDto) {
				if (transactionDto.getClientId().equals(dto.getClientId())
						&& transactionDto.getSecurityId().equals(
								dto.getSecurityId()) && transactionDto.getTransactionDate().equals(dto.getTransactionDate())) {
					if ((transactionDto.getTransactionType().equals("BUY") && dto
							.getTransactionType().equals("SELL"))
							|| (transactionDto.getTransactionType()
									.equals("SELL"))
							&& dto.getTransactionType().equals("BUY")) {
						isIntraDay = true;
						transactionDto.setFees(FEE.TEN.getFees());
						break;
					}
				}
			}
		}
		return isIntraDay;
	}

}
