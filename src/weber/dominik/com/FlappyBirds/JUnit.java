package weber.dominik.com.FlappyBirds;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JUnit {

	@Test
	void checkIfPlayerIsCreated() {
		
		Handler h = new Handler();
		for(int i = 0; i < 999999999; i++) {
			h.tick();
			int counter = 0;
			for(int j = 0; j < h.getObjectsSize(); j++) {
				GameObject g = h.getObject(j);
				if (g instanceof Player) {
					counter ++;
				}
			}
			assertEquals(1, counter);
		}

		
	}

	
}
