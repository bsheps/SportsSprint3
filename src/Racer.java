import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Racer implements Comparable<Object> {
	public static final DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss.SS");
	public String _bibNum;
	public String _name;
	public LocalTime _startTime;
	public LocalTime _endTime;

	public Racer(String bibNum) {
		_bibNum = bibNum;
	}

	public Racer(String bibNum, LocalTime start) {
		_bibNum = bibNum;
		_startTime = start;
	}

	public void setBibNum(String bibNum) {
		_bibNum = bibNum;
	}

	public void setName(String name) {
		_name = name;
	}

	public void startRace(LocalTime time) {
		_startTime = time;
	}

	public void finishRace(LocalTime time) {
		_endTime = time;
	}

	public String getBibNum() {
		return _bibNum;
	}

	public String getName() {
		return _name;
	}

	public LocalTime getStartTime() {
		return _startTime;
	}

	public LocalTime getEndTime() {
		return _endTime;
	}

	public String results() {
		if (_startTime == null) {
			return "CANCELLED";
		} else if (_endTime == null) {
			return "DNF";
		} else {
			return LocalTime.ofNanoOfDay(_endTime.toNanoOfDay() - _startTime.toNanoOfDay()).format(formatTime);
		}
	}

	@Override
	public String toString() {
		return _bibNum + " : " + _name + " : " + results();
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Racer) {
			Racer other = (Racer) o;
			if (_endTime != null) {
				return results().compareTo(other.results());
			} else {
				return 1;
			}
		}
		return 0;
	}

}