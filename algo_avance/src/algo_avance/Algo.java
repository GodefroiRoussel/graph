// Travail réalisé par Hassan.K, Piekarek.O et Roussel.G

package algo_avance;

import java.util.ArrayList;
import java.util.List;
import java.math.*;

public class Algo {
	
	public static void main(String[] args) {
		
		// Initialisation des objets
		SacADos s = new SacADos(11);
		List<Objet> objets = new ArrayList<Objet>();	
		
		Objet o = new Objet(1,1);
		objets.add(o);
		o = new Objet(6,2);
		objets.add(o);
		o = new Objet(18,5);
		objets.add(o);
		o = new Objet(22,6);
		objets.add(o);
		o = new Objet(28,7);
		objets.add(o);
		
		int[][] P = new int[objets.size()+1][s.getCapacite()+1];
		
		// Initialisation de la premiere colonne et de la premiere ligne à 0
		for (int i=0; i<objets.size();i++){
			P[i][0] = 0;
		}
		
		for (int i=0; i<=s.getCapacite();i++){
			P[0][i] = 0;
		}
		
		// Début de l'algo dynamique
		// i est le numéro de la ligne et j sera le numéro de la colonne
		for(int i=1; i<objets.size(); i++){
			for (int j=1; j<= s.getCapacite(); j++){
				Objet objetActuel = objets.get(i);
				if(objetActuel.getPoids()<=j){
					P[i][j] = Math.max(P[i-1][j], objetActuel.getGain() + P[i-1][j-objetActuel.getPoids()]);
				}
				else{
					P[i][j] = P[i-1][j];
				}
			}
		}
		
		// Affichage de la matrice
		for(int i=0; i<objets.size(); i++){
			for (int j=0; j<= s.getCapacite(); j++){
				System.out.print(P[i][j]+" ");
	        }
			System.out.println();
	    }
		
		// Affichage du résultat
		System.out.println("Résultat = "+P[objets.size()-1][s.getCapacite()]);
		
		
		///////////////////// Algorithme glouton avec génération aléatoire ///////////////////////////////
		System.out.println();
		System.out.println("Algorithme Glouton avec les ratios : gain / poids");
		System.out.println();
		
		//Initialisation
		List<Objet> o2 = new ArrayList<Objet>(); // Objets pour le deuxième sac
		int nbObjetsAGenerer = (int) (Math.random() * 10);
		int capaciteSac = (int) (Math.random() * 10);
		int capaciteEnCours = 0;
		int gainEnCours = 0;

		System.out.println("Nombre d'objets crées : "+nbObjetsAGenerer);
		//Création des objets
		for (int i=0; i<nbObjetsAGenerer; i++){
			int poidsObjet = (int) (Math.random()*10) +1; //Eviter le cas 0
			int gainObjet = (int) (Math.random()*200);
			
			Objet x = new Objet(gainObjet,poidsObjet);
			o2.add(x);
			System.out.println("Objet "+i+" de poids : "+x.getPoids()+" kg et de gain : "+x.getGain()+" € de ratio : "+x.getRatio());
		}
		
		SacADos s2 = new SacADos(capaciteSac);
		
		System.out.println();
		System.out.println("Ajout des objets dans le sac");
		System.out.println();
		
		// Algorithme glouton
		Objet objCourant;
		//Tant que le sac a dos n'est pas plein et que il existe un objet qui peut encore rentrer dans le sac et la liste d'objet n'est pas null
		while(capaciteEnCours < s2.getCapacite() && existeSolution(o2, s2.getCapacite()-capaciteEnCours) && o2.size()!=0){
			Objet objMaxi = o2.get(0); //Récupère le premier élément de la liste d'objet
			
			for(int i=1; i<o2.size(); i++){
				objCourant = o2.get(i);
				if(objCourant.getRatio()>objMaxi.getRatio() && s2.getCapacite()-capaciteEnCours-objCourant.getPoids() >= 0){
					objMaxi = objCourant;
				}
			}
			capaciteEnCours += objMaxi.getPoids();
			gainEnCours += objMaxi.getGain();
			System.out.println("L'objet de poids : "+objMaxi.getPoids()+" kg et de gain : "+objMaxi.getGain()+" € a été ajouté au sac");
			o2.remove(objMaxi);
		}
		
		// Affichage du résultat
		System.out.println();
		System.out.println("Capacité du sac : "+capaciteSac+" kg");
		System.out.println("Résultat : gain : "+gainEnCours+" € poids du sac : "+capaciteEnCours+" kg");
		
	}

	private static boolean existeSolution(List<Objet> objets, int capacite) {
		boolean solution = false;
		int i=0;
		while (i<objets.size() && !solution){
			if((objets.get(i)).getPoids() <= capacite){
				solution = true;
			}
			i++;
		}
		
		return solution;
	}

}
