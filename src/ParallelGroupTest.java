import static org.junit.Assert.*;
import org.junit.Test;

public class ParallelGroupTest {
	ParallelGroupEvent test;

	@Test
	public void testAddRacer() {
		test = new ParallelGroupEvent();
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
	public void testTrigger() {
		test = new ParallelGroupEvent(); 
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
	public void testMoveAll() {
		test = new ParallelGroupEvent();
		assertTrue(test.finished.size()==0);
		for(int i=0;i<4;i++) {
			test.addRacer("R"+i);
		}
		test.moveAll();
		assertTrue(test.finished.size()==4);
		
	}

	@Test
	public void testDNF() {
		test = new ParallelGroupEvent();
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
	public void testSwap() {
		test = new ParallelGroupEvent();
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
	public void testClear() {
		test = new ParallelGroupEvent();
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
