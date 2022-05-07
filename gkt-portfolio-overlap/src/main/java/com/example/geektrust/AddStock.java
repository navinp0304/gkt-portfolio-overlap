package com.example.geektrust;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddStock implements IStockCommand {
	private final Map<String, Set<String>> completePortFolio;
	private final List<String> currentPortFolio;

	AddStock(Map<String, Set<String>> completePortFolio, List<String> currentPortFolio) {
		this.completePortFolio = completePortFolio;
		this.currentPortFolio = currentPortFolio;
	}

	@Override
	public List<String> execute(String fullCommand) {
		String[] commands = fullCommand.split(" ");
		String fundName = new String(commands[1]);
		int stockNameOffset = fullCommand.indexOf(fundName) + fundName.length();
		String stockNameFull = new String(fullCommand.substring(stockNameOffset));
		String stockName=stockNameFull.trim();
		Set<String> collectionSet = completePortFolio.get(fundName);
		if (collectionSet != null) {
			collectionSet.add(stockName);
		} else {
			System.out.println("FUND_NOT_FOUND");
		}
		return this.currentPortFolio;
	}
}
