package de.hdm_stuttgart.mi.sd1.proect_sieb;

public class Driver {

	public static void main(String[] args) {

		final int limit = Integer.MAX_VALUE;
		Sieve instance = new Sieve(limit);

		int tester =limit;
		while (!instance.isPrime(tester)) {
			System.out.println("Die Zahl " + tester + " ist eine Primzahl.");
			tester-=1;
		}
		System.out.println("Die Zahl " + tester + " ist keine Primzahl.");
		PrimeFrequencySet pfs = instance.getPrimeFactors(tester);

		String separator = "";

		System.out.print("Primfaktorzerlegung von " + tester + ": ");

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
