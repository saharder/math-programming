/**
 * 		
 * 		Author: Sam Harder
 * 		Lab 1: Sorting Integers
 * 		14 September 2016
 * 
 * 		This program contains implementations of two 
 * 		types of sorts, selection sort and insertion sort.
 * 		There are also several smaller methods which 
 * 		support these two sort implementations, which are 
 * 		explained in detail in the documentation for those 
 * 		methods. 
 * 		
 * 		The user can specify a random array of any length to be
 * 		sorted, as well as whether or not to print the array
 * 		before and after each sort. There is also an already
 * 		sorted array generated of the same length with similar
 * 		options for printing. Additionally, the program will
 * 		time each execution of a sort and print the sort duration 
 * 		(regardless of whether or not the user opts to print arrays
 * 		and whether or not the arrays are sorted or unsorted). 
 * 
 *			
 */

import java.util.Random;
import java.util.Scanner;

public class IntegerSorter {
	
	// Constants for random number array generation exercise
	private static final int MAX_RAND_NUM = 100;
	private static final int MIN_RAND_NUM = -100;
	
	// Constants for conversion of nanoseconds to seconds
	private static final double NUM_NANOS_IN_SEC = 1000000000.0;
	
	// Constants to clarify user choices
	private static final int YES = 1;
	private static final int NO = 0;
	
	// To read in values from the keyboard and generate random numbers
	private static Scanner scan = new Scanner(System.in);
	private static Random rand = new Random();
	
	public static void main(String args[]){
		
		// Forces the program to enter the while loop
		// in which user selects array length.
		int numToSort = -1;
		
		// Makes sure the user selects valid amount of numbers to sort
		while(!(numToSort >= 0)){
			System.out.print("Enter the number of integers you would like to sort (must be >=0): ");
			numToSort = scan.nextInt();
		}
	
		
		int printArray = -1; // Negative value forces entry into while loop
		
		// Asks user whether or not to print the arrays 
		// and makes sure user selects YES or NO (1 or 0) 
		while(!(printArray == YES || printArray == NO)){
			System.out.print("Would you like to print the lists? (0 = NO, 1 = YES): ");
			printArray = scan.nextInt();
		}
		
		
		//  if user chose to print the arrays
		if (printArray == YES) {
			
			// NOTE: Creates new random array for next sort. This is required because 
			// the array is mutable, therefore the call of sortTimer alters
			// randArray even though nothing is returned. This is what enables 
			// the call printArray(randArray) to print different values without 
			// any reassignment of randArray. 
			
			double duration; 
			
			// Creates random array of length numToSort bounded by [-100, 100) 
			int[] randArray = randomArrayGenerator(numToSort, MIN_RAND_NUM, MAX_RAND_NUM);
			// creates a copy of randArray so we can use the same input for both sorts
			// since randArray is mutable and is sorted by the sort call
			int[] randArrayCopy = randArray.clone(); 
			
			// Prints results for selection sort on an unsorted array
			System.out.println("\nUnsorted Random Numbers: ");
			printArray(randArray); // print unsorted array
			duration = sortTimer("selection", randArray); // sorts and times sort
			System.out.println("Sorted: ");
			printArray(randArray); // print sorted array. 

			System.out.println("\nSelection Sort for random numbers: " + duration + "\n");

			// Prints results for insertion sort on an unsorted array
			System.out.println("\nUnsorted Random Numbers: ");
			printArray(randArrayCopy); // print unsorted random array
			duration = sortTimer("insertion", randArrayCopy); // sorts and times sort
			System.out.println("Sorted: ");
			printArray(randArrayCopy); // print sorted array

			System.out.println("\nInsertion Sort for random numbers: " + duration + "\n");

			// Generates a sorted array of length numToSort
			int[] sortedArray = sortedArrayGenerator(numToSort);

			// Prints results for insertion sort on a sorted array
			System.out.println("Already Sorted Numbers: ");
			printArray(sortedArray); // print already sorted array
			duration = sortTimer("selection",sortedArray); // sorts and times sort
			System.out.println("Sorted: ");
			printArray(sortedArray); // print sorted array

			System.out.println("\nSelection Sort for already sorted numbers: " + duration + "\n");
			
			// Prints results for insertion sort on a sorted array
			System.out.println("Already Sorted Numbers: ");
			printArray(sortedArray);
			duration = sortTimer("insertion",randArray); // sorts and times sort
			System.out.println("Sorted: ");
			printArray(sortedArray);

			System.out.println("\nInsertion Sort for already sorted numbers: " + duration + "\n");
			
		}
		// If user chose not to print the arrays
		else{
			double duration;
			// Creates random array of length numToSort bounded by [-100, 100) 
			int[] randArray = randomArrayGenerator(numToSort, MIN_RAND_NUM, MAX_RAND_NUM);
			int[] randArrayCopy = randArray.clone(); // creates a copy of randArray so we 
													 // can use the same input for both sorts

			duration = sortTimer("selection",randArray); // sorts and times sort
			System.out.println("\nSelection Sort for random numbers: " + duration + "\n");
			
			duration = sortTimer("insertion",randArrayCopy); // sorts and times sort
			System.out.println("\nInsertion Sort for random numbers: " + duration + "\n");
			
			int[] sortedArray = sortedArrayGenerator(numToSort);
			
			duration = sortTimer("selection",sortedArray); // sorts and times sort
			System.out.println("\nSelection Sort for already sorted numbers: " + duration + "\n");
			
			duration = sortTimer("insertion",sortedArray); // sorts and times sort
			System.out.println("\nInsertion Sort for already sorted numbers: " + duration + "\n");

		}
		
	}
	
	/**
	 * Purpose: This finds the index of the maximum integer
	 * 			in the first numItems terms of intArray
	 * Parameters: The integer array (intArray) and the number of terms
	 * 			   (starting from zero) to look for the max in. 
	 * Return Value: The index of the maximum integer in the first 
	 * 				 numItems terms of intArray. 
	 * NOTE: I start my search for the max at the end of the subsection of the 
	 * 		array we are examining to save time when we execute selection sort
	 * 		on already sorted arrays. If we always start at index = 0, and iterate
	 * 		from start to finish then, in an already sorted list, we are 
	 * 		updating our indexOfMax for every successive run of the for loop. 
	 * 		If we start from the end of the already sorted list, then we find the 
	 * 		actual max in our first run of the loop and do not have to update the
	 * 		indexOfMax again. This saves time. 
	 */
	private static int findIndexOfMax(int[] intArray, int numItems){
		// Initialize indexOfMax to the last index of the subsection
		// of the array we are examining
		int indexOfMax = numItems - 1;
		
		// Iterates back through the array, comparing each term to
		// the value at the indexOfMax. Updates indexOfMax if
		// a term is larger than the value at indexOfMax. 
		for(int i = numItems - 1; i > -1; --i){
			if(intArray[i] > intArray[indexOfMax]){
				indexOfMax = i;
			}
		}
		return indexOfMax;
	}
	
	
	/**
	 * Purpose: Prints array [a1,a2,...,an] on one line with each
	 * 			term separated by a space. e.g.
	 * 				a1 a2 ... an
	 * Parameters: The array to be printed. 
	 * Return Value: Nothing is returned. 
	 */
	public static void printArray(int[] intArray){
		// Iterates through the array printing each term
		// separated by a space
		for(int i: intArray){
			System.out.print(i + " ");
		}
		// Moves to next line so next print statement called
		// in the program prints to the next line. 
		System.out.println(); 
	}
	
	/**
	 * Purpose: creates an array of random integers bounded
	 * 			on top and bottom (by lowBound and highBound)
	 * Parameters: The length of the random array as well as the
	 * 			   lower and upper bound. 
	 * 			   NOTE: Assumes arrayLength >= 0;
	 * Return Value: An array of random integers
	 */
	public static int[] randomArrayGenerator(int arrayLength, int lowBound,
			int highBound){
		
		int[] randList = new int[arrayLength];
		for(int i = 0; i < arrayLength; i++){
			randList[i] = lowBound + rand.nextInt(highBound - lowBound + 1);
		}
		return randList;
		
	}
	
	/**
	 * Purpose: Creates an array sorted in ascending order
	 * Parameters: the length of the sorted array 
	 * Return Value: An integer array sorted in ascending order
	 */
	public static int[] sortedArrayGenerator(int arrayLength){
		// Generates empty array to be filled
		int[] sortedArray = new int[arrayLength];
		
		// Fills array with ascending numbers
		for(int i = 0; i < arrayLength; i++){
			sortedArray[i] = i;
		}
		
		return sortedArray;
	}
	
	
	/**
	 * Purpose: Uses selection sort algorithm to sort an array of integers 
	 * 			in ascending order. 
	 * 
	 * 			Selection sort maintains an unsorted and sorted portion of the 
	 * 			array. Each run of the sort finds the maximum integer in
	 * 			the unsorted portion and swaps it with the integer in
	 * 			the rightmost spot in the unsorted portion of the array. 
	 * 
	 * 			Example run on [3 1 4 2 5] where vertical divider | signifies
	 * 			the division between the unsorted and sorted portions:
	 * 
	 * 							[3 1 4 2 | 5]
	 * 							[3 1 2 | 4 5]
	 * 							[1 2 | 3 4 5]
	 * 							[1 | 2 3 4 5]
	 * 							[| 1 2 3 4 5]
	 * 			
	 * Parameters: The integer array to be sorted. 
	 * Return Value: A sorted integer array. 
	 */
	public static int[] selectionSort(int[] intArray){
		
		for(int numItems = intArray.length; numItems > 1; --numItems){
			// Find the max integer in the unsorted
			// portion of the list. 
			int indexOfMax = findIndexOfMax(intArray, numItems);
			
			// Swap the integer at the index of the max 
			// with the rightmost integer in the unsorted
			// portion of the list. 
			int temp = intArray[numItems - 1];
			intArray[numItems - 1] = intArray[indexOfMax];
			intArray[indexOfMax] = temp;
		}
		return intArray;
	}
	
	/**
	 * Purpose: Uses insertion sort algorithm to sort an array
	 * 			of integers in ascending order. 
	 * 
	 * 			Insertion sort maintains an unsorted and sorted portion
	 * 			of the array. The divider between the unsorted and sorted
	 * 			sections begins at the left of the array and moves 
	 * 			right with each run until it reaches the end of the array 
	 * 			at which point it is sorted. Inserts the left most 
	 * 			term in the unsorted portion into the sorted portion by swapping 
	 * 			it with the term to its left as long as it is less than that term. 
	 * 
	 * 			Example run on [4 9 7 0 2] where | shows divide between sorted and unsorted:
	 * 							
	 * 							[4 | 9 7 0 2]
	 * 							[4 9 | 7 0 2]
	 * 							[4 7 9 | 0 2]
	 * 							[0 4 7 9 | 2]
	 * 							[0 2 4 7 9 |]
	 * 				
	 * Parameters: The integer array to be sorted. 
	 * Return Value: Integer array sorted in ascending order. 
	 */
	public static int[] insertionSort(int[] intArray){
		
		for(int i = 1; i < intArray.length; i++ ){
			
			// Makes sure that the term does not go beyond the
			// beginning of the array
			while(i > 0 && intArray[i] < intArray[i-1]){
				
				// Swaps int on the right with int on the left. 
				int temp = intArray[i-1];
				intArray[i-1] = intArray[i];
				intArray[i] = temp;
				
				// Decrements i to reflect the movement of the integer
				// to the index to its left. 
				i--;
			}
		}
		
		return intArray;
		
	}
	
	/**
	 * Purpose: Times either a selection sort or an insertion 
	 * 			sort on an array. 
	 * Parameters: The type of sort ("insertion" or "selection") and
	 * 			   the array to be sorted. 
	 * 			   NOTE: If type of sort does not match "insertion"
	 * 					 or "selection" then method returns 0.0. 
	 * Return Value: a double duration that holds the time to 
	 * 				 execute. 
	 */
	public static double sortTimer(String sortType, int[] intArray){
		long startTime, endTime; // longs to store starting an ending times
		double duration;
		
		// Checks what type of sort is requested in the
		// sortType parameter. If neither "selection" nor
		// "insertion" is selected then no sort is performed
		// and 0.0 is returned. 
		if(sortType == "selection"){ 
			startTime = System.nanoTime();
			selectionSort(intArray);
			endTime = System.nanoTime();
		}
		else if(sortType == "insertion"){
			startTime = System.nanoTime();
			insertionSort(intArray);
			endTime = System.nanoTime();
		}
		else {
			return 0.0;
		}
		
		duration = (endTime - startTime) / NUM_NANOS_IN_SEC; // Converts nanoseconds into seconds
		return duration;
	}
	
}
