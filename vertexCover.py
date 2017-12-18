#! /usr/bin/python
#-*- coding: utf-8 -*-
import sys
import math
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
        noeud2= randint(1, n)
        G.add_edge(noeud1, noeud2)
    print(list(G.edges()))
    print(list(G.nodes()))

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


