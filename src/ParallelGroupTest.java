


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParallelGroupTest {
	ParalellGroupEvent test;
	
	@Test
	void testAddRacer() {
		test = new ParalellGroupEvent();
		assertTrue(test.numCompetitors==0);
		for(int i=0;i<8;i++) {
			test.addRacer("Racer"+i);
		}
		assertEquals(test.numCompetitors,8);
		assertEquals(test.competitors.length,8);
		test.addRacer("Racer9");
		assertEquals(test.numCompetitors,8);
		assertEquals(test.competitors.length,8);
	}
	
	@Test
	void testTrigger() {
		test = new ParalellGroupEvent();
	}
	
	@Test
	void testMoveAll() {
		test = new ParalellGroupEvent();
	}
	
	@Test
	void testDNF() {
		test = new ParalellGroupEvent();
	}
	
	@Test
	void testSwap() {
		test = new ParalellGroupEvent();
	}

	@Test
	void testClear() {
		test = new ParalellGroupEvent();
	}
}
