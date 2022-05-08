package com.example.geektrust;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

public class CommandBroker {
	private final String inputFileName;
	private Map<String, Set<String>> completePortFolio = null;
	private List<String> currentPortFolio;
	private final Map<String, Function<String, List<String>>> commandDispatch = Map.of("CURRENT_PORTFOLIO",
			(fullCommand) -> new CurrentPortFolio(fullCommand).execute(), "CALCULATE_OVERLAP",
			(fullCommand) -> new CalculateOverlap(completePortFolio, currentPortFolio,fullCommand).execute(),
			"ADD_STOCK", (fullCommand) -> new AddStock(completePortFolio, currentPortFolio,fullCommand).execute());

	CommandBroker(String fileName, Map<String, Set<String>> completePortFolio) {
		this.inputFileName = fileName;
		this.completePortFolio = completePortFolio;
	}

	public final void run() {
		Scanner input = null;
		try {
			input = new Scanner(new File(inputFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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