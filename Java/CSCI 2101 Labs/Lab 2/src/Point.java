/**
 *      Author: Sam Harder
 *      Point Interface
 *      15 September 2016
 *
 *      This is a simple interface which, when implemented,
 *      allows the user to make point like objects which can 
 *      be returned, copied, modified, and printed. 
 *
 */

public interface Point {
	
	// Return a copy of the instance
	public Point getCopy(); 
	
	// Return the x coordinate
	public double getX();
	// Return the y coordinate
	public double getY();
	
	// Set the point to a certain (x,y)
	public void setPoint(double x, double y);
	// Set the x coordinate
	public void setX(double x);
	// Set the y coordinate
	public void setY(double y);
	
	// Return a string representation of Point
	public String toString();
}
