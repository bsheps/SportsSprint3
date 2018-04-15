import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author bjf
 * 
 *         This class uses only a LinkedList to hold racers and finished,
 *         because racers are needed to be accessed in the middle of the lot.
 * 
 *         This class implements the methods in EventInterface, as well as some
 *         extras. The extra methods are:
 * 
 *         public void addRacer() : A no-argument way to add a racer public void
 *         setRacerNumber(String name) : Set the name of a finished racer
 *
 */
public class GroupEvent implements EventInterface {

	private LinkedList<Racer> racers, finished;
	private LocalTime _startTime;
	private boolean raceInSession;
	private int namedRacers;

	/**
	 * Simple constructor initializes instance variables.
	 */
	public GroupEvent() {
		racers = new LinkedList<Racer>();
		finished = new LinkedList<Racer>();
		raceInSession = false;
		namedRacers = 0;
	}

	/**
	 * Allow adding a racer when no name/bibNumber is specified.
	 */
	public void addRacer() {
		addRacer(null);
	}

	/*
	 * Add a racer when a name/bibNumber is specified. If this method is called with
	 * a null String (like in the addRacer() method with no arguments), this method
	 * will auto-assign a racer name based on how many racers exist already.
	 * 
	 * A racer may not be added when a race is in progress, or an
	 * IllegalStateException will be thrown.
	 * 
	 * @see EventInterface#addRacer
	 */
	@Override
	public void addRacer(String bibNumber) {
		if (raceInSession) {
			throw new IllegalStateException("Cannot add a new Racer when a race is already in progress.");
		}
		if (bibNumber == null) {
			bibNumber = "Racer #" + racers.size() + 1;
		}
		racers.add(new Racer(bibNumber));
	}

	/*
	 * Only channels 1 and 2 should be triggered. Channel 1 marks the beginning of a
	 * race, and channel will finish one racer every time it is called, as long as
	 * there are still unfinished racers.
	 * 
	 * Channel 1 may only be triggered once, or an IllegalStateException will be
	 * thrown. If this channel was accidentally triggered, the resetEvent() method
	 * may be called, as long as no racers have finished.
	 * 
	 * Channel 2 being triggered will finish a racer. If this is called when no
	 * racers are remaining, a NoSuchElementException will propagate. If this is
	 * called when a race has not begun, an IllegalStateException will be thrown.
	 * 
	 * @see EventInterface#trigger(int)
	 */
	@Override
	public void trigger(int channelNumber) {
		switch (channelNumber) {
		case (1):
			if (raceInSession) {
				throw new IllegalStateException("A GRP Event race has already begun.");
			}
			raceInSession = true;
			_startTime = Time.getCurrentTime();
			for (Racer racer : racers) {
				racer.startRace(_startTime);
			}
			break;
		case (2):
			LocalTime temp = Time.getCurrentTime();
			if (!raceInSession) {
				throw new IllegalStateException("A GRP Event race has not yet begun.");
			}
			racers.peek().finishRace(temp);
			finished.add(racers.remove());
			if (racers.isEmpty()) {
				raceInSession = false;
			}
			break;
		default:
			throw new IllegalArgumentException("This channel is not set up to be used for this event type.");
		}
	}

	/*
	 * All unfinished racers will have no finish time, indicating a DNF
	 * 
	 * @see EventInterface#moveAll()
	 */
	@Override
	public Queue<Racer> moveAll() {
		while (racers.size() > 0) {
			finished.add(racers.remove());
		}
		return finished;
	}

	/*
	 * A racer is added to the finished without no end time. This indicates a DNF.
	 * 
	 * @see EventInterface#dnf()
	 */
	@Override
	public void dnf() {
		finished.add(racers.remove());
	}

	/*
	 * This method was intended for use by IND race types only. Calling this method
	 * will throw an UnsupportedOperationException.
	 * 
	 * @see EventInterface#swap()
	 */
	@Override
	public void swap() {
		throw new UnsupportedOperationException("SWAP is used to exchange the next two competitiors to finish, in IND race types only. It is undefined for GRP race types.");
	}

	/**
	 * Sets the name of a finished racer. Each time this method is called, the name
	 * of the next finished racer is named. (eg. Calling this method three times,
	 * will rename the first three finished racers.)
	 * 
	 * If this method is called, and there is not a finished racer that hasn't
	 * already been renamed with this method, an IllegalStateException will be
	 * thrown. (eg. 3 racers have finished and have been renamed with this method
	 * already, but this method is called a fourth time)
	 * 
	 * If this method is called with a null String, an IllegalArgumentException is
	 * thrown.
	 * 
	 * @param name
	 *            - The name of the next finished racer.
	 */
	public void setRacerNumber(String name) {
		if (namedRacers >= finished.size()) {
			throw new IllegalStateException("A finished racer is not available to be named.");
		}
		if (name == null) {
			throw new IllegalArgumentException("Cannot set a name with a null value.");
		}
		finished.get(namedRacers++).setBibNum(name);
	}

}
