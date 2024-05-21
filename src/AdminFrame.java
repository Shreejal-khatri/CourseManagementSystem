import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
//Created  by: Shreejal_Khatri
//Group: L5CG7
//University ID: 2358168 

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
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
	public AdminFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1216, 873);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Welcomelabel = new JLabel("Welcome to CourseManagement System");
		Welcomelabel.setBounds(245, 11, 662, 41);
		Welcomelabel.setForeground(new Color(0, 0, 0));
		Welcomelabel.setBackground(new Color(255, 255, 255));
		Welcomelabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(Welcomelabel);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(509, 76, 100, 20);
		lblAdmin.setForeground(new Color(0, 0, 0));
		lblAdmin.setBackground(new Color(255, 255, 255));
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblAdmin);
		
		//Instantiating CourseManagement class
		CourseManagement courseManagement = new CourseManagement();

        JButton btnAddCourse = new JButton("Add Course");
        btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnAddCourse.setBounds(53, 211, 120, 41);
        btnAddCourse.setForeground(new Color(0, 0, 0));
        btnAddCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openAddCourseDialog();
                
            }

			private void openAddCourseDialog() {
				// TODO Auto-generated method stub
	            //Creating a new JFrame for adding course
	            JFrame addCourseFrame = new JFrame("Add Course");
	            addCourseFrame.setBounds(100, 100, 400, 300);
	            JPanel addCoursePanel = new JPanel();
	            addCoursePanel.setLayout(null);
	            addCourseFrame.setContentPane(addCoursePanel);

	            //Adding labels and text fields for course details
	            JLabel lblCourseName = new JLabel("Course Name:");
	            lblCourseName.setBounds(20, 20, 100, 20);
	            JTextField txtCourseName = new JTextField();
	            txtCourseName.setBounds(130, 20, 200, 20);

	            JLabel lblCourseCode = new JLabel("Course Code:");
	            lblCourseCode.setBounds(20, 60, 100, 20);
	            JTextField txtCourseCode = new JTextField();
	            txtCourseCode.setBounds(130, 60, 200, 20);

	            JLabel lblInstructor = new JLabel("Instructor:");
	            lblInstructor.setBounds(20, 100, 100, 20);
	            JTextField txtInstructor = new JTextField();
	            txtInstructor.setBounds(130, 100, 200, 20);
	            
	            JLabel lblLevel = new JLabel("Level:");
	            lblLevel.setBounds(20, 140, 100, 20);
	            JTextField txtLevel = new JTextField(); 
	            txtLevel.setBounds(130, 140, 200, 20);


	            JButton btnAdd = new JButton("Add");
	            btnAdd.setBounds(150, 200, 80, 30);
	            btnAdd.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    //Adding course using CourseManagement
	                	int level = Integer.parseInt(txtLevel.getText());
	                    courseManagement.addCourse(txtCourseName.getText(), txtCourseCode.getText(), txtInstructor.getText(), level);
	                    //Closing the add course dialog
	                    addCourseFrame.dispose();
	                }
	            });

	            addCoursePanel.add(lblCourseName);
	            addCoursePanel.add(txtCourseName);
	            addCoursePanel.add(lblCourseCode);
	            addCoursePanel.add(txtCourseCode);
	            addCoursePanel.add(lblInstructor);
	            addCoursePanel.add(txtInstructor);
	            addCoursePanel.add(lblLevel);
	            addCoursePanel.add(txtLevel);
	            addCoursePanel.add(btnAdd);

	            addCourseFrame.setVisible(true);
				
			}
        });
        contentPane.add(btnAddCourse);
        


        JButton btnDeleteCourse = new JButton("Delete Course");
        btnDeleteCourse.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnDeleteCourse.setBounds(53, 274, 120, 41);
        btnDeleteCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Delete course functionality
            	openDeleteCourseDialog();
            }

			private void openDeleteCourseDialog() {
				// TODO Auto-generated method stub
				
		        JFrame deleteCourseFrame = new JFrame("Delete Course");
		        deleteCourseFrame.setBounds(100, 100, 400, 200);
		        JPanel deleteCoursePanel = new JPanel();
		        deleteCoursePanel.setLayout(null);
		        deleteCourseFrame.setContentPane(deleteCoursePanel);

		     
		        JLabel lblCourseCode = new JLabel("Course Code:");
		        lblCourseCode.setBounds(20, 20, 100, 20);
		        JTextField txtCourseCode = new JTextField();
		        txtCourseCode.setBounds(130, 20, 200, 20);

		        JButton btnDelete = new JButton("Delete");
		        btnDelete.setBounds(150, 70, 100, 30);
		        btnDelete.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                //Deleting course using CourseManagement
		                courseManagement.deleteCourse(txtCourseCode.getText());
		                //Closing the delete course dialog
		                deleteCourseFrame.dispose();
		            }
		        });

		        deleteCoursePanel.add(lblCourseCode);
		        deleteCoursePanel.add(txtCourseCode);
		        deleteCoursePanel.add(btnDelete);

		        deleteCourseFrame.setVisible(true);
		    
				
				
			}
        });
        contentPane.add(btnDeleteCourse);
        
        JLabel Courseslabel = new JLabel("Courses");
        Courseslabel.setBounds(56, 159, 93, 41);
        Courseslabel.setForeground(new Color(0, 0, 0));
        Courseslabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        contentPane.add(Courseslabel);
        
        JLabel Assignmentlabel = new JLabel("Assignments");
        Assignmentlabel.setBounds(319, 159, 128, 41);
        Assignmentlabel.setForeground(new Color(0, 0, 0));
        Assignmentlabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        contentPane.add(Assignmentlabel);
        
        JButton btnAddAssignment = new JButton("Add Assignment");
        btnAddAssignment.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnAddAssignment.setBounds(319, 211, 139, 41);
        btnAddAssignment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openAddAssignmentDialog();
        	}

			private void openAddAssignmentDialog() {
				// TODO Auto-generated method stub
				
		        JFrame addAssignmentFrame = new JFrame("Add Assignment");
		        addAssignmentFrame.setBounds(100, 100, 400, 300);
		        JPanel addAssignmentPanel = new JPanel();
		        addAssignmentPanel.setLayout(null);
		        addAssignmentFrame.setContentPane(addAssignmentPanel);

		    
		        JLabel lblAssignmentName = new JLabel("Assignment Name:");
		        lblAssignmentName.setBounds(20, 20, 150, 20);
		        JTextField txtAssignmentName = new JTextField();
		        txtAssignmentName.setBounds(180, 20, 200, 20);

		        JLabel lblCourseCode = new JLabel("Course Code:");
		        lblCourseCode.setBounds(20, 60, 150, 20);
		        JTextField txtCourseCode = new JTextField();
		        txtCourseCode.setBounds(180, 60, 200, 20);

		        JLabel lblDeadline = new JLabel("Deadline:");
		        lblDeadline.setBounds(20, 100, 150, 20);
		        JTextField txtDeadline = new JTextField();
		        txtDeadline.setBounds(180, 100, 200, 20);

		        JButton btnAdd = new JButton("Add");
		        btnAdd.setBounds(150, 150, 100, 30);
		        btnAdd.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	try {
		                    //Parsing the deadline string to a Date object
		                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		                    Date deadline = new Date(dateFormat.parse(txtDeadline.getText()).getTime());

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
        contentPane.add(btnAddAssignment);
        
        JButton btnDeleteAssignment = new JButton("Delete Assignment");
        btnDeleteAssignment.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnDeleteAssignment.setBounds(319, 274, 139, 41);
        btnDeleteAssignment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openDeleteAssignmentDialog();
        	}

			private void openDeleteAssignmentDialog() {
				// TODO Auto-generated method stub
				
			    JFrame deleteAssignmentFrame = new JFrame("Delete Assignment");
			    deleteAssignmentFrame.setBounds(100, 100, 400, 200);
			    JPanel deleteAssignmentPanel = new JPanel(new BorderLayout());
			    deleteAssignmentFrame.setContentPane(deleteAssignmentPanel);

			  
			    JLabel lblAssignmentCode = new JLabel("Assignment Code:");
			    JTextField txtAssignmentCode = new JTextField();
			    JButton btnDelete = new JButton("Delete");

			    btnDelete.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            //Getting the assignment code
			            String assignmentCode = txtAssignmentCode.getText();
			            //Deleting the assignment using AssignmentManagement
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
        contentPane.add(btnDeleteAssignment);
        
        JLabel Examinationlabel = new JLabel("Examination");
        Examinationlabel.setBounds(564, 159, 128, 41);
        Examinationlabel.setForeground(new Color(0, 0, 0));
        Examinationlabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        contentPane.add(Examinationlabel);
        
        JButton btnAddExamwork = new JButton(" Add Exam work");
        btnAddExamwork.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 openAddExamDialog();
        		
        	}

			private void openAddExamDialog() {
				// TODO Auto-generated method stub
			
			    JFrame addExamFrame = new JFrame("Add Exam");
			    addExamFrame.setBounds(100, 100, 400, 300);
			    JPanel addExamPanel = new JPanel();
			    addExamPanel.setLayout(null);
			    addExamFrame.setContentPane(addExamPanel);

		
			    JLabel lblExamName = new JLabel("Exam Name:");
			    lblExamName.setBounds(20, 20, 100, 20);
			    JTextField txtExamName = new JTextField();
			    txtExamName.setBounds(130, 20, 200, 20);

			    JLabel lblCourseCode = new JLabel("Course Code:");
			    lblCourseCode.setBounds(20, 60, 100, 20);
			    JTextField txtCourseCode = new JTextField();
			    txtCourseCode.setBounds(130, 60, 200, 20);

			    JLabel lblExamDate = new JLabel("Exam Date (YYYY-MM-DD):");
			    lblExamDate.setBounds(20, 100, 150, 20);
			    JTextField txtExamDate = new JTextField();
			    txtExamDate.setBounds(180, 100, 150, 20);

			    JLabel lblDurationHours = new JLabel("Duration Hours:");
			    lblDurationHours.setBounds(20, 140, 100, 20);
			    JTextField txtDurationHours = new JTextField();
			    txtDurationHours.setBounds(130, 140, 50, 20);

			    JButton btnAdd = new JButton("Add");
			    btnAdd.setBounds(150, 200, 100, 30);
			    btnAdd.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            try {
			                //Parsing the exam date string to a Date object
			                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			                Date examDate = new Date(dateFormat.parse(txtExamDate.getText()).getTime());

			                //Getting the exam name, course code, and duration hours from the text fields
			                String examName = txtExamName.getText();
			                String courseCode = txtCourseCode.getText();
			                double durationHours = Double.parseDouble(txtDurationHours.getText());

			                //Adding the exam using the addExam method
			                courseManagement.addExam(examName, courseCode, examDate, durationHours);

			                //Closing the add exam dialog
			                addExamFrame.dispose();
			            } catch (ParseException | NumberFormatException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(null, "Invalid date format or duration hours.");
			            }
			        }
			    });

			    addExamPanel.add(lblExamName);
			    addExamPanel.add(txtExamName);
			    addExamPanel.add(lblCourseCode);
			    addExamPanel.add(txtCourseCode);
			    addExamPanel.add(lblExamDate);
			    addExamPanel.add(txtExamDate);
			    addExamPanel.add(lblDurationHours);
			    addExamPanel.add(txtDurationHours);
			    addExamPanel.add(btnAdd);

			    addExamFrame.setVisible(true);
			}
				
				
			
        });
        btnAddExamwork.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnAddExamwork.setBounds(564, 212, 139, 41);
        contentPane.add(btnAddExamwork);
        
        JButton btnDeleteExamwork = new JButton("Delete Exam work");
        btnDeleteExamwork.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnDeleteExamwork.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openDeleteExamDialog();

        	}

			private void openDeleteExamDialog() {
				// TODO Auto-generated method stub
				JFrame deleteExamFrame = new JFrame("Delete Exam");
			    deleteExamFrame.setBounds(100, 100, 400, 200);
			    JPanel deleteExamPanel = new JPanel();
			    deleteExamPanel.setLayout(new BorderLayout());
			    deleteExamFrame.setContentPane(deleteExamPanel);

			    JLabel lblExamName = new JLabel("Exam Name:");
			    JTextField txtExamName = new JTextField();
			    JButton btnDelete = new JButton("Delete");

			    btnDelete.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            //Calling the deleteExam method from CourseManagement to delete the exam
			            String examName = txtExamName.getText();
			            if (!examName.isEmpty()) {
			                courseManagement.deleteExam(examName);
			                JOptionPane.showMessageDialog(null, "Exam deleted successfully!");
			            } else {
			                JOptionPane.showMessageDialog(null, "Please enter a valid exam name.");
			            }
			        }
			    });

			    deleteExamPanel.add(lblExamName, BorderLayout.WEST);
			    deleteExamPanel.add(txtExamName, BorderLayout.CENTER);
			    deleteExamPanel.add(btnDelete, BorderLayout.SOUTH);

			    deleteExamFrame.setVisible(true);
				
				
			}
        });
        btnDeleteExamwork.setBounds(564, 280, 139, 41);
        contentPane.add(btnDeleteExamwork);
        
        JButton btnViewCourse = new JButton("View Courses");
        btnViewCourse.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnViewCourse.setBounds(53, 331, 120, 41);
        btnViewCourse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openViewCoursesDialog();
        	}
        	

			private void openViewCoursesDialog() {
        	     
        	        JFrame viewCoursesFrame = new JFrame("View Courses");
        	        viewCoursesFrame.setBounds(100, 100, 800, 600);
        	        JPanel viewCoursesPanel = new JPanel();
        	        viewCoursesPanel.setLayout(new BorderLayout());
        	        viewCoursesFrame.setContentPane(viewCoursesPanel);

        	       
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
        contentPane.add(btnViewCourse);
        
        JButton btnViewAssignments = new JButton("View Assignments");
        btnViewAssignments.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnViewAssignments.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openViewAssignmensDialog();
        	}

			private void openViewAssignmensDialog() {
				// TODO Auto-generated method stub
			
			    JFrame viewAssignmentsFrame = new JFrame("View Assignments");
			    viewAssignmentsFrame.setBounds(100, 100, 800, 600);
			    JPanel viewAssignmentsPanel = new JPanel(new BorderLayout());
			    viewAssignmentsFrame.setContentPane(viewAssignmentsPanel);

			    //Retrieving and displaying assignments in a table
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

			        //Creating a JTable with the table model
			        JTable table = new JTable(tableModel);
			        JScrollPane scrollPane = new JScrollPane(table);
			        viewAssignmentsPanel.add(scrollPane, BorderLayout.CENTER);
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			    }

			    viewAssignmentsFrame.setVisible(true);			}
        });
        btnViewAssignments.setBounds(319, 331, 139, 41);
        contentPane.add(btnViewAssignments);
        
        
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
        newsTextArea.setToolTipText(""); // This line is not necessary, you can remove it

      
        JScrollPane newsScrollPane = new JScrollPane(newsTextArea);
        newsPanel.add(newsScrollPane, BorderLayout.CENTER);

      
        String sampleNews = "1. New course added.\n" +
                            "2. Assignment submission\n deadline extended.\n" +
                            "3. Exam schedule updated.\n";

      
        newsTextArea.setText(sampleNews);
        newsTextArea.setEditable(false); //Making the news text area read-only


       	
        
        JButton btnViewExams = new JButton("View Exams");
        btnViewExams.addActionListener(new ActionListener() {
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
        btnViewExams.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnViewExams.setBounds(564, 337, 139, 41);
        contentPane.add(btnViewExams);
                
                JLabel lblGrades = new JLabel("Grades");
                lblGrades.setForeground(Color.BLACK);
                lblGrades.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
                lblGrades.setBounds(53, 442, 128, 41);
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
                btnAssignGrade.setFont(new Font("Tahoma", Font.PLAIN, 13));
                btnAssignGrade.setBounds(53, 494, 120, 41);
                contentPane.add(btnAssignGrade);
                
                JButton btnRemoveGrade = new JButton("Remove Grade");
                btnRemoveGrade.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		openRemoveGradeDialog();
                	}

					private void openRemoveGradeDialog() {
						// TODO Auto-generated method stub
						
					    JFrame removeGradeFrame = new JFrame("Remove Grade");
					    removeGradeFrame.setBounds(100, 100, 400, 200);
					    JPanel removeGradePanel = new JPanel();
					    removeGradePanel.setLayout(null);
					    removeGradeFrame.setContentPane(removeGradePanel);

					
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
                btnRemoveGrade.setFont(new Font("Tahoma", Font.PLAIN, 13));
                btnRemoveGrade.setBounds(53, 556, 120, 41);
                contentPane.add(btnRemoveGrade);
                
                JButton btnEditCourses = new JButton("Edit Courses");
                btnEditCourses.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		openEditCourseDialog();
                	}

					private void openEditCourseDialog() {
						// TODO Auto-generated method stub
					
					    JFrame editCourseFrame = new JFrame("Edit Course");
					    editCourseFrame.setSize(400, 200);

					 
					    JPanel editCoursePanel = new JPanel();
					    editCourseFrame.getContentPane().add(editCoursePanel);

					 
					    JLabel lblCourseCode = new JLabel("Course Code:");
					    JTextField txtCourseCode = new JTextField(10);

					    JLabel lblNewCourseName = new JLabel("New Course Name:");
					    JTextField txtNewCourseName = new JTextField(20);

					    JLabel lblNewInstructor = new JLabel("New Instructor:");
					    JTextField txtNewInstructor = new JTextField(20);

					    JLabel lblNewLevel = new JLabel("New Level:");
					    JTextField txtNewLevel = new JTextField(5);

					  
					    editCoursePanel.add(lblCourseCode);
					    editCoursePanel.add(txtCourseCode);
					    editCoursePanel.add(lblNewCourseName);
					    editCoursePanel.add(txtNewCourseName);
					    editCoursePanel.add(lblNewInstructor);
					    editCoursePanel.add(txtNewInstructor);
					    editCoursePanel.add(lblNewLevel);
					    editCoursePanel.add(txtNewLevel);

					    //Adding button to update course
					    JButton btnUpdateCourse = new JButton("Update Course");
					    btnUpdateCourse.addActionListener(new ActionListener() {
					        public void actionPerformed(ActionEvent e) {
					            //Getting input values
					            String courseCode = txtCourseCode.getText();
					            String newCourseName = txtNewCourseName.getText();
					            String newInstructor = txtNewInstructor.getText();
					            int newLevel = Integer.parseInt(txtNewLevel.getText());
					            
					            //Calling editCourse method of CourseManagement
					            courseManagement.editCourse(courseCode, newCourseName, newInstructor, newLevel);
					            
					            //Closing the edit course dialog
					            editCourseFrame.dispose();
					        }
					    });
					    editCoursePanel.add(btnUpdateCourse);

					    //Setting dialog window visible
					    editCourseFrame.setVisible(true);

						
					}
                });
                btnEditCourses.setFont(new Font("Tahoma", Font.PLAIN, 13));
                btnEditCourses.setBounds(53, 393, 120, 41);
                contentPane.add(btnEditCourses);
                
                JButton btnGenerateReport = new JButton("Generate Report");
                btnGenerateReport.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		openGenerateReportDalog();
                	}

					private void openGenerateReportDalog() {
						JFrame dialogFrame = new JFrame("Generate Report");
					    dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					    //Creating components for the dialog
					    JLabel nameLabel = new JLabel("Enter Student Name:");
					    JTextField nameField = new JTextField(20);
					    JButton generateButton = new JButton("Generate");

					    //Adding action listener to the "Generate" button
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
                btnGenerateReport.setFont(new Font("Tahoma", Font.PLAIN, 13));
                btnGenerateReport.setBounds(53, 620, 120, 41);
                contentPane.add(btnGenerateReport);
                
                JButton btnLogOut = new JButton("Log out");
                btnLogOut.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		dispose();
                		
                		Login loginFrame = new Login();
                		loginFrame.setVisible(true);
                	}
                });
                btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 16));
                btnLogOut.setBounds(917, 133, 204, 49);
                contentPane.add(btnLogOut);

	 }
 }
