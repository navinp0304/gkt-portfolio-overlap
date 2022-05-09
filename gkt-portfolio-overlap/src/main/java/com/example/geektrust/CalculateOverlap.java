package com.example.geektrust;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalculateOverlap implements IStockCommand {
	StocksCollection stocks;
	private final List<String> currentPortFolio;
	private static final double MYEPS = 1.0e-8;
	Set<String> overlapFundCollection;
	String fundName;

	void calculateOverlap() {

		int fundSize = overlapFundCollection.size();
		String fullMessage = "";
		for (String currentFundName : currentPortFolio) {
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
	}
	CalculateOverlap(StocksCollection stocks, List<String> currentPortFolio, String fullCommand) {
		this.currentPortFolio = currentPortFolio;
		this.stocks = stocks;
		this.fundName = fullCommand.split(" ")[1];
		
		overlapFundCollection = stocks.getFundStocks(fundName);
		if (overlapFundCollection == null) {
			System.out.println("FUND_NOT_FOUND");
			return;
		}
		calculateOverlap();

	}

	@Override
	public List<String> execute() {
		return this.currentPortFolio;
	}
}
