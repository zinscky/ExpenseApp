/**
 * 
 */
package myapp.gui;
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
	private JPanel inputContainer;
	/**
	 * JPanel acts as output window, displays logs and outputs if 
	 * applicable.
	 */
	private JPanel outputContainer; 
	private JTextField itemName;
	private JTextField itemCost;
	private JTextField personName;
	private JButton submit;
	private JButton clear;
	private JButton cancel;
	
	
	
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
	
	private void buildTopLevelContainer() {
		topLevelContainer = new JPanel(new GridLayout(2,1));
		buildInputContainer();
		buildOutputContainer();
		topLevelContainer.add(inputContainer);
		topLevelContainer.add(outputContainer);
	}
	
	private void buildInputContainer() {
		inputContainer = new JPanel();
		itemName = new JTextField("Item Name", 20);
		itemCost = new JTextField("Item Cost", 20);
		personName = new JTextField("Person Name", 20);
		submit = new JButton("Submit");
		clear = new JButton("Clear");
		cancel = new JButton("Cancel");
		inputContainer.add(itemName);
		inputContainer.add(itemCost);
		inputContainer.add(personName);
		inputContainer.add(submit);
		inputContainer.add(clear);
		inputContainer.add(cancel);
		
	}
	
	private void buildOutputContainer() {
		outputContainer = new JPanel();
	}
	
	private void registerListeners() {
		itemName.addFocusListener(new SelectAllHandler());
		itemCost.addFocusListener(new SelectAllHandler());
		personName.addFocusListener(new SelectAllHandler());
		submit.addActionListener(new SubmitHandler());
	}
	
	
	
	private class SubmitHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showConfirmDialog(null, "SUBMIT", "Event Notification SUBMIT", JOptionPane.INFORMATION_MESSAGE);
			
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
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
