package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

class AddStockTest {
	final String locationURL = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";

	@Test
	void testAddStock() {

		StocksCollection stocks = new StocksCollection(locationURL);
//		List<String> currentPortfolio = List.of("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX");
		String fullCommand="ADD_STOCK AXIS_BLUECHIP TCS";
		AddStock addStockObj = new AddStock(stocks, fullCommand);
		assertNotEquals(addStockObj, null);
	}

	@Test
	void testExecute() {
		StocksCollection stocks = new StocksCollection(locationURL);
	//	List<String> currentPortfolio = List.of("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX");
		String fullCommand="ADD_STOCK AXIS_BLUECHIP TCS";
		AddStock addStockObj = new AddStock(stocks, fullCommand);
		
		
		assertNotEquals(stocks.getCompletePortFolio().get("AXIS_BLUECHIP").contains("TCS"), null);
	}

	@Test
	void testExecuteNotPresent() {
		StocksCollection stocks = new StocksCollection(locationURL);
	//	List<String> currentPortfolio = List.of("UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP");
		String expected = "FUND_NOT_FOUND";
		String fullCommand="ADD_STOCK NOFUND NOCIL";
		PrintStream outStream = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		AddStock addStockObj = new AddStock(stocks, fullCommand);

		System.setOut(outStream);

		final String observed = outContent.toString().trim();
		assertAll("no file add stock",
				() -> assertEquals(stocks.getCompletePortFolio().get("PARAG_PARIKH_FLEXI_CAP").contains("NOCIL"),
						false),
				() -> assertEquals(observed, expected));
	}
}