package de.hdm_stuttgart.mi.sd1.proect_sieb;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	final Sieve sieve = new Sieve(10000000);

	@Test
	   public void testPfs () {
	      PrimeFrequencySet pfs = sieve.getPrimeFactors(12); 
	      Assert.assertEquals(2, pfs.getLength()); 
	      Assert.assertEquals(new PrimeFrequency(2, 2), pfs.get(0)); 
	      Assert.assertEquals(new PrimeFrequency(3, 1), pfs.get(1));
	      PrimeFrequencySet pfs2 = sieve.getPrimeFactors(201312);
	      Assert.assertEquals(3, pfs2.getLength()); 
	      Assert.assertEquals(new PrimeFrequency(2, 5), pfs2.get(0)); 
	      Assert.assertEquals(new PrimeFrequency(3, 3), pfs2.get(1)); 
	      Assert.assertEquals(new PrimeFrequency(233, 1), pfs2.get(2)); 
	   }
	
	@Test 
		public void testSieve(){
	      Assert.assertEquals(sieve.isPrime(607), false);
	      Assert.assertEquals(sieve.isPrime(608), true);
	      Assert.assertEquals(sieve.numPrimesFound, 664579);
	}
		
}
