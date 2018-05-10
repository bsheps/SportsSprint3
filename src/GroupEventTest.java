import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
class GroupEventTest {
	GroupEvent test;
	@org.junit.Test
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
		test.setRacerNumber("Racer1");//cannot set name of racer if they didn't finish
		assertEquals(test.finished.size(),0);
		test.trigger(1);
		test.setRacerNumber("Racer1");
		assertEquals(test.finished.size(),0);
		test.trigger(2);
		assertEquals(test.finished.size(),1);
		test.setRacerNumber("R1");
		assertEquals(test.finished.get(0)._bibNum,"R1");
	}
	
	@Test
	void testClear(){
		test = new GroupEvent();
		test.addRacer("R1");
		test.addRacer("R2");
		assertEquals(test.racers.size(),2);
		Racer r1 = test.racers.get(0);
		Racer r2 = test.racers.get(1);
		test.clear("R1");
		assertTrue(test.racers.get(0)!=r1);
		assertEquals(test.racers.size(),1);
		assertTrue(test.racers.get(0)==r2);
		test.clear("R2");
		assertEquals(test.racers.size(),0);
		
		
		
	}
	
	@Test
	void testSwap() {
		test = new GroupEvent();
		Racer r1 = new Racer("Racer1");
		Racer r2 = new Racer("Racer2");
		test.addRacer(r1._bibNum);
		test.addRacer(r2._bibNum);
		test.trigger(1);
		test.trigger(1);
		assertEquals(test.racers.size(),2);
		assertTrue(test.racers.peek()._bibNum==r1._bibNum);
		test.swap();//shouldn't do anything
		assertTrue(test.racers.peek()._bibNum==r1._bibNum);
	
	}
	
}
