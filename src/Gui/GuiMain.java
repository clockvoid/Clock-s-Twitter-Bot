package Gui;

import java.awt.*;

import javax.swing.*;

public class GuiMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LogWindow log;
	
	public String getPIN() {
		return JOptionPane.showInputDialog(this, "PINî‘çÜÇì¸óÕÇµÇƒÇ≠ÇæÇ≥Ç¢");
	}
	
	public void addText(String str) {
		log.addText(str);
	}
	
	public GuiMain() {
		setTitle("Clock's Twitter Bot - Log");
		setSize(800, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		log = new LogWindow();
		getContentPane().add(new JScrollPane(log), BorderLayout.CENTER);
		setVisible(true);
	}
	
}
