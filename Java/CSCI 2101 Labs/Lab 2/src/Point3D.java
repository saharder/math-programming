/**
 *      Author: Sam Harder
 *      Point2D Class
 *      15 September 2016
 *
 *      Point3D extends Point2D and overrides the methods 
 *      getCopy, and toString. It adds another form of the method
 *      setPoint, one which takes three parameter, as well as 
 *      creating some new methods, getZ, setZ. It also adds
 *      an instance variable, z. 
 *
 */
public class Point3D extends Point2D {
	
	private double z;
	
	// If no arguments are given then
	// x,y, and z default to 0.0.
	public Point3D(){
		super();
		z = 0;
	}
	
	// Creates an instance of the Point3D class with
	// coordinates (x,y,z).
	public Point3D(double x, double y, double z){
		super(x, y);
		this.z = z;
	}
	
	// Returns a copy of the Point3D instance
	public Point3D getCopy() {
		return new Point3D(x,y,z);
	}
	
	// Returns the z instance variable
	public double getZ(){
		return z;
	}
	
	// Sets the Point3D class instance variables to 
	// reflect a coordiante at (x,y,z)
	public void setPoint(double x, double y, double z){
		super.setPoint(x, y);
		this.z = z;
	}
	
	// Sets the z instance variable
	public void setZ(double z) { 
		this.z = z;
	}
	
	// Returns a String of form : "Point: (x,y,z)" where
	// x, y, and z are the instance variables. 
	public String toString(){
		return "Point: (" + x + ", " + y + ", " + z + ")";
	}
	
	
}
