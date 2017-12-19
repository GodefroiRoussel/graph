#! /usr/bin/python
#-*- coding: utf-8 -*-
import os
import copy as cp
import networkx as nx
from random import randint

# Génération d'un graphe aléatoire à n sommets (avec un remplissage de 25% si possible)
def init(n):
    G = nx.Graph()
    G.add_nodes_from(range(1,n+1))
    
    # Le nombre d'arêtes max est de (n*(n-1))/2 et on cherche un remplissage de 0.25%
    nbAretesMax = (n*(n-1))/2
    remplissageSouhaitee = 0.25 * nbAretesMax
    while (G.number_of_edges() < remplissageSouhaitee): #Remplissage est inférieur à 25% on ajoute des arêtes
        noeud1 = randint(1, n)
        noeud2 = noeud1
        while(noeud1==noeud2): #Ici on ne veut pas qu'un graphe possède une arête pointant vers lui même.
            noeud2= randint(1, n)
        G.add_edge(noeud1, noeud2)
    print("Le graphe possède les arêtes suivantes :")
    print(list(G.edges()))
    #print(list(G.nodes()))
    print("Affichage les couples de sommets permettant un maximum matching")
    print(nx.maximal_matching(G))  #Vérifier que on obtient bien : 

    #Calcul de la solution optimale de VertexCover (recherche exhaustive)
    grapheParent = cp.deepcopy(G) #Copie en profondeur de G (chaque modification de graphe2 ne modifiera pas G)
    
    #Ici on fait une boucle qui va vérifier que les différents graphes sont des VC. A la fin de la boucle, nous avons forcément trouvé min VC
    for i in range (1,n+1):
        newpid = os.fork()
        if newpid == 0: #Si c'est un enfant 
            grapheEnfant = cp.deepcopy(grapheParent)
            #print(isVertexCover(grapheParent,grapheEnfant))
            print("Enfant: %d\n" % i)

        else:
            newpid = os.fork()
            pids = (os.getpid(), newpid)
            #print("parent: %d, child: %d\n" % pids)

#Fonction retournant un booléen disant qu'un sous graphe est un vertex cover d'un autre graphe
# @In : Graph x Graph
# @Out: Boolean
def isVertexCover(grapheParent, grapheEnfant):
    return list(grapheEnfant.edges()) == list(grapheParent.edges())


def main():
    try:
        n = int(input("Entrer un nombre de sommets n : "))
        if(n<1):
            raise ValueError("Erreur : nombre de noeud < 1" )
        init(n)
    except ValueError as err:
            print(err)

    

if __name__ == "__main__":
    main()


#En sortie : le min VC (via une recherche exhaustive) et min VC (avec une 2-approx via le MaxMatching)


