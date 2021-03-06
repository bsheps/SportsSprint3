import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Simulator{
	CommandsInterface ct;
	boolean simulatorOn;

	/**
	 * Constructor
	 * @throws IOException 
	 */
	public Simulator() throws IOException{
		GUI g =new GUI();
		simulatorOn = true;
		ct = g.commandInt;
		Scanner s = new Scanner(System.in);
		System.out.println("Read file from console or file (c/f): ");
		String input = s.nextLine();
		if(input.equalsIgnoreCase("f")) {
			System.out.print("Enter the file name: ");
			String fileName = s.nextLine();
			try(Scanner sc = new Scanner (new File(fileName))){
				while(sc.hasNextLine() && simulatorOn){
					generalParser(sc.nextLine().trim().split("\\s+"));
				}
			}
			catch(FileNotFoundException e){
				System.out.println("File not found!");
			}
		}
		else if(input.equalsIgnoreCase("c")){
			String uInput="";
			System.out.println("ChronoTimer simulator: powering chronoTimer on.");
			ct.POWER();
			while(!uInput.equals("Q")) {
				System.out.println("Enter Full command to be sent to parser (WITH SPACES) Q to quit:");
				String time = Time.time2formattedString(Time.getLocalTime())+ " ";
				uInput =  s.nextLine();
				System.out.println("Performing action on:"+ (time+uInput));
				if(!uInput.equals("Q")) {
					generalParser((time+uInput).trim().split("\\s+"));
				}
			}			
		}
		s.close();
	}
	/**
	 * parses input text file and executes commands
	 * @param tokens
	 * @throws IOException
	 */
	private void generalParser(String[] tokens) throws IOException {
		Time.setTime(tokens[0]);
		Time.startTime();
		switch(tokens[1]){
		case "POWER":
			ct.POWER();
			break;
		case "NEWRUN":
			ct.NEWRUN();
			break;
		case "TOG" :
			ct.TOG(Integer.parseInt(tokens[2]));
			break;
		case "TRIG":
			ct.TRIG(Integer.parseInt(tokens[2]));
			break;
		case "PRINT":
			if(tokens.length == 3)
				ct.PRINT(Integer.parseInt(tokens[2]));
			else
				ct.PRINTERPOWER();
			break;
		case "ENDRUN":
			ct.ENDRUN();
			break;
		case "DNF":
			ct.DNF();
			break;
		case "CLR":
			ct.CLR(tokens[2]);
			break;
		case "RESET":
			ct.RESET();
			break;
		case "SWAP":
			ct.SWAP();
			break;
		case "TIME":
			//tokens[2] to set time 
			ct.TIME(tokens[2]);
			break;
		case "NUM":
			ct.NUM(tokens[2]);
			break;
		case "EVENT":
			ct.EVENT(tokens[2]);;
			break;
		case "EXIT":
			simulatorOn = false;
			break;
		case "EXPORT":
			ct.EXPORT(Integer.parseInt(tokens[2]));
			break;
		case "CONN" :
			ct.CONN(tokens[2], Integer.parseInt(tokens[3]));
			break;
		case "DISC" :
			ct.DISC(Integer.parseInt(tokens[2]));
			break;
		case "START" :
			ct.START();
			break;
		case "FINISH" :
			ct.FINISH();
			break;
		}	
	}

}