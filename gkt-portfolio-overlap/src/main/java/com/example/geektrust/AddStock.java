package com.example.geektrust;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddStock implements IStockCommand {
	private final List<String> currentPortFolio;
	private final String fullCommand;
	private final int stockOffset;
	private final Set<String> fundCollection;

	AddStock(Map<String, Set<String>> completePortFolio, List<String> currentPortFolio, String fullCommand) {
		this.currentPortFolio = currentPortFolio;
		this.fullCommand = fullCommand;
		String fundName = fullCommand.split(" ")[1];
		stockOffset = fullCommand.indexOf(fundName) + fundName.length();
		fundCollection = completePortFolio.get(fundName);
	}

	@Override
	public List<String> execute() {
		String stockName = fullCommand.substring(stockOffset);
		if (fundCollection != null) {
			fundCollection.add(stockName.trim());
		} else {
			System.out.println("FUND_NOT_FOUND");
		}
		return this.currentPortFolio;
	}
}