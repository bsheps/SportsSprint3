//import org.junit.Test;
//
//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class TestRacer {

	Racer r1, r2, r3, r4, r5, r6, r7, r8;

	public TestRacer() throws InterruptedException {
		testResults();
		testCompareTo();
	}

	@Test
	void testResults() {
		// set a start time
		LocalTime start = Time.getCurrentTime();

		// initialize racers with 3 different constructors
		r1 = new Racer();
		r2 = new Racer("123");
		r3 = new Racer("456", start);

		// get initial results
		String results1 = r1.results();
		String results2 = r2.results();
		String results3 = r3.results();

		// no results should be null
		assertFalse(results1 == null);
		assertFalse(results2 == null);
		assertFalse(results3 == null);

		// all should be cancelled except those with start time
		assertTrue(results1.equals("CANCELLED"));
		assertTrue(results2.equals("CANCELLED"));
		assertFalse(results3.equals("CANCELLED"));

		// all should be DNF except those without start time
		assertFalse(results1.equals("DNF"));
		assertFalse(results2.equals("DNF"));
		assertTrue(results3.equals("DNF"));

		// set an end time
		LocalTime end = Time.getCurrentTime();

		// set the races to the finish time
		r1.finishRace(end);
		r2.finishRace(end);
		r3.finishRace(end);

		// calculate the time result (end minus start)
		String result = Time.time2formattedString(LocalTime.ofNanoOfDay(end.toNanoOfDay() - start.toNanoOfDay()));

		// get updated results
		results1 = r1.results();
		results2 = r2.results();
		results3 = r3.results();

		// check that all results with start and end times are accurate
		assertFalse(results1.equals(result));
		assertFalse(results2.equals(result));
		assertTrue(results3.equals(result));
	}

	@Test
	void testCompareTo() throws InterruptedException {
		// set some start times
		LocalTime start1 = Time.getCurrentTime();
		Thread.sleep(2250);
		LocalTime start2 = Time.getCurrentTime();

		// initialize racers with 3 different constructors
		r1 = new Racer("9110"); // no start, no end
		r2 = new Racer("0420"); // no start, no end
		r3 = new Racer("7100"); // no start, has end
		r4 = new Racer("0609"); // no start, has end
		r5 = new Racer("1800", start1); // has start, no end
		r6 = new Racer("9343", start1); // has start, no end
		r7 = new Racer("1800", start2); // has start, has end
		r8 = new Racer("9343", start2); // has start, has end

		// set some end times
		LocalTime end1 = Time.getCurrentTime();
		Thread.sleep(1337);
		LocalTime end2 = Time.getCurrentTime();

		// assign end times
		r3.finishRace(end1);
		r4.finishRace(end2);
		r7.finishRace(end1);
		r8.finishRace(end2);

		// all races should equal themselves
		assertEquals(r1.compareTo(r1), 0);
		assertEquals(r2.compareTo(r2), 0);
		assertEquals(r3.compareTo(r3), 0);
		assertEquals(r4.compareTo(r4), 0);
		assertEquals(r5.compareTo(r5), 0);
		assertEquals(r6.compareTo(r6), 0);
		assertEquals(r7.compareTo(r7), 0);
		assertEquals(r8.compareTo(r8), 0);

		// similar comparisons
		assertEquals(r1.compareTo(r2), 0);
		assertEquals(r2.compareTo(r1), 0);
		assertEquals(r3.compareTo(r4), 0);
		assertEquals(r4.compareTo(r3), 0);
		assertEquals(r5.compareTo(r6), 0);
		assertEquals(r6.compareTo(r5), 0);

		// times should be opposite of eachother's compare
		assertEquals(r7.compareTo(r8), (-1 * r8.compareTo(r7)));

		// comparing a racer with a result to a racer without a result
		assertTrue(r7.compareTo(r1) < 0);
		assertTrue(r7.compareTo(r3) < 0);
		assertTrue(r7.compareTo(r5) < 0);
		assertTrue(r8.compareTo(r2) < 0);
		assertTrue(r8.compareTo(r4) < 0);
		assertTrue(r8.compareTo(r6) < 0);
	}
}
