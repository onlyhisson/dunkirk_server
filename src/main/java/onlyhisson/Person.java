package onlyhisson;

public class Person {

	private final String name;
	private final String address;
	private final String hobby;
	private final String nationality;
	

	public Person(String name, String address, String hobby, String nationality) {
		this.name = name;
		this.address = address;
		this.hobby = hobby;
		this.nationality = nationality;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getHobby() {
		return hobby;
	}
	
	public String getNationality() {
		return nationality;
	}
}

