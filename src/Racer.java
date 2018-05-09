import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Racer implements Comparable<Object> {
	public static final DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
	public String _bibNum;
	public String _name;
	public LocalTime _startTime;
	public LocalTime _endTime;

	public Racer() {
		this(null, null);
	}

	public Racer(String bibNum) {
		this(bibNum, null);
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
//			For Debugging
//			System.out.println("start: " + Time.time2formattedString(_startTime));
//			System.out.println("end: " + Time.time2formattedString(_endTime));
//			System.out.println("Output: " + Time.time2formattedString(
//					LocalTime.ofNanoOfDay(Duration.between(_startTime, _endTime).abs().toNanos())));
			return Time.time2formattedString(LocalTime.ofNanoOfDay(_endTime.toNanoOfDay() - _startTime.toNanoOfDay()));
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
			if (this == other) {
				return 0;
			}
			return results().compareTo(other.results());
		}
		return 0;
	}
}