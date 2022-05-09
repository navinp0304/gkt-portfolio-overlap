package com.example.geektrust;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class CommandBroker {
	private final String inputFileName;
	private StocksCollection stocks=null;
	private List<String> currentPortFolio;
	private final Map<String, Function<String, List<String>>> commandDispatch = Map.of("CURRENT_PORTFOLIO",
			(fullCommand) -> new CurrentPortFolio(fullCommand).execute(), "CALCULATE_OVERLAP",
			(fullCommand) -> new CalculateOverlap(stocks, currentPortFolio,fullCommand).execute(),
			"ADD_STOCK", (fullCommand) -> new AddStock(stocks, currentPortFolio,fullCommand).execute());

	CommandBroker(String fileName, StocksCollection stocks ) {
		this.inputFileName = fileName;
		this.stocks = stocks;
	}

	public final void run() {
		Scanner input ;
		try {
			input = new Scanner(new File(inputFileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("FILE NOT FOUND");
		}
		Function<String, List<String>> commandFunction = null;
		while (input.hasNextLine()) {
			String fullCommand = new String(input.nextLine());
			String[] commands = fullCommand.split(" ");
			commandFunction = commandDispatch.get(commands[0]);
			this.currentPortFolio = commandFunction.apply(fullCommand);
		}
		input.close();
	}
}