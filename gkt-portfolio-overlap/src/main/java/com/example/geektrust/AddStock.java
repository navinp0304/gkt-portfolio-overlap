package com.example.geektrust;

import java.util.List;
import java.util.Set;

public class AddStock implements IStockCommand {
	StocksCollection stocks;
	private final List<String> currentPortFolio;

	AddStock(StocksCollection stocks, List<String> currentPortFolio, String fullCommand) {
		this.stocks= stocks;
		this.currentPortFolio = currentPortFolio;
		String fundName = fullCommand.split(" ")[1];
		int stockOffset = fullCommand.indexOf(fundName) + fundName.length();
		Set<String> fundCollection = stocks.getFundStocks(fundName);
	
		if (fundCollection == null) {
			System.out.println("FUND_NOT_FOUND");
			return;
		}

		String stockName = fullCommand.substring(stockOffset);

		stocks.addStockFund(fundName, stockName.trim());

	}

	@Override
	public List<String> execute() {
		return this.currentPortFolio;
	}
}