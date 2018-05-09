
public class Channel {
	private boolean[] channel = new boolean[13]; // 0 slot left empty
	private String[] sensor = new String[13]; // 0 slot left empty

	public Channel() {
		for (int i = 0; i < channel.length; i++)
			channel[i] = false;
	}

	public boolean Toggle(int ch) {
		if (ch > 0 && ch < channel.length) {
			channel[ch] = !channel[ch];
			return channel[ch];
		}
		return false;
	}

	public boolean isChannelEnabled(int channelNumber) {
		if (channelNumber > 0 && channelNumber < channel.length) {
			return channel[channelNumber];
		}
		return false;
	}

	public boolean connectSensor(String sensortype, int chan) {
		if (sensortype == null) {
			return false;
		}
		if (chan > 0 && chan < channel.length) {
			if (sensor[chan] != null) {
				return false;
				/* can't connect because something is already there */} else {
				sensor[chan] = sensortype;
				return true;
			}
		}
		return false;
	}

	public boolean disconnectSensor(int chan) {
		if (chan > 0 && chan < channel.length) {
			if (sensor[chan] == null)
				return false;
			else {
				sensor[chan] = null;
				return true;
			}
		}
		return false;
	}
}
