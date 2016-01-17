package de.hdm_stuttgart.mi.sd1.proect_sieb;

public class Driver {

	public static void main(String[] args) {

		final int limit = Integer.MAX_VALUE;
		Sieve instance = new Sieve(limit);
		System.out.println("Die Zahl " + limit + " ist " + (instance.isPrime(limit) ? "keine" : "eine") + " Primzahl.");

		if (instance.isPrime(limit)) {
			PrimeFrequencySet pfs = instance.getPrimeFactors(limit);

			String separator = "";

			System.out.print("Primfaktorzerlegung von " + limit + ": ");

			for (PrimeFrequency pf : pfs.get()) {

				if (pf != null) {
					for (int i = 0; i < pf.getFrequency(); i++) {
						System.out.print(separator + pf.getPrime());
						separator = " * ";
					}
				}
			}

		}
	}
}
