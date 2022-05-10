package com.example.geektrust;

import java.util.HashSet;
import java.util.Set;

public class CalculateOverlap {
	StocksCollection stocks;
<<<<<<< HEAD
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

=======
	private static final double MYEPS = 1.0e-8;
	Set<String> overlapFundCollection;
	String fundName;
	String fullMessage = "";

	void checkNoFund(CurrentPortFolio currentPortFolio) {
		int fundSize = 0;
		try {
			fundSize = overlapFundCollection.size();
			for (String currentFundName : currentPortFolio.getCurrentPortFolio()) {
				double fundPortFolioUnion = stocks.getFundSize(currentFundName) + fundSize;
				Set<String> fundPortFolioIntersection = new HashSet<>(stocks.getFundStocks(fundName));
				fundPortFolioIntersection.retainAll(stocks.getFundStocks(currentFundName));
				double overlap = (2.0 * fundPortFolioIntersection.size() * 100.0) / fundPortFolioUnion;
				// if (overlap > MYEPS)
				// System.out.println(fundName + " " + currentFundName + " " +
				// String.format("%.2f", overlap) + "%");
				fullMessage = fundName + " " + currentFundName + " " + String.format("%.2f", overlap) + "%\n";
				// String printMessage = (overlap > MYEPS) ? fullMessage:emptyMessage;

				int isgreater = (int) Math.min(Math.max(overlap - MYEPS, 0), 1);
				int issmaller = 1 - isgreater;
				int len = (isgreater * fullMessage.length()) + (issmaller * 0);

				System.out.print(fullMessage.substring(0, len));
			}
		} catch (Exception ex) {
			System.out.println("FUND_NOT_FOUND");
>>>>>>> 6e73b13f80a1295c9eb875830abf03eafd457785
		}
		
	}

	CalculateOverlap(StocksCollection stocks, CurrentPortFolio currentPortFolio, String fullCommand) {
<<<<<<< HEAD
		this.currentPortFolio = currentPortFolio;
		this.stocks = stocks;
		this.fundName = fullCommand.split(" ")[1];

		overlapFundCollection = stocks.getFundStocks(fundName);
		if (overlapFundCollection != null) {
			calculateOverlap();
			return;
		}
		
		fundNotFound();
		

=======
		this.stocks = stocks;
		this.fundName = fullCommand.split(" ")[1];
		overlapFundCollection = stocks.getFundStocks(fundName);
		checkNoFund(currentPortFolio);
>>>>>>> 6e73b13f80a1295c9eb875830abf03eafd457785

	}

}
