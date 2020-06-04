package com.feecalculator.apprunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.feecalculator.calculator.FeeCalculator;
import com.feecalculator.model.TransactionDto;
import com.feecalculator.reader.DataReader;
import com.feecalculator.readerFactory.ReaderFactory;
import com.feecalculator.writer.DataWriter;

public class FeeCalculatorRunner {

	public static void main(String args[]) {
		System.out.println("Transaction Report Generation Started!!");
		FeeCalculator feeCalculator = new FeeCalculator();
		List<TransactionDto> listeFeeCalculatorXlsx = xlsxReader(feeCalculator);
		csvWriter(listeFeeCalculatorXlsx);
		System.out.println("Transaction Report Generated !!");
	}
	private static void csvWriter(
		List<TransactionDto> listeFeeCalculatorXlsx) {
		DataWriter dataWriter = new DataWriter();
		List<String> listData=dataWriter.generateContentFeeReport(listeFeeCalculatorXlsx);
		dataWriter.generateReport(listData);
	}
	private static List<TransactionDto> xlsxReader(FeeCalculator feeCalculator) {
		List<TransactionDto> listeFeeCalculator = new ArrayList<>();
		DataReader xlsxReader = ReaderFactory.readFile("XLSX");
		try {
			List<TransactionDto> listeDto = xlsxReader
					.read("D:/Sample_Input.xlsx");
			if (listeDto != null) {
				listeFeeCalculator = feeCalculator.calculateFee(listeDto);

			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return listeFeeCalculator;
	}
}
