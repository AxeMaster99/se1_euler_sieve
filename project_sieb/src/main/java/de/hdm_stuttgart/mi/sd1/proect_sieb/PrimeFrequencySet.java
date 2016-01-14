package de.hdm_stuttgart.mi.sd1.proect_sieb;

public class PrimeFrequencySet {

	private PrimeFrequency[] store; 

	public PrimeFrequencySet(int initialCapacity) {

		this.store = new PrimeFrequency[initialCapacity];

	}

	/**
	 * Searching for the existence of a {@link PrimeFrequency} with a matching
	 * prime value (frequency may be different!).
	 * 
	 * Example: If the set contains the prime values {3, 7, 11} (we ignore
	 * frequencies) then searching for:
	 * <ul>
	 * <li>3 return index 0</li>
	 * <li>11 returns index 2</li>
	 * <li>5 return -2</li>
	 * <li>13 returns -3</li>
	 * <li>2 returns -1</li>
	 * </ul>
	 * 
	 * @param primeFrequency
	 *            The candidate to be looked up.
	 * 
	 * @return If a prime value match exists return its index. If no match
	 *         exists return the negative value of the first index belonging to
	 *         a set value having a larger prime value than the candidate (the
	 *         "natural" insertion point) minus 1 (see example).
	 */
	public int find(final PrimeFrequency primeFrequency) {
		int i = 0;

		if (store[0] == null) {
			return -1;
		}

		for (PrimeFrequency item : store) {

			if (item == null) {
				int negIndex = (i + 1) * -1;
				return negIndex;
			}

			if (item.equals(primeFrequency)) {
				return i;
			} else {
				i++;
			}

		}
		return 0;
	}

	/**
	 * Either add a new prime or just add a prime's frequency to an existing
	 * one.
	 * 
	 * @param primeFrequency
	 *            If the prime is already in the current set, add its frequency.
	 *            Otherwise insert the new element preserving the order of prime
	 *            values.
	 */
	public void add(final PrimeFrequency primeFrequency) {
		int index = this.find(primeFrequency);

		if (index >= 0) {
			store[index].addFrequency(primeFrequency.getFrequency());
		} else {
			store[this.getLength()] = primeFrequency;
		}

	}

	/**
	 * @return The count of all prime factors.
	 */
	public int getLength() {
		int laenge = 0;
		while (store[laenge] != null) {
			laenge++;
		}
		return laenge;
	}

	/**
	 * 
	 * @param index
	 *            
	 * @return The prime factor corresponding to a given index.
	 */
	public PrimeFrequency get(int index) {
		return store[index];
	}

	/**
	 * @return All prime factors among with their respective frequencies.
	 */
	public PrimeFrequency[] get() {
		return store;
	}
}
