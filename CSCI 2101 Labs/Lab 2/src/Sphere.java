/**
 *      Author: Sam Harder
 *     	Sphere Class
 *      15 September 2016
 *
 *      This class is a very Sphere class which extends
 *      the Circle class. This class overrides the 
 *      getCenter, setCenter, getArea, and toString 
 *      methods. 
 *
 */
public class Sphere extends Circle {
	
	private Point3D center;
	
	// If no arguments are passed to the constructor
	// then the center defaults to (0,0,0) and the
	// radius defaults to 0. 
	public Sphere(){
		super.setRadius(0);
		center = new Point3D(0,0,0);
	}
	
	// Creates an instance of the sphere class with center
	// at user inputed (x,y,z) and radius
	public Sphere(double x, double y, double z, double radius){
		center = new Point3D(x,y,z);
		super.setRadius(radius);
	}
	
	// Returns the center instance variable
	public Point3D getCenter() {
		return center;
	}
	
	// Sets the Center to point to the point at
	// (x,y,z)
	public void setCenter(double x, double y, double z){
		center.setPoint(x,y,z);
	}
	
	// Returns the surface area of a sphere
	// SA = 4πr^2
	public double getArea() {
		return 4 * Math.PI * radius * radius;
	}
	
	// Returns the volume of the sphere
	// V = 4/3 πr^3
	public double getVolume() {
		return 4.0/3 * Math.PI * Math.pow(radius, 3);
	}
	
	// Returns a string of the form
	// "Sphere[radius = radius, center = Point (x,y,z)]"
	public String toString() {
		return "Sphere[radius = " + radius + ", center = " 
				+ center.toString() + "]";
	}
}
