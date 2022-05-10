package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class CommandBrokerTest {
	final String locationURL = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";

	@Test
	void testCommandBroker() {
		StocksCollection stockCollection = new StocksCollection(locationURL);
		CommandBroker commandBroker = new CommandBroker("sample_input/input1.txt", stockCollection);
		assertNotEquals(commandBroker, null);
	}

	@Test
	void testCommandBrokerNofile() {
		StocksCollection stockCollection = new StocksCollection(locationURL);
		String observed = "";
		String expected = "FILE NOT FOUND";
		CommandBroker commandBroker = new CommandBroker("nofile.txt", stockCollection);
		try {
			commandBroker.run();
		} catch (Exception ex) {
			observed = ex.getMessage();
		}
		assertEquals(observed, expected);
	}

	@Test
	void testRun() {
		StocksCollection stockCollection = new StocksCollection(locationURL);
		CommandBroker commandBroker = new CommandBroker("sample_input/input1.txt", stockCollection);
		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		String inputData = "CURRENT_PORTFOLIO AXIS_BLUECHIP ICICI_PRU_BLUECHIP UTI_NIFTY_INDEX\n"
				+ "CALCULATE_OVERLAP MIRAE_ASSET_EMERGING_BLUECHIP\n" + "CALCULATE_OVERLAP MIRAE_ASSET_LARGE_CAP\n"
				+ "ADD_STOCK AXIS_BLUECHIP TCS\n" + "CALCULATE_OVERLAP MIRAE_ASSET_EMERGING_BLUECHIP\n";

		String expected = "MIRAE_ASSET_EMERGING_BLUECHIP AXIS_BLUECHIP 39.13%\n"
				+ "MIRAE_ASSET_EMERGING_BLUECHIP ICICI_PRU_BLUECHIP 38.10%\n"
				+ "MIRAE_ASSET_EMERGING_BLUECHIP UTI_NIFTY_INDEX 65.52%\n"
				+ "MIRAE_ASSET_LARGE_CAP AXIS_BLUECHIP 43.75%\n" + "MIRAE_ASSET_LARGE_CAP ICICI_PRU_BLUECHIP 44.62%\n"
				+ "MIRAE_ASSET_LARGE_CAP UTI_NIFTY_INDEX 95.00%\n"
				+ "MIRAE_ASSET_EMERGING_BLUECHIP AXIS_BLUECHIP 38.71%\n"
				+ "MIRAE_ASSET_EMERGING_BLUECHIP ICICI_PRU_BLUECHIP 38.10%\n"
				+ "MIRAE_ASSET_EMERGING_BLUECHIP UTI_NIFTY_INDEX 65.52%\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setIn(new ByteArrayInputStream(inputData.getBytes()));

		commandBroker.run();

		System.setIn(stdin);
		System.setOut(stdout);
		assertEquals(expected, outContent.toString());
	}
}
