package com.example.geektrust;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StocksCollection {

	private final Map<String, Set<String>> completePortFolio = new HashMap<>();

	public final Map<String, Set<String>> getCompletePortFolio() {
		return completePortFolio;
	}

	public final Set<String> getFundStocks(String fundName) {
		return completePortFolio.get(fundName);
	}

	public void addStockFund(String fundName, String stockName) {
		Set<String> fundStocks = new HashSet<>(completePortFolio.get(fundName));
		fundStocks.add(stockName);
		completePortFolio.put(fundName, fundStocks);
	}

	StocksCollection(String locationURL) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readValue(new URL(locationURL), JsonNode.class);

			JsonNode allFundsCollection = jsonNode.get("funds");
			for (JsonNode innerJsonNode : allFundsCollection) {
				String fundName = innerJsonNode.get("name").asText();

				String[] stocksCollection = new ObjectMapper().readValue(innerJsonNode.get("stocks").toString(),
						String[].class);

				// Set<String> uniqueStockCollection =
				// Arrays.stream(stocksCollection).collect(Collectors.toSet());
				Set<String> uniqueStockCollection = new HashSet<>();
				uniqueStockCollection.addAll(Arrays.asList(stocksCollection));
				completePortFolio.put(fundName, uniqueStockCollection);
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("BAD URL");
		}

	}
}