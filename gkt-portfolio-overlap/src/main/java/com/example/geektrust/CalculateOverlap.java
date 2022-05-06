package com.example.geektrust;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalculateOverlap implements IStockCommand {
	private final Map<String, Set<String>> completePortFolio;
	private final List<String> currentPortFolio;
	private static final double MYEPS = 1.0e-8;

	CalculateOverlap(Map<String, Set<String>> completePortFolio, List<String> currentPortFolio) {
		this.completePortFolio = completePortFolio;
		this.currentPortFolio = currentPortFolio;
	}

	@Override
	public List<String> execute(String fullCommand) {
		String[] commands = fullCommand.split(" ");
		String fundName = commands[1];
		int fundSize = 0;
		try {
			fundSize = completePortFolio.get(fundName).size();
		} catch (Exception e) {
			System.out.println("FUND_NOT_FOUND");
			return this.currentPortFolio;
		}
		for (String currentFundName : currentPortFolio) {
			double fundPortFolioUnion = completePortFolio.get(currentFundName).size() + fundSize;
			Set<String> fundPortFolioIntersection = new HashSet<>(completePortFolio.get(fundName));
			fundPortFolioIntersection.retainAll(completePortFolio.get(currentFundName));
			double overlap = (2.0 * fundPortFolioIntersection.size() * 100.0) / fundPortFolioUnion;
			// if (overlap > MYEPS)
			System.out.println(fundName + " " + currentFundName + " " + String.format("%.2f", overlap) + "%");
		}
		return this.currentPortFolio;
	}
}
