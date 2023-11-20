package adressbuchWithUI;

import java.awt.EventQueue;
import java.awt.TextArea;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class AdressbuchWithUImain {
	
	static data data = new data();
	private static String TextAreaText = "NULL";
	private static int CurrentIndex = 0;
	private JFrame frame;
	private JTextField textField;
	private static ArrayList<String> listData = new ArrayList<String>();
	//public static JList<String> list = new JList<String>(listData.toArray(new String[listData.size()]));
	public static JList<String> list = new JList<String>();
	private static JTextArea textArea2 = new JTextArea(TextAreaText);
	private JTable table;
	private JTextField Name;
	private JTextField Strasse;
	private JTextField Tele;
	private JTextField Ort;
	private JTextField PLZ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		File file = new File("save.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		readFile("save.txt", file, data);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdressbuchWithUImain window = new AdressbuchWithUImain();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		
		listData = getAdressIndex();
		list.setListData(listData.toArray(new String[listData.size()]));
	
		/*while(true) {
			
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
	}

	
	/**
	 * Create the application.
	 */
	public AdressbuchWithUImain() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */ 
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JInternalFrame internalFrame2 = new JInternalFrame("New Contact");
		
		internalFrame2.setBounds(43, 31, 324, 205);
		frame.getContentPane().add(internalFrame2);
		internalFrame2.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Done");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame2.setVisible(false);
				CreateAdress(Name.getText(), Strasse.getText(), Ort.getText(), PLZ.getText(), Tele.getText());
				writeFile("save.txt");
				listData = getAdressIndex();
				list.setListData(listData.toArray(new String[listData.size()]));
				
			}
		});
		btnNewButton_1.setBounds(196, 152, 112, 23);
		internalFrame2.getContentPane().add(btnNewButton_1);
		
		Name = new JTextField();
		Name.setBounds(79, 11, 219, 20);
		internalFrame2.getContentPane().add(Name);
		Name.setColumns(10);
		
		Strasse = new JTextField();
		Strasse.setColumns(10);
		Strasse.setBounds(79, 42, 219, 20);
		internalFrame2.getContentPane().add(Strasse);
		
		Tele = new JTextField();
		Tele.setColumns(10);
		Tele.setBounds(79, 73, 219, 20);
		internalFrame2.getContentPane().add(Tele);
		
		Ort = new JTextField();
		Ort.setColumns(10);
		Ort.setBounds(79, 104, 86, 20);
		internalFrame2.getContentPane().add(Ort);
		
		PLZ = new JTextField();
		PLZ.setColumns(10);
		PLZ.setBounds(231, 104, 67, 20);
		internalFrame2.getContentPane().add(PLZ);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(23, 14, 46, 14);
		internalFrame2.getContentPane().add(lblNewLabel);
		
		JLabel lblStrasse_1 = new JLabel("Strasse:");
		lblStrasse_1.setBounds(23, 45, 46, 14);
		internalFrame2.getContentPane().add(lblStrasse_1);
		
		JLabel lblStrasse = new JLabel("Telefon:");
		lblStrasse.setBounds(23, 76, 46, 14);
		internalFrame2.getContentPane().add(lblStrasse);
		
		JLabel lblOrt = new JLabel("Ort:");
		lblOrt.setBounds(23, 107, 46, 14);
		internalFrame2.getContentPane().add(lblOrt);
		
		JLabel lblNewLabel_3_1 = new JLabel("PLZ:");
		lblNewLabel_3_1.setBounds(175, 107, 46, 14);
		internalFrame2.getContentPane().add(lblNewLabel_3_1);
		
		internalFrame2.setVisible(false);
		textField = new JTextField();
		textField.setBounds(0, 0, 308, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				CurrentIndex = list.getSelectedIndex();
				textArea2.setText(AdressLookUp(CurrentIndex));
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(0, 21, 168, 240);
		frame.getContentPane().add(list);
		
		
		textArea2.setBounds(174, 21, 260, 240);
		frame.getContentPane().add(textArea2);
		
		
		JButton btnNewButton = new JButton("New Contact");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame2.setVisible(true);
			}
		});
		btnNewButton.setBounds(318, -1, 116, 23);
		frame.getContentPane().add(btnNewButton);
		
	}
	
	static ArrayList<String> getAdressIndex() {
		ArrayList<String> tmpList = new ArrayList<String>();
		
		for (int i = 0; i < data.getLength(); i++) {
			tmpList.add(data.getAdressData(i, 0));
		}
		return tmpList;
	}
	
	static String AdressLookUp(int index) {
		return "Name: " + data.getAdressData(index, 0) + "\n" + "Strasse: " + data.getAdressData(index, 1) + "\n" + "Ort: " + data.getAdressData(index, 2) + " " + data.getAdressData(index, 3) + "\n" + "Telefonnummer: " + data.getAdressData(index, 4);
		//System.out.println("Name: " + data.getAdressData(index, 0));
		//System.out.println("Strasse: " + data.getAdressData(index, 1));
		//System.out.println("Ort: " + data.getAdressData(index, 2) + " " + data.getAdressData(index, 3));
		//System.out.println("Telefonnummer: " + data.getAdressData(index, 4));
	}
	
	static void CreateAdress(String Name, String Street, String Ort, String PLZ, String Tele) {
		int length = data.createAdress();
		data.modifyAdress(length, 0, Name);
		data.modifyAdress(length, 1, Street);
		data.modifyAdress(length, 2, Ort);
		data.modifyAdress(length, 3, PLZ);
		data.modifyAdress(length, 4, Tele);
		
	}

	
	static void readFile(String FileName, File file, data Data) {
		LinkedList<String> inputData = new LinkedList<>();
		
		Scanner input;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		while (input.hasNextLine()) {
	        inputData.addLast(input.nextLine());
	      }
		parseFile(inputData, Data);
		input.close();
	}
	
	static void writeFile(String FileName) {
		
		PrintWriter output;
		try {
			 output = new PrintWriter(FileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		
		for (int i = 0; i < data.getLength(); i++) {
			for (int t = 0; t < 5; t++) {
				output.println(data.getAdressData(i, t));
			}
		}
		
		output.close();
	}
	
	static void parseFile(LinkedList<String> DataList, data data) {
		int currentAdress = 0;
		for (int i = 0; i < (DataList.size() / 5); i++) {
			int currentAdressIndex = data.createAdress();
			for (int t = 0; t < 5; t++) {
				data.modifyAdress(currentAdressIndex, t, DataList.get(currentAdress));
				currentAdress++;
			}
		}
		
		return;
	}
	
	static void search(String tmpName) {
		int searchResult = data.search(0, tmpName);
		if (searchResult == -1) {
			System.out.println("Name " + tmpName + " konnte nicht gefunden werden.");
		} else {
			System.out.println("Der Index von dem Namen  " + tmpName + " ist " + searchResult + ".");
		}
		return;
	}
}
