package classes;

public class Subject {
	private String subject_name;
	private String subject_grade;
	private int subject_credit;
	
	Subject(){}
	Subject(String s_n){
		subject_name = s_n;
	}
	Subject(String s_n, String s_g, int s_c){
		subject_name = s_n;
		subject_grade = s_g;
		subject_credit = s_c;
	}
	public String getSubjectName() {return subject_name;}
	public String getSubjectGrade() {return subject_grade;}
	public int getSubjectCredit() {return subject_credit;}
	public void setSubjectGrade(String grade) {subject_grade = grade;}
	public void setSubjectCredit(int s_c) {subject_credit = s_c;}
}
