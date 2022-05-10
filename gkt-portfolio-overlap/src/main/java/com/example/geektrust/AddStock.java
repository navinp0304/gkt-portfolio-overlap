package com.example.geektrust;

import java.util.Set;

public class AddStock {
	StocksCollection stocks;

	AddStock(StocksCollection stocks,  String fullCommand) {
		this.stocks = stocks;
		String fundName = fullCommand.split(" ")[1];
		Set<String> fundCollection = stocks.getFundStocks(fundName);

		if (fundCollection == null) {
			System.out.println("FUND_NOT_FOUND");
			return;
		}
		int stockOffset = fullCommand.indexOf(fundName) + fundName.length();

		String stockName = fullCommand.substring(stockOffset);

		stocks.addStockFund(fundName, stockName.trim());

	}

}