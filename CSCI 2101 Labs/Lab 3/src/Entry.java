/**
 * 		Author: Sam Harder
 * 		Entry Class
 * 		28 September 2016
 * 
 * 		This is an implementation of a simple entry class
 * 		intended to be used by the Database class to implement
 * 		a database in the form of a singly linked list. 
 * 
 * 		Each instance of the Entry class holds data (firstName,
 * 		lastName, age, isStudent) as well as a reference to the
 * 		next entry in the list (next). 
 */
public class Entry {
	
    // instance variables
	private String firstName;
	private String lastName;
	private int age;
	private boolean isStudent;
	private Entry next;
    
	// default constructor
	public Entry(){
		firstName = "";
		lastName = "";
		age = 0;
		isStudent = false;
	}
	
    
	// Constructor which specifies the data of the entry but not the next 
	// instance variable (this is determined when the entry is inserted 
	// into a list). 
	public Entry(String firstName, String lastName, int age, boolean isStudent){
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.isStudent = isStudent;
	}

	// Returns a new instance of the Entry class with identical
	// data (but no defined reference). This is necessary when
	// inserting the entry into multiple list (one item cannot 
	// reference two items at once). 
	public Entry getClone(){
		return new Entry(firstName, lastName, age, isStudent);
	}
	
	// Returns firstName
	public String getFirstName() {
		return firstName;
	}

	// Sets firstName
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Gets lastName
	public String getLastName() {
		return lastName;
	}

	// Sets lastName
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// Gets age
	public int getAge() {
		return age;
	}

	// Sets age
	public void setAge(int age) {
		this.age = age;
	}

	// Sets isStudent
	public boolean isStudent() {
		return isStudent;
	}

	// Returns isStudent
	public void setIsStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	// Returns the next reference
	public Entry getNext() {
		return next;
	}

	// Sets the next reference
	public void setNext(Entry next) {
		this.next = next;
	}
	
	
    // Returns a string describing the Entry's data
    // in the format indicated in the sample output
    // in the lab handout.
	public String toString(){
		String studentString;
		if(isStudent){
			studentString = "student";
		}
		else{
			studentString = "not a student";
		}
		return "Entry: " + firstName + " " + lastName + ", " + age + ", " +  studentString;
	}

	
	

}
