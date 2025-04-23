from typing import final

import networkx as nx
import matplotlib.pyplot as plt

def wrap_right(init_pos, shift, G):
    final_pos = init_pos + shift #O(1)
    if final_pos >= len(G): #O(1)
        return final_pos % len(G) #O(1)
    return init_pos + shift % len(G) #O(1)

def wrap_left(init_pos, shift, G):
    final_pos = (init_pos - shift) #O(1)
    if final_pos < 0: #O(1)
        return len(G) - abs(final_pos) #O(1)
    return init_pos - shift % len(G) #O(1)

def create_list(input_str):
    stripped = input_str.strip('[] ') #O(n)
    all_nodes_pairs = stripped.split(' , ') #O(n)
    result = []
    for i in all_nodes_pairs: #O(V+V+V^2)=O(V^2)
        pair = i.strip('() ') #Loops over each pair and there are V pairs in total
        pairs = [pair.strip() for pair in pair.split(',')] #Loops over each pair and creates a list
        char = pairs[0] #O(1)
        number = int(pairs[1]) #O(1)
        result.append((char, number)) #1+2+3+...+|V|
    return result

def string_to_dict(input_str):
    l = create_list(input_str) #O(V^2)
    result_dict = {}
    for i in l: #O(V)
        result_dict[i[0]] = i[1] #O(1)
    return result_dict

def create_graph(input_str):
    list = create_list(input_str) #O(V^2)
    dict = string_to_dict(input_str) #O(V^2)
    G = nx.DiGraph() #O(1)
    G.add_nodes_from(dict.keys()) #O(V)
    count = 0
    for i in dict.keys(): #O(V)
        first_edge = wrap_left(count, dict[i], list)
        second_edge = wrap_right(count, dict[i], list)
        print(i, list[first_edge][0], list[second_edge][0])
        G.add_edge(i, list[first_edge][0]) #O(V)
        G.add_edge(i, list[second_edge][0]) #O(V)
        count += 1

    nx.draw(G, with_labels=True)
    plt.savefig("graph.png") #O(V)

    return G

create_graph("[ (I, 2) , (A, 5) , (E, 4) , (F,2) , (T, 2) , (S, 3) ]")
