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
		 * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Euler.27s_Sieve,
		 * letzter Hinweise des Artikels.
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
		 * sind (currentPrime * nextNumber). Die Werte werden nicht sofort in
		 * nonPrimes auf true gesetzt, sondern im temporären Array rausstreichen
		 * auf true gesetzt. Das liegt daran, das die 9 z.B. nicht gleich in
		 * nonPrimes raussgestrichen werden kann (3*3=9), weil sie eben auch
		 * noch mit der 3 multipliziert wird). Die innere Schleife läuft so
		 * lange, bis currentPrime*nextNumber das Limit erreicht. Anschließend
		 * wird das Array rausstrichen auf das Array nonPrimes Übertragen.
		 * 
		 * Dieser Vorgang wird durch die äußere Schleife so lange wiederholt,
		 * bis currentprime*currentPrime das Limit erreicht.
		 * 
		 * Komplettes Beispiel mit limit = 28: innere Schleife: 3*3=9, 3*5=15,
		 * 3*7=21, 3*!9!=27, rausspringen bei 3*10=30. Dann übertragen des
		 * Arrays auf nonPrimes mit der Anweisung .clone(); currentPrime und
		 * nextNumber werden jetzt auf 5 gesetzt; innereSchleife: 5*5=25,
		 * rausspringen bei 5*7 = 35. Danach ist auch die Bedingung für die
		 * äußere Schleife false, da 7*7=49. Alle Primzahlen sind gefunden und
		 * in nonPrimes gespeichert.
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
	 *         gestrichen wurde, also nonPrimes[i/2]==false Hinweis: ist nicht
	 *         zwingend eine Primzahl, Erläuterung im langen Kommentar des
	 *         Konstruktors "Sieve" (vor den verschachtelten While-Schleifen)
	 */
	private int getNextNumber(int currentNumber, int limit) {
		for (int i = currentNumber + 2; i <= limit && i > 0; i += 2) {
			if (nonPrimes[i / 2] == false)
				return i;
		}
		return 0;
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
					i = getNextNumber(i, nonPrime);
			}

		}

		return pfs;

	}

}
