package com.feecalculator.reader.impl;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.feecalculator.model.TransactionDto;
import com.feecalculator.reader.DataReader;

public class XlsxDataReaderImpl implements DataReader {

	public List<TransactionDto> read(String file) {
		List<TransactionDto> list = new ArrayList<>();	
		try(XSSFWorkbook book= new XSSFWorkbook(new FileInputStream(file));) {
			XSSFSheet sheet = book.getSheetAt(0);
			final int totalRows = sheet.getLastRowNum();
			for(int i=1;i<totalRows;i++){
				final Row row = sheet.getRow(i);
				TransactionDto transactionDto = new TransactionDto();
				transactionDto.setExternalTransactionID(row.getCell(0).getStringCellValue());
				transactionDto.setClientId(row.getCell(1).getStringCellValue());
				transactionDto.setSecurityId(row.getCell(2).getStringCellValue());
				transactionDto.setTransactionType(row.getCell(3).getStringCellValue());
				transactionDto.setTransactionDate(row.getCell(4).getDateCellValue());
				transactionDto.setMarketValue(new BigDecimal(row.getCell(5).getNumericCellValue()));
				transactionDto.setPriority(validatePriority(row.getCell(6).getStringCellValue()));
				list.add(transactionDto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return list;
	}

	private static Boolean validatePriority(String priority) {
			return priority.equalsIgnoreCase("Y");
	}



}
