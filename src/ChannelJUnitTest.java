import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ChannelJUnitTest {
	Channel c;
	@Test
	void testInitialization() {
		c = new Channel();
		for(int i = 0; i <13; i++) {
			assertFalse(c.isChannelEnabled(i));
		}
	}
	@Test
	void testToggleAndisEnabled() {
		c = new Channel();
		for(int i =0; i<13; i++) {
			assertTrue(c.Toggle(i));
			assertTrue(c.isChannelEnabled(i));			
		}
		for(int i =0; i<13; i++) {
			assertFalse(c.Toggle(i));
			assertFalse(c.isChannelEnabled(i));			
		}
		assertFalse(c.Toggle(-1));
		assertFalse(c.Toggle(13));
	}
	@Test
	void testconnectSensoranddisconnectSensor() {
		c = new Channel();
		for(int i =0; i<13; i++) {
			assertTrue(c.connectSensor("gate",i));		
		}
		for(int i =0; i<13; i++) {
			assertEquals(c.getSensor(i),"gate");		
		}
		for(int i =0; i<13; i++) {
			assertTrue(c.disconnectSensor(i));		
		}
		for(int i =0; i<13; i++) {
			assertEquals(c.getSensor(i),null);		
		}
		assertFalse(c.connectSensor("", -1));
		assertFalse(c.connectSensor("", 15));
		assertFalse(c.connectSensor(null, 3));
		assertFalse(c.disconnectSensor(-1));
		assertFalse(c.disconnectSensor(14));
		assertTrue(c.connectSensor("gate", 1));
		assertFalse(c.connectSensor("gate", 1));
	}
}
