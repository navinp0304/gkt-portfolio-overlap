package com.example.geektrust;

public class Main {
	void init() {}
	public static void main(String[] args) {
		final String locationURL = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";

		StocksCollection stockCollection = new StocksCollection(locationURL);
		CommandBroker commandBroker = new CommandBroker(args[0], stockCollection);
		commandBroker.run();
	}
}
