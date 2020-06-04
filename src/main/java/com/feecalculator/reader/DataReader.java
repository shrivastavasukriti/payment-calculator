package com.feecalculator.reader;

import java.io.IOException;
import java.util.List;

import com.feecalculator.model.TransactionDto;

public interface DataReader {

	public List<TransactionDto> read(String file)  throws IOException;

}
