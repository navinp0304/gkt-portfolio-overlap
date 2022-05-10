package com.example.geektrust;

import java.util.HashSet;
import java.util.Set;

public class CalculateOverlap {
	StocksCollection stocks;
	private final CurrentPortFolio currentPortFolio;
	private static final double MYEPS = 1.0e-8;
	Set<String> overlapFundCollection;
	String fundName;


	
	Boolean printMessage(String message) {
		System.out.print(message);
		return true;
	}

	Boolean calculateOverlap() {

		int fundSize = overlapFundCollection.size();
		String fullMessage = "";
		for (String currentFundName : currentPortFolio.getCurrentPortFolio()) {
			double fundPortFolioUnion = stocks.getFundStocks(currentFundName).size() + fundSize;
			Set<String> fundPortFolioIntersection = new HashSet<>(stocks.getFundStocks(fundName));
			fundPortFolioIntersection.retainAll(stocks.getFundStocks(currentFundName));
			double overlap = (2.0 * fundPortFolioIntersection.size() * 100.0) / fundPortFolioUnion;
			//if (overlap > MYEPS) continue;
			// System.out.println(fundName + " " + currentFundName + " " +
			// String.format("%.2f", overlap) + "%");
			fullMessage = fundName + " " + currentFundName + " " + String.format("%.2f", overlap) + "%\n";
			// String printMessage = (overlap > MYEPS) ? fullMessage:emptyMessage;
			
			Boolean either = (overlap>MYEPS)?printMessage(fullMessage):printMessage("") ; 

		}
		return true;
	}

	CalculateOverlap(StocksCollection stocks, CurrentPortFolio currentPortFolio, String fullCommand) {

		this.currentPortFolio = currentPortFolio;
		this.stocks = stocks;
		this.fundName = fullCommand.split(" ")[1];

		overlapFundCollection = stocks.getFundStocks(fundName);
		Boolean either = (overlapFundCollection != null)?calculateOverlap():printMessage("FUND_NOT_FOUND\n");
				
	}

}
