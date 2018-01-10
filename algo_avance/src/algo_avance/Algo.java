package algo_avance;

import java.util.ArrayList;
import java.util.List;
import java.math.*;

public class Algo {
	
	public static void main(String[] args) {
		
		// Initialisation des objets
		SacADos s = new SacADos(11);
		List<Objet> objets = new ArrayList<Objet>();
		
		Objet o = new Objet(0,0);
		objets.add(o);
		o = new Objet(1,1);
		objets.add(o);
		o = new Objet(6,2);
		objets.add(o);
		o = new Objet(18,5);
		objets.add(o);
		o = new Objet(22,6);
		objets.add(o);
		o = new Objet(28,7);
		objets.add(o);
		
		int[][] P = new int[objets.size()][s.getCapacite()];
		
		//Initialisation de la premiere colonne et de la premiere ligne à 0
		for (int i=0; i<objets.size();i++){
			P[i][0] = 0;
		}
		
		for (int i=0; i<s.getCapacite();i++){
			P[0][i] = 0;
		}
		
		// Début de l'algo dynamique
		// i est le numéro de la ligne et j sera le numéro de la colonne
		for(int i=1; i<objets.size(); i++){
			for (int j=1; j< s.getCapacite(); j++){
				Objet objetActuel = objets.get(i);
				if(objetActuel.getPoids()<=j){
					P[i][j] = Math.max(P[i-1][j], objetActuel.getGain() + P[i-1][j-objetActuel.getPoids()]);
				}
				else{
					P[i][j] = P[i-1][j];
				}
			}
		}
		
		//Affichage de la matrice
		for(int i=0; i<objets.size(); i++){
			for (int j=0; j< s.getCapacite(); j++){
				System.out.print(P[i][j]+" ");
	        }
			System.out.println();
	    }
		
		//Affichage du résultat
		System.out.println("Résultat = "+P[objets.size()-1][s.getCapacite()-1]);
		
	}

}
