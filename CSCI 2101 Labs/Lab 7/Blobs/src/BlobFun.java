/*
 * 
 * Stephen Majercik
 * 25 October 2016
 * 
 * Class to test the Blobs class.
 * 
 */


import java.util.Scanner;

public class BlobFun {

	private static Scanner scan = new Scanner(System.in);

	
	private static final int MAX_BLOB_SIZE = 10;
	
	public static void main(String[] args) {


		System.out.print("Please enter the file name without the \".txt\" extension: ");
		String fileName = scan.next();

		
	    Blobs blobs = new Blobs(fileName);
	    System.out.println();
	    blobs.printBlobs();
	    blobs.findBlobs();
	    
	    System.out.println();
	    System.out.println("Erase blobs of size greater than " + MAX_BLOB_SIZE + ":");
	    blobs.eraseBigBlobs(MAX_BLOB_SIZE);
	    System.out.println();
	    blobs.printBlobs();
	}

}
