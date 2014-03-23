package Gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class GuiMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private String text;
	
	public String getPIN() {
		return JOptionPane.showInputDialog(this, "PINî‘çÜÇì¸óÕÇµÇƒÇ≠ÇæÇ≥Ç¢");
	}
	
	public void addText(String str) {
		text += str + "\n";
		textArea.setText(text);
	}
	
	private void setTextArea() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	public GuiMain() {
		setTitle("Clock's Twitter Bot - Log");
		setSize(800, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTextArea();
		setVisible(true);
		text = "";
	}

}
