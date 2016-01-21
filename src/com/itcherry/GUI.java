/**
 * Клас, що забезпечує вивід графічного інтерфейсу, 
 * для легшого користування користувачу
 * 
 * @author Andrey Chernysh
 * @version 1.0 17/12/2015
 */
package com.itcherry;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GUI{
	private JFrame frame;
	private JTextField wordEncryptorTextField;
	private JPasswordField keyEncryptorTextField;
	private JTextField encryptedWordTextField;
	private JTextField wordDecryptorTextField;
	private JPasswordField keyDecryptorTextField;
	private JTextField decryptedWordTextField;
	Encryptor encryptor;
	Decryptor decryptor;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	GUI() throws HeadlessException, IOException{
		frame = new  JFrame("RC2 Encryption");
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		
		frame.setSize(1024,700);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setIconImage(ImageIO.read(new File("res/icon.png")));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{407, 407, 79, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel BackgroundImageLabel = new JLabel("");
		BackgroundImageLabel.setIcon(new ImageIcon("C:\\Users\\Dron\\Desktop\\Study\\Java SE\\ProtectionOfInformation\\res\\crypto-key.jpg"));
		GridBagConstraints gbc_BackgroundImageLabel = new GridBagConstraints();
		gbc_BackgroundImageLabel.gridheight = 2;
		gbc_BackgroundImageLabel.gridwidth = 18;
		gbc_BackgroundImageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_BackgroundImageLabel.gridx = 0;
		gbc_BackgroundImageLabel.gridy = 0;
		frame.getContentPane().add(BackgroundImageLabel, gbc_BackgroundImageLabel);
		
		JPanel encryptorPanel = new JPanel();
		encryptorPanel.setBackground(new Color(0, 0, 0));
		encryptorPanel.setBorder(new LineBorder(Color.GRAY, 5));
		GridBagConstraints gbc_encryptorPanel = new GridBagConstraints();
		gbc_encryptorPanel.ipady = 10;
		gbc_encryptorPanel.gridwidth = 17;
		gbc_encryptorPanel.insets = new Insets(0, 0, 5, 0);
		gbc_encryptorPanel.fill = GridBagConstraints.BOTH;
		gbc_encryptorPanel.gridx = 18;
		gbc_encryptorPanel.gridy = 0;
		frame.getContentPane().add(encryptorPanel, gbc_encryptorPanel);
		GridBagLayout gbl_encryptorPanel = new GridBagLayout();
		gbl_encryptorPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_encryptorPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_encryptorPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_encryptorPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		encryptorPanel.setLayout(gbl_encryptorPanel);
		
		JLabel encryptorLabel = new JLabel("Encryptor :");
		encryptorLabel.setForeground(new Color(65, 105, 225));
		encryptorLabel.setFont(new Font("Tiger Expert", Font.BOLD, 25));
		GridBagConstraints gbc_encryptorLabel = new GridBagConstraints();
		gbc_encryptorLabel.gridwidth = 3;
		gbc_encryptorLabel.insets = new Insets(8, 0, 5, 5);
		gbc_encryptorLabel.gridx = 0;
		gbc_encryptorLabel.gridy = 1;
		encryptorPanel.add(encryptorLabel, gbc_encryptorLabel);
		
		JLabel wordEncryptorLabel = new JLabel("Word : ");
		wordEncryptorLabel.setForeground(new Color(65, 105, 225));
		wordEncryptorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		wordEncryptorLabel.setFont(new Font("Mangal", Font.ITALIC, 14));
		GridBagConstraints gbc_wordEncryptorLabel = new GridBagConstraints();
		gbc_wordEncryptorLabel.gridwidth = 3;
		gbc_wordEncryptorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_wordEncryptorLabel.anchor = GridBagConstraints.EAST;
		gbc_wordEncryptorLabel.gridx = 0;
		gbc_wordEncryptorLabel.gridy = 2;
		encryptorPanel.add(wordEncryptorLabel, gbc_wordEncryptorLabel);
		
		wordEncryptorTextField = new JTextField();
		wordEncryptorTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_wordEncryptorTextField = new GridBagConstraints();
		gbc_wordEncryptorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_wordEncryptorTextField.insets = new Insets(0, 0, 5, 0);
		gbc_wordEncryptorTextField.ipadx = 99;
		gbc_wordEncryptorTextField.gridx = 3;
		gbc_wordEncryptorTextField.gridy = 2;
		encryptorPanel.add(wordEncryptorTextField, gbc_wordEncryptorTextField);
		wordEncryptorTextField.setColumns(11);
		
		JLabel keyEncryptorLabel = new JLabel("Key : ");
		keyEncryptorLabel.setForeground(new Color(65, 105, 225));
		keyEncryptorLabel.setFont(new Font("Mangal", Font.PLAIN, 14));
		keyEncryptorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_keyEncryptorLabel = new GridBagConstraints();
		gbc_keyEncryptorLabel.anchor = GridBagConstraints.EAST;
		gbc_keyEncryptorLabel.gridwidth = 3;
		gbc_keyEncryptorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_keyEncryptorLabel.gridx = 0;
		gbc_keyEncryptorLabel.gridy = 4;
		encryptorPanel.add(keyEncryptorLabel, gbc_keyEncryptorLabel);
		
		keyEncryptorTextField = new JPasswordField();
		keyEncryptorTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_keyEncryptorTextField = new GridBagConstraints();
		gbc_keyEncryptorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_keyEncryptorTextField.insets = new Insets(0, 0, 5, 0);
		gbc_keyEncryptorTextField.ipadx = 99;
		gbc_keyEncryptorTextField.gridx = 3;
		gbc_keyEncryptorTextField.gridy = 4;
		encryptorPanel.add(keyEncryptorTextField, gbc_keyEncryptorTextField);
		keyEncryptorTextField.setColumns(10);
		
		JButton encryptButton = new JButton("Encrypt");
		encryptButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		encryptButton.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_encryptButton = new GridBagConstraints();
		gbc_encryptButton.insets = new Insets(9, 0, 5, 0);
		gbc_encryptButton.gridx = 3;
		gbc_encryptButton.gridy = 5;
		encryptButton.addActionListener(new encryptListener());
		encryptorPanel.add(encryptButton, gbc_encryptButton);
		
		
		JLabel encryptedWordLabel = new JLabel("Encrypted word : ");
		encryptedWordLabel.setForeground(new Color(65, 105, 225));
		encryptedWordLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		GridBagConstraints gbc_encryptedWordLabel = new GridBagConstraints();
		gbc_encryptedWordLabel.gridheight = 2;
		gbc_encryptedWordLabel.gridwidth = 4;
		gbc_encryptedWordLabel.insets = new Insets(10, 0, 5, 0);
		gbc_encryptedWordLabel.gridx = 0;
		gbc_encryptedWordLabel.gridy = 6;
		encryptorPanel.add(encryptedWordLabel, gbc_encryptedWordLabel);
		
		encryptedWordTextField = new JTextField();
		encryptedWordTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		encryptedWordTextField.setHorizontalAlignment(SwingConstants.CENTER);
		encryptedWordTextField.setEditable(false);
		GridBagConstraints gbc_encryptedWordTextField = new GridBagConstraints();
		gbc_encryptedWordTextField.gridheight = 2;
		gbc_encryptedWordTextField.insets = new Insets(6, 0, 5, 0);
		gbc_encryptedWordTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_encryptedWordTextField.gridwidth = 4;
		gbc_encryptedWordTextField.gridx = 0;
		gbc_encryptedWordTextField.gridy = 8;
		encryptorPanel.add(encryptedWordTextField, gbc_encryptedWordTextField);
		encryptedWordTextField.setColumns(10);
		
		JButton copyEncryptorButton = new JButton("Copy to clipboard");
		copyEncryptorButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_copyEncryptorButton = new GridBagConstraints();
		gbc_copyEncryptorButton.insets = new Insets(30, 0, 5, 0);
		gbc_copyEncryptorButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_copyEncryptorButton.gridx = 3;
		gbc_copyEncryptorButton.gridy = 10;
		copyEncryptorButton.addActionListener(new copyEncryptedListener());
		encryptorPanel.add(copyEncryptorButton, gbc_copyEncryptorButton);
		
		JPanel decryptorPanel = new JPanel();
		decryptorPanel.setBackground(new Color(0, 0, 0));
		decryptorPanel.setBorder(new LineBorder(Color.GRAY, 5));
		GridBagConstraints gbc_decryptorPanel = new GridBagConstraints();
		gbc_decryptorPanel.insets = new Insets(0, 0, 5, 0);
		gbc_decryptorPanel.ipady = 10;
		gbc_decryptorPanel.fill = GridBagConstraints.BOTH;
		gbc_decryptorPanel.ipadx = 99;
		gbc_decryptorPanel.gridwidth = 17;
		gbc_decryptorPanel.gridx = 18;
		gbc_decryptorPanel.gridy = 1;
		frame.getContentPane().add(decryptorPanel, gbc_decryptorPanel);
		GridBagLayout gbl_decryptorPanel = new GridBagLayout();
		gbl_decryptorPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_decryptorPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_decryptorPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_decryptorPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		decryptorPanel.setLayout(gbl_decryptorPanel);
		
		JLabel decryptorLabel = new JLabel("Decryptor :");
		decryptorLabel.setForeground(new Color(65, 105, 225));
		decryptorLabel.setFont(new Font("Tiger Expert", Font.BOLD, 25));
		GridBagConstraints gbc_decryptorLabel = new GridBagConstraints();
		gbc_decryptorLabel.gridwidth = 3;
		gbc_decryptorLabel.insets = new Insets(8, 0, 5, 5);
		gbc_decryptorLabel.gridx = 0;
		gbc_decryptorLabel.gridy = 1;
		decryptorPanel.add(decryptorLabel, gbc_decryptorLabel);
		
		JLabel wordDecryptorLabel = new JLabel("Word : ");
		wordDecryptorLabel.setForeground(new Color(65, 105, 225));
		wordDecryptorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		wordDecryptorLabel.setFont(new Font("Mangal", Font.ITALIC, 14));
		GridBagConstraints gbc_wordDecryptorLabel = new GridBagConstraints();
		gbc_wordDecryptorLabel.anchor = GridBagConstraints.EAST;
		gbc_wordDecryptorLabel.gridwidth = 3;
		gbc_wordDecryptorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_wordDecryptorLabel.gridx = 0;
		gbc_wordDecryptorLabel.gridy = 2;
		decryptorPanel.add(wordDecryptorLabel, gbc_wordDecryptorLabel);
		
		wordDecryptorTextField = new JTextField();
		wordDecryptorTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		wordDecryptorTextField.setColumns(11);
		GridBagConstraints gbc_wordDecryptorTextField = new GridBagConstraints();
		gbc_wordDecryptorTextField.ipadx = 99;
		gbc_wordDecryptorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_wordDecryptorTextField.insets = new Insets(0, 0, 5, 0);
		gbc_wordDecryptorTextField.gridx = 3;
		gbc_wordDecryptorTextField.gridy = 2;
		decryptorPanel.add(wordDecryptorTextField, gbc_wordDecryptorTextField);
		
		JLabel keyDecryptorLabel = new JLabel("Key : ");
		keyDecryptorLabel.setForeground(new Color(65, 105, 225));
		keyDecryptorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		keyDecryptorLabel.setFont(new Font("Mangal", Font.PLAIN, 14));
		GridBagConstraints gbc_keyDecryptorLabel = new GridBagConstraints();
		gbc_keyDecryptorLabel.anchor = GridBagConstraints.EAST;
		gbc_keyDecryptorLabel.gridwidth = 3;
		gbc_keyDecryptorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_keyDecryptorLabel.gridx = 0;
		gbc_keyDecryptorLabel.gridy = 4;
		decryptorPanel.add(keyDecryptorLabel, gbc_keyDecryptorLabel);
		
		keyDecryptorTextField = new JPasswordField();
		keyDecryptorTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		keyDecryptorTextField.setColumns(10);
		GridBagConstraints gbc_keyDecryptorTextField = new GridBagConstraints();
		gbc_keyDecryptorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_keyDecryptorTextField.ipadx = 99;
		gbc_keyDecryptorTextField.insets = new Insets(0, 0, 5, 0);
		gbc_keyDecryptorTextField.gridx = 3;
		gbc_keyDecryptorTextField.gridy = 4;
		decryptorPanel.add(keyDecryptorTextField, gbc_keyDecryptorTextField);
		
		JButton decryptButton = new JButton("Decrypt");
		decryptButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		decryptButton.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_decryptButton = new GridBagConstraints();
		gbc_decryptButton.insets = new Insets(9, 0, 5, 0);
		gbc_decryptButton.gridx = 3;
		gbc_decryptButton.gridy = 5;
		decryptButton.addActionListener(new decryptListener());
		decryptorPanel.add(decryptButton, gbc_decryptButton);
		
		JLabel decryptedWordLabel = new JLabel("Decrypted word : ");
		decryptedWordLabel.setForeground(new Color(65, 105, 225));
		decryptedWordLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		GridBagConstraints gbc_decryptedWordLabel = new GridBagConstraints();
		gbc_decryptedWordLabel.gridheight = 2;
		gbc_decryptedWordLabel.gridwidth = 4;
		gbc_decryptedWordLabel.insets = new Insets(10, 0, 5, 0);
		gbc_decryptedWordLabel.gridx = 0;
		gbc_decryptedWordLabel.gridy = 6;
		decryptorPanel.add(decryptedWordLabel, gbc_decryptedWordLabel);
		
		decryptedWordTextField = new JTextField();
		decryptedWordTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		decryptedWordTextField.setHorizontalAlignment(SwingConstants.CENTER);
		decryptedWordTextField.setEditable(false);
		decryptedWordTextField.setColumns(10);
		GridBagConstraints gbc_decryptedWordTextField = new GridBagConstraints();
		gbc_decryptedWordTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_decryptedWordTextField.gridheight = 2;
		gbc_decryptedWordTextField.gridwidth = 4;
		gbc_decryptedWordTextField.insets = new Insets(11, 0, 5, 0);
		gbc_decryptedWordTextField.gridx = 0;
		gbc_decryptedWordTextField.gridy = 8;
		decryptorPanel.add(decryptedWordTextField, gbc_decryptedWordTextField);
		
		JButton copyDecryptorButton = new JButton("Copy to clipboard");
		copyDecryptorButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_copyDecryptorButton = new GridBagConstraints();
		gbc_copyDecryptorButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_copyDecryptorButton.insets = new Insets(30, 0, 5, 0);
		gbc_copyDecryptorButton.gridx = 3;
		gbc_copyDecryptorButton.gridy = 10;
		decryptorPanel.add(copyDecryptorButton, gbc_copyDecryptorButton);
		copyDecryptorButton.addActionListener(new copyDecryptedListener());
		frame.setVisible(true);
	}
	private class encryptListener implements ActionListener{

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				if(keyEncryptorTextField.getText().isEmpty()) return;
				encryptor = new Encryptor(keyEncryptorTextField.getText());
				encryptor.encrypt(wordEncryptorTextField.getText());
				encryptedWordTextField.setText(encryptor.getEncryptedWord());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}	
	}
	private class copyEncryptedListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			StringSelection stringSelection = new StringSelection (encryptedWordTextField.getText());
			Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
			clpbrd.setContents (stringSelection, null);
		}	
	}
	private class decryptListener implements ActionListener{

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				if(keyDecryptorTextField.getText().isEmpty()) return;
				decryptor = new Decryptor(keyDecryptorTextField.getText());
				decryptor.decrypt(wordDecryptorTextField.getText());
				decryptedWordTextField.setText(decryptor.getDecryptedWord());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}	
	}
	private class copyDecryptedListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			StringSelection stringSelection = new StringSelection (decryptedWordTextField.getText());
			Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
			clpbrd.setContents (stringSelection, null);
		}	
	}
}
