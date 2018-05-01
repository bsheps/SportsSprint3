import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ParalellGroupEvent implements EventInterface {

	private Racer[] competitors = new Racer[8];
	private int numCompetitors = 0;
	private boolean raceInSession = false;
	private Queue<Racer> finished = new LinkedList<Racer>();
	private LocalTime _startTime;

	/* Add racer to the array
	 * @see EventInterface#addRacer(java.lang.String)
	 */
	@Override
	public void addRacer(String bibNumber) {
		if (numCompetitors < 8) {
			competitors[numCompetitors++] = new Racer(bibNumber);
		}
	}

	/*
	 * If the race has not been started, a trigger on channel 1 will start timing
	 * all racers in "competitors". If the race has been started, a trigger will add
	 * an end time for a racer on the specified channel, if that racer has not
	 * already finished. Each racer that finishes, is added to the finished queue in
	 * that order, and they are set to null in the competitors array.
	 * 
	 * @see EventInterface#trigger(int)
	 */
	@Override
	public void trigger(int channelNumber) {
		if (channelNumber < 1 || channelNumber > numCompetitors) {
			// bad trigger, ignore
			return;
		} else if (!raceInSession && channelNumber == 1) {
			// start all times
			_startTime = Time.getCurrentTime();
			for (int i = 0; i < numCompetitors; i++) {
				competitors[i].startRace(_startTime);
			}
		} else {
			LocalTime finishTime = Time.getCurrentTime();
			if (competitors[channelNumber - 1].getEndTime() != null) {
				competitors[channelNumber - 1].finishRace(finishTime);
				finished.add(competitors[channelNumber - 1]);
				competitors[channelNumber - 1] = null;
			}
		}
	}

	/* Move racers from array to finished queue
	 * @see EventInterface#moveAll()
	 */
	@Override
	public Queue<Racer> moveAll() {
		for (Racer racer : competitors) {
			if (racer != null) {
				finished.add(racer);
				racer = null;
			}
		}
		return null;
	}

	@Override
	public void dnf() {
		throw new UnsupportedOperationException(
				"A dnf cannot be triggered for this race type, because there is no \"next\" competitor");
	}

	@Override
	public void swap() {
		throw new UnsupportedOperationException("A swap cannot be triggered for this race type...");
	}

	/* Remove a racer from the competitors array, and the finished queue
	 * @see EventInterface#clear(java.lang.String)
	 */
	@Override
	public void clear(String bibNumber) {

		for (Racer racer : competitors) {
			if (racer.getBibNum().equals(bibNumber)) {
				racer = null;
				break;
			}
		}
		Iterator<Racer> it = finished.iterator();
		while (it.hasNext()) {
			Racer n = it.next();
			if (n._bibNum.equals(bibNumber)) {
				it.remove();
				break;
			}
		}
	}

	@Override
	public void guiDisplay() {
		GUI.queueScreen.setText("Start Time:" + Time.time2formattedString(_startTime));
		Iterator<Racer> it = finished.iterator();
		while (it.hasNext()) {
			Racer n = it.next();
			GUI.queueScreen.append("\nLast Finish\n" + n.getBibNum() + " finish time: " + Time.time2formattedString(n.getEndTime()));
		}

	}

}
