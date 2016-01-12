package de.hdm_stuttgart.mi.sd1.proect_sieb;

public class Sieve {

	boolean[] nonPrimes;
	final int numPrimesFound;

	/**
	 * Creating a prime number Euler variant sieve
	 * 
	 * @param limit
	 *            The last value to be considered. May or may not be prime.
	 * 
	 */
	
	public Sieve(final int limit) {
		nonPrimes = new boolean[limit / 2 + 1];

		boolean[] rausstreichen = new boolean[limit / 2 + 1];
		rausstreichen[0] = true;

		int currentPrime = 3;
		int nextNumber = 3;

		while (currentPrime * currentPrime <= limit && currentPrime > 0) {
			while (currentPrime * nextNumber <= limit && nextNumber > 0 && currentPrime * nextNumber > 0) {
				int index = (currentPrime * nextNumber) / 2;
				rausstreichen[index] = true;
				nextNumber = this.getNextNumber(nextNumber, limit);
			}
			nonPrimes = rausstreichen.clone();
			currentPrime = this.getNextNumber(currentPrime, limit);
			nextNumber = currentPrime;
		}

		int count = 0;
		for (int i = 0; i < nonPrimes.length; i++) {

			if (nonPrimes[i] == false) {
				count++;
			}
		}
		numPrimesFound = count;
		System.out.println("Von 0 bis " + limit + " wurden " + count + " Primzahlen gefunden.");
	}

	public int getNextNumber(int currentNumber, int limit) {
		for (int i = currentNumber + 2; i < limit && i > 0; i += 2) {
			if (nonPrimes[i / 2] == false)
				return i;
		}
		return 0;
	}

	public boolean isPrime(int candidate) {
		if (candidate % 2 == 0 || nonPrimes[candidate / 2] == true)
			return true;
		else
			return false;
	}

	public PrimeFrequencySet getPrimeFactors(int nonPrime) {
		PrimeFrequencySet pfs = new PrimeFrequencySet();

		int currentPrime = 0;
		int frequency = 0;
		PrimeFrequency prime;
		
		while (nonPrimes[nonPrime/2]==true || nonPrime%2==0) {
			currentPrime = this.getNextNumber(currentPrime, nonPrime);
			while (nonPrime % currentPrime == 0) {
				frequency++;
				nonPrime= nonPrime/currentPrime;
			}
			prime = new PrimeFrequency(currentPrime, frequency);
			pfs.add(prime);
			frequency =0;
		}
		prime = new PrimeFrequency(nonPrime,1);
		pfs.add(prime);
		return pfs;
	}
}
