package classes;
import java.util.Vector;

public class Lecturer extends Person implements Pupil{
	private Subject subject; //aggregation
	private Vector<Student> students; //aggregation
	private double total = 0.00;
	private double successCounter;
	private int count = 0;
	
	Lecturer(String u, String p, String i, String e){
		super(u, p, i, e);
		subject = new Subject();
		students = new Vector<Student>();
	}
	public Subject getSubject(){return subject;}
	public void addStudent(Student s) {students.add(s);}
	public Vector<Student> getStudentList(){return students;}
	public void addSubject(Subject s) {subject = s;}
	public double OverallGrade() { //calculate the overall grade of the lecturer
		for(int i=0; i<students.size(); i++) {
			for(int j=0; j<students.get(i).getSubjectList().size(); j++) {
				if(students.get(i).getSubjectList().get(j).getSubjectName().equals(subject.getSubjectName())) {
					count++;
					if(students.get(i).getSubjectList().get(j).getSubjectGrade().equals("A")) {
						total += 4.00;	
					}else if(students.get(i).getSubjectList().get(j).getSubjectGrade().equals("A-")) {
						total += 3.67;
					}else if(students.get(i).getSubjectList().get(j).getSubjectGrade().equals("B+")) {
						total += 3.33;
					}else if(students.get(i).getSubjectList().get(j).getSubjectGrade().equals("B")) {
						total += 3.00;
					}else if(students.get(i).getSubjectList().get(j).getSubjectGrade().equals("B-")) {
						total += 2.67;
					}else if(students.get(i).getSubjectList().get(j).getSubjectGrade().equals("C+")) {
						total += 2.33;
					}else if(students.get(i).getSubjectList().get(j).getSubjectGrade().equals("C")) {
						total += 2.00;
					}else if(students.get(i).getSubjectList().get(j).getSubjectGrade().equals("D")) {
						total += 1.00;
					}else if(students.get(i).getSubjectList().get(j).getSubjectGrade().equals("F")) {
						total += 0.00;
					}else
						total = 0.00;
				}
			}
		}
		return total/count;
	}
	public double successRate() { //calculate the success rate of the lecturer
		if(students.size() > 0) {
			successCounter = 0.00;
			for(int i=0; i<students.size(); i++) {
				for(int j=0; j<students.get(i).getSubjectList().size(); j++) {
					if(students.get(i).getSubjectList().get(j).getSubjectName().equals(subject.getSubjectName())) {
						String _grade = students.get(i).getSubjectList().get(j).getSubjectGrade();
						if(_grade.equals("A") || _grade.equals("A-") || _grade.equals("B+")) {
							successCounter++;
						}else
							continue;
					}
				}
			}
			return (successCounter/students.size())*100;
		}else
			return 0.00;
	}
	public void display() { //display the information of the lecturer
		System.out.println("Lecture Name: " + getUsername());
		System.out.println("Lecturer ID: " + getID());
		System.out.println("Lecturer Email: " + getEmail());
		System.out.println("Subject: " + subject.getSubjectName());
		System.out.println("Number Of Students: " + students.size());
		System.out.printf("Overall Grade: %.2f\n", OverallGrade());
		System.out.printf("Success Rate: %.2f", successRate());
		System.out.printf("%s", "%");
		System.out.println();
		System.out.println();
	}
}
