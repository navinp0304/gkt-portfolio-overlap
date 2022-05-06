package com.example.geektrust;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StocksCollection {

	private Map<String, Set<String>> completePortFolio = new HashMap<>();

	public final Map<String, Set<String>> getCompletePortFolio() {
		return completePortFolio;
	}

	StocksCollection(String locationURL) {
		List<Map<String, Object>> stocksCollection;
		try {
			stocksCollection = (ArrayList<Map<String, Object>>) (new ObjectMapper()
					.readValue(new URL(locationURL), HashMap.class).get("funds"));
			// parsing stocks into Map
			for (Map<String, Object> eachStock : stocksCollection) {
				String portfolioName = (String) eachStock.get("name");
				Set<String> stockCollection = new HashSet<>((ArrayList<String>) eachStock.get("stocks"));
				completePortFolio.put(portfolioName, stockCollection);
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("BAD URL");
		}
	}
}