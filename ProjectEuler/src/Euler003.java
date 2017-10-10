import java.util.Arrays;

/**
 * This is a solution to Project Euler Problem 3. This problem is begging for an implementation of the sieve
 * of eratosthanes. 
 * @author samuelpx2016
 *
 */
public class Euler003 {	
	
	public static void main(String args[]) {
		long num = 600851475143L;
		int trials = 100;
		
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < trials; i++) {
			largestPrimeFactor(num);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("runtime: " + (endTime - startTime)/trials + " ms");
		System.out.println(largestPrimeFactor(num));
	}
	
	/**
	 * Generates an array of all the prime numbers less than some limit
	 * @param limit
	 * @return
	 */
	public static boolean[] sieve(int limit) {
		boolean[] nums = new boolean[limit + 1];
		
		// Fill the array with true
		Arrays.fill(nums, Boolean.TRUE);
		for(int i = 2; i <= Math.sqrt(limit); i++) {
			if(nums[i] == true) {
				for(int j = i*i; j < limit; j += i) {
					nums[j] = false;
				}
			}
		}
		return nums;
	}
	
	/**
	 * Finds the largest prime factor of a number n. 
	 * @param n
	 * @return
	 */
	public static long largestPrimeFactor(long n) {
		boolean[] primes = sieve((int)Math.sqrt(n));
		long product = 1L; 
		long largestPrimeFactor = 1L;
		
		for(int i = primes.length - 1; i >= 2; i-- ) {
				if(primes[i] == true && n % i == 0) {
					product *= (long) i;
					if((long)i > largestPrimeFactor) {
						largestPrimeFactor = (long)i;
					}
				}
			}
		
		if(product == n) {
			return largestPrimeFactor;
		}
		return n/product;
	}
}
