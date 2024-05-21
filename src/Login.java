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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.*;
import java.awt.Color;

//Created  by: Shreejal_Khatri
//Group: L5CG7
//University ID: 2358168 


public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtemail;
    private JTextField txt1;
    private JPasswordField txtpassword;
    private JComboBox<String> comboBox; //Changed to a class member
    String selectedRole;

    /**
     * Launch the application.
     */ 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
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
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 446, 425);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Email");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(75, 136, 72, 14);
        contentPane.add(lblNewLabel);
        
        txtemail = new JTextField();
        txtemail.setBounds(159, 131, 136, 32);
        contentPane.add(txtemail);
        txtemail.setColumns(10);
        
        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String username = txt1.getText(); //Retrieving username
                String email = txtemail.getText();
                String password = new String(txtpassword.getPassword()); //Using getPassword to retrieve password

                if (comboBox != null) {
                    String selectedRole = comboBox.getSelectedItem().toString();

                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/tutorial", "root", "");
                         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND email = ? AND password = ?");
                    ) {
                        stmt.setString(1, username); //Setting username parameter
                        stmt.setString(2, email);
                        stmt.setString(3, password);
                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            String storedUsername = rs.getString("username");
                            String storedEmail = rs.getString("email");
                            String storedPassword = rs.getString("password");
                            String storedRole = rs.getString("roles");

                            if (username.equals(storedUsername) && email.equals(storedEmail) && password.equals(storedPassword) && selectedRole.equals(storedRole)) {
                                JOptionPane.showMessageDialog(null, "Login Successful");

                                //Navigating to appropriate role-specific functionality
                                if ("Admin".equals(selectedRole)) {
                                    //Redirect to admin functionality
                                    AdminFrame adminFrame = new AdminFrame(); 
                                    adminFrame.setVisible(true);
                                    dispose(); // Close the login frame
                                } else if ("User".equals(selectedRole)) {
                                    //Redirect to user functionality
                                    UserFrame userFrame = new UserFrame(); 
                                    userFrame.setVisible(true);
                                    dispose(); // Close the login frame
                                } else if ("Instructor".equals(selectedRole)) {
                                    //Redirect to instructor functionality
                                    Instructor instructorFrame = new Instructor(); 
                                    instructorFrame.setVisible(true);
                                    dispose(); //Closing the login frame
                                
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid Username/Email/Password/Role combination");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Username/Email/Password combination");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.err.println("Error: comboBox is null");
                }
            }
        });
        btnNewButton.setBounds(75, 278, 89, 40);
        contentPane.add(btnNewButton);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(75, 181, 72, 14);
        contentPane.add(lblPassword);
        
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblLogin.setBounds(182, 25, 59, 29);
        contentPane.add(lblLogin);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsername.setBounds(75, 93, 77, 14);
        contentPane.add(lblUsername);
        
        txt1 = new JTextField();
        txt1.setColumns(10);
        txt1.setBounds(159, 84, 136, 36);
        contentPane.add(txt1);
        
        txtpassword = new JPasswordField();
        txtpassword.setBounds(159, 174, 136, 32);
        contentPane.add(txtpassword);
        
        comboBox = new JComboBox<>();
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                selectedRole = comboBox.getSelectedItem().toString();
            }
        });
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose Roles", "Admin", "User", "Instructor"}));
        comboBox.setBounds(159, 215, 136, 32);
        contentPane.add(comboBox);
    
        JLabel lblRoles = new JLabel("Roles");
        lblRoles.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblRoles.setBounds(75, 222, 66, 17);
        contentPane.add(lblRoles);
        
        JButton btnSignup = new JButton("Signup");
        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Signup sgn = new Signup();
                sgn.setVisible(true);
                dispose();
            }
        });
        btnSignup.setBounds(234, 278, 97, 40);
        contentPane.add(btnSignup);
    }
}
