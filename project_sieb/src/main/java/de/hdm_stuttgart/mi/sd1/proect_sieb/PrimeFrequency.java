package de.hdm_stuttgart.mi.sd1.proect_sieb;

public class PrimeFrequency {
	
		private int prime;
	   
	   /**
	    * The prime's frequency of appearance. 
	    */
	   private int frequency;

	   
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
	   
	   public int getPrime(){
		   return prime;
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
		   if(((PrimeFrequency)obj).getPrime() == this.getPrime()){
			   return true;
		   }
		   else{
			   return false;
		   }
	   }   			
}
