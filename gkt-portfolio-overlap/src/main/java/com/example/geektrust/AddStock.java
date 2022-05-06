package com.example.geektrust;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddStock implements IStockCommand {
	private Map<String, Set<String>> completePortFolio;
	private final List<String> currentPortFolio;

	AddStock(Map<String, Set<String>> completePortFolio, List<String> currentPortFolio) {
		this.completePortFolio = completePortFolio;
		this.currentPortFolio = currentPortFolio;
	}

	@Override
	public List<String> execute(String fullCommand) {
		String[] commands = fullCommand.split(" ");
		String fundName = commands[1];
		String stockName = fullCommand.substring(fullCommand.indexOf(fundName) + fundName.length()).trim();
		try {
			completePortFolio.get(fundName).add(stockName);
		} catch (Exception ex) {
			System.out.println("FUND_NOT_FOUND");
		}
		return this.currentPortFolio;
	}
}
