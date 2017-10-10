/**
 *      Author: Sam Harder
 *      Circle Class
 *      15 September 2016
 *
 *      This class is a very simple Circle class. The only instance variables
 *      are the radius of the circle and the center point of the circle. 
 *      A few simple methods are supplied. All methods are self-documenting.
 *
 */

public class Circle{

	protected double radius;
	protected Point2D center;
	
	// If no arguments are supplied then 
	// center defaults to (0,0) and the 
	// radius is set to 0.0
	public Circle() {
		radius = 0.0;
		center = new Point2D(0,0);
	}
	
	// Creates a circle centered around (x,y) with 
	// the supplied radius. 
	public Circle(double x, double y, double radius){
		setRadius(radius); // Prevents negative choice of radius
		center = new Point2D(x,y);
	}
	
	// Returns the radius 
	public double getRadius() {
		return radius;
	}
	
	// Returns a Point2D object equal to the center
	public Point2D getCenter() {
		return center;
	}
	
	// Changes the center to the desired (x,y)
	public void setCenter(double x, double y){
		center.setPoint(x, y);
	}
	
	// Sets the radius, but checks to ensure that
	// the user of the instance chooses a 
	// positive (valid) radius. 
	public void setRadius(double radius) {
		if (radius >= 0) {
			this.radius = radius;
		}
		else{
			System.out.println("error: radius cannot be negative");
		}
	}
	
	// Returns the area of the Circle
	public double getArea() {
		return radius * radius * Math.PI;
	}
	
	// Returns a string of the form:
	//    "Circle[radius = radius, center = Point (x,y)]"
	// where 
	public String toString() {
		return "Circle[radius = " + radius + ", center = " 
				+ center.toString() + "]";
	}
	
}
