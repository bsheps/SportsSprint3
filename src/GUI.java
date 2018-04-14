import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class GUI {

	private JFrame frame;
	private ArrayList<JButton> JButtons;
	private ArrayList<JButton> keypad;
	private ArrayList<JRadioButton> RButtons;
	private JTextArea queueScreen;
	private String instruction;
	private String [] instructions = {"1 - CLR","2 - CONN","3 - DISC","4 - DNF",
			"5 - ENDRUN", "6 - IND EVENT", "7 - PARA EVENT", "8 - GRP EVENT",
			"9 - PARGRP EVENT", "10 - EXPORT","11 - FINISH","12 - NEWRUN","13 - NUM",
			"14 - PRINT","15 - RESET", "16 - TIME","17 - START","18 - SWAP"};
	private CommandsInterface commandInt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 **/
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 833, 614);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("ChronoTimer");

		frame.setResizable(false);

		JRadioButton ch_1 = new JRadioButton("");
		RButtons.add(ch_1);
		ch_1.setBounds(240, 116, 25, 25);
		frame.getContentPane().add(ch_1);

		JRadioButton ch_2 = new JRadioButton("");
		RButtons.add(ch_2);
		ch_2.setBounds(240, 223, 25, 25);
		frame.getContentPane().add(ch_2);

		JButton btnFunction = new JButton("Function");
		btnFunction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

		JRadioButton ch_3 = new JRadioButton("");
		RButtons.add(ch_3);
		ch_3.setBounds(291, 116, 25, 25);
		frame.getContentPane().add(ch_3);

		JRadioButton ch_4 = new JRadioButton("");
		RButtons.add(ch_4);
		ch_4.setBounds(291, 223, 25, 25);
		frame.getContentPane().add(ch_4);

		JRadioButton ch_5 = new JRadioButton("");
		RButtons.add(ch_5);
		ch_5.setBounds(344, 116, 25, 25);
		frame.getContentPane().add(ch_5);

		JRadioButton ch_6 = new JRadioButton("");
		RButtons.add(ch_6);
		ch_6.setBounds(344, 223, 25, 25);
		frame.getContentPane().add(ch_6);

		JRadioButton ch_7 = new JRadioButton("");
		RButtons.add(ch_7);
		ch_7.setBounds(394, 116, 25, 25);
		frame.getContentPane().add(ch_7);

		JRadioButton ch_8 = new JRadioButton("");
		RButtons.add(ch_8);
		ch_8.setBounds(394, 223, 25, 25);
		frame.getContentPane().add(ch_8);


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
		btnPrinterPower.setBounds(509, 41, 157, 25);
		frame.getContentPane().add(btnPrinterPower);

		JTextArea printScreen = new JTextArea();
		printScreen.setEditable(false);
		printScreen.setBounds(509, 82, 158, 88);
		frame.getContentPane().add(printScreen);

		JPanel panel = new JPanel();
		panel.setBounds(509, 255, 143, 136);
		frame.getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "[][][]", "[][][][]"));

		JButton number1 = new JButton("1");
		number1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instruction+="1";
			}
		});
		keypad.add(number1);
		panel.add(number1, "cell 0 0");

		JButton number2 = new JButton("2");
		number2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instruction+="2";
			}
		});
		keypad.add(number2);
		panel.add(number2, "cell 1 0,growx");

		JButton number3 = new JButton("3");
		number3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instruction+="3";
			}
		});
		keypad.add(number3);
		panel.add(number3, "cell 2 0,growx");

		JButton number4 = new JButton("4");
		number4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instruction+="4";
			}
		});
		keypad.add(number4);
		panel.add(number4, "cell 0 1");

		JButton number5 = new JButton("5");
		number5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instruction+="5";
			}
		});
		keypad.add(number5);
		panel.add(number5, "cell 1 1");

		JButton number6 = new JButton("6");
		number6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instruction+="6";
			}
		});
		keypad.add(number6);
		panel.add(number6, "cell 2 1,growx");

		JButton number7 = new JButton("7");
		number7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instruction+="7";
			}
		});
		keypad.add(number7);
		panel.add(number7, "cell 0 2");

		JButton number8 = new JButton("8");
		number8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instruction+="8";
			}
		});
		keypad.add(number8);
		panel.add(number8, "cell 1 2");

		JButton number9 = new JButton("9");
		number9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instruction+="9";
			}
		});
		keypad.add(number9);
		panel.add(number9, "cell 2 2,growx");

		JButton symb_1 = new JButton("*");
		keypad.add(symb_1);
		panel.add(symb_1, "cell 0 3");

		JButton number0 = new JButton("0");
		keypad.add(number0);
		panel.add(number0, "cell 1 3");

		JButton symb_2 = new JButton("#");
		symb_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queueScreen.setText(instruction);
				switch(instruction) {
				case "1": 
					//queueScreen.setText("Enter bib number:");
					//commandInt.CLR(bibNumber);
					break;
				case "2":
					break;
				case "3":
					break;
				case "4":
					break;
				case "5":
					break;
				case "6":
					break;
				case "7":
					break;
				case "8":
					break;
				case "9":
					break;
				case "10":
					break;
				case "11":
					break;
				case "12":
					break;
				case "13":
					break;
				case "14":
					break;
				case "15":
					break;
				case "16":
					break;
				case "17":
					break;
				case "18":
					break;
				default: queueScreen.setText("Invalid Selection");
				break;
				}
				instruction = "";
			}

		});

		keypad.add(symb_2);
		panel.add(symb_2, "cell 2 3");


		JButton tgbCH1 = new JButton("");
		JButtons.add(tgbCH1);
		tgbCH1.setBounds(240, 79, 25, 25);
		frame.getContentPane().add(tgbCH1);

		JButton tgbCH3 = new JButton("");
		JButtons.add(tgbCH3);
		tgbCH3.setBounds(291, 79, 25, 25);
		frame.getContentPane().add(tgbCH3);

		JButton tgbCH5 = new JButton("");
		JButtons.add(tgbCH5);
		tgbCH5.setBounds(344, 79, 25, 25);
		frame.getContentPane().add(tgbCH5);

		JButton tgbCH7 = new JButton("");
		JButtons.add(tgbCH7);
		tgbCH7.setBounds(394, 79, 25, 25);
		frame.getContentPane().add(tgbCH7);

		JButton tgbCH2 = new JButton("");
		JButtons.add(tgbCH2);
		tgbCH2.setBounds(240, 186, 25, 25);
		frame.getContentPane().add(tgbCH2);

		JButton tgbCH4 = new JButton("");
		JButtons.add(tgbCH4);
		tgbCH4.setBounds(291, 186, 25, 25);
		frame.getContentPane().add(tgbCH4);

		JButton tgbCH6 = new JButton("");
		JButtons.add(tgbCH6);
		tgbCH6.setBounds(344, 186, 25, 25);
		frame.getContentPane().add(tgbCH6);

		JButton tgbCH8 = new JButton("");
		JButtons.add(tgbCH8);
		tgbCH8.setBounds(394, 186, 25, 25);
		frame.getContentPane().add(tgbCH8);

		for(JButton jb : JButtons) {
			jb.setEnabled(false);
		}
		for(JRadioButton rb : RButtons) {
			rb.setEnabled(false);
		}

		for(JButton nums : keypad){
			nums.setEnabled(false);
		}
		JToggleButton tglbtnPower = new JToggleButton("Power");
		tglbtnPower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
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

	}
}
