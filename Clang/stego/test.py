import sys

dist = [
    [0, 10, 15, 20],
    [10, 0, 35, 25],
    [15, 35, 0, 30],
    [20, 25, 30, 0]
]
 
n = len(dist)
VISITED_ALL = (1 << n) - 1
 
dp = [[-1] * (1 << n) for _ in range(n)]
parent = [[-1] * (1 << n) for _ in range(n)]
 
def tsp(pos, visited):
    if visited == VISITED_ALL:
        return dist[pos][0]
 
    if dp[pos][visited] != -1:
        return dp[pos][visited]
 
    min_cost = sys.maxsize
 
    for city in range(n):
        if (visited & (1 << city)) == 0:
            new_visited = visited | (1 << city)
            cost = dist[pos][city] + tsp(city, new_visited)
            if cost < min_cost:
                min_cost = cost
                parent[pos][visited] = city  
 
    dp[pos][visited] = min_cost
    return min_cost
 
 
def get_path():
    path = [0]  
    visited = 1  
    pos = 0
 
    while True:
        next_city = parent[pos][visited]
        if next_city == -1:
            break
        path.append(next_city)
        visited |= (1 << next_city)
        pos = next_city
 
    path.append(0)  
    return path
 
 
min_cost = tsp(0, 1)
path = get_path()
 
 
print("Biaya minimum:", min_cost)
print("Jalur terbaik:", ' → '.join(map(str, path)))
 
 
