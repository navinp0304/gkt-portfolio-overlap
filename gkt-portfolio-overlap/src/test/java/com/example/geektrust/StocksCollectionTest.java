package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StocksCollectionTest {
	final String locationURL = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";

	@Test
	void testGetCompletePortFolio() {
		StocksCollection obj=new StocksCollection(locationURL);
		assertAll("Not null",
				()-> assertNotEquals(obj,null),
				()->assertNotEquals(obj.getCompletePortFolio().size(),0)
				);
	}

	@Test
	void testStocksCollection() {
		StocksCollection obj=new StocksCollection(locationURL);
		assertNotEquals(obj,null);
	}
	@Test
	void testStocksCollectionbadURL() {
		String observed="";
		String expected="BAD URL";
		try {
		StocksCollection obj=new StocksCollection(locationURL+"bad");
		} catch(Exception ex) {
			observed=ex.getMessage();
		}
		assertEquals(expected,observed);
	}

}
