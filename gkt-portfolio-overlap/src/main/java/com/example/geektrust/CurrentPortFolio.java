package com.example.geektrust;

import java.util.Arrays;
import java.util.List;

public class CurrentPortFolio {

	private List<String> currentPortFolio;
	String fullCommand;

	CurrentPortFolio(String fullCommand) {
		this.fullCommand = fullCommand;
		String[] commands = fullCommand.split(" ");
		this.currentPortFolio = Arrays.asList(Arrays.copyOfRange(commands, 1, commands.length));
	}

	public final List<String> getCurrentPortFolio() {
		return currentPortFolio;
	}


}
