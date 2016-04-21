/**
 * 
 */
package myapp.gui;

import myapp.helper.AppHelper;
import myapp.constants.Constants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Z.
 *
 */
public final class MainWindow {

	private JFrame frame;
	private JPanel topLevelContainer;
	private JPanel parentInputContainer;
	
	private JPanel inputContainer;
	private JTextField itemName;
	private JTextField itemCost;
	private JTextField personName;
	private JButton submit;
	private JButton clear;
	private JButton exit;
	
	private JPanel inputContainer2;
	private JTextField year;
	private JButton search;
	private JButton calculate;
	private JButton deleteFile;
	private JComboBox<String> months;
	/**
	 * JPanel acts as output window, displays logs and outputs if 
	 * applicable.
	 */
	private JPanel outputContainer; 
	private JTextArea consoleArea;
	private JScrollPane scrollPane;
	
	
	/**
	 * <p>Builds the main frame window (JFrame) and calls buildToplevelContainer() to build the
	 * rest.
	 */
	public void buildGUI() {
		frame = new JFrame("Expense App");
		buildTopLevelContainer();
		frame.getContentPane().add(topLevelContainer);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,  600);
		frame.setVisible(true);
		registerListeners();
		
	}
	
	/**
	 * <p>topLevelContainer (JPanel) contains 2 JPanels, parentInputContainer and outputContainer
	 * <p>Layout used is GridBagLayout with 20% height for parentInputContainer an d80% for outputContainer.  
	 */
	private void buildTopLevelContainer() {
		topLevelContainer = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		buildParentInputContainer();
		buildOutputContainer();
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.2;
		c.weightx = 1;
		topLevelContainer.add(parentInputContainer, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0.8;
		topLevelContainer.add(outputContainer, c);
	}
	
	
	private void buildParentInputContainer() {
		parentInputContainer = new JPanel(new GridLayout(2,1));
		buildInputContainer1();
		buildInputContainer2();
		parentInputContainer.add(inputContainer);
		parentInputContainer.add(inputContainer2);
		
	}
	
	private void buildInputContainer1() {
		inputContainer = new JPanel();
		inputContainer.setBorder(BorderFactory.createTitledBorder("Add Items"));
		itemName = new JTextField("Item Name", 20);
		itemCost = new JTextField("Item Cost", 20);
		personName = new JTextField("Person Name", 20);
		submit = new JButton("Submit");
		clear = new JButton("Clear");
		exit = new JButton("Exit");
		inputContainer.add(itemName);
		inputContainer.add(itemCost);
		inputContainer.add(personName);
		inputContainer.add(submit);
		inputContainer.add(clear);
		inputContainer.add(exit);
	}
	
	private void buildInputContainer2() {
		inputContainer2 = new JPanel();
		inputContainer2.setBorder(BorderFactory.createTitledBorder("Search and Calculate"));
		year = new JTextField("2016");
		search = new JButton("Search");
		calculate = new JButton("Calculate");
		months = new JComboBox<String>();
		deleteFile = new JButton("Delete File");
		populateMonthsComboBox();
		inputContainer2.add(months);
		inputContainer2.add(year);
		inputContainer2.add(search);
		inputContainer2.add(calculate);
		inputContainer2.add(deleteFile);
	}
	
	private void populateMonthsComboBox() {
		for(int i = 0; i < 12; i++) {
			months.addItem(Constants.MONTHS[i]);
		}
	}
	
	private void buildOutputContainer() {
		outputContainer = new JPanel(new BorderLayout());
		consoleArea = new JTextArea();
		scrollPane = new JScrollPane(consoleArea);
		outputContainer.setBorder(BorderFactory.createTitledBorder("Console"));
		consoleArea.setBackground(Color.BLACK);		
		consoleArea.setForeground(Color.WHITE);
		consoleArea.setEditable(false);
		consoleArea.setText("Ready...\n");
		outputContainer.add(scrollPane);
	}
	
	private void registerListeners() {
		itemName.addFocusListener(new SelectAllHandler());
		itemCost.addFocusListener(new SelectAllHandler());
		personName.addFocusListener(new SelectAllHandler());
		year.addFocusListener(new SelectAllHandler());
		submit.addActionListener(new ButtonHandler());
		clear.addActionListener(new ButtonHandler());
		exit.addActionListener(new ButtonHandler());
		search.addActionListener(new ButtonHandler());
		calculate.addActionListener(new ButtonHandler());
		deleteFile.addActionListener(new ButtonHandler());
	}
	
	
	
	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submit) {
				//AppHelper helper = new AppHelper(personName.getText(),
				//		itemName.getText(), itemCost.getText());
				AppHelper helper = new AppHelper();
				
				if (helper.addRecord(personName.getText(),
						itemName.getText(),
						itemCost.getText(),
						consoleArea))
					consoleArea.append("Record added to database...\n");
			}
			if(e.getSource() == clear) {
				itemName.setText("");
				itemCost.setText("");
				personName.setText("");
				consoleArea.append("All items cleared...\n");
			}
			if(e.getSource() == exit) {
				
			}
			if(e.getSource() == search) {
				AppHelper helper = new AppHelper();
				helper.search(months.getSelectedItem().toString(), year.getText(), consoleArea);
			}
			if(e.getSource() == calculate) {
				AppHelper helper = new AppHelper();
				helper.calculate(months.getSelectedItem().toString(), year.getText(), consoleArea);
			}
			if(e.getSource() == deleteFile) {
				AppHelper helper = new AppHelper();
				helper.deleteFile(months.getSelectedItem().toString(), year.getText(), consoleArea);
			}
		}
		
	}
	
	
	
	
	/**
	 * Inner class to handle the event when any text box comes in focus
	 * <p>It checks which text box is in focus and then selects all the text in it
	 * so that user may overwrite.
	 * @implements {@link FocusListner} 
	 * @methods focusGained(FocusEvent) <br>
	 * focusLost(FOcusEvent)
	 */
	private class SelectAllHandler implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource() == itemName)
				itemName.selectAll();
			if(e.getSource() == itemCost)
				itemCost.selectAll();
			if(e.getSource() == personName)
				personName.selectAll();
			if(e.getSource() == year)
				year.selectAll();
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
