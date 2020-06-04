package com.feecalculator.readerFactory;

import com.feecalculator.reader.DataReader;
import com.feecalculator.reader.impl.CsvDataReaderImpl;
import com.feecalculator.reader.impl.XmlDataReaderImpl;
import com.feecalculator.reader.impl.XlsxDataReaderImpl;

public class ReaderFactory {

	 public static DataReader readFile(String fileType){
	        if(fileType.equals("CSV")) 
	                return new CsvDataReaderImpl();
	        else  if(fileType.equals("XML"))
	                return new XmlDataReaderImpl();
	        if(fileType.equals("XLSX"))
	                return new XlsxDataReaderImpl();   
	        return null;
	    }
}
