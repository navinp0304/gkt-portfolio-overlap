package com.example.geektrust;

import java.util.Set;

public class AddStock {
	StocksCollection stocks;

	void printNoFund() {
		System.out.println("FUND_NOT_FOUND");

	}

	AddStock(StocksCollection stocks, String fullCommand) {
		this.stocks = stocks;
		String fundName = fullCommand.split(" ")[1];
		int stockOffset = fullCommand.indexOf(fundName) + fundName.length();
		Set<String> fundCollection = stocks.getFundStocks(fundName);

		if (fundCollection == null) {
			printNoFund();
			return;
		}

		String stockName = fullCommand.substring(stockOffset);

		stocks.addStockFund(fundName, stockName.trim());

	}

}