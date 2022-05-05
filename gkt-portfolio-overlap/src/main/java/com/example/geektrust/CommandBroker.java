package com.example.geektrust;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

public class CommandBroker {
	private final String fileName;
	private Map<String, Set<String>> completePortFolio;
	private List<String> currentPortFolio;
	private final Map<String, Function<String, List<String>>> commandDispatch = Map.of("CURRENT_PORTFOLIO",
			(fullCommand) -> new CurrentPortFolio().execute(fullCommand), "CALCULATE_OVERLAP",
			(fullCommand) -> new CalculateOverlap(completePortFolio, currentPortFolio).execute(fullCommand),
			"ADD_STOCK", (fullCommand) -> new AddStock(completePortFolio, currentPortFolio).execute(fullCommand));

	CommandBroker(String fileName, Map<String, Set<String>> completePortFolio) {
		this.fileName = fileName;
		this.completePortFolio = completePortFolio;
	}

	public final void run() {
		Scanner input = null;
		try {
			input = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("FILE NOT FOUND");
		}

		while (input.hasNextLine()) {
			String fullCommand = input.nextLine();
			String[] commands = fullCommand.split(" ");
			this.currentPortFolio = commandDispatch.get(commands[0]).apply(fullCommand);
		}
		input.close();
	}
}