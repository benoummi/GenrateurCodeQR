package webcam;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.github.sarxos.webcam.*;
import com.google.zxing.WriterException;

/**
 * 
 * @author SBENOUMMI
 *
 */

@SuppressWarnings("serial")
public class cam extends JFrame {

	
	Webcam webcam ; 
	JTextArea textArea ;
	WebcamPanel panel; 
	 JLabel lblData;
	BufferedImage image;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cam frame = new cam();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public cam() {
		super("Génerateur Code QR");
		setResizable(false);
		//setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 915, 495);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(640,480));
		panel = new WebcamPanel(webcam); 
		panel.setBounds(481, 11, 418, 405);
		contentPane.add(panel);
		
		JButton btnShoot = new JButton("Scan Code");
		btnShoot.setFont(new Font("Andalus", Font.PLAIN, 12));
		btnShoot.setBounds(808, 427, 91, 28);
		btnShoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screenShot();
			}

			private void screenShot() {
				// TODO Auto-generated method stub
				 image =webcam.getImage();
				 String msg=null;
				try {
					 
					ImageIO.write(image, "PNG", new File(image.hashCode()+"AMR.png"));
					msg =Scan.ReadQR(image);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 lblData.setText(msg);
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnShoot);
		
		  textArea = new JTextArea();
		textArea.setBounds(74, 53, 330, 97);
		contentPane.add(textArea);
		
		JLabel lblContenu = new JLabel("Create QR Code :");
		lblContenu.setFont(new Font("Andalus", Font.PLAIN, 16));
		lblContenu.setBounds(10, 24, 113, 20);
		contentPane.add(lblContenu);
		
		JButton btnGenerateCode = new JButton("Generate Code");
		btnGenerateCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 File qrFile = new File(showSaveFileDialog()+".PNG");
				try {
				if(	GenerateQRCode.createQRImage(qrFile,  textArea.getText().toString() ))
					JOptionPane.showMessageDialog(null, "Votre Code QR est bien Géneré ");
				} catch (WriterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		});
		btnGenerateCode.setFont(new Font("Andalus", Font.PLAIN, 12));
		btnGenerateCode.setBounds(322, 180, 113, 23);
		contentPane.add(btnGenerateCode);
		
		JLabel label = new JLabel("Data QR Code :");
		label.setFont(new Font("Andalus", Font.PLAIN, 16));
		label.setBounds(10, 327, 113, 20);
		contentPane.add(label);
		
		lblData = new JLabel("");
		lblData.setFont(new Font("Andalus", Font.PLAIN, 16));
		lblData.setBounds(74, 358, 330, 89);
		contentPane.add(lblData);
		
	 
	}
	
	private String showSaveFileDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			return fileToSave.getAbsolutePath();
		}return null;
	}
}
