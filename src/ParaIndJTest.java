import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ParaIndJTest {
	ParaIndEvent test;
	@Test
	void testAddRacers() {
		test = new ParaIndEvent();
		assertTrue(test.waitingToRace.size()==0);
		test.addRacer("1");
		assertTrue(test.waitingToRace.size()==1);
		test.addRacer("2");
		assertTrue(test.channels12.size()==0);
		assertTrue(test.channels34.size()==0);
		assertTrue(test.finishers.size()==0);
		for(int i = 3; i <10000; i++) {
			test.addRacer("i");

		}
		assertTrue(test.waitingToRace.size()==9999);

	}
	@Test
	void testTrigger1and2() {
		test = new ParaIndEvent();
		test.trigger(4);
		test.trigger(2);
		assertTrue(test.waitingToRace.size()==0);
		assertTrue(test.channels12.size()==0);
		assertTrue(test.channels34.size()==0);
		assertTrue(test.finishers.size()==0);
		test.trigger(1);
		assertTrue(test.channels12.size()==1);
		test.trigger(2);
		assertTrue(test.channels12.size()==0);
		assertTrue(test.finishers.size()==1);
	}
	@Test
	void testTrigger3and4() {
		test = new ParaIndEvent();
		test.addRacer("1234");
		test.trigger(4);
		assertTrue(test.waitingToRace.size()==1);
		test.trigger(3);
		assertTrue(test.waitingToRace.size()==0);
		assertTrue(test.channels34.size()==1);
		test.trigger(4);
		assertTrue(test.finishers.size()==1);
	}
	@Test
	void testTriggerOtherChnls() {
		test = new ParaIndEvent();
		for(int i =5; i<= 8 ;++i) {
			test.trigger(i);
			assertEquals(test.waitingToRace.size(),0);
			assertEquals(test.channels12.size(),0);
			assertEquals(test.channels34.size(),0);
			assertEquals(test.finishers.size(),0);
		}
	}
	
	@Test
	void testEndEvent() {
		test = new ParaIndEvent();
		test.addRacer("name");
		test.endEvent(true);
		assertEquals(test.channels12.size(),0);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.waitingToRace.size(),1);
		test.trigger(3);
		assertEquals(test.channels12.size(),0);
		assertEquals(test.channels34.size(),1);
		assertEquals(test.waitingToRace.size(),0);
		test.endEvent(false);
		assertEquals(test.channels12.size(),0);
		assertEquals(test.channels34.size(),1);
		assertEquals(test.waitingToRace.size(),0);
		test.endEvent(true);
		assertEquals(test.finishers.size(),1);
		assertEquals(test.channels12.size(),0);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.waitingToRace.size(),0);
		
	}
	
	@Test
	void testmoveAll() {
		test = new ParaIndEvent();
		test.moveAll();
		assertEquals(test.channels12.size(),0);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.waitingToRace.size(),0);
		assertEquals(test.finishers.size(),0);
		test.addRacer("Racer1");
		test.addRacer("Racer2");
		assertEquals(test.waitingToRace.size(),2);
		test.trigger(1);
		test.trigger(3);
		assertEquals(test.channels12.size(),1);
		assertEquals(test.channels34.size(),1);
		test.moveAll();
		assertEquals(test.channels12.size(),1);
		assertEquals(test.channels34.size(),1);
		assertEquals(test.waitingToRace.size(),0);
		assertEquals(test.finishers.size(),2);//shouldn't it be 0?
		test.trigger(2);
		assertEquals(test.finishers.size(),3);//shouldn't it be 1?
		assertEquals(test.channels12.size(),0);
		assertEquals(test.channels34.size(),1);
		test.trigger(4);
		assertEquals(test.channels12.size(),0);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.waitingToRace.size(),0);
		assertEquals(test.finishers.size(),4);//shouldn't it be 2?
	}
	
	@Test
	void testdnf() {
		test = new ParaIndEvent();
		
	}
	
	@Test
	void testSwap() {
		test = new ParaIndEvent();
		test.addRacer("Racer1");
		test.addRacer("Racer2");
		
	}
	
	@Test
	void testClear() {
		test = new ParaIndEvent();
		for(int i=0;i<20;i++) {
			test.addRacer(Integer.toString(i));
		}
		assertEquals(test.channels12.size(),0);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.finishers.size(),0);
		assertEquals(test.waitingToRace.size(),20);
		for(int i=0;i<5;i++) {
			test.trigger(1);
		}
		assertEquals(test.channels12.size(),5);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.finishers.size(),0);
		assertEquals(test.waitingToRace.size(),15);
		for(int i=0;i<3;i++) {
			test.trigger(2);
		}
		assertEquals(test.channels12.size(),2);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.finishers.size(),3);
		assertEquals(test.waitingToRace.size(),15);
		for(int i=0;i<3;i++) {
			test.clear(Integer.toString(i));
		}
		assertEquals(test.channels12.size(),2);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.finishers.size(),0);
		assertEquals(test.waitingToRace.size(),15);
		for(int i=3;i<20;i++) {
			test.clear(Integer.toString(i));
		}
		assertEquals(test.channels12.size(),0);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.finishers.size(),0);
		assertEquals(test.waitingToRace.size(),0);	
	}
	
	@Test
	void moreTests() {
		test = new ParaIndEvent();
		for(int i=0;i<20;i++) {
			test.addRacer(Integer.toString(i));
		}
		assertEquals(test.channels12.size(),0);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.finishers.size(),0);
		assertEquals(test.waitingToRace.size(),20);
		for(int i=0;i<10;i++) {
			test.trigger(1);
		}
		assertEquals(test.channels12.size(),10);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.finishers.size(),0);
		assertEquals(test.waitingToRace.size(),10);
		for(int i=0;i<7;i++) {
			test.trigger(2);
		}
		assertEquals(test.channels12.size(),3);
		assertEquals(test.channels34.size(),0);
		assertEquals(test.finishers.size(),7);
		assertEquals(test.waitingToRace.size(),10);
	}
}
