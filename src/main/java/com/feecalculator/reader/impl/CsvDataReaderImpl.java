package com.feecalculator.reader.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.feecalculator.model.TransactionDto;
import com.feecalculator.reader.DataReader;

public class CsvDataReaderImpl implements DataReader {

	public List<TransactionDto> read(String file) throws IOException {
		return new ArrayList<>();
	}

}
