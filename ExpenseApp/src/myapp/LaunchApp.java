package myapp;

import myapp.gui.MainWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * This the launcher class containing the main method and
 * main GUI (JFrame)
 */
public class LaunchApp {
	public static void main(String[] args) {
		
		
		/**
		 * Setting up system look and feel
		 * @throws UnsupportedLookAndFeelException
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		MainWindow mainWindow = new MainWindow();
		mainWindow.buildGUI();
		
		//MyPanel mainPanel = new MyPanel();
		//JFrame mainWindow = new JFrame("Expense Report");
		//mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainWindow.getContentPane().add(mainPanel);
		//mainWindow.setSize(800, 600);
		//mainWindow.setVisible(true);
	}
}
