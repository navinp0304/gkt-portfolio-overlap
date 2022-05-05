package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testMain() {
		Main mainInstance = new Main();
		String[] args = {"sample_input/input1.txt"};
		mainInstance.main(args);
		assertNotEquals(mainInstance,null);
	}

}
