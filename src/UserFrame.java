import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
//Created  by: Shreejal_Khatri
//Group: L5CG7
//University ID: 2358168 

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CourseManagement courseManagement; 
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Defining the database URL, user name, and password
    private static final String DB_URL = "jdbc:mysql://localhost/tutorial";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

	/**
	 * Create the frame.
	 */
	public UserFrame() {
		courseManagement = new CourseManagement();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1216, 873);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Welcomelabel = new JLabel("Welcome to CourseManagement System");
		Welcomelabel.setBounds(245, 11, 662, 41);
		Welcomelabel.setForeground(new Color(0, 0, 0));
		Welcomelabel.setBackground(new Color(255, 255, 255));
		Welcomelabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(Welcomelabel);
		
		JLabel lblUser = new JLabel("Student");
		lblUser.setBounds(509, 76, 129, 20);
		lblUser.setForeground(new Color(0, 0, 0));
		lblUser.setBackground(new Color(255, 255, 255));
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblUser);
		
		JLabel lblNewLabel = new JLabel("Courses Enrollment");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(72, 164, 222, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnEnroll = new JButton("Enroll");
		btnEnroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Getting all available courses
		        ArrayList<String[]> courses = courseManagement.showCourses();

		        //Creating an array to hold formatted course names with codes
		        String[] courseOptions = new String[courses.size()];
		        for (int i = 0; i < courses.size(); i++) {
		            String[] course = courses.get(i);
		            String courseNameWithCode = course[0] + " - " + course[1]; //course[0] contains course name and course[1] contains course code
		            courseOptions[i] = courseNameWithCode;
		        }

		        //dialog with the formatted course names with codes
		        String selectedCourse = (String) JOptionPane.showInputDialog(null, "Select Course", "Available Courses",
		                JOptionPane.PLAIN_MESSAGE, null, courseOptions, courseOptions[0]);

		        //Checking if a course is selected
		        if (selectedCourse != null && !selectedCourse.isEmpty()) {
		            String[] selectedCourseParts = selectedCourse.split(" - ");
		            String selectedCourseCode = selectedCourseParts[selectedCourseParts.length - 1]; // Extract last part as course code
		            // Get user input for student name
		            String studentName = JOptionPane.showInputDialog(null, "Enter your name:");

		            //Enrolling student in the selected course
		            courseManagement.enrollStudent(selectedCourseCode, studentName);

		            //After enrolling the student, get the count of enrolled courses
		            int coursesEnrolledCount = courseManagement.getCoursesEnrolledCount(studentName);
		            System.out.println(studentName + " has enrolled in " + coursesEnrolledCount + " courses.");
		        } else {
		            System.out.println("No course selected.");
		        }
		    
			}

			
		});
		btnEnroll.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEnroll.setBounds(73, 226, 204, 49);
		contentPane.add(btnEnroll);
		

            
    
		
		
		
		
		JButton btnDisenroll = new JButton("Disenroll");
		btnDisenroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
		        ArrayList<String[]> courses = courseManagement.showCourses();

		       
		        JComboBox<String> courseComboBox = new JComboBox<>();
		        for (String[] course : courses) {
		            courseComboBox.addItem(course[1] + " - " + course[0]); //course[1] contains the course code and course[0] contains the course name
		        }

		   
		        int option = JOptionPane.showOptionDialog(null, courseComboBox, "Select Course to Disenroll", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		        //Checking if OK button is clicked and a course is selected
		        if (option == JOptionPane.OK_OPTION && courseComboBox.getSelectedIndex() != -1) {
		            //Getting the selected course code
		            String selectedCourseCode = ((String) courseComboBox.getSelectedItem()).split(" - ")[0];

		            //Getting user input for student name
		            String studentName = JOptionPane.showInputDialog(null, "Enter your name:");

		            //Disenrolling student from the selected course
		            courseManagement.disenrollStudent(selectedCourseCode, studentName);

		            //After disenrolling the student, get the count of enrolled courses
		            int coursesEnrolledCount = courseManagement.getCoursesEnrolledCount(studentName);
		            System.out.println(studentName + " is enrolled in " + coursesEnrolledCount + " courses after disenrollment.");
		        } else {
		            System.out.println("No course selected for disenrollment.");
		        }
		    
			}
		});
		btnDisenroll.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDisenroll.setBounds(72, 301, 205, 44);
		contentPane.add(btnDisenroll);
		
		JButton btnViewenrolled = new JButton("View Courses Enrolled in");
		btnViewenrolled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 //Prompting the user to enter the student name
		        String studentName = JOptionPane.showInputDialog(null, "Enter the student name:");

		        //Checking if the user entered a student name
		        if (studentName != null && !studentName.isEmpty()) {
		            //Calling the viewEnrolledStudents method with the provided student name
		            ArrayList<String[]> enrolledCourses = courseManagement.viewEnrolledStudents(studentName);

		            //Displaying enrolled courses in a new window
		            JFrame enrolledCoursesFrame = new JFrame("Enrolled Courses");
		            enrolledCoursesFrame.setSize(600, 400);

		            if (!enrolledCourses.isEmpty()) {
		             
		                String[] columnNames = {"Course Name", "Course Code", "Instructor"};
		                DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		               
		                for (String[] course : enrolledCourses) {
		                    model.addRow(course);
		                }

		            
		                JTable table = new JTable(model);
		                JScrollPane scrollPane = new JScrollPane(table);

		             
		                enrolledCoursesFrame.getContentPane().add(scrollPane);
		            } else {
		                JOptionPane.showMessageDialog(null, "No courses enrolled for student: " + studentName);
		            }

		         
		            enrolledCoursesFrame.setVisible(true);
		        } else {
		            JOptionPane.showMessageDialog(null, "No student name entered.");
		        }

		    }
		});
		btnViewenrolled.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewenrolled.setBounds(72, 356, 205, 47);
		contentPane.add(btnViewenrolled);
		
		JLabel lblAssessmentSubmission = new JLabel("Assessment and Exams");
		lblAssessmentSubmission.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblAssessmentSubmission.setBounds(429, 164, 244, 31);
		contentPane.add(lblAssessmentSubmission);
		
		JButton btnUnsubmitAssess = new JButton("View Exams");
		btnUnsubmitAssess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DefaultTableModel examTableModel = courseManagement.showExams(); //Calling the method to retrieve exam data
	                JTable examTable = new JTable(examTableModel); 
	                JScrollPane scrollPane = new JScrollPane(examTable); 
	                JFrame frame = new JFrame("View Exams"); 
	                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                frame.getContentPane().add(scrollPane, BorderLayout.CENTER); 
	                frame.setSize(800, 600); 
	                frame.setVisible(true); 
	        		
			}
		});
		btnUnsubmitAssess.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUnsubmitAssess.setBounds(429, 230, 229, 41);
		contentPane.add(btnUnsubmitAssess);
		
		JButton btnViewAssess = new JButton("View Assessment");
		btnViewAssess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openViewAssignmensDialog();
			}

			private void openViewAssignmensDialog() {
				// TODO Auto-generated method stub
				JFrame viewAssignmentsFrame = new JFrame("View Assignments");
			    viewAssignmentsFrame.setBounds(100, 100, 800, 600);
			    JPanel viewAssignmentsPanel = new JPanel(new BorderLayout());
			    viewAssignmentsFrame.setContentPane(viewAssignmentsPanel);

			    //Retrieving and display assignments in a table
			    try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
			        String sql = "SELECT assignment_name, course_code, deadline FROM assignments";
			        PreparedStatement statement = connection.prepareStatement(sql);
			        ResultSet resultSet = statement.executeQuery();

			        //Creating a table model to hold the data
			        DefaultTableModel tableModel = new DefaultTableModel();
			        tableModel.addColumn("Assignment Name");
			        tableModel.addColumn("Course Code");
			        tableModel.addColumn("Deadline");

			        //Populating the table model with the retrieved data
			        while (resultSet.next()) {
			            String assignmentName = resultSet.getString("assignment_name");
			            String courseCode = resultSet.getString("course_code");
			            Date deadline = resultSet.getDate("deadline");
			            tableModel.addRow(new Object[]{assignmentName, courseCode, deadline});
			        }

			     
			        JTable table = new JTable(tableModel);
			        JScrollPane scrollPane = new JScrollPane(table);
			        viewAssignmentsPanel.add(scrollPane, BorderLayout.CENTER);
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			    }

			    viewAssignmentsFrame.setVisible(true);			
				
			}
		});
		btnViewAssess.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewAssess.setBounds(429, 282, 229, 41);
		contentPane.add(btnViewAssess);
		
		JLabel lblGrades = new JLabel("Grades");
		lblGrades.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblGrades.setBounds(72, 437, 222, 31);
		contentPane.add(lblGrades);
		
		JButton btnStudentGrades = new JButton("View Grades");
		btnStudentGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openGenerateReportDalog();
			}

			private void openGenerateReportDalog() {
				// TODO Auto-generated method stub
				JFrame dialogFrame = new JFrame("Generate Report");
			    dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			
			    JLabel nameLabel = new JLabel("Enter Student Name:");
			    JTextField nameField = new JTextField(20);
			    JButton generateButton = new JButton("Generate");

			
			    generateButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            String studentName = nameField.getText();
			            //Calling the generateReport method from CourseManagement to get the report
			            String report = courseManagement.generateResultSlip(studentName);
			            //Displaying the report in a new window
			            displayReportDialog(report);
			            //Closing the dialog
			            dialogFrame.dispose();
			        }
			    });

			
			    JPanel panel = new JPanel(new FlowLayout());
			    panel.add(nameLabel);
			    panel.add(nameField);
			    panel.add(generateButton);
			    dialogFrame.getContentPane().add(panel);

			    dialogFrame.pack();
			    dialogFrame.setLocationRelativeTo(null); 
			    dialogFrame.setVisible(true);
			}

			private void displayReportDialog(String report) {
			    JFrame reportFrame = new JFrame("Generated Report");
			    JTextArea textArea = new JTextArea(report);
			    textArea.setEditable(false);
			    JScrollPane scrollPane = new JScrollPane(textArea);
			    reportFrame.getContentPane().add(scrollPane);
			    reportFrame.setSize(400, 300);
			    reportFrame.setLocationRelativeTo(null);
			    reportFrame.setVisible(true);
			
				
			}
		});
		btnStudentGrades.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStudentGrades.setBounds(72, 479, 205, 47);
		contentPane.add(btnStudentGrades);
		
		 //NEWS SECTION
        JPanel newsPanel = new JPanel(new BorderLayout());
        newsPanel.setBounds(965, 300, 205, 400);
        contentPane.add(newsPanel);

      
        JLabel newsLabel = new JLabel("NEWS");
        newsLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        newsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        newsPanel.add(newsLabel, BorderLayout.NORTH);

     
        JTextArea newsTextArea = new JTextArea();
        newsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        newsTextArea.setToolTipText(""); 

    
        JScrollPane newsScrollPane = new JScrollPane(newsTextArea);
        newsPanel.add(newsScrollPane, BorderLayout.CENTER);

      
        String sampleNews = "1. New course added.\n" +
                            "2. Assignment submission\n deadline extended.\n" +
                            "3. Exam schedule updated.\n";

    
        newsTextArea.setText(sampleNews);
        newsTextArea.setEditable(false); 
        
        JButton btnLogOut = new JButton("Log out");
        btnLogOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		
        		Login loginFrame = new Login();
        		loginFrame.setVisible(true);
        		
        	}
        });
        btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnLogOut.setBounds(913, 125, 204, 49);
        contentPane.add(btnLogOut);



    }
	
	
}
