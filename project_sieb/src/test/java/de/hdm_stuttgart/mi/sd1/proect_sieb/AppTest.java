package de.hdm_stuttgart.mi.sd1.proect_sieb;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	final Sieve sieve = new Sieve(100000);

	@Test
	   public void test2 () {
	      PrimeFrequencySet pfs = sieve.getPrimeFactors(12); 
	      Assert.assertEquals(2, pfs.getLength()); 
	      Assert.assertEquals(new PrimeFrequency(2, 2), pfs.get(0)); 
	      Assert.assertEquals(new PrimeFrequency(3, 1), pfs.get(1)); 
	   }
}
