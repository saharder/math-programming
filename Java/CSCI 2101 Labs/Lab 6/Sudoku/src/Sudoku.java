/*
 * YOUR DOCUMENTATION GOES HERE!
 * 
 */

import java.util.Scanner;
import java.io.File;

public class Sudoku {

	private static final int BLANK = 0;;
	
	
	// A standard Sudoku puzzle is 9x9.
	private static final int DIMENSION = 9;
	
	// Arrays to hold the puzzle and the actual solution so that
	// we can check the program-generated solution against the
	// actual solution.
	private int[][] puzzle = new int[DIMENSION][DIMENSION];
	private int[][] solution = new int[DIMENSION][DIMENSION];

	// To read puzzle and solution files.
	private Scanner scan;

	
	// This constructor takes the name of a puzzle file and calls
	// readPuzzle to read the puzzle into the puzzle array.
	public Sudoku(String fileName) {
		readPuzzle(fileName);
	}

	
	// Purpose: Read the puzzle into the puzzle array.
	// Parameters: 
	// Return Value:
	//
	//              NOTE: The program assumes that blank
	//                    spaces are indicated by zeros in
	//                    the puzzlefile.
	public void readPuzzle(String fileName) {

		try {
			// The puzzles are in folder called "puzzles" and the
			// user will enter the filename without the .txt file
			// extension, so we must add those.
			String puzzleFileName = "puzzles/" + fileName + ".txt";
			scan = new Scanner(new File(puzzleFileName));

			for (int i = 0; i < DIMENSION; i++) {
				for (int j = 0; j < DIMENSION; j++) {
					puzzle[i][j] = scan.nextInt();
				}
			}
			
			// The solutions are also in the "puzzles" folder and
			// they have the same name as the puzzle file with the
			// addition of ""-solution.txt".
			String solutionFileName = "puzzles/" + fileName + "-solution.txt";
			scan = new Scanner(new File(solutionFileName));

			for (int i = 0; i < DIMENSION; i++) {
				for (int j = 0; j < DIMENSION; j++) {
					solution[i][j] = scan.nextInt();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	// Purpose: Solve the puzzle.
	// Parameters: None.
	// Return Value: None.
	//
	public void solve() {

		
    
    }


	// Purpose: Get the digit from a specified location in the puzzle.
	// Parameters: The row and column we want to get the digit of.
	// Return Value: The digit at that row and column.
	//
	public int getDigit(int row, int col) {
		return puzzle[row][col];
	}


	// 
	// Purpose: Put a digit in a specified location in the puzzle.
	// Parameters: The digit to be palced and the row and column 
	//             that it should be placed at.
	// Return Value: None.
	//
	public void makeMove(int row, int col, int digit) {
		puzzle[row][col] = digit;
	}

	
	// Purpose: Change the move at a specified location, if possible.
	// Parameters: The row and column of the specified location and
	//             the most recent digit tried there,
	// Return Value: A Move object that contains the move found or
	//               a value indicating that there are no more legal
	//               moves at that location.
	//
	public Move changeMove(int row, int col, int lastDigit) {

		// The Move object to return.
		Move move = new Move();

		// Try all the digits that come after lastDigit.
		for (int next = lastDigit + 1; next <= DIMENSION; next++) {
			
			// If the move is legal, return it.
			if (legalMove(row, col, next)) {
				move.setRow(row);
				move.setCol(col);
				move.setDigit(next);
				return move;
			}
		}

		// No legal moves were found, so set the digit to indicate
		// that and return the Move.
		move.setDigit(Move.NO_MOVE);
		return move;
	}


	// Purpose: Find the first empty space in the puzzle (going row by
	//          row, left to right) and try to find a legal move for that
	//          space.
	// Parameters: None.
	// Return Value: A Move object that contains the move found for the
	//               first empty space or a value indicating that there 
	//               were no legal moves at that location.
	//
	public Move nextMove() {

		// The Move object to return.
		Move move = new Move();


		// Go through row byrow, left to right.
		for (int row = 0; row < DIMENSION; row++) {
			for (int col = 0; col < DIMENSION; col++) {

				if (puzzle[row][col] == BLANK) {

					// Try all possible digits.
					for (int next = 1; next <= DIMENSION; next++) {

						// If the move is legal, return it.
						if (legalMove(row, col, next)) {
							move.setRow(row);
							move.setCol(col);
							move.setDigit(next);
							return move;                    }
					}

					// No legal moves were found, so set the digit 
					// to indicate that and return the Move.
					move.setDigit(Move.NO_MOVE);
					return move;            }

			}
		}

		// We should never get here, because if there are no more
		// empty places, the puzzle is solved, but we have to
		// return something.
		return null;
	}


	// Purpose: Check if a move is legal, given the state of the puzzle.
	// Parameters: The row and column of the move and the digit to 
	//             be palced there.
	// Return Value: True, if the move is legal; otherwise, false.
	//
	public boolean legalMove(int row, int col, int digit) {

		// Check the row and column.
		for (int i = 0; i < DIMENSION; i++) {
			if (puzzle[row][i] == digit || puzzle[i][col] == digit) {
				return false;
			}
		}

		// Check the 3x3 group that [row,col] belongs to. Note that
		// rGroup and cGroup are essentially indices of 3x3 groups.
		// In other words, they are indexed as if they were individual
		// entities, e.g. if rGroup and cGroup are both 1, they refer
		// to the 3x3 group in the center of the puzzle.
		int rGroup = row / 3;
		int cGroup = col / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (puzzle[rGroup * 3 + i][cGroup * 3 + j] == digit) {
					return false;
				}
			}
		}

		// If we never found a problem and returned false, it's legal.
		return true;
	}


	// Purpose: Check to see if the puzzle has been successfully completed.
	// Parameters: None.
	// Return Value: True, if the puzzle has been successfully completed; 
	//               otherwise, false.
	//               NOTE: 	Since we have ensured that we make only legal
	//                      moves, we have solved the puzzle if there are
	//                      no more blank spaces.
	// 
	public boolean success() {
		for (int r = 0; r < DIMENSION; r++) {
			for (int c = 0; c < DIMENSION; c++) {
				if (puzzle[r][c] == 0)
					return false;
			}
		}
		return true;
	}


	// Purpose: Print out the puzzle in a nicely formatted way.
	// Parameters: None.
	// Return Value: None.
	//
	public void printPuzzle(){

		for (int r = 0; r < DIMENSION; r++){
			for (int c = 0; c < DIMENSION; c++){
				if (puzzle[r][c] == 0)
					System.out.print("_ ");
				else
					System.out.print(puzzle[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();

	}

	
	// Purpose: Check the computed solution against the actual solution.
	// Parameters: None.
	// Return Value: The number of errors found.
	//
	public int checkSolution() {

		int numErrors = 0;

	    for (int r = 0; r < DIMENSION; r++){
	        for (int c = 0; c < DIMENSION; c++){
	            if (puzzle[r][c] != solution[r][c])
	            	++numErrors;
	        }
	    }

	    return numErrors;
	}
	
}




