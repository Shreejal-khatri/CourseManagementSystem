import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
//Created  by: Shreejal_Khatri
//Group: L5CG7
//University ID: 2358168 

public class CourseManagement {
    
    private static final String DB_URL = "jdbc:mysql://localhost/tutorial";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    //Method to add a new course to the database
    public void addCourse(String courseName, String courseCode, String instructor, int level) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO courses (course_name, course_code, instructor,level) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, courseName);
            statement.setString(2, courseCode);
            statement.setString(3, instructor);
			statement.setInt(4, level); // Set the level parameter
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Course added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    //Method to delete a course from the database
    public void deleteCourse(String courseCode) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "DELETE FROM courses WHERE course_code = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, courseCode);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Course deleted successfully!");
            } else {
                System.out.println("No course found with the given course code.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 //Method to show all courses in the database and return them as a list of String arrays
    public ArrayList<String[]> showCourses() {
        ArrayList<String[]> courses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT course_name, course_code, instructor, level FROM courses";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String courseCode = resultSet.getString("course_code");
                String instructor = resultSet.getString("instructor");
                String level = resultSet.getString("level");
                String[] course = {courseName, courseCode, instructor, level};
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    
 //Method to edit an existing course in the database
    public void editCourse(String courseCode, String newCourseName, String newInstructor, int newLevel) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE courses SET course_name = ?, instructor = ?, level = ? WHERE course_code = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newCourseName);
            statement.setString(2, newInstructor);
            statement.setInt(3, newLevel);
            statement.setString(4, courseCode);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Course updated successfully!");
              
            } else {
                System.out.println("No course found with the given course code.");
             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Method to enroll a student in a course
    public void enrollStudent(String courseCode, String studentName) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO student_courses (course_code, student_name) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, courseCode);
            statement.setString(2, studentName);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student enrolled in the course successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



	public void addAssignment(String assignmentName, String courseCode, Date deadline) {
		// TODO Auto-generated method stub
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO assignments (assignment_name, course_code, deadline) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, assignmentName);
            statement.setString(2, courseCode);
            statement.setDate(3, deadline);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Assignment added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
		
	}
	
	public void deleteAssignment(String assignmentCode) {
	    try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
	        String sql = "DELETE FROM assignments WHERE course_code = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, assignmentCode);
	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Assignment deleted successfully!");
	        } else {
	            System.out.println("No assignment found with the given assignment code.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void addExam(String examName, String courseCode, Date examDate, double durationHours) {
	    try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
	        String sql = "INSERT INTO exams (exam_name, course_code, exam_date, duration_hours) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, examName);
	        statement.setString(2, courseCode);
	        statement.setDate(3, examDate);
	        statement.setDouble(4, durationHours);
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("Exam added successfully!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	//Method to show all exams in the database
	public DefaultTableModel showExams() {
		 DefaultTableModel model = new DefaultTableModel();
		    // Add columns to the model
		    model.addColumn("Exam Name");
		    model.addColumn("Course Code");
		    model.addColumn("Exam Date");
		    model.addColumn("Duration Hours");

		    try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
		        String sql = "SELECT exam_name, course_code, exam_date, duration_hours FROM exams";
		        PreparedStatement statement = connection.prepareStatement(sql);
		        ResultSet resultSet = statement.executeQuery();
		        while (resultSet.next()) {
		            // Retrieve data from the result set and add it to the model
		            String examName = resultSet.getString("exam_name");
		            String courseCode = resultSet.getString("course_code");
		            Date examDate = resultSet.getDate("exam_date");
		            int durationHours = resultSet.getInt("duration_hours");
		            model.addRow(new Object[]{examName, courseCode, examDate, durationHours});
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return model;

	}
	
	public void deleteExam(String examName) {
	    try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
	        String sql = "DELETE FROM exams WHERE exam_name = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, examName);
	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Exam deleted successfully!");
	        } else {
	            System.out.println("No exam found with the name: " + examName);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void assignGrade(String courseName, String courseCode, String studentName, double grade) {
	    try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
	        String sql = "INSERT INTO grades (course_name, course_code, student_name, grade) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, courseName);
	        statement.setString(2, courseCode);
	        statement.setString(3, studentName);
	        statement.setDouble(4, grade);
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("Grade assigned successfully!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public void removeGrade(String courseName, String courseCode, String studentName) {
	    try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
	        String sql = "DELETE FROM grades WHERE course_name = ? AND course_code = ? AND student_name = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, courseName);
	        statement.setString(2, courseCode);
	        statement.setString(3, studentName);
	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Grade removed successfully!");
	        } else {
	            System.out.println("No grade found for the specified assignment, course, and student.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	//Method to retrieve grades for a specific student from the database
    public ArrayList<Object[]> getStudentGrades(String studentName) {
        ArrayList<Object[]> studentGrades = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT course_name, course_code, student_name, grade FROM grades WHERE student_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Object[] gradesRow = new Object[4];
                gradesRow[0] = resultSet.getString("course_name");
                gradesRow[1] = resultSet.getString("course_code");
                gradesRow[2] = resultSet.getString("student_name");
                gradesRow[3] = resultSet.getDouble("grade");
                studentGrades.add(gradesRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentGrades;
    }
    
 //Method to generate a report/result slip for a student

    	public String generateResultSlip(String studentName) {
    	    //Retrieving grades for the student
    	    ArrayList<Object[]> studentGrades = getStudentGrades(studentName);

    	    //Constructing the result slip using StringBuilder
    	    StringBuilder resultSlip = new StringBuilder();
    	    resultSlip.append("Result Slip for Student: ").append(studentName).append("\n");
    	    resultSlip.append("-----------------------------------------\n");
    	    resultSlip.append(" Course Name | Course Code | Grade\n");
    	    resultSlip.append("-----------------------------------------\n");

    	    //Iterating through the grades and append module details to the result slip
    	    for (Object[] gradesRow : studentGrades) {
    	        String courseName = (String) gradesRow[0]; // Modify here to get course name
    	        String courseCode = (String) gradesRow[1];
    	        double grade = (double) gradesRow[3];
    	        resultSlip.append(courseName).append(" | ").append(courseCode).append(" | ").append(grade).append("\n");
    	    }

    	    //Calculating average grade and append to the result slip
    	    double averageGrade = calculateAverageGrade(studentGrades);
    	    resultSlip.append("-----------------------------------------\n");
    	    resultSlip.append("Average Grade: ").append(averageGrade).append("\n");

    	    //Determining decision based on average grade and append it to the result slip
    	    if (averageGrade >= 60.0) {
    	        resultSlip.append("Decision: Progress to the next level.\n");
    	    } else {
    	        resultSlip.append("Decision: Retain in the current level.\n");
    	    }

    	    //Return the result slip as a String
    	    return resultSlip.toString();

    }
    
    //Method to calculate average grade for a student
    private double calculateAverageGrade(ArrayList<Object[]> studentGrades) {
        // Calculate average grade based on the grades retrieved
        double totalGrade = 0.0;
        for (Object[] gradesRow : studentGrades) {
            double grade = (double) gradesRow[3];
            totalGrade += grade;
        }
        return totalGrade / studentGrades.size();
    }



	public int getCoursesEnrolledCount(String studentName) {
		// TODO Auto-generated method stub
        int count = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT COUNT(*) AS count FROM student_courses WHERE student_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
  
	}
	 //Method to get enrolled students for a specific course
    public ArrayList<String[]> getEnrolledStudents(String courseCode) {
        ArrayList<String[]> enrolledStudents = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT student_name, enrollment_id FROM student_courses WHERE course_code = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, courseCode);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String studentName = resultSet.getString("student_name");
                String enrollmentID = resultSet.getString("enrollment_id");
                enrolledStudents.add(new String[]{studentName, enrollmentID});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrolledStudents;
    }








	




	public void disenrollStudent(String courseCode, String studentName) {
		// TODO Auto-generated method stub
		try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
	        String sql = "DELETE FROM student_courses WHERE course_code = ? AND student_name = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, courseCode);
	        statement.setString(2, studentName);
	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Student disenrolled from the course successfully!");
	        } else {
	            System.out.println("No enrollment found for the student in the specified course.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	//Method to view enrolled courses for a specific student
    public ArrayList<String[]> viewEnrolledStudents(String studentName) {
        ArrayList<String[]> enrolledCourses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT courses.course_name, courses.course_code, courses.instructor " +
                         "FROM courses " +
                         "JOIN student_courses ON courses.course_code = student_courses.course_code " +
                         "WHERE student_courses.student_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String courseCode = resultSet.getString("course_code");
                String instructor = resultSet.getString("instructor");
                enrolledCourses.add(new String[]{courseName, courseCode, instructor});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrolledCourses;
    }
    
    




		
     



	
	

        
    

    
 








	
	}

