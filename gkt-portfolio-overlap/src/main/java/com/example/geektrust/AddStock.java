package com.example.geektrust;

import java.util.Set;

public class AddStock {
	StocksCollection stocks;

	void printNoFund() {
		System.out.println("FUND_NOT_FOUND");

	}


	String fullCommand; 
	String fundName; 
	Set<String> fundCollection;

	void checkNoFund(StocksCollection stocks) {
		try {
			int stockOffset = fullCommand.indexOf(fundName) + fundName.length();
			String stockName = new String(fullCommand.substring(stockOffset));
			stockOffset += fundCollection.size();
			stocks.addStockFund(fundName, stockName.trim());
		} catch (Exception ex) {
			System.out.println("FUND_NOT_FOUND");

		}
	}

	AddStock(StocksCollection stocks, String fullCommand) {
		this.fullCommand = fullCommand;
		fundName = fullCommand.split(" ")[1];
		fundCollection = stocks.getFundStocks(fundName);
		checkNoFund(stocks);
	}
}