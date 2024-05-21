import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
//Created  by: Shreejal_Khatri
//Group: L5CG7
//University ID: 2358168 


public class Signup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 408, 375);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 255));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(143, 76, 108, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 119, 108, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(67, 79, 49, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(67, 122, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(67, 155, 72, 14);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(143, 153, 108, 23);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("Signup");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(166, 11, 79, 37);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose Roles", "Admin", "User", "Instructor"}));
		comboBox.setBounds(143, 187, 108, 23);
		contentPane.add(comboBox);
		

		JLabel lblNewLabel_2_1 = new JLabel("Roles");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_1.setBounds(67, 188, 49, 14);
		contentPane.add(lblNewLabel_2_1);
		
		
		JButton btnNewButton = new JButton("Signup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
		        String email = textField_1.getText();
		        String password = new String(passwordField.getPassword());
		        String Roles = comboBox.getSelectedItem().toString();
		        //Checking if any of the fields are empty
		        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || Roles.equals("Choose Roles")) {
		            JOptionPane.showMessageDialog(null, "Please fill all fields before signing up");
		        } else {

		        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/tutorial", "root", "");
		             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, email, password, roles) VALUES (?, ?, ?, ?)");
		        ) {
		            stmt.setString(1, name);
		            stmt.setString(2, email);
		            stmt.setString(3, password);
		            stmt.setString(4, Roles); 
		            int result = stmt.executeUpdate();
		            if (result > 0) {
		                //Signup successful
		                JOptionPane.showMessageDialog(null, "Signup Successful");
		                
		                //Redirect user to the login window
	                    dispose(); //Close the signup window
	                    Login loginFrame = new Login(); //Create an instance of the login frame
	                    loginFrame.setVisible(true); //Make the login frame visible
		            } else {
		                //Signup failed
		                JOptionPane.showMessageDialog(null, "Signup Failed");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		 }
		});
		btnNewButton.setBounds(143, 249, 108, 37);
		contentPane.add(btnNewButton);
		

	}
}
