package de.hdm_stuttgart.mi.sd1.proect_sieb;

public class PrimeFrequency {

	/**
	 * The Prime Factor
	 */
	private int prime;

	/**
	 * The prime's frequency of appearance.
	 */
	private int frequency;
	
	/**
	 * @return The prime factor's frequency of appearance.
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * 
	 * @return The prime factor
	 */
	public int getPrime() {
		return prime;
	}

	/**
	 * @param prime
	 *            {@link #prime}
	 * @param frequency
	 *            The prime's frequency of appearance.
	 */
	public PrimeFrequency(final int prime, final int frequency) {
		this.prime = prime;
		this.frequency = frequency;
	}

	

	/**
	 * change the given frequency value.
	 * 
	 * @param frequency
	 *            change by this value.
	 */
	public void addFrequency(@SuppressWarnings("hiding") final int frequency) {
		this.frequency += frequency;
	}

	/**
	 * vergleicht die Primzahl zweier PrimFrequency-Objekte
	 * 
	 * @param Ein
	 *            PrimeFrequency Objekt
	 * @return true, wenn die PrimZahl beider Objekte übereinstimmt. Ansonsten
	 *         false.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (((PrimeFrequency) obj).getPrime() == this.getPrime()) {
			return true;
		} else {
			return false;
		}
	}
}
