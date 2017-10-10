/**
 * @author Sam Harder (Bowdoin College)
 * 
 * This class solves Project Euler problem 1. There are two implementations
 * here. The first is a naive implementation, while the second takes advantage
 * of some useful sums. 
 *
 */

public class Euler001 {
	
	public static void main(String args[]) {
		long startTime = System.currentTimeMillis();
		System.out.println(multipleAdder(1000));
		long endTime = System.currentTimeMillis();
		System.out.println("runtime: " + (endTime - startTime) + " milliseconds");
		
		startTime = System.currentTimeMillis();
		System.out.println(advMultipleAdder(1000));
		endTime = System.currentTimeMillis();
		System.out.println("runtime: " + (endTime - startTime) + " milliseconds");
		
	}	
	
	/**
	 * Adds up all the multiples of 3 or 5 below some limit. 
	 * @param limit 
	 * @return the sum of the multiples of 3 or 5 below limit. 
	 */
	public static int multipleAdder(int limit) {
		int sum = 0;
		for(int i = 0; i < limit; i++) {
			if((i % 3 == 0) || (i % 5 == 0)) {
				sum+=i;
			}
		}
		return sum;
	}
	
	/**
	 * More sophisticated multiple adder. Adds all the
	 * multiples of n up until the limit. It does this by using the 
	 * formula for the sum 1 + 2 + ... + n = n(n+1)/2. Take the sum
	 * 3 + 6 + ... + 24 = 3(1 + 2 + ... + 8) = 3(8(9))/2. So you can see
	 * that we can use the sequential sum formula to compute the sums
	 * of multiples. The only issue is calculating what "n" to use in our
	 * sequential sum. This is accomplished in finding the "newLimit". 
	 * @return
	 */
	public static int multipleSum(int limit, int n) {
		// highest n in our sequential sum
		int newLimit = (limit-1)/n;
		// the actual sum of the multiples
		int sum = n * ((newLimit)*(newLimit+ 1))/2;
		return sum;
	}
	
	/**
	 * An advanced version of multipleAdder with better scalability. Uses
	 * multipleSum. 
	 * @param limit
	 * @return
	 */
	public static int advMultipleAdder(int limit) {
		return multipleSum(limit, 3) + multipleSum(limit, 5) - multipleSum(limit, 15);
	}
	
}
