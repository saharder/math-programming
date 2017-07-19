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
		// must be greater than zero

        // Create the database by calling the createDatabase method below

        // Print the database

        // Get a last name to search for and call the findLastName method
        // in the Database class

		// Get an age range from the user; both ages must be from MIN_AGE
        // to MAX_AGE, and the starting age must be less than the ending age.
        // Call the searchAgeRange method in the Database class
		
        // Print out the student entries by calling the printStudents
        // method in the Database class


	}

    // createDatabase method:
    // but the createDatabase method takes as input a single
    // parameter specifiying the number of entries in the
    // database.  It creates that number of entries, each
    // containing randomly generated data, and returns a
    // Database object
    //
    // THIS IS NOT IN THE CORRRECT METHOD SUMMARY FORMAT.
    // YOU NEED TO DO THAT.






}


