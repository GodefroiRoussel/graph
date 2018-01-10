package algo_avance;

public class Objet {

		private int gain;
		private int poids;
		
		public Objet(int g, int p){
			this.gain = g;
			this.poids = p; 
		}
		
		public int getPoids(){
			return poids;
		}
		
		public int getGain(){
			return gain;
		}
}
