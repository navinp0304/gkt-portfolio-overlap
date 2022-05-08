package com.example.geektrust;

import java.util.Arrays;
import java.util.List;

public class CurrentPortFolio implements IStockCommand {

	private List<String> currentPortFolio;
	String fullCommand;

	CurrentPortFolio(String fullCommand) {
		this.fullCommand = fullCommand;
	}

	public final List<String> getCurrentPortFolio() {
		return currentPortFolio;
	}

	@Override
	public List<String> execute() {
		String[] commands = fullCommand.split(" ");
		this.currentPortFolio = Arrays.asList(Arrays.copyOfRange(commands, 1, commands.length));
		return this.currentPortFolio;
	}
}
