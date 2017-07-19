/**
 * 
 * 		Author: Sam Harder
 * 		Database Class 
 * 		28 September 2016
 * 
 * 		This class implements a Database of people in the 
 *		form of a singly linked list composed of instances
 *		of the Entry class. Entries are sorted lexicographically
 *		by last name. 
 *
 *		In addition to the constructor there are several methods
 *		which filter and print entries. Each of these methods'
 *		uses are explained in the documentation for that method. 
 *
 */

public class Database {
	
    // instance variables
	private Entry head = null; // First entry of the list
	private int size = 0; // initially the list is empty
    
    // default constructor only
	public Database(){
	}

	
    /**
     * 	Purpose: Inserts an entry into the database, preserving lexicographic order by last name.
			 	Inserting the entry we reassign the references of the entry
			 	that should go before and the entry which should go after.
			 	This is best shown with the diagram:
						prev -> curr 
			 			prev -> clone -> curr
     * 	Parameters: dataEntry, the entry you would like inserted
     * 	Special cases:
     * 		1. If the list is empty then the item is added to the front of the list.
     * 	Return Value: None.
     */
	public void insertEntry(Entry dataEntry){
		
		Entry clone = dataEntry.getClone();
		//  NOTE: We clone the original entry so that we can insert that 
		//  entry (or an identical one) into multiple databases. In the process
		//  of filtering we create new databases, which necessitate cloning. For
		//  example, if we have an entry object and we insert it into the 
		// 	database for students, and then also insert it into the database
		//  for people with last name "Yang" we cannot assign two values 
		// 	for the instance variable next. 
		 
		
		// If the entry will be inserted at the front of the list then the 
		// insertion is slightly different. Since it is at the head of the list
		// no entry references it. 
		if(isEmpty() || clone.getLastName().compareTo(head.getLastName()) < 0){
			clone.setNext(head);
			head = clone;
		}
		else {
			// curr starts one entry beyond head (head was already compared to dataEntry 
			// in the above if statement). 
			Entry curr = head.getNext();
			Entry prev = head;
			
			// Conditions for stopping the while:
			// 1. We have reached the end of the list without finding any entries 
			// our entry precedes lexicographically (in which case the item will be placed at the end).
			// or
			// 2. The entry we are inserting precedes the current entry in lexicographic order
			while(curr != null && !(clone.getLastName().compareTo(curr.getLastName()) < 0)){
				prev = curr;
				curr = curr.getNext();
			}
			// reassigns references to insert entry
			prev.setNext(clone);
			clone.setNext(curr);
		}
		size++; // increment the list size
	}
	
	// Returns true if the Database is empty
	public boolean isEmpty(){
		return size == 0;
	}
  
	
	/**
	 * 	
	 *  Purpose: Finds entries in the Database with matching last names
	 *  Parameters: lastName, the last name searched for
	 *  Return Value: None.
	 * 
	 */
	public void findLastName(String lastName){
		Entry curr = head;
		Database db = new Database();
		while(curr != null){
			if(curr.getLastName().equals(lastName)){
				db.insertEntry(curr);
			}
			curr = curr.getNext();
		}
		if(db.isEmpty()){
			System.out.println(lastName + " is not in the database.");
		}
		else{
			db.printEntries();
		}
	}


	/**
	 * 
	 * 	Purpose: Given a low age and high age specified by the parameters,
     * 			print out all entries whose ages are between the low age
     *  		and high age, inclusive. Indicate if no entries with in
     *			that range are found.
     *	Parameters: the low age and high age bounds on the search
     *	Return Value: None.
	 * 
	 */
	public void searchAgeRange(int minAge, int maxAge){
		// Prints the searching range in form "minAge-maxAge"
		System.out.println("People in the age range " + minAge + "-" + maxAge + ":");
		
		// curr reflects the current entry we are checking in the database
		Entry curr = head;
		// Create a new database to store valid entries
		Database db = new Database();
		while(curr != null){
			if(curr.getAge() >= minAge && curr.getAge() <= maxAge ){ // If curr is in the range
				db.insertEntry(curr);
			}
			curr = curr.getNext();
		}
		db.printEntries();
	}
	
	/**
	 * 	Purpose: prints entries from the database that are
	 * 			students. 
	 * 	Parameters: None.
	 * 	Return Value: None.
	 */
	public void printStudents(){
		
		System.out.println("Students: ");
		
		// curr reflects the current entry we are checking in the database
		Entry curr = head;
		// Create a new database to store valid entries
		Database db = new Database();
		
		// While we have not reached the end of the list
		while(curr != null){
			if(curr.isStudent()){
				db.insertEntry(curr);
			}
			curr = curr.getNext();
		}
		db.printEntries();
		
		// Notifies user if search came up empty
		if(db.isEmpty()){
			System.out.println("There are no students in the database.");
		}
	}
	
	
	/**
	 * 	Purpose: Prints the contents of the database, one per line. 
	 * 	Parameters: None.
	 * 	Return Value: None.
	 */
	public void printEntries(){
		// curr reflects the current entry we are printing
		Entry curr = head;
		
		// While we have not reached the end of the list
		while (curr != null) {
			System.out.println(curr.toString());
			curr = curr.getNext();
		}
		System.out.println();

	}
}
