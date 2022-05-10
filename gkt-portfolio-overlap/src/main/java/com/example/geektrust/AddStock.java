package com.example.geektrust;

import java.util.Set;

public class AddStock {
	StocksCollection stocks;

	Boolean printNoFund() {
		System.out.println("FUND_NOT_FOUND");
		return true;
	}


	String fullCommand; 
	String fundName; 
	Set<String> fundCollection;

	Boolean addFundStock(StocksCollection stocks) {
			int stockOffset = fullCommand.indexOf(fundName) + fundName.length();
			String stockName = new String(fullCommand.substring(stockOffset));

			stocks.addStockFund(fundName, stockName.trim());
			return true;
	}

	AddStock(StocksCollection stocks, String fullCommand) {
		this.fullCommand = fullCommand;
		fundName = fullCommand.split(" ")[1];
		fundCollection = stocks.getFundStocks(fundName);
		Boolean either = (fundCollection!=null)?addFundStock(stocks):printNoFund();
	}
}