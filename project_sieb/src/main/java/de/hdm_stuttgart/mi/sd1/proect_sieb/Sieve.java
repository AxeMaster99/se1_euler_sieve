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
		/*
		 * erstellt neues Array, wo alle ungeraden Werte gespeichert sind,
		 * Standartwert ist false, Primzahlen sind false, Nicht-Primzahlen
		 * werden true. Vielfache von 2 sind nicht im Array vorhanden, deswegen
		 * ist Größe das Limit / 2 + 1
		 */
		nonPrimes = new boolean[limit / 2 + 1];

		/*
		 * erstellt ein neues Array, wo alle Werte gespeichert werden, die am
		 * Ende eines Durchlaufs gestrichen werden. Grund hierfür siehe
		 * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Euler.27s_Sieve
		 */
		boolean[] rausstreichen = new boolean[limit / 2 + 1];

		/*
		 * Die Zahl 1 wird zu Beginn als Nicht-Primzahl deklariert, da diese in
		 * den Schleifen nicht beachtet wird, da currentPrime und nextNumber bei
		 * 3 starten
		 */
		rausstreichen[0] = true;
		int currentPrime = 3;
		int nextNumber = 3;

		/*
		 * In der inneren Schleife werden Zahlen markiert, die keine Primzahl
		 * sind (currentPRime * nextNumber) Bsp. für erten Durchlauf: 3 * 3 = 9
		 * 3 * 5 = 15 3 * 7 = 21 3 *_9_= 27 usw. Die Werte werden nicht sofort
		 * in nonPrimes auf true gesetzt, sondern im temporären Array
		 * rausstreichen gespeichert. Die innere Schleife läuft so lange, bis
		 * currentPrime*nextNumber das Limit erreicht. Anschließend wird das
		 * Array rausstrichen auf das Array nonPrimes übertragen.
		 * 
		 * Dieser Vorgang wird durch die äußere Schleife so lange wiederholt,
		 * bis currentprime*currentPrime das Limit erreicht.
		 */
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

		/*
		 * Zählt die Primzahlen (Alle Werte die false sind)
		 */
		int count = 0;
		for (int i = 0; i < nonPrimes.length; i++) {

			if (nonPrimes[i] == false) {
				count++;
			}
		}
		numPrimesFound = count;
		System.out.println("Von 0 bis " + limit + " wurden " + count + " Primzahlen gefunden.");
	}

	/**
	 * 
	 * @param currentNumber
	 *            die aktuelle Primzahl, ab der die nächste, nicht gestrichene
	 *            Zahl aus dem Array nonPrimes gesucht werden soll.
	 * @param limit
	 *            das Limit, bis zu denen Primzahlen gesucht werden.
	 * @return die nächste Nummer, die in dem Array nonPrimes noch nicht
	 *         gestrichen wurde, also nonPrimes[i/2]==false
	 */
	private int getNextNumber(int currentNumber, int limit) {
		for (int i = currentNumber + 2; i <= limit && i > 0; i += 2) {
			if (nonPrimes[i / 2] == false)
				return i;
		}
		return 0;
	}

	/**
	 * Überprüft, ob eine Zahl eine Primzahl ist, basierend auf dem Array nonPrimes.
	 * Ist public, da von außerhalb eine beliebige Zahl im Limit überprüft werden kann.
	 * @param candidate Die Zahl, welche überprüft werden soll
	 * @return true, wenn die Zahl keine Primzahl ist, ansonsten false
	 */
	public boolean isPrime(int candidate) {
		if (candidate % 2 == 0 || nonPrimes[candidate / 2] == true)
			return true;
		else
			return false;
	}

	/**
	 * Primfaktorzerlegung einer gegebenen Zahl, basierend auf dem Array nonPrimes
	 * @param prime 
	 * @return PrimeFrequencySet Ein Objekt, wo alle Primzahlen mit ihrer Häufigkeit drin gespeichert sind
	 */
	public PrimeFrequencySet getPrimeFactors(int prime) {

		int initialCapacity = 0;
		PrimeFrequency pf;
		PrimeFrequencySet pfs;

		/*
		 * Hier wird die Größe für das PrimeFrequencySet ermittelt, indem alle Primzahlen bis zur gegebenen Primzahl des Arrays nonPrimes gezählt werden
		 */
		for (int i = 0; i <= prime / 2; i++) {

			if (!nonPrimes[i]) {
				initialCapacity++;
			}
		}

		/*
		 * Ein neues PrimeFrequencySet wird erstellt.
		 * initialCapacity ist die Größe, basierend auf der Zählung der Primzahlen
		 */
		pfs = new PrimeFrequencySet(initialCapacity);

		/*
		 * 
		 */
		for (int i = 2; i <= prime;) {

			if (prime % i == 0) {

				pf = new PrimeFrequency(i, 1);
				pfs.add(pf);

				prime = prime / i;

			} else {
				if (i == 2)
					i = 3;
				else
					i = getNextNumber(i, prime);
			}

		}

		return pfs;

	}

}
