package classes;
import java.util.Vector;

public class Student extends Person implements Pupil{
	private double cgpa = 0.0;
	private Vector<Subject> subjects; //aggregation
	
	Student(String u, String p, String i, String e){
		super(u, p, i, e);
		subjects = new Vector<Subject>();
	}
	public Vector<Subject> getSubjectList(){return subjects;}
	public void addSubject(String n, String g, int c) {
		subjects.add(new Subject(n, g, c));
	}
	public String getSubjectGrade(String s_n) {
		String n = null;
		for(int i=0; i<subjects.size(); i++) {
			if(subjects.get(i).getSubjectName().equals(s_n))
				n = subjects.get(i).getSubjectGrade();
		}
		return n;
	}
	public double calcCGPA() { //calculate student's cgpa
		int totalCredit = 0;
		double gradePoint;
		double total = 0.0;
		for(int i=0; i<subjects.size(); i++) {
			if(subjects.get(i).getSubjectGrade().equals("A")) gradePoint = 4.00;
			else if(subjects.get(i).getSubjectGrade().equals("A-")) gradePoint = 3.67;
			else if(subjects.get(i).getSubjectGrade().equals("B+")) gradePoint = 3.33;
			else if(subjects.get(i).getSubjectGrade().equals("B")) gradePoint = 3.00;
			else if(subjects.get(i).getSubjectGrade().equals("B-")) gradePoint = 2.67;
			else if(subjects.get(i).getSubjectGrade().equals("C+")) gradePoint = 2.33;
			else if(subjects.get(i).getSubjectGrade().equals("C")) gradePoint = 2.00;
			else if(subjects.get(i).getSubjectGrade().equals("D")) gradePoint = 1.00;
			else if(subjects.get(i).getSubjectGrade().equals("F")) gradePoint = 0.00;
			else gradePoint = 0.00;
			total += gradePoint*subjects.get(i).getSubjectCredit();
			totalCredit += subjects.get(i).getSubjectCredit();
		}
		cgpa = total/totalCredit;
		return cgpa;
	}
	public void display() { //diplay student's information
		System.out.println("Student Name: " + getUsername());
		System.out.println("Student ID: " + getID());
		System.out.println("Student Email: " + getEmail());
		System.out.printf("Student CGPA: %.2f", calcCGPA());
		System.out.println("\n\nSubject List - ");
		System.out.printf("%-20s%-20s\n", "Subject Name", "Grade");
		for(int i=0; i<subjects.size(); i++) {
			System.out.printf("%-20s%-20s\n", subjects.get(i).getSubjectName(), subjects.get(i).getSubjectGrade());
		}
		System.out.println();
	}
}
