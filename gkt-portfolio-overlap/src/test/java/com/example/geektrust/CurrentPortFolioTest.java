package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class CurrentPortFolioTest {

	@Test
	void testGetCurrentPortFolio() {
		CurrentPortFolio currentPortFolioObj = new CurrentPortFolio();
		assertEquals(currentPortFolioObj.getCurrentPortFolio(),null);
	}

	@Test
	void testExecute() {
		CurrentPortFolio currentPortFolioObj = new CurrentPortFolio();
		List<String> parsedList = currentPortFolioObj.execute("CURRENT_PORTFOLIO UTI_NIFTY_INDEX AXIS_MIDCAP PARAG_PARIKH_FLEXI_CAP");
		List<String> expected=List.of("UTI_NIFTY_INDEX", "AXIS_MIDCAP" ,"PARAG_PARIKH_FLEXI_CAP");
		assertEquals(parsedList,expected);
	}

}
