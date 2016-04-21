package myapp;

import myapp.gui.MainWindow;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * This the launcher class containing the main method and
 * main GUI (JFrame)
 */
public class LaunchApp {
	
	
	
	/**
	 * There is no use for instantiating this class, so 
	 * I made it private.
	 */
	private LaunchApp() {}
		
	public static void main(String[] args) {
		setupSystemLookAndFeel();
		MainWindow mainWindow = new MainWindow();
		mainWindow.buildGUI();
	}
	/**
	 * Setting up system look and feel
	 * @throws UnsupportedLookAndFeelException
	 */
	private static void setupSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
