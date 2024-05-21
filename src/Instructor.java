import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
//Created  by: Shreejal_Khatri
//Group: L5CG7
//University ID: 2358168 




public class Instructor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CourseManagement courseManagement; 
	private JTextField courseCodeField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instructor frame = new Instructor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// Define your database URL, user name, and password
    private static final String DB_URL = "jdbc:mysql://localhost/tutorial";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

	/**
	 * Create the frame.
	 */
	public Instructor() {
		courseManagement = new CourseManagement();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1216, 873);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Welcomelabel = new JLabel("Welcome to CourseManagement System");
		Welcomelabel.setBounds(245, 11, 662, 41);
		Welcomelabel.setForeground(new Color(0, 0, 0));
		Welcomelabel.setBackground(new Color(255, 255, 255));
		Welcomelabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(Welcomelabel);
		
		JLabel lblTeacher = new JLabel("Teacher");
		lblTeacher.setBounds(509, 76, 129, 20);
		lblTeacher.setForeground(new Color(0, 0, 0));
		lblTeacher.setBackground(new Color(255, 255, 255));
		lblTeacher.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblTeacher);
		
		JLabel lblCoursesToTeach = new JLabel("Courses To Teach");
		lblCoursesToTeach.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblCoursesToTeach.setBounds(86, 168, 222, 31);
		contentPane.add(lblCoursesToTeach);
		
		JLabel lblAssessmentCreation = new JLabel("Assessment Creation");
		lblAssessmentCreation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblAssessmentCreation.setBounds(442, 168, 222, 31);
		contentPane.add(lblAssessmentCreation);
		
		JButton btnCoursesToTeach = new JButton("Students Enrolled ");
		btnCoursesToTeach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewEnrolledCourses(); 
			}

			private void viewEnrolledCourses() {

				courseCodeField = new JTextField();
				courseCodeField.setBounds(10, 10, 200, 30);
				contentPane.add(courseCodeField);
				
	
				// TODO Auto-generated method stub
				//Using an input dialog to prompt the user to enter the course code
				String courseCode = JOptionPane.showInputDialog(null, "Enter the course code:");

				//Checking if the course code is not empty and the user clicked OK
				if (courseCode != null && !courseCode.isEmpty()) {
				    //Retrieving the list of students enrolled in the course
				    ArrayList<String[]> enrolledStudents = courseManagement.getEnrolledStudents(courseCode);
				    
				    if (!enrolledStudents.isEmpty()) {
				        //Defining the column names for the table
				        String[] columnNames = {"Student Name", "Student ID"};
				        
				        //Creating a DefaultTableModel with the column names and 0 rows initially
				        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
				        
				        //Populating the table model with the enrolled students' data
				        for (String[] student : enrolledStudents) {
				            model.addRow(student);
				        }
				        
				        //Creating a JTable with the populated table model
				        JTable table = new JTable(model);
				        
				        //Wrapping the table in a scroll pane
				        JScrollPane scrollPane = new JScrollPane(table);
				        
				        //Displaying the table in a dialog window
				        JOptionPane.showMessageDialog(null, scrollPane, "Students Enrolled in Course " + courseCode, JOptionPane.PLAIN_MESSAGE);
				    } else {
				        JOptionPane.showMessageDialog(null, "No students enrolled in course " + courseCode);
				    }
				} else {
				    JOptionPane.showMessageDialog(null, "Please enter a course code.");
				}
			}
		});
		btnCoursesToTeach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCoursesToTeach.setBounds(84, 233, 204, 49);
		contentPane.add(btnCoursesToTeach);
		
		JButton btnEnroll_1 = new JButton("Courses");
		btnEnroll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openViewCoursesDialog();
			}

			private void openViewCoursesDialog() {
				// TODO Auto-generated method stub
				  //Creating a new JFrame for viewing courses
    	        JFrame viewCoursesFrame = new JFrame("View Courses");
    	        viewCoursesFrame.setBounds(100, 100, 800, 600);
    	        JPanel viewCoursesPanel = new JPanel();
    	        viewCoursesPanel.setLayout(new BorderLayout());
    	        viewCoursesFrame.setContentPane(viewCoursesPanel);

    	        //Adding a JTextArea to display the courses
    	        JTextArea textArea = new JTextArea();
    	        textArea.setEditable(false);
    	        JScrollPane scrollPane = new JScrollPane(textArea);
    	        viewCoursesPanel.add(scrollPane, BorderLayout.CENTER);

    	        //Retrieving and display courses
    	        StringBuilder coursesInfo = new StringBuilder();
    	        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
    	            String sql = "SELECT course_name, course_code, instructor, level FROM courses";
    	            PreparedStatement statement = connection.prepareStatement(sql);
    	            ResultSet resultSet = statement.executeQuery();
    	            //Creating a table model to hold the data
    	            DefaultTableModel tableModel = new DefaultTableModel();
    	            tableModel.addColumn("Course Name");
    	            tableModel.addColumn("Course Code");
    	            tableModel.addColumn("Instructor");
    	            tableModel.addColumn("Level");
    	            
    	         //Populating the table model with the retrieved data
    	            while (resultSet.next()) {
    	                String courseName = resultSet.getString("course_name");
    	                String courseCode = resultSet.getString("course_code");
    	                String instructor = resultSet.getString("instructor");
    	                String level = resultSet.getString("level");
    	                tableModel.addRow(new Object[]{courseName, courseCode, instructor, level});
    	            }

    	            //Creating a JTable with the table model
    	            JTable table = new JTable(tableModel);
    	            JScrollPane scrollPane1 = new JScrollPane(table);
    	            viewCoursesPanel.add(scrollPane1, BorderLayout.CENTER);
    	        } catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }



    	        viewCoursesFrame.setVisible(true);
				
			}
		});
		btnEnroll_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEnroll_1.setBounds(84, 325, 204, 49);
		contentPane.add(btnEnroll_1);
		
		JButton btnAssign = new JButton("Assign");
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openAssignDialog();
				
				
			}

			private void openAssignDialog() {
				//Creating a new JFrame for adding an assignment
			    JFrame addAssignmentFrame = new JFrame("Add Assignment");
			    addAssignmentFrame.setBounds(100, 100, 400, 300);
			    JPanel addAssignmentPanel = new JPanel();
			    addAssignmentPanel.setLayout(null);
			    addAssignmentFrame.setContentPane(addAssignmentPanel);

			    //Adding labels and text fields for assignment details
			    JLabel lblAssignmentName = new JLabel("Assignment Name:");
			    lblAssignmentName.setBounds(20, 20, 150, 20);
			    JTextField txtAssignmentName = new JTextField();
			    txtAssignmentName.setBounds(180, 20, 200, 20);

			    JLabel lblCourseCode = new JLabel("Course Code:");
			    lblCourseCode.setBounds(20, 60, 150, 20);
			    JTextField txtCourseCode = new JTextField();
			    txtCourseCode.setBounds(180, 60, 200, 20);

			    JLabel lblDeadline = new JLabel("Deadline (yyyy/MM/dd):");
			    lblDeadline.setBounds(20, 100, 150, 20);
			    JTextField txtDeadline = new JTextField();
			    txtDeadline.setBounds(180, 100, 200, 20);

			    JButton btnAdd = new JButton("Add");
			    btnAdd.setBounds(150, 150, 100, 30);
			    btnAdd.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	
			        	try {
			        		//As txtDeadline.getText() returns a string representing the deadline date
			        		String deadlineStr = txtDeadline.getText();
			        		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			        		java.util.Date utilDate = dateFormat.parse(deadlineStr);
			        		java.sql.Date deadline = new java.sql.Date(utilDate.getTime());
		                  

		                    //Adding the assignment using CourseManagement
		                    courseManagement.addAssignment(txtAssignmentName.getText(), txtCourseCode.getText(), deadline);

		                    //Closing the add assignment dialog
		                    addAssignmentFrame.dispose();
		                } catch (ParseException ex) {
		                    ex.printStackTrace();
		                }
			           
			        }
			    });

			    addAssignmentPanel.add(lblAssignmentName);
			    addAssignmentPanel.add(txtAssignmentName);
			    addAssignmentPanel.add(lblCourseCode);
			    addAssignmentPanel.add(txtCourseCode);
			    addAssignmentPanel.add(lblDeadline);
			    addAssignmentPanel.add(txtDeadline);
			    addAssignmentPanel.add(btnAdd);

			    addAssignmentFrame.setVisible(true);
				
				
			}
		});
		btnAssign.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAssign.setBounds(444, 233, 204, 49);
		contentPane.add(btnAssign);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDeleteAssignmentDialog();
			}

			private void openDeleteAssignmentDialog() {
				// TODO Auto-generated method stub
				 //Creating a new JFrame for deleting assignments
			    JFrame deleteAssignmentFrame = new JFrame("Delete Assignment");
			    deleteAssignmentFrame.setBounds(100, 100, 400, 200);
			    JPanel deleteAssignmentPanel = new JPanel(new BorderLayout());
			    deleteAssignmentFrame.setContentPane(deleteAssignmentPanel);

			    //Adding labels and text field for assignment code
			    JLabel lblAssignmentCode = new JLabel("Assignment Code:");
			    JTextField txtAssignmentCode = new JTextField();
			    JButton btnDelete = new JButton("Delete");

			    btnDelete.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            //Getting the assignment code
			            String assignmentCode = txtAssignmentCode.getText();
			            // Delete the assignment using AssignmentManagement
			            courseManagement.deleteAssignment(assignmentCode);
			            //Closing the delete assignment dialog
			            deleteAssignmentFrame.dispose();
			        }
			    });

			    //Adding components to the panel
			    deleteAssignmentPanel.add(lblAssignmentCode, BorderLayout.WEST);
			    deleteAssignmentPanel.add(txtAssignmentCode, BorderLayout.CENTER);
			    deleteAssignmentPanel.add(btnDelete, BorderLayout.SOUTH);

			    deleteAssignmentFrame.setVisible(true);
				
				
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemove.setBounds(444, 325, 204, 49);
		contentPane.add(btnRemove);
		
		JLabel lblGrades = new JLabel("Grades");
		lblGrades.setForeground(Color.BLACK);
		lblGrades.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblGrades.setBounds(86, 447, 128, 41);
		contentPane.add(lblGrades);
		
		JButton btnAssignGrade = new JButton("Assign Grade");
		btnAssignGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openAssignGradeDialog();
			}

			private void openAssignGradeDialog() {
				// TODO Auto-generated method stub
				JFrame assignGradeFrame = new JFrame("Assign Grade");
			    assignGradeFrame.setBounds(100, 100, 400, 300);
			    JPanel assignGradePanel = new JPanel();
			    assignGradePanel.setLayout(null);
			    assignGradeFrame.setContentPane(assignGradePanel);

			    //Adding labels and text fields for grade details
			    JLabel lblCourseName = new JLabel("Course Name:");
			    lblCourseName.setBounds(20, 20, 150, 20);
			    JTextField txtCourseName = new JTextField();
			    txtCourseName.setBounds(180, 20, 200, 20);

			    JLabel lblCourseCode = new JLabel("Course Code:");
			    lblCourseCode.setBounds(20, 60, 150, 20);
			    JTextField txtCourseCode = new JTextField();
			    txtCourseCode.setBounds(180, 60, 200, 20);

			    JLabel lblStudentName = new JLabel("Student Name:");
			    lblStudentName.setBounds(20, 100, 150, 20);
			    JTextField txtStudentName = new JTextField();
			    txtStudentName.setBounds(180, 100, 200, 20);

			    JLabel lblGrade = new JLabel("Grade:");
			    lblGrade.setBounds(20, 140, 150, 20);
			    JTextField txtGrade = new JTextField();
			    txtGrade.setBounds(180, 140, 50, 20);

			    JButton btnAssign = new JButton("Assign");
			    btnAssign.setBounds(150, 200, 100, 30);
			    btnAssign.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            //Getting the input values
			            String courseName = txtCourseName.getText();
			            String courseCode = txtCourseCode.getText();
			            String studentName = txtStudentName.getText();
			            double grade = Double.parseDouble(txtGrade.getText());

			            //Calling the method to add the grade
			            courseManagement.assignGrade(courseName, courseCode, studentName, grade);

			            //Closing the assign grade dialog
			            assignGradeFrame.dispose();
			        }
			    });

			    assignGradePanel.add(lblCourseName);
			    assignGradePanel.add(txtCourseName);
			    assignGradePanel.add(lblCourseCode);
			    assignGradePanel.add(txtCourseCode);
			    assignGradePanel.add(lblStudentName);
			    assignGradePanel.add(txtStudentName);
			    assignGradePanel.add(lblGrade);
			    assignGradePanel.add(txtGrade);
			    assignGradePanel.add(btnAssign);

			    assignGradeFrame.setVisible(true);
			

				
				
			}
		});
		btnAssignGrade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAssignGrade.setBounds(84, 513, 204, 49);
		contentPane.add(btnAssignGrade);
		
		JButton btnRemoveGrade = new JButton("Remove Grade");
		btnRemoveGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openRemoveGradeDialog();
			}

			private void openRemoveGradeDialog() {
				// TODO Auto-generated method stub
				//Creating a new JFrame for removing a grade
			    JFrame removeGradeFrame = new JFrame("Remove Grade");
			    removeGradeFrame.setBounds(100, 100, 400, 200);
			    JPanel removeGradePanel = new JPanel();
			    removeGradePanel.setLayout(null);
			    removeGradeFrame.setContentPane(removeGradePanel);

			    //Adding labels and text fields for grade removal details
			    JLabel lblCourseName = new JLabel("Assignment Name:");
			    lblCourseName.setBounds(20, 20, 150, 20);
			    JTextField txtCourseName = new JTextField();
			    txtCourseName.setBounds(180, 20, 200, 20);

			    JLabel lblCourseCode = new JLabel("Course Code:");
			    lblCourseCode.setBounds(20, 60, 150, 20);
			    JTextField txtCourseCode = new JTextField();
			    txtCourseCode.setBounds(180, 60, 200, 20);

			    JLabel lblStudentName = new JLabel("Student Name:");
			    lblStudentName.setBounds(20, 100, 150, 20);
			    JTextField txtStudentName = new JTextField();
			    txtStudentName.setBounds(180, 100, 200, 20);

			    JButton btnRemove = new JButton("Remove");
			    btnRemove.setBounds(150, 150, 100, 30);
			    btnRemove.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            //Calling the removeGrade method with the provided inputs
			            String courseName = txtCourseName.getText(); 
			            String courseCode = txtCourseCode.getText();
			            String studentName = txtStudentName.getText();
			            courseManagement.removeGrade(courseName, courseCode, studentName);
			            //Closing the remove grade dialog
			            removeGradeFrame.dispose();
			        }
			    });

			    removeGradePanel.add(lblCourseName);
			    removeGradePanel.add(txtCourseName);
			    removeGradePanel.add(lblCourseCode);
			    removeGradePanel.add(txtCourseCode);
			    removeGradePanel.add(lblStudentName);
			    removeGradePanel.add(txtStudentName);
			    removeGradePanel.add(btnRemove);

			    removeGradeFrame.setVisible(true);
				
				
			}
		});
		btnRemoveGrade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemoveGrade.setBounds(86, 604, 204, 49);
		contentPane.add(btnRemoveGrade);
		
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
        newsTextArea.setEditable(false); //Making the news text area read-only
        
        JButton btnLogOut = new JButton("Log out");
        btnLogOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		
        		Login loginFrame = new Login();
        		loginFrame.setVisible(true);
        	}
        });
        btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnLogOut.setBounds(934, 126, 204, 49);
        contentPane.add(btnLogOut);

	}
}
