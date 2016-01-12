package de.hdm_stuttgart.mi.sd1.proect_sieb;

public class Driver {

	public static void main(String[] args) {

		final int limit = 12;
		Sieve instance = new Sieve(limit);
		System.out.println("Die Zahl " + limit + " ist " + (instance.isPrime(limit) ? "keine" : "eine") + " Primzahl.");
		PrimeFrequencySet pfs = instance.getPrimeFactors(limit);
	}
}
