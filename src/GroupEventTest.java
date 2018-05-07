import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GroupEventTest {
	GroupEvent test;
	@Test
	void testAddRacer() {
		test = new GroupEvent();
		assertEquals(test.racers.size(),0);
		assertEquals(test.finished.size(),0);
		assertFalse(test.raceInSession);
		for(int i=0;i<10000;i++) {
			test.addRacer();	
		}
		assertEquals(test.racers.size(),10000);
		assertEquals(test.finished.size(),0);
		assertFalse(test.raceInSession);
		test.addRacer();//doesn't add more than 10000 racers
		assertEquals(test.racers.size(),10000);
		test = new GroupEvent();
		for(int j=0;j<2000;j++) {
			test.addRacer("Racer"+j);
		}
		assertEquals(test.racers.size(),2000);
		assertEquals(test.finished.size(),0);
		assertFalse(test.raceInSession);
		test.raceInSession=true;
		test.addRacer();//shouldn't allow us to add a racer since there is a race in session
		assertEquals(test.racers.size(),2000);
		test.raceInSession= false;
		test.addRacer();
		assertEquals(test.racers.size(),2001);
	}

	@Test
	void testTrigger() {
		test = new GroupEvent();
		test.addRacer();
		assertEquals(test.racers.size(),1);
		assertEquals(test.finished.size(),0);
		assertFalse(test.raceInSession);
		test.trigger(3);
		assertFalse(test.raceInSession);
		test.trigger(4);
		assertFalse(test.raceInSession);
		test.trigger(5);
		assertFalse(test.raceInSession);
		test.trigger(6);
		assertFalse(test.raceInSession);
		test.trigger(7);
		assertFalse(test.raceInSession);
		test.trigger(8);
		assertFalse(test.raceInSession);
		for(int i=1;i<5;i++) {
			test.addRacer();
		}
		assertEquals(test.racers.size(),5);
		assertEquals(test.finished.size(),0);
		assertFalse(test.raceInSession);
		test.trigger(1);
		assertTrue(test.raceInSession);
		test.trigger(2);
		assertTrue(test.raceInSession);
		for(int i=1;i<5;i++) {
			test.trigger(1);
			test.trigger(2);
		}
		assertEquals(test.racers.size(),0);
		assertEquals(test.finished.size(),5);
		assertFalse(test.raceInSession);
	}

	@Test
	void testMoveAll() {
		test = new GroupEvent();
		for(int i=0;i<10;i++) {
			test.addRacer();
		}
		assertEquals(test.racers.size(),10);
		assertEquals(test.finished.size(),0);
		assertFalse(test.raceInSession);
		test.trigger(1);
		test.trigger(2);
		test.moveAll();
		assertEquals(test.finished.size(),10);
	}
	
	@Test
	void testDNF() {
		test = new GroupEvent();
		test.addRacer();
		test.addRacer();
		test.trigger(1);
		test.trigger(2);
		test.trigger(1);
		assertEquals(test.racers.size(),1);
		assertEquals(test.finished.size(),1);
		test.dnf();
		assertEquals(test.racers.size(),0);
		assertEquals(test.finished.size(),2);
		
	}
	
	
	@Test
	void testSetRacerNum(){
		test = new GroupEvent();
		test.addRacer("Racer1");
		test.addRacer("Racer2");
		assertEquals(test.finished.size(),0);
		assertEquals(test.namedRacers,0);
//		test.trigger(1);
//		test.setRacerNumber("");
		
	}
	
	@Test
	void testClear(){
		test = new GroupEvent();
		
	}
	
	@Test
	void testSwap() {
		test = new GroupEvent();
	}
	
}
