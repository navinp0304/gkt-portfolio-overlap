package com.example.geektrust;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalculateOverlap implements IStockCommand {
	private final Map<String, Set<String>> completePortFolio;
	private final List<String> currentPortFolio;
	private static final double MYEPS = 1.0e-8;
	String fullCommand;
	

	CalculateOverlap(Map<String, Set<String>> completePortFolio, List<String> currentPortFolio,String fullCommand) {
		this.completePortFolio = completePortFolio;
		this.currentPortFolio = currentPortFolio;
		this.fullCommand = fullCommand;
	}

	@Override
	public List<String> execute() {
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
			// System.out.println(fundName + " " + currentFundName + " " +
			// String.format("%.2f", overlap) + "%");
			String fullMessage = fundName + " " + currentFundName + " " + String.format("%.2f", overlap) + "%\n";
			// String printMessage = (overlap > MYEPS) ? fullMessage:emptyMessage;
			
			int isgreater = (int) Math.min(Math.max(overlap-MYEPS,0),1);
			int issmaller = 1 - isgreater;
			int len=(isgreater*fullMessage.length())+ (issmaller*0);

			System.out.print(fullMessage.substring(0,len));
		}
		return this.currentPortFolio;
	}
}
