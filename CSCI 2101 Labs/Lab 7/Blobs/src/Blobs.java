/*
 * Stephen Majercik
 * 25 October 2016
 * 
 * This class reads a "blobs" file into a 2-dimensional array of characters and
 * counts the size of the blobs.  It also will erase blobs of a given size.
 * 
 */


import java.io.File;
import java.util.Scanner;


public class Blobs {

	private static final int MAX_ROWS = 50;
	private static final int MAX_COLS = 50;

	// actual number of rows and columns
	int numRows;
	int numCols;
	char theBlobs[][] = new char[MAX_ROWS][MAX_COLS];


	public Blobs(String fileName) {
		readBlobs(fileName);
	}


	public void readBlobs(String fileName) {

		try {
			
			
			// The blobs files are in folder called "files" and the
			// user will enter the filename without the .txt file
			// extension, so we must add those.
			String blobsFileName = "files/" + fileName + ".txt";
			Scanner fileScan = new Scanner(new File(blobsFileName));

			String line = fileScan.nextLine();

			// put the first line in theBlobs; this also tells
			// us how many columns there need to be in each row.
			numCols = line.length();
			for (int i = 0; i < numCols; i++) {
				theBlobs[0][i] = line.charAt(i);
			}

			// put the rest of the blobs lines in theBlobs
			for (numRows = 1; fileScan.hasNext(); numRows++) {

				// get the next line
				line = fileScan.nextLine();

				// make sure it has the right number of columns
				if (line.length() != numCols) {
					System.out.println("Error: blobs are not rectangular");
					numRows = 0;
					numCols = 0;
					return;
				}

				// put this blobs line in theBlobs array
				for (int i = 0; i < numCols; i++) {
					theBlobs[numRows][i] = line.charAt(i);
				}
			}
			
			fileScan.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	// print out the blobs array in a nicely formatted way
	public void printBlobs() {

		System.out.print("  ");
		for (int c = 0; c < numCols; ++c) {
			System.out.print(c % 10);
		}
		System.out.println();
		for (int r = 0; r < numRows; ++r) {
			System.out.print(r % 10 + " ");
			for (int c = 0; c < numCols; ++c) {
				System.out.print(theBlobs[r][c]);
			}
			System.out.println();
		}
		System.out.println();

	}


	// Since we are counting the blobs "destructively," we need to make copies
	// to send to checkBlob
	public void findBlobs() {

		char blobsCopy[][] = new char[MAX_ROWS][MAX_COLS];

		// loop to call countBlob for every position in the array
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {

				// copy the blobs into another array
				for (int i = 0; i < numRows; i++) {
					for (int j = 0; j < numCols; j++) {
						blobsCopy[i][j] = theBlobs[i][j];
					}
				}

				// call the blob counter
				int blobSize = countBlob(blobsCopy, r, c);

				// print out the result for each position that is part of a blob
				if (blobSize > 0) {
					System.out.println("There is a blob of size " + blobSize + " at (" + r + "," + c + ")");
				}
			}
		}

	}

	public int countBlob(char[][] blobs, int r, int c) {

		// stop if outside the array
		if (r < 0 || r >= numRows || c < 0 || c >= numCols) {
			return 0;
		}

		// stop if there's not an 'X' there
		if (blobs[r][c] == ' ') {
			return 0;
		}

		// blank out the 'X' and sum 1 (for the one just blanked out)
		// and the result of the recursieve calls in all 8 directions
		blobs[r][c] = ' ';
		int blobSize = 1;
		for (int rOff = -1; rOff <= 1; rOff++) {
			for (int cOff = -1; cOff <= 1; cOff++) {
				if (rOff != 0 || cOff != 0)
					blobSize += countBlob(blobs, r + rOff, c + cOff);
			}
		}

		return blobSize;
	}



	public void eraseBigBlobs(int maxBlobSize) {

		char blobsCopy[][] = new char[MAX_ROWS][MAX_COLS];

		// loop to call countBlob for every position in the array
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {

				// copy the blobs into another array
				for (int i = 0; i < numRows; i++) {
					for (int j = 0; j < numCols; j++) {
						blobsCopy[i][j] = theBlobs[i][j];
					}
				}

				// call the blob counter
				int blobSize = countBlob(blobsCopy, r, c);

				// erase the blob if its size exceeds the maximum
				if (blobSize > maxBlobSize)
					eraseBlob(theBlobs,r, c);
			}
		}

	}


	public void eraseBlob(char[][] blobs, int r, int c) {

		// stop if outside the array
		if (r < 0 || r >= numRows || c < 0 || c >= numCols) {
			return;
		}

		// stop if there's not an 'X' there
		if (blobs[r][c] == ' ') {
			return;
		}

		// blank out the 'X' and call the method recursively in a;;
		// 8 directions to blank out other 'X's in the blob
		blobs[r][c] = ' ';
		for (int rOff = -1; rOff <= 1; rOff++) {
			for (int cOff = -1; cOff <= 1; cOff++) {
				if (rOff != 0 || cOff != 0)
					eraseBlob(blobs, r + rOff, c + cOff);
			}
		}
	}



}
