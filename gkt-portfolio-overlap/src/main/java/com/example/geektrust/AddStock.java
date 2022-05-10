package com.example.geektrust;

import java.util.Set;

public class AddStock {
	StocksCollection stocks;
	
	private Boolean printFundNotFound() {
		System.out.println("FUND_NOT_FOUND");
		return true;
	}
	
	private Boolean addStocksFund(String fullCommand,String fundName) {
		
		int stockOffset = fullCommand.indexOf(fundName) + fundName.length();

		String stockName = new String(fullCommand.substring(stockOffset));

		stocks.addStockFund(fundName, stockName.trim());
		return true;
		
	}


	AddStock(StocksCollection stocks,  String fullCommand) {
		this.stocks = stocks;
		String fundName = fullCommand.split(" ")[1];
		Set<String> fundCollection = stocks.getFundStocks(fundName);

		Boolean either = ((fundCollection==null) && printFundNotFound()) 
					||
						 ((fundCollection!=null) && addStocksFund(fullCommand,fundName));

		String dummy = "";
		int len=either.compareTo(true);
		System.out.print(dummy.substring(len));

	}

}