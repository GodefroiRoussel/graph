#! /usr/bin/python
#-*- coding: utf-8 -*-
import sys
import math
import networkx as nx


# Génération d'un graphe aléatoire à n sommets (avec un remplissage de 25% si possible)
def init():
    G = nx.Graph()
    G.add_nodes_from(range(1,10))
    G.add_edges_from([(1, 2), (1, 3)])
    print(G.number_of_nodes())
    print(G.number_of_edges())
    print(list(G.edges))
    

def main():
    init()

if __name__ == "__main__":
    main()


#En sortie : le min VC (via une recherche exhaustive) et min VC (avec une 2-approx via le MaxMatching)


