#! /usr/bin/python
#-*- coding: utf-8 -*-
import sys
import math
import networkx as nx


# Génération d'un graphe aléatoire à n sommets (avec un remplissage de 25% si possible)
def init(n):
    G = nx.Graph()
    G.add_nodes_from(range(1,n))
    

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


