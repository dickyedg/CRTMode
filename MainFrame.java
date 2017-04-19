package AES;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

public class MainFrame extends JFrame implements ActionListener{
	
	JPanel parentPanel;
	JPanel encPanel;
	JPanel decPanel;
	JPanel mainPanel;
	
	private JTextPane title;
	private SpringLayout springLayout;
	private JTextPane subtitle;
	private JButton buttonEnc;
	private JButton buttonDec;
	private Color background;
	
	private JTextPane titleEnc;
	private JTextArea fileContainer;
	private JTextArea keyContainer;
	private JButton encryptButton;
	private SpringLayout springEncLayout;
	private JLabel fileSign;
	private JLabel keySign;
	private JLabel text;
	private JLabel text2;
	private JButton chooseFile;
	private JButton chooseKey;
	private JFileChooser fc1;
	//private int returnVal;
	File file;
	private File key;
	private File cipher;
	
	private JTextPane titleDec;
	private JTextArea fileContainerD;
	private JTextArea keyContainerD;
	private JButton decryptButton;
	private SpringLayout springDecLayout;
	private JLabel fileSignD;
	private JLabel keySignD;
	private JLabel textD;
	private JLabel text2D;
	private JButton chooseFileD;
	private JButton chooseKeyD;
	private JFileChooser fc1D;
	
	
	public MainFrame() {
		mainPanel = new JPanel(new CardLayout());
		
		parentPanel = new JPanel();
		createParentPanel(parentPanel);
	
		
		encPanel = new JPanel();
		createEncPanel(encPanel);
		
		decPanel = new JPanel();
		createDecPanel(decPanel);
		
		setupFrame();
		
	}
	
	private void setupFrame(){
		mainPanel.add(parentPanel);
		setContentPane(mainPanel);
	}
	
	public void start(){
		this.setSize(555, 365);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void createParentPanel(JPanel panel){
		background = new Color(245, 222, 179);
		title = new JTextPane();
		
		subtitle = new JTextPane();
		
		springLayout = new SpringLayout();
		
		
		buttonEnc = new JButton("Ecryption");
		buttonEnc.addActionListener(this);
		
		buttonDec = new JButton("Decryption");
		buttonDec.addActionListener(this);
		
		setupParentPanel(panel);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonEnc){
			//implement change panel to enc
			mainPanel.removeAll();
			mainPanel.add(encPanel);
			mainPanel.repaint();
			mainPanel.revalidate();
		}else if (e.getSource()==buttonDec){
			//implement change panel to dec
			mainPanel.removeAll();
			mainPanel.add(decPanel);
			mainPanel.repaint();
			mainPanel.revalidate();
		}else if (e.getSource() == chooseFile) {
			//implement to choosefile
            int returnVal = fc1.showOpenDialog(encPanel);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc1.getSelectedFile();
                fileContainer.setText(file.getName());
                //process file
            } 
        } else if(e.getSource()==encryptButton){
        	if(file==null&&key==null){
        		
        	} else {
            	//process encrypt/decrypt, popup, next page
        		try {
        			Scanner in = new Scanner(key);
        			String keyCTR = in.nextLine();
        			if(keyCTR.length()==32||keyCTR.length()==48||keyCTR.length()==64){
        				CTRnew CTR = new CTRnew(keyCTR);
        				byte[] plainByte = toByteArray(file);
        				byte[] keyByte = keyCTR.getBytes("UTF-8");
        				byte[] cipherByte = CTR.encrypt(plainByte, keyByte);
        				writeToFile(cipherByte, cipher);
        				//success
        			} else {
        				
        				//not 32/48/64
        			}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		fileContainer.setText("Drop your file here");
        		keyContainer.setText("Drop your key file here");
        		this.file = null;
        		this.key = null;
        		this.cipher = null;
        		mainPanel.removeAll();
    			mainPanel.add(parentPanel);
    			mainPanel.repaint();
    			mainPanel.revalidate();
        	}
        } else if(e.getSource() == chooseKey) {
        	//implement choosekey
            int returnVal = fc1.showOpenDialog(encPanel);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                key = fc1.getSelectedFile();
                keyContainer.setText(key.getName());
                //process file
            } 
        } else if (e.getSource() == chooseFileD) {
			//implement to choosefile
            int returnValD = fc1D.showOpenDialog(decPanel);

            if (returnValD == JFileChooser.APPROVE_OPTION) {
                cipher = fc1D.getSelectedFile();
                fileContainerD.setText(cipher.getName());
                //process file
            } 
        } else if(e.getSource() == chooseKeyD) {
        	//implement choosekey
            int returnValD = fc1D.showOpenDialog(decPanel);

            if (returnValD == JFileChooser.APPROVE_OPTION) {
                key = fc1D.getSelectedFile();
                keyContainerD.setText(key.getName());
                //process file
            } 
        } else {
        	if(cipher==null&&key==null){
        		
        	} else {
            	//process encrypt/decrypt, popup, next page
        		try {
        			Scanner in = new Scanner(key);
        			String keyCTR = in.nextLine();
        			if(keyCTR.length()==32||keyCTR.length()==48||keyCTR.length()==64){
        				CTRnew CTR = new CTRnew(keyCTR);
        				byte[] cipherByte = toByteArray(cipher);
        				byte[] keyByte = keyCTR.getBytes("UTF-8");
        				byte[] plainByte = CTR.encrypt(cipherByte, keyByte);
        				writeToFile(plainByte, file);
        				//success
        			} else {
        				//not 32/48/64
        			}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		
        		fileContainerD.setText("Drop your file here");
        		keyContainerD.setText("Drop your key file here");
        		this.file = null;
        		this.key = null;
        		this.cipher = null;
        		mainPanel.removeAll();
    			mainPanel.add(parentPanel);
    			mainPanel.repaint();
    			mainPanel.revalidate();
        	}
        }
	}
	
	
	private void setupParentPanel(JPanel panel){
		springLayout.putConstraint(SpringLayout.WEST, subtitle, 161, SpringLayout.WEST, parentPanel);
		springLayout.putConstraint(SpringLayout.NORTH, title, 10, SpringLayout.NORTH, parentPanel);
		springLayout.putConstraint(SpringLayout.WEST, title, 10, SpringLayout.WEST, parentPanel);
		springLayout.putConstraint(SpringLayout.NORTH, buttonEnc, 196, SpringLayout.NORTH, parentPanel);
		springLayout.putConstraint(SpringLayout.EAST, buttonEnc, -325, SpringLayout.EAST, parentPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, subtitle, -48, SpringLayout.NORTH, buttonEnc);
		springLayout.putConstraint(SpringLayout.NORTH, buttonDec, 0, SpringLayout.NORTH, buttonEnc);
		springLayout.putConstraint(SpringLayout.WEST, buttonDec, 96, SpringLayout.EAST, buttonEnc);
		
		panel.setLayout(springLayout);
		panel.setBackground(background);
		
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
		
		buttonDec.setBackground(new Color(205, 133, 63));
		buttonDec.setForeground(new Color(178, 34, 34));
		buttonDec.setContentAreaFilled(false);
		buttonDec.setOpaque(true);
		buttonDec.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		
		panel.add(title);
		panel.add(subtitle);
		panel.add(buttonEnc);
		panel.add(buttonDec);
	}
	
	public void createEncPanel(JPanel panel) {
		Color background2 = new Color(245, 222, 179);
		panel.setBackground(background2);
		titleEnc = new JTextPane();
		
		springEncLayout = new SpringLayout();
		
		fileContainer = new JTextArea();
		
		fileSign = new JLabel("File");
		
		keySign = new JLabel("Key");
		
		keyContainer = new JTextArea();
			
		text = new JLabel("Manually");
		text2 = new JLabel("Manually");
		
		chooseFile = new JButton("Choose File");
		chooseFile.addActionListener(this);
		chooseKey = new JButton("Choose Key");
		chooseKey.addActionListener(this);
		fc1 = new JFileChooser();
		encryptButton = new JButton("Encrypt!");
		encryptButton.addActionListener(this);
		setupEncPanel(panel);
	}
	
	
	private void setupEncPanel(JPanel panel){
		springEncLayout.putConstraint(SpringLayout.NORTH, titleEnc, 24, SpringLayout.NORTH, encPanel);
		springEncLayout.putConstraint(SpringLayout.EAST, titleEnc, -46, SpringLayout.EAST, encPanel);
		springEncLayout.putConstraint(SpringLayout.NORTH, fileContainer, 34, SpringLayout.SOUTH, titleEnc);
		springEncLayout.putConstraint(SpringLayout.WEST, fileContainer, 32, SpringLayout.WEST, encPanel);
		springEncLayout.putConstraint(SpringLayout.SOUTH, fileContainer, 147, SpringLayout.SOUTH, titleEnc);
		springEncLayout.putConstraint(SpringLayout.EAST, fileContainer, -319, SpringLayout.EAST, encPanel);
		springEncLayout.putConstraint(SpringLayout.NORTH, fileSign, 6, SpringLayout.SOUTH, titleEnc);
		springEncLayout.putConstraint(SpringLayout.WEST, fileSign, 102, SpringLayout.WEST, encPanel);
		springEncLayout.putConstraint(SpringLayout.EAST, keySign, -123, SpringLayout.EAST, encPanel);
		springEncLayout.putConstraint(SpringLayout.SOUTH, keySign, -6, SpringLayout.NORTH, keyContainer);
		springEncLayout.putConstraint(SpringLayout.NORTH, keyContainer, 0, SpringLayout.NORTH, fileContainer);
		springEncLayout.putConstraint(SpringLayout.WEST, keyContainer, -188, SpringLayout.EAST, titleEnc);
		springEncLayout.putConstraint(SpringLayout.SOUTH, keyContainer, 0, SpringLayout.SOUTH, fileContainer);
		springEncLayout.putConstraint(SpringLayout.EAST, keyContainer, 0, SpringLayout.EAST, titleEnc);
		springEncLayout.putConstraint(SpringLayout.NORTH, text2, 10, SpringLayout.SOUTH, keyContainer);
		springEncLayout.putConstraint(SpringLayout.NORTH, text, 0, SpringLayout.NORTH, text2);
		springEncLayout.putConstraint(SpringLayout.WEST, text, 6, SpringLayout.EAST, chooseFile);
		springEncLayout.putConstraint(SpringLayout.NORTH, chooseFile, 6, SpringLayout.SOUTH, fileContainer);
		springEncLayout.putConstraint(SpringLayout.WEST, chooseFile, 10, SpringLayout.WEST, fileContainer);
		springEncLayout.putConstraint(SpringLayout.WEST, text2, 6, SpringLayout.EAST, chooseKey);
		springEncLayout.putConstraint(SpringLayout.NORTH, chooseKey, 6, SpringLayout.SOUTH, keyContainer);
		springEncLayout.putConstraint(SpringLayout.WEST, chooseKey, 10, SpringLayout.WEST, keyContainer);
		springEncLayout.putConstraint(SpringLayout.WEST, encryptButton, 226, SpringLayout.WEST, encPanel);
		springEncLayout.putConstraint(SpringLayout.SOUTH, encryptButton, -38, SpringLayout.SOUTH, encPanel);
		
		panel.setLayout(springEncLayout);
		
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
		            for (File files : droppedFiles) {
		                // process files
		            	fileContainer.setText(files.getName());
		            	file = files;
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
		
		titleEnc.setForeground(new Color(255, 160, 122));
		titleEnc.setBackground(new Color(245, 222, 179));
		titleEnc.setEditable(false);
		titleEnc.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 24));
		titleEnc.setText("Choose File That You Want to Encrypt!");
		
		panel.add(encryptButton);
		panel.add(titleEnc);
		panel.add(fileContainer);
		panel.add(keyContainer);
		panel.add(fileSign);
		panel.add(keySign);
		panel.add(text);
		panel.add(text2);
		panel.add(chooseFile);
		panel.add(chooseKey);
	}
	
	public void createDecPanel(JPanel panel) {
		Color background2 = new Color(245, 222, 179);
		panel.setBackground(background2);
		titleDec = new JTextPane();
		
		springDecLayout = new SpringLayout();
		
		
		fileContainerD = new JTextArea();
		
		
		fileSignD = new JLabel("File");
		
		
		
		keySignD = new JLabel("Key");
		
		
		keyContainerD = new JTextArea();
			
		textD = new JLabel("Manually");
		text2D = new JLabel("Manually");
		
		chooseFileD = new JButton("Choose File");
		chooseFileD.addActionListener(this);
		chooseKeyD = new JButton("Choose Key");
		chooseKeyD.addActionListener(this);
		fc1D = new JFileChooser();
		decryptButton = new JButton("Decrypt!");
		decryptButton.addActionListener(this);
		setupDecPanel(panel);
	}
	
	
	private void setupDecPanel(JPanel panel){
		springDecLayout.putConstraint(SpringLayout.NORTH, titleDec, 24, SpringLayout.NORTH, decPanel);
		springDecLayout.putConstraint(SpringLayout.EAST, titleDec, -46, SpringLayout.EAST, decPanel);
		springDecLayout.putConstraint(SpringLayout.NORTH, fileContainerD, 34, SpringLayout.SOUTH, titleDec);
		springDecLayout.putConstraint(SpringLayout.WEST, fileContainerD, 32, SpringLayout.WEST, decPanel);
		springDecLayout.putConstraint(SpringLayout.SOUTH, fileContainerD, 147, SpringLayout.SOUTH, titleDec);
		springDecLayout.putConstraint(SpringLayout.EAST, fileContainerD, -319, SpringLayout.EAST, decPanel);
		springDecLayout.putConstraint(SpringLayout.NORTH, fileSignD, 6, SpringLayout.SOUTH, titleDec);
		springDecLayout.putConstraint(SpringLayout.WEST, fileSignD, 102, SpringLayout.WEST, decPanel);
		springDecLayout.putConstraint(SpringLayout.EAST, keySignD, -123, SpringLayout.EAST, decPanel);
		springDecLayout.putConstraint(SpringLayout.SOUTH, keySignD, -6, SpringLayout.NORTH, keyContainerD);
		springDecLayout.putConstraint(SpringLayout.NORTH, keyContainerD, 0, SpringLayout.NORTH, fileContainerD);
		springDecLayout.putConstraint(SpringLayout.WEST, keyContainerD, -188, SpringLayout.EAST, titleDec);
		springDecLayout.putConstraint(SpringLayout.SOUTH, keyContainerD, 0, SpringLayout.SOUTH, fileContainerD);
		springDecLayout.putConstraint(SpringLayout.EAST, keyContainerD, 0, SpringLayout.EAST, titleDec);
		springDecLayout.putConstraint(SpringLayout.NORTH, text2D, 10, SpringLayout.SOUTH, keyContainerD);
		springDecLayout.putConstraint(SpringLayout.NORTH, textD, 0, SpringLayout.NORTH, text2D);
		springDecLayout.putConstraint(SpringLayout.WEST, textD, 6, SpringLayout.EAST, chooseFileD);
		springDecLayout.putConstraint(SpringLayout.NORTH, chooseFileD, 6, SpringLayout.SOUTH, fileContainerD);
		springDecLayout.putConstraint(SpringLayout.WEST, chooseFileD, 10, SpringLayout.WEST, fileContainerD);
		springDecLayout.putConstraint(SpringLayout.WEST, text2D, 6, SpringLayout.EAST, chooseKeyD);
		springDecLayout.putConstraint(SpringLayout.NORTH, chooseKeyD, 6, SpringLayout.SOUTH, keyContainerD);
		springDecLayout.putConstraint(SpringLayout.WEST, chooseKeyD, 10, SpringLayout.WEST, keyContainerD);
		springDecLayout.putConstraint(SpringLayout.WEST, decryptButton, 226, SpringLayout.WEST, decPanel);
		springDecLayout.putConstraint(SpringLayout.SOUTH, decryptButton, -38, SpringLayout.SOUTH, decPanel);
		
		panel.setLayout(springDecLayout);
		
		fileSignD.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		
		keySignD.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		
		fileContainerD.setText("Drop your file here");
		fileContainerD.setEditable(false);
		//file drag n drop
		fileContainerD.setDropTarget(new DropTarget() {
		    public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            List<File> droppedFiles = (List<File>)
		                evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		            for (File file : droppedFiles) {
		                // process files
		            	fileContainerD.setText(file.getName());
		            	cipher = file;
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		keyContainerD.setText("Drop your key file here");
		keyContainerD.setEditable(false);
		keyContainerD.setDropTarget(new DropTarget() {
		    public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            List<File> droppedFiles = (List<File>)
		                evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		            for (File file : droppedFiles) {
		                // process files
		            	keyContainerD.setText(file.getName());
		            	key = file;
		            	
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		titleDec.setForeground(new Color(255, 160, 122));
		titleDec.setBackground(new Color(245, 222, 179));
		titleDec.setEditable(false);
		titleDec.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 24));
		titleDec.setText("Choose File That You Want to Decrypt!");
		
		panel.add(decryptButton);
		panel.add(titleDec);
		panel.add(fileContainerD);
		panel.add(keyContainerD);
		panel.add(fileSignD);
		panel.add(keySignD);
		panel.add(textD);
		panel.add(text2D);
		panel.add(chooseFileD);
		panel.add(chooseKeyD);
	}
	
	public static void writeToFile(byte[] data, File file) throws IOException {
        FileOutputStream stream = new FileOutputStream(file);
        
        stream.write(data);
        stream.close();
    }
	
	 public static byte[] toByteArray(File file) throws IOException {
	        FileInputStream stream = null;
	        byte[] byteResult = new byte[(int) file.length()];
	        try{
	        	stream = new FileInputStream(file);
	        	stream.read(byteResult);
	        	stream.close();
	        } catch (IOException io){
	        	io.printStackTrace();
	        }
	       	return byteResult;
	    }
	
}

