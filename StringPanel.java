package AES;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.SpringLayout;

public class StringPanel extends JPanel {
	private JTextPane title;
	private SpringLayout springLayout;
	private JTextPane subtitle;
	private JButton buttonEnc;
	private JButton buttonDec;
	private Color background;
	
	public StringPanel() {
		background = new Color(245, 222, 179);
		title = new JTextPane();
		
		subtitle = new JTextPane();
		
		springLayout = new SpringLayout();
		
		buttonEnc = new JButton("Ecryption");
		
		buttonDec = new JButton("Decryption");
		
		setupLayout();
		setupPanel();
	}
	
	private void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonEnc){
			//JPanel panel = InputPanel;
		}else{
			
		}
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, title, 32, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, title, -10, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, subtitle, 170, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, subtitle, -47, SpringLayout.NORTH, buttonEnc);
		springLayout.putConstraint(SpringLayout.NORTH, buttonEnc, 209, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonEnc, -98, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, buttonEnc, 101, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, buttonEnc, 218, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, buttonDec, 0, SpringLayout.NORTH, buttonEnc);
		springLayout.putConstraint(SpringLayout.WEST, buttonDec, 90, SpringLayout.EAST, buttonEnc);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonDec, 0, SpringLayout.SOUTH, buttonEnc);
		springLayout.putConstraint(SpringLayout.EAST, buttonDec, 207, SpringLayout.EAST, buttonEnc);
		
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.setBackground(background);
		
		subtitle.setForeground(new Color(255, 127, 80));
		subtitle.setEditable(false);
		subtitle.setBackground(background);
		subtitle.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		subtitle.setText("Encrypt or Decrypt?");
		
		title.setForeground(new Color(255, 160, 122));
		title.setBackground(new Color(245, 222, 179));
		title.setEditable(false);
		title.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 24));
		title.setText("Welcome to CTR Mode Encryption - Decryption!");
		
		buttonEnc.setForeground(new Color(178, 34, 34));
		buttonEnc.setBackground(new Color(205, 133, 63));
		buttonEnc.setContentAreaFilled(false);
		buttonEnc.setOpaque(true);
		buttonEnc.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		buttonEnc.addActionListener((ActionListener) this);
		
		buttonDec.setBackground(new Color(205, 133, 63));
		buttonDec.setForeground(new Color(178, 34, 34));
		buttonDec.setContentAreaFilled(false);
		buttonDec.setOpaque(true);
		buttonDec.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		
		this.add(title);
		this.add(subtitle);
		this.add(buttonEnc);
		this.add(buttonDec);
	}
}
