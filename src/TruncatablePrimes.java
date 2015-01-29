import java.util.Arrays;


public class TruncatablePrimes {

	/**
	 * Author: James Norcross
	 * Date: 1/26/15
	 * Purpose: Project Euler problem 37
	 * Description: Finds the sum of all numbers which are truncatable primes both from the right and left.
	 */
	
	private static final int MAX = 1000000;
	private static boolean[] sieve = new boolean[MAX];
	
	public static void main(String[] args) {
		
		generateSieve();
		
		int count = 0, sum = 0;
		
		for(int i=11; count<11; i++)
		{
			if(sieve[i])
			{
				if (isLeftTruncatablePrime(i) && isRightTruncatablePrime(i))
				{
					System.out.println(i);
					count++;
					sum += i;
				}
			}
		}
		
		System.out.println("The sum of the 11 right and left truncatable primes is " + sum);

	}
	
	// creates boolean array prime number sieve
	private static void generateSieve()
	{
		Arrays.fill(sieve, true);
		sieve[0] = false;
		sieve[1] = false;
		for(int i=2; i*i<MAX; i++)
		{
			if(sieve[i])
			{
				for(int j=i*2; j<MAX; j += i)
					sieve[j] = false;
			}
		}
	}
	
	// postcondition: return true if i is left truncatable prime
	private static boolean isLeftTruncatablePrime(int i)
	{
		do
		{
			if(!sieve[i])
			{
				return false;
			}
			i = truncateLeft(i);
		}
		while(i > 0);
		
		return true;
	}

	// postcondition: returns true if i is right truncatable prime
	private static boolean isRightTruncatablePrime(int i)
	{	
		
		do
		{
			if(!sieve[i])
			{
				return false;
			}
			i /= 10;
		} 
		while(i > 0);
		
		return true;	
	}
	
	// postcondition: for i > 10 returns integer which is i with leftmost digit truncated, for i <= 10 returns 0
	private static int truncateLeft(int i)
	{
		double order = Math.floor(Math.log10((double)i));
		int multiplier = (int)Math.pow(10.0, order);
		int leftDigit = i/multiplier;
		
		i = i - (leftDigit*multiplier);
		
		return i;
	}
}
