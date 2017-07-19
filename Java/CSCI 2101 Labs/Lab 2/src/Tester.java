
public class Tester extends Circle{
	
	public static void main(String args[]){
		
		Point3D a = new Point3D(1,2,3);
		System.out.println(a);
		a.setY(100);
		System.out.println(a);
		
		a.setPoint(1,2);
		System.out.println(a);
	}
}
