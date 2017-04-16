package AES;

import java.awt.Color;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

public class InputPanel extends JPanel {
	private JTextPane title;
	private JTextArea fileContainer;
	private JTextArea keyContainer;
	private JButton encryptButton;
	private SpringLayout springLayout;
	private JLabel fileSign;
	private JLabel keySign;
	private JLabel text;
	private JLabel text2;
	private JButton chooseFile;
	private JButton chooseKey;
	private JFileChooser fc1;
	private int returnVal;
	private File file;
	private File key;
	
	
	public InputPanel() {
		Color background = new Color(245, 222, 179);
		setBackground(background);
		title = new JTextPane();
		
		springLayout = new SpringLayout();
		
		fileContainer = new JTextArea();
		
		fileSign = new JLabel("File");

		keySign = new JLabel("Key");
		
		keyContainer = new JTextArea();
		
		text = new JLabel("Manually");
		text2 = new JLabel("Manually");
		chooseFile = new JButton("Choose File");
		chooseFile.addActionListener((ActionListener) this);
		chooseKey = new JButton("Choose Key");
		chooseKey.addActionListener((ActionListener) this);
		fc1 = new JFileChooser();
		encryptButton = new JButton("Encrypt!");
		encryptButton.addActionListener((ActionListener) this);
		setupPanel();
	}
	
	private void actionPerformed(ActionEvent e) {
		 if (e.getSource() == chooseFile) {
	            int returnVal = fc1.showOpenDialog(InputPanel.this);

	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                file = fc1.getSelectedFile();
	                //process file
	            } 
	        } else if(e.getSource()==encryptButton){
	        	//process encrypt, popup, next page
	        } else  {
	            int returnVal = fc1.showOpenDialog(InputPanel.this);

	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                key = fc1.getSelectedFile();
	                //process file
	            } 
	        }
	}
	
	private void setupPanel(){
		//springLayout Constraint
		springLayout.putConstraint(SpringLayout.NORTH, title, 24, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, title, 64, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, keyContainer, 0, SpringLayout.NORTH, fileContainer);
		springLayout.putConstraint(SpringLayout.WEST, keyContainer, 71, SpringLayout.EAST, fileContainer);
		springLayout.putConstraint(SpringLayout.SOUTH, keyContainer, 114, SpringLayout.NORTH, fileContainer);
		springLayout.putConstraint(SpringLayout.EAST, keyContainer, 263, SpringLayout.EAST, fileContainer);
		springLayout.putConstraint(SpringLayout.WEST, fileContainer, 47, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, fileContainer, 234, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, encryptButton, 222, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, encryptButton, -36, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, fileContainer, -70, SpringLayout.NORTH, encryptButton);
		springLayout.putConstraint(SpringLayout.SOUTH, fileSign, -255, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, fileContainer, 12, SpringLayout.SOUTH, fileSign);
		springLayout.putConstraint(SpringLayout.WEST, fileSign, 119, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, fileSign, 166, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, keySign, 0, SpringLayout.NORTH, fileSign);
		springLayout.putConstraint(SpringLayout.EAST, keySign, -140, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, text2, 23, SpringLayout.SOUTH, keyContainer);
		springLayout.putConstraint(SpringLayout.NORTH, text, 0, SpringLayout.NORTH, text2);
		springLayout.putConstraint(SpringLayout.NORTH, chooseFile, 19, SpringLayout.SOUTH, fileContainer);
		springLayout.putConstraint(SpringLayout.EAST, chooseFile, -402, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, text, 6, SpringLayout.EAST, chooseFile);
		springLayout.putConstraint(SpringLayout.NORTH, chooseKey, 19, SpringLayout.SOUTH, keyContainer);
		springLayout.putConstraint(SpringLayout.EAST, chooseKey, -142, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, text2, 6, SpringLayout.EAST, chooseKey);
		setLayout(springLayout);
		
		fileSign.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		
		keySign.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		
		fileContainer.setText("Drop your file here");
		fileContainer.setEditable(false);
		//file drag n drop
		fileContainer.setDropTarget(new DropTarget() {
		    public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            List<File> droppedFiles = (List<File>)
		                evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		            for (File file : droppedFiles) {
		                // process files
		            	fileContainer.setText(file.getName());
		            	file = file;
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		keyContainer.setText("Drop your key file here");
		keyContainer.setEditable(false);
		keyContainer.setDropTarget(new DropTarget() {
		    public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            List<File> droppedFiles = (List<File>)
		                evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		            for (File file : droppedFiles) {
		                // process files
		            	keyContainer.setText(file.getName());
		            	key = file;
		            	
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		title.setForeground(new Color(255, 160, 122));
		title.setBackground(new Color(245, 222, 179));
		title.setEditable(false);
		title.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 24));
		title.setText("Choose File That You Want to Encrypt!");
		
		this.add(encryptButton);
		this.add(title);
		this.add(fileContainer);
		this.add(keyContainer);
		this.add(fileSign);
		this.add(keySign);
		this.add(text);
		this.add(text2);
		this.add(chooseFile);
		this.add(chooseKey);
	}

}
