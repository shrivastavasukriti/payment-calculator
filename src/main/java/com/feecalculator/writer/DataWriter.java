package com.feecalculator.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.feecalculator.model.TransactionDto;

public class DataWriter {
	public void generateReport(List<String> listeData) {
		try (FileWriter csvWriter = new FileWriter("D:/Sample_Output.csv");){	
			csvWriter.append("Client Id");
			csvWriter.append(",");
			csvWriter.append("Transaction Type");
			csvWriter.append(",");
			csvWriter.append("Priority");
			csvWriter.append(",");
			csvWriter.append("Transaction Date");
			csvWriter.append(",");
			csvWriter.append("Processing Fee");
			csvWriter.append("\n");
			for (String content : listeData) {
			    csvWriter.append(content);
			    csvWriter.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> generateContentFeeReport(List<TransactionDto> listTransactionDto) {
		List<String> dataLines = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		listTransactionDto.forEach(transactionDto-> {
			String strDate= formatter.format(transactionDto.getTransactionDate());
			String priority = transactionDto.getPriority() ? "Y" : "N";
			dataLines.add(transactionDto.getClientId()+ ","+
					transactionDto.getTransactionType()+ ","+
					priority+ ","+strDate+","+
					transactionDto.getFees().toString());
		});
		return dataLines;
	}
}
