package classes;

public class Person {
	private String username;
	private String password;
	private String id;
	private String email;
	
	Person(String u, String p, String i, String e){
		username = u;
		password = p;
		id = i;
		email = e;
	}
	//polimorphism
	public String getUsername() {return username;}
	public String getPassword() {return password;}
	public String getID() {return id;}
	public String getEmail() {return email;}
}
