package de.hdm_stuttgart.mi.sd1.proect_sieb;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
	
	final Sieve sieve10mio = new Sieve(10000000);
	final Sieve sieveIntMax = new Sieve(Integer.MAX_VALUE);

	@Test
	   public void testPfs12 () {
	      PrimeFrequencySet pfs = sieve10mio.getPrimeFactors(12); 
	      Assert.assertEquals(2, pfs.getLength()); 
	      Assert.assertEquals(new PrimeFrequency(2, 2), pfs.get(0)); 
	      Assert.assertEquals(new PrimeFrequency(3, 1), pfs.get(1));
	   }
	
	@Test
	   public void testPfs201312 () {
	      PrimeFrequencySet pfs2 = sieve10mio.getPrimeFactors(201312);
	      Assert.assertEquals(3, pfs2.getLength()); 
	      Assert.assertEquals(new PrimeFrequency(2, 5), pfs2.get(0)); 
	      Assert.assertEquals(new PrimeFrequency(3, 3), pfs2.get(1)); 
	      Assert.assertEquals(new PrimeFrequency(233, 1), pfs2.get(2)); 
	   }
	
	@Test 
		public void testSieve10mio(){
	      Assert.assertEquals(sieve10mio.isPrime(607), false);
	      Assert.assertEquals(sieve10mio.isPrime(608), true);
	      Assert.assertEquals(sieve10mio.numPrimesFound, 664579);
	}
	
	@Test 
	public void testSieveIntMax(){
      Assert.assertEquals(sieveIntMax.isPrime(607), false);
      Assert.assertEquals(sieveIntMax.isPrime(608), true);
      Assert.assertEquals(sieveIntMax.numPrimesFound, 105097565);
}
	
	
		
}
