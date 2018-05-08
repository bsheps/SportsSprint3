
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class TestRacer {

	LocalTime start, end;
	Racer r1, r2, r3, r4, r5, r6;

	public TestRacer() {
		testResults();
	}

	@Test
	void testResults() {
		// set a start time
		start = Time.getCurrentTime();

		// initialize racers with 3 different constructors
		r1 = new Racer();
		r2 = new Racer();
		r3 = new Racer("123");
		r4 = new Racer("abc");
		r5 = new Racer("456", start);
		r6 = new Racer("def", start);

		// get initial results
		String results1 = r1.results();
		String results2 = r2.results();
		String results3 = r3.results();
		String results4 = r4.results();
		String results5 = r5.results();
		String results6 = r6.results();

		// no results should be null
		assertFalse(results1 == null);
		assertFalse(results2 == null);
		assertFalse(results3 == null);
		assertFalse(results4 == null);
		assertFalse(results5 == null);
		assertFalse(results6 == null);

		// all should be cancelled except those with start time
		assertTrue(results1.equals("CANCELLED"));
		assertTrue(results2.equals("CANCELLED"));
		assertTrue(results3.equals("CANCELLED"));
		assertTrue(results4.equals("CANCELLED"));
		assertFalse(results5.equals("CANCELLED"));
		assertFalse(results6.equals("CANCELLED"));

		// all should be DNF except those without start time
		assertFalse(results1.equals("DNF"));
		assertFalse(results2.equals("DNF"));
		assertFalse(results3.equals("DNF"));
		assertFalse(results4.equals("DNF"));
		assertTrue(results5.equals("DNF"));
		assertTrue(results6.equals("DNF"));

		// set an end time
		end = Time.getCurrentTime();

		// set the races to the finish time
		r1.finishRace(end);
		r2.finishRace(end);
		r3.finishRace(end);
		r4.finishRace(end);
		r5.finishRace(end);
		r6.finishRace(end);

		// calculate the time result (end minus start)
		String result = Time.time2formattedString(LocalTime.ofNanoOfDay(end.toNanoOfDay() - start.toNanoOfDay()));

		// get updated results
		results1 = r1.results();
		results2 = r2.results();
		results3 = r3.results();
		results4 = r4.results();
		results5 = r5.results();
		results6 = r6.results();

		// check that all results with start and end times are accurate
		assertFalse(results1.equals(result));
		assertFalse(results2.equals(result));
		assertFalse(results3.equals(result));
		assertFalse(results4.equals(result));
		assertTrue(results5.equals(result));
		assertTrue(results6.equals(result));
	}

}
