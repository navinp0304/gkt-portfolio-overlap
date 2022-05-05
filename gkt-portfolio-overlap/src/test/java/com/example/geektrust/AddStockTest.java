package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class AddStockTest {
	final String locationURL = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";

	@Test
	void testAddStock() {

		StocksCollection stocks = new StocksCollection(locationURL);
		List<String> currentPortfolio = List.of("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX");

		AddStock addStockObj = new AddStock(stocks.getCompletePortFolio(), currentPortfolio);
		assertNotEquals(addStockObj, null);
	}

	@Test
	void testExecute() {
		StocksCollection stocks = new StocksCollection(locationURL);
		List<String> currentPortfolio = List.of("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX");

		AddStock addStockObj = new AddStock(stocks.getCompletePortFolio(), currentPortfolio);

		addStockObj.execute("ADD_STOCK AXIS_BLUECHIP TCS");
		assertNotEquals(stocks.getCompletePortFolio().get("AXIS_BLUECHIP").contains("TCS"), null);
	}

	@Test
	void testExecuteNotPresent() {
		StocksCollection stocks = new StocksCollection(locationURL);
		List<String> currentPortfolio = List.of("UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP");
		String observed = "";
		String expected = "FUND_NOT_FOUND";
		AddStock addStockObj = new AddStock(stocks.getCompletePortFolio(), currentPortfolio);

		try {
			addStockObj.execute("ADD_STOCK NOFUND NOCIL");
		} catch (Exception ex) {
			observed = ex.getMessage();
		}
		final String found = observed;
		assertAll("no file add stock",
				() -> assertEquals(stocks.getCompletePortFolio().get("PARAG_PARIKH_FLEXI_CAP").contains("NOCIL"),
						false),
				() -> assertEquals(found, expected));
	}
}