package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

class CalculateOverlapTest {
	final String locationURL = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";

	@Test
	void testCalculateOverlap() {
		StocksCollection stocks = new StocksCollection(locationURL);
		List<String> currentPortfolio = List.of("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX");
		String fullCommand="CALCULATE_OVERLAP MIRAE_ASSET_EMERGING_BLUECHIP";

		CalculateOverlap obj = new CalculateOverlap(stocks.getCompletePortFolio(), currentPortfolio,fullCommand);
		assertNotEquals(obj, null);
	}

	@Test
	void testExecute() {
		StocksCollection stocks = new StocksCollection(locationURL);
		List<String> currentPortfolio = List.of("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX");
		String fullCommand="CALCULATE_OVERLAP MIRAE_ASSET_EMERGING_BLUECHIP";
		CalculateOverlap obj = new CalculateOverlap(stocks.getCompletePortFolio(), currentPortfolio,fullCommand);
		PrintStream outStream = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		List<String> observedPortFolio = obj.execute();

		System.setOut(outStream);
		List<String> observed = List.of(outContent.toString().split("\n"));
		List<String> expected = List.of("MIRAE_ASSET_EMERGING_BLUECHIP AXIS_BLUECHIP 39.13%",
				"MIRAE_ASSET_EMERGING_BLUECHIP ICICI_PRU_BLUECHIP 38.10%",
				"MIRAE_ASSET_EMERGING_BLUECHIP UTI_NIFTY_INDEX 65.52%");

		assertAll("check overlap", () -> assertEquals(observed, expected),
				() -> assertEquals(currentPortfolio, observedPortFolio));
	}

	@Test
	void testExecuteNoFund() {
		StocksCollection stocks = new StocksCollection(locationURL);
		List<String> currentPortfolio = List.of("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX");
		String fullCommand = "CALCULATE_OVERLAP NOFUNDNAMETHISONE";
		CalculateOverlap obj = new CalculateOverlap(stocks.getCompletePortFolio(), currentPortfolio,fullCommand);
		PrintStream outStream = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		
		List<String> observedPortFolio = obj.execute();
		
		System.setOut(outStream);
		String expected = "FUND_NOT_FOUND";
		String observed = outContent.toString().trim();

		assertEquals(expected, observed);
	}

}
