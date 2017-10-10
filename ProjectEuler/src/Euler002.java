/**
 * 
 * @author Sam Harder (Bowdoin College)
 *
 * This code is the solution to Project Euler Problem 2. The problem deals
 * with sums of even fibonacci numbers. I make use of the constant time formula
 * for the fibonacci numbers, as well as noting that only every 3rd term is 
 * even. 
 * 
 * Quick note: in this case fib1 = 1, fib2 = 2. 
 */
public class Euler002 {
	
	public static final double PHI = (Math.sqrt(5) + 1.0)/2.0;
	
	public static void main(String args[]) {
		long startTime = System.currentTimeMillis();
		System.out.println(evenFibSum(4000000));
		long endTime = System.currentTimeMillis();
		System.out.println("runtime: " + (endTime - startTime) + " miliseconds");
		
		startTime = System.currentTimeMillis();
		System.out.println(evenFibSumAlt(4000000));
		endTime = System.currentTimeMillis();
		System.out.println("runtime: " + (endTime - startTime) + " miliseconds");
	}
	
	/**
	 * Computes the nth fibonacci number. 
	 * @param n
	 * @return nth fibonacci number. 
	 */
	public static int fib(int n) {
		int fibN = (int) Math.round((Math.pow(PHI,n+1) - Math.pow(PHI, -(n+1)))/Math.sqrt(5));
		return fibN;
	}
	
	/**
	 * Finds the sum of the even fibonacci numbers less than the limit. 
	 * @param limit
	 * @return the sum. 
	 */
	public static int evenFibSum(int limit) {
		int sum = 0;
		int n = 2;
		
		while(fib(n) < limit) {
			sum += fib(n);
			n += 3; // next even term is 3 away
		}
		return sum;
	}
	
	/**
	 * This is another nice solution I saw which I sort of liked. It takes advantages
	 * of the fact that the ratio of fibonacci numbers approaches the golden ratio. So the ratio
	 * of even terms is roughly phi^3. 
	 */
	public static int evenFibSumAlt(int limit){
		int fibTerm = 2;
		int sum = 0;
		while(fibTerm < limit) {
			sum+= fibTerm;
			fibTerm = (int) Math.round(fibTerm*Math.pow(PHI, 3));
		}
		return sum;
	}
	
	/**
	 * Yet another solution. This one takes advantage of the recursive definition
	 * to figure out the even terms. evenfib(n) = 4*evenfib(n-1) + evenfib(n-2). 
	 */
	
	
}
