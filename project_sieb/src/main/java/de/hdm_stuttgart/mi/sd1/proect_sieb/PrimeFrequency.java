package de.hdm_stuttgart.mi.sd1.proect_sieb;

public class PrimeFrequency {
	
	 public final int prime;
	   
	   /**
	    * The prime's frequency of appearance. 
	    */
	   int frequency;

	   
	   /**
	    * @param prime {@link #prime} 
	    * @param frequency The prime's frequency of appearance.
	    */
	   public PrimeFrequency(final int prime, final int frequency) {
	      this.prime = prime;
	      this.frequency = frequency;
	   }

	   /**
	    * @return The prime factor's frequency of appearance.
	    */
	   public int getFrequency() {
	      return frequency;
	   }
	   
	   /**
	    * change the given frequency value.
	    * @param frequency change by this value.
	    */
	   public void addFrequency(@SuppressWarnings("hiding") final int frequency) {
	      this.frequency += frequency;
	   }

	   @Override
	   public boolean equals(final Object obj) {
		return false;
		//TODO
	   }   			
}
