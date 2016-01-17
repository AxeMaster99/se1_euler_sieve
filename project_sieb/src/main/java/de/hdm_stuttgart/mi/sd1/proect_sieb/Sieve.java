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
		 * Die Zahl 1 wird zu Beginn als Nicht-Primzahl deklariert, da diese in
		 * den Schleifen nicht beachtet wird, da currentPrime bei 3 startet
		 */
		nonPrimes[0] = true;
		int currentPrime = 3;
		int nextNumber;

		/*
		 * Erklärung anhand eines Beispiels: Limit sei 20. Die Bedingung für die
		 * erste While-Schleife ist true. Der Methodenaufruf liefert die letzte
		 * Primzahl zurück, die mit currentPrime -> (3) multipliziert noch unter
		 * dem Limit liegt. In diesem Fall wäre das 5, da 3*5=15 aber 3*7
		 * bereits 21. In der inneren Schleife werden dann alle Primzahlen mit
		 * currentPrime multipliziert, wobei immer die nächste kleinere Primzahl
		 * durch Methodenaufrufe ausgerechnet wird. In der inneren Schleife
		 * passiert also folgendes: Das Arrayelement, das die Zahlen 3*5=15,
		 * 3*3=9 repräsentiert, wird auf true gesetzt. Dann wird currentPrime
		 * durch einen Methodenaufruf neu berechnet -> (5). Bereits dann gilt
		 * die Bedingung für die äußere Schleife nicht mehr, da 5*5 bereits 25
		 * und somit größer als 20. Damit sind alle Zahlen des Arrays, die nicht
		 * auf true gesetzt wurden, Primzahlen.
		 */

		while (currentPrime * currentPrime <= limit && currentPrime > 0) {
			int lastNumber = this.getLastNumber(currentPrime, limit);
			nextNumber = lastNumber;
			while (currentPrime * nextNumber <= limit && currentPrime <= nextNumber) {
				nonPrimes[(currentPrime * nextNumber) / 2] = true;
				nextNumber = this.getNextNumber(nextNumber);
			}
			currentPrime = this.getNextPrime(currentPrime, limit);
			nextNumber = currentPrime;
		}

		/*
		 * Zählt die Primzahlen (Alle Werte die false sind)
		 */
		int count = 1;
		for (int i = 0; i < nonPrimes.length; i++) {

			if (nonPrimes[i] == false && 2 * i + 1 <= limit) {
				count++;
			}
		}
		numPrimesFound = count;
		
	}

	/**
	 * 
	 * @param currentNumber
	 *            die aktuelle Primzahl, ab der die nächste, nicht gestrichene
	 *            Zahl aus dem Array nonPrimes gesucht werden soll.
	 * @param limit
	 *            das Limit, bis zu denen Primzahlen gesucht werden.
	 * @return die nächste Nummer, die in dem Array nonPrimes noch nicht
	 *         gestrichen wurde, also nonPrimes[i/2]==false Hinweis: ist nicht
	 *         zwingend eine Primzahl, Erläuterung im langen Kommentar des
	 *         Konstruktors "Sieve" (vor den verschachtelten While-Schleifen)
	 */
	private int getNextNumber(int currentNumber) {
		for (int i = currentNumber - 2; i > 2; i -= 2) {
			if (nonPrimes[i / 2] == false)
				return i;
		}
		return 0;
	}

	/**
	 * @param currentPrime
	 *            die aktuelle Primzahl, ab der die nächste gesucht werden soll.
	 * 
	 * @param limit
	 *            bis zu maximal diesem Limit werden Primzahlen gesucht
	 * 
	 * @return gibt die nächste gefundene Primzahl zurück, sofern es eine gibt.
	 *         Ansonsten ist die Rückgabe 0.
	 */
	private int getNextPrime(int currentPrime, int limit) {
		for (int i = currentPrime + 2; i <= limit && i > 0; i += 2) {
			if (nonPrimes[i / 2] == false)
				return i;
		}
		return 0;
	}

	/**
	 * @param currentPrime
	 *            Die aktuelle Primzahl, mit der gerade gerechnet wird.
	 * 
	 * @param das
	 *            Limit des Siebes.
	 * 
	 * @return gibt die Zahl zurück, die mit currentPrime multipliziert zuletzt
	 *         noch kleiner als das Limit ist.
	 */

	private int getLastNumber(int currentPrime, int limit) {
		int temp = limit / currentPrime;
		if (temp % 2 == 0)
			temp -= 1;
		if (nonPrimes[temp / 2] == false)
			return temp;
		else {
			temp = this.getNextNumber(temp);
			return temp;
		}
	}

	/**
	 * Überprüft, ob eine Zahl eine Primzahl ist, basierend auf dem Array
	 * nonPrimes. Ist public, da von außerhalb eine beliebige Zahl im Limit
	 * überprüft werden kann.
	 * 
	 * @param candidate
	 *            Die Zahl, welche überprüft werden soll
	 * @return true, wenn die Zahl keine Primzahl ist, ansonsten false
	 */
	public boolean isPrime(int candidate) {
		if (candidate % 2 == 0 || nonPrimes[candidate / 2] == true)
			return true;
		else
			return false;
	}

	/**
	 * Primfaktorzerlegung einer gegebenen Zahl, basierend auf dem Array
	 * nonPrimes
	 * 
	 * @param nonPrime
	 *            eine Zahl, die keine Primzahl ist (bei Primzahlen keine
	 *            Primfaktorzerlegung notwendig)
	 * @return PrimeFrequencySet Ein Objekt, wo alle Primzahlen mit ihrer
	 *         Häufigkeit gespeichert sind.
	 */
	public PrimeFrequencySet getPrimeFactors(int nonPrime) {
		// hab den Parameter von prime zu nonPrime umbenannt, da ja die
		// Primfaktorzerlegung von einer Zahl gesucht wird die keine Primzahl
		// ist...
		int initialCapacity = 0;
		PrimeFrequency pf;
		PrimeFrequencySet pfs;

		/*
		 * Hier wird die Größe für das PrimeFrequencySet ermittelt, indem alle
		 * Primzahlen bis zur gegebenen Primzahl des Arrays nonPrimes gezählt
		 * werden. Zwar werden nicht alle Speicherstellen gebraucht, aber so
		 * kann es keine ArrayIndexOutOfBounce Exception geben.
		 */
		for (int i = 0; i <= nonPrime / 2; i++) {

			if (!nonPrimes[i]) {
				initialCapacity++;
			}
		}

		/*
		 * Ein neues PrimeFrequencySet wird erstellt. initialCapacity ist die
		 * Größe, basierend auf der Zählung der Primzahlen im vorherigen
		 * Abschnitt
		 */
		pfs = new PrimeFrequencySet(initialCapacity);

		/*
		 * 
		 */
		for (int i = 2; i <= nonPrime;) {

			if (nonPrime % i == 0) {

				pf = new PrimeFrequency(i, 1);
				pfs.add(pf);

				nonPrime = nonPrime / i;

			} else {
				if (i == 2)
					i = 3;
				else
					i = getNextPrime(i, nonPrime);
			}

		}

		return pfs;

	}

}
