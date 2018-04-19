import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class GUI {
	String userEntered = "";
	private JButton number0, number1, number2, number3, number4, number5, number6, number7, number8, number9, astrix, poundSign;
	JRadioButton ch_1, ch_2, ch_3,ch_4,ch_5,ch_6,ch_7,ch_8;
	private String firstFunction;
	JToggleButton tglbtnPower;
	JButton tgbCH1, tgbCH2, tgbCH3,tgbCH4,tgbCH5,tgbCH6,tgbCH7,tgbCH8;
	private JFrame frame;
	private ArrayList<JButton> JButtons;
	private ArrayList<JButton> keypad;
	private ArrayList<JRadioButton> RButtons;
	public JTextArea queueScreen;
	private String instruction;
	private String [] instructions = {"1 - CLR","2 - CONN","3 - DISC","4 - DNF",
			"5 - ENDRUN", "6 - IND EVENT", "7 - PARA EVENT", "8 - GRP EVENT",
			"9 - PARGRP EVENT", "10 - EXPORT","11 - FINISH","12 - NEWRUN","13 - NUM",
			"14 - PRINT","15 - RESET", "16 - TIME","17 - START"};
	private CommandsInterface commandInt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new GUI();
	}

	/**
	 * Create the application.
	 */
	public GUI(){
		commandInt = new ChronoTimer();
		JButtons = new ArrayList<JButton>();
		RButtons = new ArrayList<JRadioButton>();
		keypad = new ArrayList<JButton>();
		queueScreen = new JTextArea();
		
		instruction = "";

		frame = new JFrame();
		frame.setBounds(100, 100, 833, 614);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("ChronoTimer");

		frame.setResizable(false);

		JButton btnFunction = new JButton("Function");
		btnFunction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queueScreen.setText("");
				for(JButton nums : keypad){
					nums.setEnabled(true);
				}
				//queueScreen.append("Type the number of the instruction on the keypad \nto the right, then press the (#)\n");
				for(String str: instructions) {
					queueScreen.append(str + "\n");
				}

			}
		});
		JButtons.add(btnFunction);
		btnFunction.setBounds(12, 325, 94, 25);
		frame.getContentPane().add(btnFunction);

		JButton btnSwap = new JButton("Swap");
		JButtons.add(btnSwap);
		btnSwap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandInt.SWAP();
			}
		});
		btnSwap.setBounds(12, 366, 94, 25);
		frame.getContentPane().add(btnSwap);

		toggleButtonHandler toghandler = new toggleButtonHandler();
		
		ch_1 = new JRadioButton("");
		RButtons.add(ch_1);
		ch_1.setBounds(240, 116, 25, 25);
		frame.getContentPane().add(ch_1);
		ch_1.addActionListener(toghandler);
		
		ch_2 = new JRadioButton("");
		RButtons.add(ch_2);
		ch_2.setBounds(240, 223, 25, 25);
		frame.getContentPane().add(ch_2);
		ch_2.addActionListener(toghandler);
		
		ch_3 = new JRadioButton("");
		RButtons.add(ch_3);
		ch_3.setBounds(291, 116, 25, 25);
		frame.getContentPane().add(ch_3);
		ch_3.addActionListener(toghandler);

		ch_4 = new JRadioButton("");
		RButtons.add(ch_4);
		ch_4.setBounds(291, 223, 25, 25);
		frame.getContentPane().add(ch_4);
		ch_4.addActionListener(toghandler);

		ch_5 = new JRadioButton("");
		RButtons.add(ch_5);
		ch_5.setBounds(344, 116, 25, 25);
		frame.getContentPane().add(ch_5);
		ch_5.addActionListener(toghandler);

		ch_6 = new JRadioButton("");
		RButtons.add(ch_6);
		ch_6.setBounds(344, 223, 25, 25);
		frame.getContentPane().add(ch_6);
		ch_6.addActionListener(toghandler);

		ch_7 = new JRadioButton("");
		RButtons.add(ch_7);
		ch_7.setBounds(394, 116, 25, 25);
		frame.getContentPane().add(ch_7);
		ch_7.addActionListener(toghandler);

		ch_8 = new JRadioButton("");
		RButtons.add(ch_8);
		ch_8.setBounds(394, 223, 25, 25);
		frame.getContentPane().add(ch_8);
		ch_8.addActionListener(toghandler);

		queueScreen.setEditable(false);
		queueScreen.setBounds(185, 255, 278, 300);
		frame.getContentPane().add(queueScreen);

		JButton btnPrinterPower = new JButton("Printer Power");
		JButtons.add(btnPrinterPower);
		btnPrinterPower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandInt.PRINTERPOWER();
			}
		});
		btnPrinterPower.setBounds(495, 41, 171, 25);
		frame.getContentPane().add(btnPrinterPower);

		JPanel panel = new JPanel();
		panel.setBounds(509, 255, 143, 136);
		frame.getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "[][][]", "[][][][]"));

		keypadHandler keyhandle = new keypadHandler();
		
		number1 = new JButton("1");
		number1.addActionListener(keyhandle);
		keypad.add(number1);
		panel.add(number1, "cell 0 0");

		number2 = new JButton("2");
		number2.addActionListener(keyhandle);
		keypad.add(number2);
		panel.add(number2, "cell 1 0,growx");

		number3 = new JButton("3");
		number3.addActionListener(keyhandle);
		keypad.add(number3);
		panel.add(number3, "cell 2 0,growx");

		number4 = new JButton("4");
		number4.addActionListener(keyhandle);
		keypad.add(number4);
		panel.add(number4, "cell 0 1");

		number5 = new JButton("5");
		number5.addActionListener(keyhandle);
		keypad.add(number5);
		panel.add(number5, "cell 1 1");

		number6 = new JButton("6");
		number6.addActionListener(keyhandle);
		keypad.add(number6);
		panel.add(number6, "cell 2 1,growx");

		number7 = new JButton("7");
		number7.addActionListener(keyhandle);
		keypad.add(number7);
		panel.add(number7, "cell 0 2");

		number8 = new JButton("8");
		number8.addActionListener(keyhandle);
		keypad.add(number8);
		panel.add(number8, "cell 1 2");

		number9 = new JButton("9");
		number9.addActionListener(keyhandle);
		keypad.add(number9);
		panel.add(number9, "cell 2 2,growx");

		astrix = new JButton("*");
		keypad.add(astrix);
		panel.add(astrix, "cell 0 3");

		number0 = new JButton("0");
		number0.addActionListener(keyhandle);
		keypad.add(number0);
		panel.add(number0, "cell 1 3");
		
		poundSign = new JButton("#");
		poundSign.addActionListener(keyhandle);
		keypad.add(poundSign);
		panel.add(poundSign, "cell 2 3");
		
		triggerButtonHandler trighandle = new triggerButtonHandler();
		tgbCH1 = new JButton("");
		JButtons.add(tgbCH1);
		tgbCH1.setBounds(240, 79, 25, 25);
		frame.getContentPane().add(tgbCH1);
		tgbCH1.addActionListener(trighandle);

		tgbCH3 = new JButton("");
		JButtons.add(tgbCH3);
		tgbCH3.setBounds(291, 79, 25, 25);
		frame.getContentPane().add(tgbCH3);
		tgbCH3.addActionListener(trighandle);

		tgbCH5 = new JButton("");
		JButtons.add(tgbCH5);
		tgbCH5.setBounds(344, 79, 25, 25);
		frame.getContentPane().add(tgbCH5);
		tgbCH5.addActionListener(trighandle);

		tgbCH7 = new JButton("");
		JButtons.add(tgbCH7);
		tgbCH7.setBounds(394, 79, 25, 25);
		frame.getContentPane().add(tgbCH7);
		tgbCH7.addActionListener(trighandle);

		tgbCH2 = new JButton("");
		JButtons.add(tgbCH2);
		tgbCH2.setBounds(240, 186, 25, 25);
		frame.getContentPane().add(tgbCH2);
		tgbCH2.addActionListener(trighandle);
		
		tgbCH4 = new JButton("");
		JButtons.add(tgbCH4);
		tgbCH4.setBounds(291, 186, 25, 25);
		frame.getContentPane().add(tgbCH4);
		tgbCH4.addActionListener(trighandle);

		tgbCH6 = new JButton("");
		JButtons.add(tgbCH6);
		tgbCH6.setBounds(344, 186, 25, 25);
		frame.getContentPane().add(tgbCH6);
		tgbCH6.addActionListener(trighandle);

		tgbCH8 = new JButton("");
		JButtons.add(tgbCH8);
		tgbCH8.setBounds(394, 186, 25, 25);
		frame.getContentPane().add(tgbCH8);
		tgbCH8.addActionListener(trighandle);

		for(JButton jb : JButtons) {
			jb.setEnabled(false);
		}
		for(JRadioButton rb : RButtons) {
			rb.setEnabled(false);
		}

		for(JButton nums : keypad){
			nums.setEnabled(false);
		}
		powerButtonHandler powerhandler = new powerButtonHandler();
		tglbtnPower = new JToggleButton("Power");
		tglbtnPower.addActionListener(powerhandler);
		tglbtnPower.setBounds(12, 24, 94, 29);
		frame.getContentPane().add(tglbtnPower);

		JLabel lblNewLabel = new JLabel("CHRONOTMER 1009");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(208, 0, 231, 41);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblStart = new JLabel("Start");
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStart.setBounds(187, 82, 38, 20);
		frame.getContentPane().add(lblStart);

		JLabel lblFinish = new JLabel("Finish");
		lblFinish.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFinish.setBounds(185, 186, 46, 20);
		frame.getContentPane().add(lblFinish);

		JLabel lblEnabledisable = new JLabel("Enable/Disable");
		lblEnabledisable.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnabledisable.setBounds(122, 116, 113, 20);
		frame.getContentPane().add(lblEnabledisable);

		JLabel label = new JLabel("Enable/Disable");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(122, 223, 107, 20);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("  1");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(240, 55, 25, 25);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("  3");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(291, 55, 25, 25);
		frame.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("  5");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(344, 55, 25, 25);
		frame.getContentPane().add(label_3);

		JLabel label_4 = new JLabel("  7");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(396, 55, 25, 25);
		frame.getContentPane().add(label_4);

		JLabel label_5 = new JLabel("  2");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(240, 163, 25, 25);
		frame.getContentPane().add(label_5);

		JLabel label_6 = new JLabel("  4");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(291, 163, 25, 25);
		frame.getContentPane().add(label_6);

		JLabel label_7 = new JLabel("  6");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_7.setBounds(344, 163, 25, 25);
		frame.getContentPane().add(label_7);

		JLabel label_8 = new JLabel(" 8");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_8.setBounds(396, 163, 25, 25);
		frame.getContentPane().add(label_8);
		
		
		JTextArea printScreen = new JTextArea();
		printScreen.setWrapStyleWord(true);
		printScreen.setLineWrap(true);
		printScreen.setEditable(false);
		JScrollPane scroll = new JScrollPane(printScreen);
		scroll.setBounds(495, 75, 171, 136);
		frame.getContentPane().add(scroll);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);		
		
		frame.setVisible(true);

	}
	private class toggleButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== ch_1) commandInt.TOG(1);
			else if(e.getSource()== ch_2) commandInt.TOG(2);
			else if(e.getSource()== ch_3) commandInt.TOG(3);
			else if(e.getSource()== ch_4) commandInt.TOG(4);
			else if(e.getSource()== ch_5) commandInt.TOG(5);
			else if(e.getSource()== ch_6) commandInt.TOG(6);
			else if(e.getSource()== ch_7) commandInt.TOG(7);
			else if(e.getSource()== ch_8) commandInt.TOG(8);
			
		}
		
	}
	private class triggerButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== tgbCH1) commandInt.TRIG(1);
			else if(e.getSource()== tgbCH2) commandInt.TRIG(2);
			else if(e.getSource()== tgbCH3) commandInt.TRIG(3);
			else if(e.getSource()== tgbCH4) commandInt.TRIG(4);
			else if(e.getSource()== tgbCH5) commandInt.TRIG(5);
			else if(e.getSource()== tgbCH6) commandInt.TRIG(6);
			else if(e.getSource()== tgbCH7) commandInt.TRIG(7);
			else if(e.getSource()== tgbCH8) commandInt.TRIG(8);
		}
		
	}
	private class powerButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(tglbtnPower.isSelected()) {
				commandInt.POWER();
				for(JButton jb : JButtons) {
					jb.setEnabled(true);
				}
				for(JRadioButton rb : RButtons) {
					rb.setEnabled(true);
				}
				
				for(JButton nums : keypad){
					nums.setEnabled(false);
				}
			}
			else if(!tglbtnPower.isSelected()) {
				commandInt.POWER();
				for(JButton jb : JButtons) {
					jb.setEnabled(false);
				}
				for(JRadioButton rb : RButtons) {
					rb.setEnabled(false);
				}
				for(JButton nums : keypad){
					nums.setEnabled(false);
				}
				queueScreen.setText("");
			}
			
		}
		
	}
	private class keypadHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//
			if(e.getSource() == number0)userEntered +="0";
			else if(e.getSource()==number1)userEntered += "1";
			else if(e.getSource()==number2)userEntered += "2";
			else if(e.getSource()==number3)userEntered += "3";
			else if(e.getSource()==number4)userEntered += "4";
			else if(e.getSource()==number5)userEntered += "5";
			else if(e.getSource()==number6)userEntered += "6";
			else if(e.getSource()==number7)userEntered += "7";
			else if(e.getSource()==number8)userEntered += "8";
			else if(e.getSource()==number9)userEntered += "9";
			else if(e.getSource()==astrix) {/*DO NOTHING*/}
			else if(e.getSource() == poundSign) {
				if(firstFunction==null&&(userEntered.equals("1") || userEntered.equals("2")||userEntered.equals("3")||
						userEntered.equals("10")||userEntered.equals("13")||userEntered.equals("14"))) 
				{
					firstFunction = userEntered;
					queueScreen.setText("Enter number");;
				}
				else {
					if(firstFunction == null) {
						functionController(userEntered, null);
						queueScreen.setText("");
						for(String str: instructions) {
							queueScreen.append(str + "\n");
						}
					}
					else {
						functionController(firstFunction, userEntered);
						firstFunction = null;
						queueScreen.setText("");
						for(String str: instructions) {
							queueScreen.append(str + "\n");
						}
					}
						
				}
				userEntered = "";
			}
		}
		
	}
	private void functionController(String instruction1, String instruction2) {
		//System.out.printf("inst1 = %s, inst2 = %s\n", instruction1, instruction2); //for debugging
		switch(instruction1) {
		case "1": 
			commandInt.CLR(instruction2);
			break;
		case "2":
			commandInt.CONN(JOptionPane.showInputDialog(frame, "Enter sensor type:",null), 
					Integer.parseInt(instruction2));
			break;
		case "3":
			commandInt.DISC(Integer.parseInt(instruction2));
			break;
		case "4":
			commandInt.DNF();
			break;
		case "5":
			commandInt.ENDRUN();
			break;
		case "6":
			commandInt.EVENT("IND");
			break;
		case "7":
			commandInt.EVENT("PARIND");
			break;
		case "8":
			commandInt.EVENT("GRP");
			break;
		case "9":
			commandInt.EVENT("PARGRP");
			break;
		case "10":
			commandInt.EXPORT(Integer.parseInt(instruction2));
			break;
		case "11":
			commandInt.FINISH();
			break;
		case "12":
			commandInt.NEWRUN();
			break;
		case "13":
			commandInt.NUM(instruction2);
			break;
		case "14":
			commandInt.PRINT(Integer.parseInt(instruction2));
			break;
		case "15":
			commandInt.RESET();
			break;
		case "16":
			commandInt.TIME(JOptionPane.showInputDialog(frame, "Enter time:",null));
			break;
		case "17":
			commandInt.START();
			break;
		default:
			System.out.println(instruction1+" is invalid.");
			JOptionPane.showMessageDialog(null, instruction1+" is invalid.");
			break;
		}
	}
}
