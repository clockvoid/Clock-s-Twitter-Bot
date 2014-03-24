package Gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LogWindow extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private String text;
	
	public LogWindow() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		setLayout(new BorderLayout());
		add(textArea, BorderLayout.CENTER);
		text = "";
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	public void addText(String str) {
		text += str + "\n";
		textArea.setText(text);
	}
	
}
