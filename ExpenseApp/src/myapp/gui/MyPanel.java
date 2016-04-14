package myapp.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;


public class MyPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7873767078911234601L;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JTextField inputItem;
	private JTextField inputCost;
	private JTextField inputPersonName;
	private JButton submit;
	private JButton clear;
	private JButton cancel;
	
	public MyPanel() {
		super(new GridLayout(2, 1)); //calling super constructor to make a gridlayout, 2 rows and 1 column
		addComponents();
	}
	
	private void addComponents() {
		createTopPanel();
		createBottomPanel();
		add(topPanel);
		add(bottomPanel);
	}
	
	private void createTopPanel() {
		topPanel = new JPanel();
		inputItem = new JTextField("Item Name", 20);
		inputCost = new JTextField("Item Cost", 20);
		inputPersonName = new JTextField("Person Name", 20);
		topPanel.add(inputItem);
		topPanel.add(inputCost);
		topPanel.add(inputPersonName);
		topPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//topPanel.setPreferredSize(new Dimension(d.width, x));
	}
	
	private void createBottomPanel() {
		bottomPanel = new JPanel();
		submit = new JButton("Submit");
		clear = new JButton("Clear");
		cancel = new JButton("Cancel");
		bottomPanel.add(submit);
		bottomPanel.add(clear);
		bottomPanel.add(cancel);
		bottomPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}
	
}
