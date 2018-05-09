/**
 * @author BS
 * Channel class is a way to keep track of which channels are 
 * enabled/disabled and what sensors are attached.
 */
public class Channel {
	private boolean[] channel = new boolean[13]; // 0 slot left empty
	private String[] sensor = new String[13]; // 0 slot left empty

	public Channel() {
		for (int i = 0; i < channel.length; i++)
			channel[i] = false;
	}

	public boolean Toggle(int ch) {
		if (ch < 0 || ch >= channel.length) return false;
		channel[ch] = !channel[ch];
		return channel[ch];	
	}

	public boolean isChannelEnabled(int channelNumber) {
		if (channelNumber < 0 || channelNumber >= channel.length)return false; 
		return channel[channelNumber];
	}

	public boolean connectSensor(String sensortype, int chan) {
		if (chan < 0 || chan >= channel.length)return false; 
		if (sensortype == null||sensor[chan] != null) return false;
		
		sensor[chan] = sensortype;
		return true;
	}
	public boolean disconnectSensor(int chan) {
		if (chan < 0 || chan >= channel.length|| sensor[chan]==null)return false; 
		sensor[chan] = null;
		return true;
	}
	/**For Testing purposes only */
	public String getSensor(int chn) {
		return sensor[chn];
	}	
}
