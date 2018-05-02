import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Queue;

import com.google.gson.Gson;
/**
 * This class handles the administrative parts of 
 * instantiating a race. It prevents multiple races
 * from happening simultaneously. Handles communications
 * with the printer/results. Also acts as the connection 
 * between channels and events.
 * @author BS
 *
 */
public class ChronoTimer implements CommandsInterface {
	Channel chan;
	String _eventName = "IND";
	EventInterface _event;
	boolean _powerOn= false;
	boolean _raceInSession=false;
	Printer _print;	
	ArrayList<Queue<Racer>> _storageUnit;
	ArrayList<String> _storageUnitEventName;

	public void CLR(String bibNumber) {
		if(_event== null || !_raceInSession)_print.printThis(Time.getCurrentTimeString()+"CLR FAILED - no event selected or race not insession");
		else 
			_event.clear(bibNumber);
		
	}

	public void CONN(String sensorType, int channel) {
		_print.printThis(Time.getCurrentTimeString()+" " +sensorType+ (chan.connectSensor(sensorType, channel)? " successfully connected.": " connection unsucessful"));

	}

	public void DISC(int channel2disconnect) {
		_print.printThis(Time.getCurrentTimeString()+" Sensor at channel " +
				channel2disconnect + (chan.disconnectSensor(channel2disconnect)? " disconnected successfully.": " disconnect failed."));
	}

	public void DNF() {
		if(_event== null || !_raceInSession) _print.printThis(Time.getCurrentTimeString()+"DNF FAILED - no event selected or race not insession");
		else {
		_event.dnf();
		_print.printThis(Time.getCurrentTimeString()+ " Setting DNF for next trigger");
		}
	}

	public void ENDRUN() {
		if(!_raceInSession) _print.printThis(Time.getCurrentTimeString() + " ENDRUN FAILED - NO RACE IN SESSION");
		else {
			_raceInSession = false;
			Queue<Racer> tmp = _event.moveAll();
			_storageUnit.add(tmp);
			_print.printThis(Time.getCurrentTimeString()+ " Ending run");
		}
	}

	public void EVENT(String eventName) {
		if(_raceInSession) _print.printThis(Time.getCurrentTimeString()+ " ERROR: End current event before setting a new event.");
		else if(eventName.equals("IND")|| eventName.equals("PARIND") || eventName.equals("GRP") || eventName.equals("PARGRP"))
		{
			_eventName = eventName;
			_print.printThis(Time.getCurrentTimeString()+" Setting event to " + eventName);
		}
		else
			_print.printThis("ERROR: INVALID EVENT NAME");
	}

	public void EXPORT(int runNumber) {
		if(runNumber==0 || runNumber>_storageUnit.size()) _print.printThis("EXPORT METHOD ERROR: Invalid run number; out of bounds exception");
		else {
			Gson exportProxy = new Gson();
			String exportFilename = "RUN"+runNumber+".txt";
			try {
				@SuppressWarnings("resource")
				PrintWriter exportFile = new PrintWriter(exportFilename);
				exportFile.println(exportProxy.toJson(_storageUnit.get(runNumber-1)));
				exportFile.flush();
				_print.printThis(Time.getCurrentTimeString()+ " Exporting run "+ runNumber);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	public void FINISH() {
		if(_event== null || !_raceInSession) _print.printThis(Time.getCurrentTimeString()+"FINISH FAILED - no event selected or race not insession");
		else {
		_event.trigger(2);
		_print.printThis(Time.getCurrentTimeString()+ " Triggered channel: 2");
		}
	}

	public void NEWRUN() {
		if(_raceInSession) _print.printThis(Time.getCurrentTimeString()+ " ERROR RACE IN SESSION: End current run before starting a NEWRUN.");
		else{
			_raceInSession = true;
			_storageUnitEventName.add(_eventName);
			if(_eventName.equals("IND"))
				_event = new IndividualEvent();
			else if(_eventName.equals("PARIND"))
				_event = new ParaIndEvent();
			else if (_eventName.equals("GRP"))
				_event = new GroupEvent();
			else if (_eventName.equals("PARGRP"))
				_event = new GroupEvent();
			
			_print.printThis(Time.getCurrentTimeString()+ " starting " + _eventName);
			
		}
	}

	public void NUM(String bibNumber) {
		if(_event == null || !_raceInSession) _print.printThis(Time.getCurrentTimeString()+ "NUM FAILED - no event selected or race not in session");
		else {
			_event.addRacer(bibNumber);
			_print.printThis(Time.getCurrentTimeString()+ " racer created with bib: "+bibNumber);
			_event.guiDisplay();

		}
	}


	public void POWER() {
		if(!_powerOn) {
			_powerOn = true;
			chan = new Channel();
			try {
				_print = new Printer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_storageUnit = new ArrayList<Queue<Racer>>();
			_storageUnitEventName = new ArrayList<String>();
			_raceInSession = false;
			Time.startTime();
			_print.printThis(Time.getCurrentTimeString()+ " powering unit on");
		}
		else if(_powerOn){ 
			_powerOn = false;
			_raceInSession = false;
			_print.printThis(Time.getCurrentTimeString()+" POWERING OFF- PENDING ITEMS:");
			_print.shutDownPrinter();
		}	
	}

	
	public void PRINT(int runNumber) {
		if(runNumber>_storageUnit.size()) _print.printThis("PRINT METHOD ERROR: Invalid run number; out of bounds exception");
		else {
			_print.printThis("Printing " + _storageUnitEventName.get(runNumber-1) + runNumber);
			Queue<Racer> temp = _storageUnit.get(runNumber-1);
			for(Racer t : temp) {
				_print.printThis(t._bibNum + ": " + t.results());
			}
		}
	}

	public void PRINTERPOWER() {
		_print.PRINTERPOWER();
		_print.printThis(Time.getCurrentTimeString() + " PRINTER POWER BUTTON");
	}

	
	public void RESET() {
		chan = new Channel();
		try {
			_print = new Printer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_eventName = "IND";
		_storageUnit = new ArrayList<Queue<Racer>>();
		_storageUnitEventName = new ArrayList<String>();
		_raceInSession = false;
		Time.startTime();
		_print.printThis(Time.getCurrentTimeString()+"SYSTEM RESET");
	}

	public void START() {
		if(_event == null || !_raceInSession) _print.printThis(Time.getCurrentTimeString()+ " START FAILED - No race started");
		else {
			_event.trigger(1);
			_print.printThis(Time.getCurrentTimeString()+ " START");
		}
	}

	public void SWAP() {
		if(!_raceInSession) _print.printThis(Time.getCurrentTimeString() + "No event in session");
		else if(_storageUnitEventName.get(_storageUnitEventName.size()-1).equals("IND")&&_event != null && _raceInSession) { 
			_event.swap();
			_print.printThis(Time.getCurrentTimeString() + " Swapping racers");
		}
	}
	
	public void TIME(String time) {
		Time.setTime(time);
		Time.startTime();
		_print.printThis(Time.getCurrentTimeString() + " TIME SET");
	}

	
	public void TOG(int channelNumber) {
		_print.printThis(Time.getCurrentTimeString()+" Channel "+channelNumber+" was " + (chan.Toggle(channelNumber)? "Enabled" : "Disabled"));
	}


	//could we pass on the event type because this is only for indrun 
	
	public void TRIG(int channelNumber) {
		_print.printThis(Time.getCurrentTimeString()+ " Trigger channel "+channelNumber );
		if(chan.isChannelEnabled(channelNumber) && _raceInSession && _event != null && _raceInSession) {
			_event.trigger(channelNumber);	
			_event.guiDisplay();
		}
		// else do nothing, channel is disabled or a race is not in session
	}
	



	
	
}