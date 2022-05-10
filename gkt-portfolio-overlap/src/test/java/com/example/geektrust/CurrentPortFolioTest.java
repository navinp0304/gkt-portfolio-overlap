package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class CurrentPortFolioTest {

	@Test
	void testGetCurrentPortFolio() {
		String fullCommand = "CURRENT_PORTFOLIO UTI_NIFTY_INDEX AXIS_MIDCAP PARAG_PARIKH_FLEXI_CAP";
		CurrentPortFolio currentPortFolioObj = new CurrentPortFolio(fullCommand);
		assertNotEquals(currentPortFolioObj.getCurrentPortFolio(), null);
	}

	@Test
	void testExecute() {
		String fullCommand = "CURRENT_PORTFOLIO UTI_NIFTY_INDEX AXIS_MIDCAP PARAG_PARIKH_FLEXI_CAP";
		CurrentPortFolio currentPortFolioObj = new CurrentPortFolio(fullCommand);
<<<<<<< HEAD
=======
		List<String> parsedList = currentPortFolioObj.getCurrentPortFolio();
				
>>>>>>> 6e73b13f80a1295c9eb875830abf03eafd457785
		List<String> expected = List.of("UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP");
		assertEquals(currentPortFolioObj.getCurrentPortFolio(), expected);
	}

}
