//import org.junit.Test;
//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class TestChannel {

	@Test
	void testChannel() {
		Channel c1 = new Channel();

		// test boundary cases for channels
		for (int i = -1; i < 14; i++) {
			assertFalse(c1.isChannelEnabled(i));
		}

		// test boundary cases for toggling
		assertFalse(c1.Toggle(-1));
		assertFalse(c1.Toggle(0));
		for (int i = 1; i < 13; i++) {
			assertTrue(c1.Toggle(i));
		}
		assertFalse(c1.Toggle(13));
		assertFalse(c1.Toggle(14));

		// test boundary cases for disconnecting sensors
		assertFalse(c1.disconnectSensor(-1));
		assertFalse(c1.disconnectSensor(0));
		for (int i = 1; i < 13; i++) {
			assertFalse(c1.disconnectSensor(i));
		}
		assertFalse(c1.disconnectSensor(13));
		assertFalse(c1.disconnectSensor(14));

		String sensor = "GATE";

		// test boundary cases for connection sensors
		assertFalse(c1.connectSensor(sensor, -1));
		assertFalse(c1.connectSensor(sensor, 0));
		for (int i = 1; i < 13; i++) {
			assertTrue(c1.connectSensor(sensor, i));
		}
		assertFalse(c1.connectSensor(sensor, 13));
		assertFalse(c1.connectSensor(sensor, 14));

		// try to insert sensors where there are already sensors
		for (int i = 1; i < 13; i++) {
			assertFalse(c1.connectSensor(sensor, i));
		}

		// disconnect sensors
		for (int i = 1; i < 13; i++) {
			assertTrue(c1.disconnectSensor(i));
		}

		for (int i = 1; i < 13; i++) {
			assertFalse(c1.connectSensor(null, i));
		}
	}

}
