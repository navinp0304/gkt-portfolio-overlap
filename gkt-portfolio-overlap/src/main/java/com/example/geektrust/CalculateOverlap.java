package com.example.geektrust;

import java.util.HashSet;
import java.util.Set;

public class CalculateOverlap {
	StocksCollection stocks;
	private final CurrentPortFolio currentPortFolio;
	private static final double MYEPS = 1.0e-8;
	Set<String> overlapFundCollection;
	String fundName;

	void fundNotFound() {
		System.out.println("FUND_NOT_FOUND");
	}
	
	Boolean printMessage(String message) {
		System.out.print(message);
		return true;
	}

	void calculateOverlap() {

		int fundSize = overlapFundCollection.size();
		String fullMessage = "";
		for (String currentFundName : currentPortFolio.getCurrentPortFolio()) {
			double fundPortFolioUnion = stocks.getFundSize(currentFundName) + fundSize;
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
	}

	CalculateOverlap(StocksCollection stocks, CurrentPortFolio currentPortFolio, String fullCommand) {
		this.currentPortFolio = currentPortFolio;
		this.stocks = stocks;
		this.fundName = fullCommand.split(" ")[1];

		overlapFundCollection = stocks.getFundStocks(fundName);
		if (overlapFundCollection != null) {
			calculateOverlap();
			return;
		}
		
		fundNotFound();
		


	}

}
