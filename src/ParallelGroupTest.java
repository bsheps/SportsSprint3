


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
		test  = new ParalellGroupEvent();
		assertTrue(test.finished.size()==0);
		for(int i=0;i<3;i++) {
			test.addRacer("Racer"+i);
		}
		System.out.println(test.raceInSession);
		test.trigger(1);
		System.out.println(test.raceInSession);
		test.trigger(1);
		System.out.println(test.finished.size());
		System.out.println(test.finished.peek()._endTime);
//		test = new ParalellGroupEvent();
//		assertEquals(test.finished.size(),0);
//		assertTrue(test.numCompetitors==0);
//		for(int i=0;i<3;i++) {
//			test.addRacer("Racer"+i);
//		}
//		assertTrue(test.numCompetitors==3);
//		assertEquals(test.finished.size(),0);
//		test.trigger(1);
//		for(int i=0;i<3;i++) {
//			assertTrue(test.competitors[i]._startTime == test._startTime) ;
//			System.out.println(test.competitors[i]);
//		}
//		test.trigger(1);
//		System.out.println(test.finished.size());
//		test.trigger(2);
//		System.out.println(test.finished.size());
//		//assertEquals(test.finished.size(),1);
		
	}

	@Test
	void testMoveAll() {
		test = new ParalellGroupEvent();
		assertTrue(test.finished.size()==0);
		for(int i=0;i<4;i++) {
			test.addRacer("R"+i);
		}
		test.moveAll();
		assertTrue(test.finished.size()==4);
		
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
