import java.io.IOException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Gui / cmd?");
		if(sc.nextLine().toLowerCase().equals("gui")){
			new GUI();
			}
		else {
			try {
				Simulator sim = new Simulator();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.exit(0);
	}
}
