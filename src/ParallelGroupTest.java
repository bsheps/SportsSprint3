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
		assertEquals(test.finished.size(),0);
		assertTrue(test.numCompetitors==0);
		for(int i=0;i<3;i++) { 
			test.addRacer("Racer"+i);
		}
		assertTrue(test.numCompetitors==3);
		assertEquals(test.finished.size(),0);
		test.trigger(1);
		for(int i=0;i<3;i++) {
			assertTrue(test.competitors[i]._startTime == test._startTime) ;
		}
		test.trigger(1);
		assertEquals(test.finished.size(),1);
		test.trigger(2);
		assertEquals(test.finished.size(),2);
		test.trigger(2);
		assertEquals(test.finished.size(),2);
		test.trigger(3);
		assertEquals(test.finished.size(),3);	
		for(int i=3;i<8;i++) {
			test.trigger(i);
		}
		assertEquals(test.finished.size(),3);
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
		test.addRacer("R1");
		test.addRacer("R2");
		assertEquals(test.numCompetitors,2);
		assertEquals(test.finished.size(),0);
		test.trigger(1);
		test.trigger(1);//R1 ended race
		assertEquals(test.finished.size(),1);
		test.dnf();
		assertEquals(test.finished.size(),1);
		
	}

	@Test
	void testSwap() {
		test = new ParalellGroupEvent();
		test.addRacer("r1");
		test.addRacer("r2");
		test.trigger(1);
		assertEquals(test.numCompetitors,2);
		assertTrue(test.competitors[0]._bibNum == "r1");
		assertTrue(test.competitors[1]._bibNum == "r2");
		test.swap();
		assertTrue(test.competitors[0]._bibNum == "r1");
		assertTrue(test.competitors[1]._bibNum == "r2");
	}

	@Test
	void testClear() {
		test = new ParalellGroupEvent();
		test.addRacer("r1");
		test.addRacer("r2");
		test.addRacer("r3");
		assertEquals(test.numCompetitors,3);
		assertEquals(test.finished.size(),0);
		test.trigger(1);
		test.trigger(1);
		test.trigger(2);
		assertEquals(test.finished.size(),2);
		test.clear("r1");
		assertEquals(test.competitors[0],null);
		assertEquals(test.finished.size(),1);
		test.clear("r2");
		assertEquals(test.competitors[1],null);
	}
}
