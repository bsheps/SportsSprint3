import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

/** 
 * @author faassad
 * channels12 is start, channels24 is for finish 
 */

public class ParaIndEvent implements EventInterface{
	Queue<Racer> channels12= (Queue<Racer>) new LinkedList<Racer>(),
			finishers= (Queue<Racer>) new LinkedList<Racer>(),
			channels34 = (Queue<Racer>) new LinkedList<Racer>(),
			waitingToRace = (Queue<Racer>) new LinkedList<Racer>(); 
	boolean dnfracer=false;

	public void addRacer(String name) {
		waitingToRace.add(new Racer(name));
	}

	/**
	 * @param channelNumber
	 * @param Time.getCurrentTime()
	 */
	public void trigger(int channelNumber) {		
			if(channelNumber==1) {
				if(waitingToRace.size()==0) 
					channels12.add(new Racer("noName", Time.getCurrentTime()));
				else {
					Racer racer = waitingToRace.remove();
					racer.startRace(Time.getCurrentTime());
					channels12.add(racer);
				}
			}
			else if(channelNumber==2) {
				if(channels12.size() == 0) {/*nobody in queue*/}
				else if(dnfracer) {
					finishers.add(channels12.remove());
					dnfracer = false;
				}
				else {
					Racer racer = channels12.remove();
					racer.finishRace(Time.getCurrentTime());
					finishers.add(racer);
				}
			}
			else if(channelNumber==3) {
				if(waitingToRace.size()==0) 
					channels34.add(new Racer("noName", Time.getCurrentTime()));
				else {
					Racer racer = waitingToRace.remove();
					racer.startRace(Time.getCurrentTime());
					channels34.add(racer);
				}
			}
			else if(channelNumber==4) {
				if(channels34.size() == 0) {/*nobody in queue*/}
				else if(dnfracer) {
					finishers.add(channels34.remove());
					dnfracer = false;
				}
				else {
					Racer racer = channels34.remove();
					racer.finishRace(Time.getCurrentTime());
					finishers.add(racer);
				}
			}
	}

	public void endEvent(boolean endRace) {
		if(endRace) {
			while(channels12.size()!=0)
				finishers.add(channels12.remove());
			while(channels34.size()!=0)
				finishers.add(channels34.remove());
		}
	}
	
	public Queue<Racer> moveAll() {
		finishers.addAll(channels12);
		finishers.addAll(channels34);
		finishers.addAll(waitingToRace);
		return finishers;
	}

	
	public void dnf() {
		// TODO Auto-generated method stub
		
	}

	
	public void swap() {/* should never get here; DO NOTHING*/}

	@Override
	public void clear(String bibNumber) {
		Iterator<Racer> it = waitingToRace.iterator();
		while(it.hasNext()) {
			Racer n = it.next();
			if(n._bibNum.equals(bibNumber)) { it.remove(); return;}
		}
		it = channels12.iterator();
		while(it.hasNext()) {
			Racer n = it.next();
			if(n._bibNum.equals(bibNumber)) { it.remove(); return;}
		}
		it = channels34.iterator();
		while(it.hasNext()) {
			Racer n = it.next();
			if(n._bibNum.equals(bibNumber)) { it.remove(); return;}
		}
		it = finishers.iterator();
		while(it.hasNext()) {
			Racer n = it.next();
			if(n._bibNum.equals(bibNumber)) { it.remove(); return;}
		}
	}

	@Override
	public void guiDisplay() {
		GUI.queueScreen.setText("Next to start\n");
		GUI.queueScreen.append(waitingToRace.peek()._bibNum+"\nIn The Race (Start Time):\n");
		Iterator<Racer> it = channels12.iterator();
		
		while(it.hasNext()) {
			Racer n = it.next();
			GUI.queueScreen.append(n._bibNum+ " start: "+ Time.time2formattedString(n._startTime)+"\n");
		}
		it = channels34.iterator();
		while(it.hasNext()) {
			Racer n = it.next();
			GUI.queueScreen.append(n._bibNum+ " start: "+ Time.time2formattedString(n._startTime)+"\n");
		}
		it = finishers.iterator();
		int cnt =0;
		while(it.hasNext()&&cnt <2) {
			++cnt;
			Racer n = it.next();
			GUI.queueScreen.append(n._bibNum+ " result: "+ n.results()+"\n");
		}
	}

}
