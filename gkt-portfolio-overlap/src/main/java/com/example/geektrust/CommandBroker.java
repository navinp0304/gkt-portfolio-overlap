package com.example.geektrust;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
<<<<<<< HEAD

public class CommandBroker {
	private final String inputFileName;
	private StocksCollection stocks = null;
	CurrentPortFolio currentPortFolio;
	private final Map<String, Consumer<String>> commandDispatch = Map.of("CURRENT_PORTFOLIO", (fullCommand) -> {
		currentPortFolio = new CurrentPortFolio(fullCommand);
	}, "CALCULATE_OVERLAP", (fullCommand) -> new CalculateOverlap(stocks, currentPortFolio, fullCommand), "ADD_STOCK",
			(fullCommand) -> new AddStock(stocks, fullCommand));

	CommandBroker(String fileName, StocksCollection stocks) {
=======
import java.util.function.Function;

public class CommandBroker {
	private final String inputFileName;
	private StocksCollection stocks=null;
	//private List<String> currentPortFolio;
	CurrentPortFolio currentPortFolio;

	private final Map<String, Consumer<String>> commandDispatch = Map.of(
			"CURRENT_PORTFOLIO",(fullCommand) ->{ this.currentPortFolio= new CurrentPortFolio(fullCommand); }, 
			"CALCULATE_OVERLAP",(fullCommand) -> new CalculateOverlap(stocks, currentPortFolio,fullCommand),
			"ADD_STOCK", (fullCommand) -> new AddStock(stocks, fullCommand));
	CommandBroker(String fileName, StocksCollection stocks ) {
>>>>>>> 6e73b13f80a1295c9eb875830abf03eafd457785
		this.inputFileName = fileName;
		this.stocks = stocks;
	}

	public final void run() {
		Scanner input;
		try {
			input = new Scanner(new File(inputFileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("FILE NOT FOUND");
		}
		Consumer<String> commandFunction = null;
		while (input.hasNextLine()) {
			String fullCommand = new String(input.nextLine());
			String[] commands = fullCommand.split(" ");
			commandFunction = commandDispatch.get(commands[0]);
			commandFunction.accept(fullCommand);
		}
		input.close();
	}
}