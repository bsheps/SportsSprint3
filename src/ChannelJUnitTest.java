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
	void testToggle() {
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
}
