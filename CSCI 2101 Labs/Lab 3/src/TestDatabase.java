/**
 * 		Author: Sam Harder
 * 		TestDatabase Class
 * 		28 September 2016
 * 
 * 		This program tests the Database Class using user input. There
 * 		are four basic exercises:
 * 			1) Creating and printing a database of a length specified
 * 			   by the user. 
 * 			2) Searching for a user specified last name in the database 
 * 			   and printing the results. 
 * 			3) Searching for entries with ages between user specified ages
 * 			   and printing those entries
 * 			4) Printing entries which are students. 
 * 		Specific cases for each exercise are documented within the code. 
 * 
 * 		In addition to the exercises there is one method, createDatabase,
 * 		which creates an instance of the database class and fills it with
 * 		randomized entries generated from the TestDatabase class's instance
 * 		variables. 
 *
 */


import java.util.Scanner;
import java.util.Random;
public class TestDatabase {

    // Range of valid ages.
	private static final int MIN_AGE = 1;
	private static final int MAX_AGE = 120;

    // Array of first names for generating random first names.
	private static String[] firstNames = 		
		{ "Abir", "Angela", "Kanye", "Sonya", "DeRay", "Robert", "Svetlana", "Karl",
				"Sujit", "Niejls", "Alejandro", "Itzhak", "Emily", "Terik", "Nevin", "Linus", "Ebba", 
				"Gwyneth", "Clarissa", "Abbud", "Eric", "Alexei", "Michele", "Artemisia", "Ansari" };
    
    // Array of last names for generating random last names.
	private static String[] lastNames =
		{ "Borisov", "Johnson", "Ben-Haim", "Kourakis", "Elfasi", "Newton", "Shammas",
				"Kuznetsov", "Marinescu", "Voltaire", "Mckesson", "Ross", "Yang", "Awad", "Lopez",
				"Kraft", "Roitman", "Christakos", "Karlsson", "Freidhof", "Schumacher", "Faure" };

    // To get input and generate random values.
	private static Scanner scan = new Scanner(System.in);
	private static Random rand = new Random();

	public static void main(String[] args) {

		// Get the number of entries the user wants in the database;
		// must be greater than zero. If input is not greater than zero
		// it prompts user again. 
		int numEntries = (-1);
		while(!(numEntries > 0)){
			System.out.print("Enter the number of database entries you would like (must be > 0): ");
			numEntries = scan.nextInt();
		}

        // Creates a database of size numEntries filled with randomly generated entries
		Database db = createDatabase(numEntries);
		System.out.println();
		
        // Prints the database
		System.out.println("Database: ");
		db.printEntries();

        // Gets a last name to search for
		System.out.print("Name to search for: ");
		String lastName = scan.next();
		// Finds entries with matching last names and prints them.
		db.findLastName(lastName);
		
		System.out.println();
		
		// Sets age variables such that the program always
		// enters the while loop to ask the user for ages. 
		int	startAge = -1;
		int endAge = 200;
		
		// This while loop gets the user's inputs for
		// the bounds on the ageRange search. It also
		// prevents the user from inputing values outside of the
		// MIN_AGE to MAX_AGE interval defined by the constant
		// instance variables, as well as inputing a starting age
		// that is larger than the ending age. If either of these
		// errors are detected, they are printed to the console. 
		while((startAge < MIN_AGE) || (endAge > MAX_AGE)
				|| (endAge < startAge)){
			
			System.out.print("Enter starting age: ");
			startAge = scan.nextInt();
			System.out.print("Enter ending age: ");
			endAge = scan.nextInt();
			
			// Checks if both startAge and endAge are within the 
			// bounds set by MIN_AGE and MAX_AGE.
			if((startAge < MIN_AGE) || (startAge > MAX_AGE) 
					|| (endAge > MAX_AGE) || (endAge < MIN_AGE)){
				System.out.println("All ages must be from 1 to 120 inclusive.");
			}
			// Checks that endAge is not less than startAge
			if(endAge < startAge){
				System.out.println("Starting age must be less than ending age.");
			}
		}
		// Search for entries within the age bounds set by the user in the
		// above while loop. Prints results.
		db.searchAgeRange(startAge, endAge);
		
        // Print out the student entries by calling the printStudents
        // method in the Database class
		
		db.printStudents();
	}

    /**
     * Purpose: Creates a database of size numEntries with randomized 
     * 			contents. Used for testing features of the Database
     * 			class. 
     * Parameters: numEntries, the number of entries you wish to be in the 
     * 			   database. 
	 * Returns: A database of size numEntries filled with random entries
	 * 			generated from the instance variables of this class.  
     */
	public static Database createDatabase(int numEntries){
		// Create new empty database
		Database db = new Database();
		
		// Fill that database with entries of randomized names, ages, and students
		for(int i = 0; i < numEntries; i++){
			// generates integer age between MIN_AGE and MAX_AGE
			int randAge = rand.nextInt(MAX_AGE) + MIN_AGE; 
			// randomly selects name from array firstNames
			String randFirstName = firstNames[rand.nextInt(firstNames.length)]; 
			// randomly selects name from array lastNames
			String randLastName = lastNames[rand.nextInt(lastNames.length)];
			// randomly creates true or false boolean
			boolean isStudent = rand.nextBoolean();
			
			// inserts a new Entry into the list with the above generated parameters
			db.insertEntry(new Entry(randFirstName, randLastName, randAge, isStudent));
		}
		return db;
	}




}


