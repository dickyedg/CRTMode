package AES;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class MainFrame extends JFrame {
	
	private StringPanel currentPanel;
	private InputPanel panel;
	
	public MainFrame() {
		
		currentPanel = new StringPanel();
		panel = new InputPanel();
		
		setupFrame();
		
	}
	
	private void setupFrame(){
		
		this.setContentPane(panel);
		
	}
	
	public void start(){
		this.setSize(555, 365);
		this.setVisible(true);
	}

}
