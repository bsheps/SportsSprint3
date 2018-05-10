import java.util.Queue;

import org.junit.Test;

import static org.junit.Assert.*;

class IndividualEventJUnitTest {
	IndividualEvent n;
	@Test
	void testAddRacer() {
		n = new IndividualEvent();
		n.addRacer("a");
		n.addRacer("aname");
		n.addRacer(null);
		assertTrue(n.WaitingToRace.size()==3);
		assertTrue(n.inTheRace.size()==0);
		assertTrue(n.finishers.size()==0);
		Racer m = n.WaitingToRace.remove();
		assertEquals(m._bibNum, "a");
		m = n.WaitingToRace.remove();
		assertEquals(m._bibNum, "aname");
		m = n.WaitingToRace.remove();
		assertEquals(m._bibNum, null);
	}
	@Test
	void testTrigger() {
		n = new IndividualEvent();
		n.addRacer("a");
		n.addRacer("aname");
		n.addRacer(null);
		assertTrue(n.WaitingToRace.size()==3);
		assertTrue(n.inTheRace.size()==0);
		assertTrue(n.finishers.size()==0);
		for(int i = 3; i<13;++i) {
			n.trigger(i);
		}
		assertTrue(n.WaitingToRace.size()==3);
		assertTrue(n.inTheRace.size()==0);
		assertTrue(n.finishers.size()==0);
		n.trigger(1);
		assertTrue(n.WaitingToRace.size()==2);
		assertTrue(n.inTheRace.size()==1);
		assertTrue(n.finishers.size()==0);
		n.trigger(1);
		n.trigger(1);
		n.trigger(2);
		assertTrue(n.WaitingToRace.size()==0);
		assertTrue(n.inTheRace.size()==2);
		assertTrue(n.finishers.size()==1);
		n.trigger(1);
		n.trigger(1);
		assertTrue(n.WaitingToRace.size()==0);
		assertTrue(n.inTheRace.size()==4);
		assertTrue(n.finishers.size()==1);
		n.trigger(2);
		n.trigger(2);
		n.trigger(2);
		n.trigger(2);
		n.trigger(2);
		n.trigger(2);
		assertTrue(n.WaitingToRace.size()==0);
		assertTrue(n.inTheRace.size()==0);
		assertTrue(n.finishers.size()==5);
	}
	@Test
	void testMoveAll() {
		n = new IndividualEvent();
		n.addRacer("a");
		n.addRacer("aname");
		n.addRacer(null);
		n.trigger(1);
		n.trigger(1);
		n.trigger(2);
		assertTrue(n.WaitingToRace.size()==1);
		assertTrue(n.inTheRace.size()==1);
		assertTrue(n.finishers.size()==1);
		Queue<Racer> g = n.moveAll();
		assertTrue(g.size() == 3);
	}
	@Test
	void testSwap() {
		n= new IndividualEvent();
		Racer a = new Racer("uno");
		Racer b = new Racer("dos");
		n.inTheRace.add(a);
		n.inTheRace.add(b);
		assertTrue(n.inTheRace.peek() == a);
		n.swap();
		assertTrue(n.inTheRace.peek() ==b);	
		Racer c = new Racer("Tres");
		n.inTheRace.add(c);
		n.swap();
		assertTrue(n.inTheRace.remove() == a);
		assertTrue(n.inTheRace.remove() == b);
		assertTrue(n.inTheRace.remove() == c);
	}
	@Test
	void testCLR() {
		n= new IndividualEvent();
		Racer a = new Racer("uno");
		Racer b = new Racer("dos");
		Racer c = new Racer("tres");
		n.WaitingToRace.add(a);
		n.inTheRace.add(b);
		n.finishers.add(c);
		assertTrue(n.WaitingToRace.contains(a));
		n.clear("uno");
		assertTrue(!n.WaitingToRace.contains(a));
		assertTrue(n.inTheRace.contains(b));
		n.clear("dos");
		assertTrue(!n.inTheRace.contains(b));
		assertTrue(n.finishers.contains(c));
		n.clear("tres");
		assertTrue(!n.finishers.contains(c));
	}
	void testDNF() {
		n= new IndividualEvent();
		Racer a = new Racer("uno");
		Racer b = new Racer("dos");
		Racer c = new Racer("tres");
		n.inTheRace.add(a);
		n.inTheRace.add(b);
		n.inTheRace.add(c);
		n.trigger(2);
		assertTrue(n.finishers.contains(a));
		assertTrue(n.finishers.peek()._endTime != null);
		n.dnf();
		n.trigger(2);
		assertTrue(n.finishers.contains(b));
		assertTrue(n.finishers.peek()._endTime == null);
		n.trigger(2);
		assertTrue(n.finishers.contains(c));
		assertTrue(n.finishers.peek()._endTime != null);
	}
}
