/**
 *      Author: Sam Harder
 *      Point2D Class
 *      15 September 2016
 *
 *      Point 2D implements the Point interface.
 *
 */
public class Point2D implements Point {

	protected double x;
	protected double y;
	
	// If no parameters are entered in the constructor
	// then x and y default to zero. 
	public Point2D(){
		x = 0;
		y = 0;
	}
	
	// Creates a Point2D instance with coordinates (x,y)
	public Point2D(double x, double y){
		this.x = x;
		this.y = y;
	}

	// Returns a copy of this instance of the Point2D class
	public Point2D getCopy() {
		return new Point2D(x, y);
	}

	// Returns the x coordinate
	public double getX() {
		return x;
	}

	// Returns the y coordinate
	public double getY() {
		return y;
	}

	// Sets the x and y to doubles, x and y
	public void setPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Sets the x instance variable
	public void setX(double x) {
		this.x = x;
	}

	// Sets the y instance variable
	public void setY(double y) {
		this.y = y;
	}
	
	// Returns a string of the form:
	// "Point: (x,y)"
	public String toString(){
		return "Point: (" + x + ", " + y + ")";
	}

}
