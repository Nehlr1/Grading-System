package classes;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class GradingSystem {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//variable to get the value from the file where all the infomation are saved
		String username = null;
		String password = null;
		String id = null;
		String email = null;
		String subject_name = null;
		String subject_grade = null;
		String subject_credit = null;
		
		//variable to control while loops
		boolean pass = true;
		boolean portal_pass = true;
		boolean main_loop = true;
		boolean lecturer_loop = true;
		boolean student_loop = true;
		
		//Create objects of the necessary classes
		Lecturer lecturer = null;
		Student student = null;
		Subject subject = null;
		
		//create file objects where to write information and get information 
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		PrintWriter outFile = new PrintWriter(new FileWriter("D:\\\\Eclipse Workplace\\\\OOP_Project\\\\src\\\\classes\\\\lecturerdata.txt", true));
		PrintWriter outFileS = new PrintWriter(new FileWriter("D:\\\\Eclipse Workplace\\\\OOP_Project\\\\src\\\\classes\\\\studentdata.txt", true));
		Scanner inFile = new Scanner(new File("D:\\Eclipse Workplace\\OOP_Project\\src\\classes\\lecturerdata.txt"));
		Scanner inFileS = new Scanner(new File("D:\\Eclipse Workplace\\OOP_Project\\src\\classes\\studentdata.txt"));
		
		//create a key-value relationship to get the object using the username. Here username is the key and object is the value. Hashmap concept.
		HashMap<String, String> profile = new HashMap<String, String>();
		HashMap<String, Lecturer> lecturer_profile = new HashMap<String, Lecturer>();
		HashMap<String, Student> student_profile = new HashMap<String, Student>();
		
		//create vector to add file information
		Vector<String> fileInfo = new Vector<String>();
		Vector<String> fileInfoS = new Vector<String>();
		Vector<Student> studentList = new Vector<Student>();
		
		//store values from lecturerdata.txt file into the vector created above
		while(inFile.hasNext()) {
			String _username = inFile.next();
			String _password = inFile.next();
			String _id = inFile.next();
			String _email = inFile.next();
			String _subject = inFile.next();
			fileInfo.add(_username);
			fileInfo.add(_password);
			fileInfo.add(_id);
			fileInfo.add(_email);
			fileInfo.add(_subject);
			lecturer = new Lecturer(_username, _password, _id, _email);
			subject = new Subject(_subject);
			lecturer_profile.put(_username, lecturer);
			lecturer_profile.get(_username).addSubject(subject);
		}
		
		//store values from studentdata.txt file into the vector created above
		while(inFileS.hasNext()) {
			String _username = inFileS.next();
			String _password = inFileS.next();
			String _id = inFileS.next();
			String _email = inFileS.next();
			String _subject = inFileS.next();
			String _grade = inFileS.next();
			String _credit = inFileS.next();
			fileInfoS.add(_username);
			fileInfoS.add(_password);
			fileInfoS.add(_id);
			fileInfoS.add(_email);
			fileInfoS.add(_subject);
			student = new Student(_username, _password, _id, _email);
			studentList.add(student);
			subject = new Subject(_subject, _grade, Integer.parseInt(_credit));
			student_profile.put(_username, student);
			student_profile.get(_username).addSubject(_subject, _grade, Integer.parseInt(_credit));
		}
		
		//welcome message
		JOptionPane.showMessageDialog(null, "Welcome to Utm Grading System", "GradingSystem", JOptionPane.PLAIN_MESSAGE);
		
		//main loop starts
		while(main_loop) {
			//exception handling - if anything goes wrong the program ends with a message
			try {
			System.out.println("1. Lecturer");
			System.out.println("2. Student");
			System.out.println("3. Exit");
			System.out.printf("You are a - ");
			int key = input.nextInt();
			
			if(key==1) {
				while(portal_pass) {
					System.out.println("Lecturer Portal");
					System.out.println("1. Login    2. SignUp	3. EXIT");
					int value = input.nextInt();
					if(value == 1) {
						while(pass) {
							//login system
							username = JOptionPane.showInputDialog(null, "Username", "Login", JOptionPane.PLAIN_MESSAGE);
							if(fileInfo.contains(username)) {
								password = JOptionPane.showInputDialog(null, "Password", "Login", JOptionPane.PLAIN_MESSAGE);
								if(password.equals(fileInfo.get(fileInfo.indexOf(username) + 1))) {
									System.out.println("Successfully Login!");
									while(lecturer_loop) {
										System.out.println("1. Display Info\n2. Add student\n3. View Student List\n4. Log out\n");
										System.out.printf("Enter: ");
										int choice = input.nextInt();
										if(choice == 1) {
											lecturer_profile.get(username).display(); //display lecturer information
										}
										else if(choice == 2){ //add students to the section
											System.out.println("List of students who are taking " + lecturer_profile.get(username).getSubject().getSubjectName());
											for(int i=0; i<studentList.size(); i++) {
												for(int j=0; j<studentList.get(i).getSubjectList().size(); j++) {
													if(studentList.get(i).getSubjectList().get(j).getSubjectName().equals(lecturer_profile.get(username).getSubject().getSubjectName())) {
														System.out.println(studentList.get(i).getUsername());
													}
												}
											}
											System.out.printf("Enter Student Name: ");
											String stu_name = input.next();
											if(student_profile.containsKey(stu_name)) {
												lecturer_profile.get(username).addStudent(student_profile.get(stu_name));
												System.out.println("Successfully Added!");
											}
											else System.out.println("Enter valid name");
										}else if(choice == 3){ //view student's results
											System.out.printf("%-20s%-20s%-20s\n", "Student Name", "Student Matric", "Student Grade");
											for(int i=0; i<lecturer_profile.get(username).getStudentList().size(); i++) {
                                                	System.out.printf("%-20s%-20s%-20s\n", lecturer_profile.get(username).getStudentList().get(i).getUsername(), 
                                                			lecturer_profile.get(username).getStudentList().get(i).getID(), 
                                                			lecturer_profile.get(username).getStudentList().get(i).getSubjectGrade(lecturer_profile.get(username).getSubject().getSubjectName()));
											}
											System.out.println();
										}else {
											System.out.println("Successfully Logout...");
											lecturer_loop = false;
										}
									}
									pass = false;
								}else { //if password is wrong
									System.out.println("Incorrect Password. 1. Sign up or 2. Try again.");
									int _value = input.nextInt();
									if(_value == 1) break;
									else pass = true;
								}
							}else { //if username is wrong
								System.out.println("Incorrect Username. 1. Sign up or 2. Try again.");
								int _value = input.nextInt();
								if(_value == 1) break;
								else pass = true;
							}
						}
						System.out.println();

						continue;
					}else if(value == 2){
						while(pass) { //signup system
							username = JOptionPane.showInputDialog(null, "Username", "Login", JOptionPane.PLAIN_MESSAGE);
							if(fileInfo.contains(username)) {
								System.out.println("Username Exists. Please use another name"); //need to give unique username to create a new profile
								pass = true;
							}else pass = false;
						}
						password = JOptionPane.showInputDialog(null, "Password", "Login", JOptionPane.PLAIN_MESSAGE);
						id = JOptionPane.showInputDialog(null, "ID", "Login", JOptionPane.PLAIN_MESSAGE);
						email = JOptionPane.showInputDialog(null, "Email", "Login", JOptionPane.PLAIN_MESSAGE);
						subject_name = JOptionPane.showInputDialog(null, "Which Subject you tought?", "Login", JOptionPane.PLAIN_MESSAGE);
						profile.put(username, password);
						lecturer = new Lecturer(username, password, id, email);
						subject = new Subject(subject_name);
						lecturer_profile.put(username, lecturer);
						
					}else {
						break;
					}
					lecturer_profile.get(username).addSubject(subject);
					outFile.printf("%-20s%-20s%-20s%-30s%-20s\n", username, password, id, email, subject_name);	//print information to the lecturerdata.txt file
					break;
				}
			}
			else if(key==2) { //student portal
				while(portal_pass) {
					System.out.println("Student Portal");
					System.out.println("1. Login    2. SignUp	3. Exit");
					int value = input.nextInt();
					if(value == 1) {
						while(pass) { //login system
							username = JOptionPane.showInputDialog(null, "Username", "Login", JOptionPane.PLAIN_MESSAGE);
							if(fileInfoS.contains(username)) {
								password = JOptionPane.showInputDialog(null, "Password", "Login", JOptionPane.PLAIN_MESSAGE);
								if(password.equals(fileInfoS.get(fileInfoS.indexOf(username) + 1))) {
									System.out.println("Successfully Login!");
									while(student_loop) {
										System.out.println("1. Display Info\n2. Add subject\n3. Logout\n");
										System.out.printf("Enter: ");
										int choice = input.nextInt();
										if(choice == 1)
											student_profile.get(username).display();
										else if(choice == 2){ //add subjects
											System.out.printf("Enter Subject Name: ");
											String sub_name = input.next();
											System.out.printf("Enter Subject Grade: ");
											String s_gade = input.next();
											System.out.println("Enter Subject Credit Hour: ");
											int s_credit = input.nextInt();
											student_profile.get(username).addSubject(sub_name, s_gade, s_credit);
											System.out.println("Successfully Added!");
										}else {
											System.out.println("Successfully Logout..."); //logout
											student_loop = false;
										}
									}
									pass = false;
								}else {
									System.out.println("Incorrect Password. 1. Sign up or 2. Try again.");
									int _value = input.nextInt();
									if(_value == 1) break;
									else pass = true;
								}
							}else {
								System.out.println("Incorrect Username. 1. Sign up or 2. Try again.");
								int _value = input.nextInt();
								if(_value == 1) break;
								else pass = true;
							}
						}
						System.out.println();

						continue;
					}else if(value == 2){
						while(pass) { //signup system
							username = JOptionPane.showInputDialog(null, "Username", "Signup", JOptionPane.PLAIN_MESSAGE);
							if(fileInfoS.contains(username)) {
								System.out.println("Username Exists. Please use another name");
								pass = true;
							}else pass = false;
						}
						password = JOptionPane.showInputDialog(null, "Password", "Signup", JOptionPane.PLAIN_MESSAGE);
						id = JOptionPane.showInputDialog(null, "ID", "Signup", JOptionPane.PLAIN_MESSAGE);
						email = JOptionPane.showInputDialog(null, "Email", "Signup", JOptionPane.PLAIN_MESSAGE);
						subject_name = JOptionPane.showInputDialog(null, "Which Subject you take?", "Signup", JOptionPane.PLAIN_MESSAGE);
						subject_grade = JOptionPane.showInputDialog(null, "Grade of the Subject", "Signup", JOptionPane.PLAIN_MESSAGE);
						subject_credit = JOptionPane.showInputDialog(null, "Credit Hour of the Subject", "Signup", JOptionPane.PLAIN_MESSAGE);
						profile.put(username, password);
						student = new Student(username, password, id, email);
						student_profile.put(username, student);
						student_profile.get(username).addSubject(subject_name, subject_grade, Integer.parseInt(subject_credit));						
						//save information to the studentdata.txt file
						outFileS.printf("%-20s%-20s%-20s%-30s%-20s%-20s%-20s\n", username, password, id, email, subject_name, subject_grade, subject_credit);	
						break;
					}else {
						break;
					}
				}
			}else {
				System.out.println("Succesfully EXIT");
				break;
			}
		}catch(Exception e) { //handle the exception
			System.out.println("Wrong Input (You typed character(a,b..) instead of numer(1,2..)).\nApplication closed. \nTry running again.");
			e.printStackTrace();
			break;
		}
		}
		//close all file
		outFile.close();
		inFile.close();
		outFileS.close();
		inFileS.close();
		
	}

}
