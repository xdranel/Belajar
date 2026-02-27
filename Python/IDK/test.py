import heapq


class Node:
    def __init__(self, position, g=0, h=0):
        self.position = position  # (x, y) coordinates

        self.g = g  # Cost from start to this node

        self.h = h  # Heuristic cost to goal

        self.f = g + h  # Total cost

    def __lt__(self, other):
        return self.f < other.f


def heuristic(a, b):
    # Using Manhattan distance as heuristic

    return abs(a[0] - b[0]) + abs(a[1] - b[1])


def a_star(start, goal, grid):
    open_list = []

    closed_set = set()

    start_node = Node(start, 0, heuristic(start, goal))

    heapq.heappush(open_list, start_node)

    while open_list:
        current_node = heapq.heappop(open_list)

        if current_node.position == goal:
            return current_node.g  # Return the cost to reach the goal

        closed_set.add(current_node.position)

        # Possible movements: up, down, left, right

        neighbors = [(0, 1), (1, 0), (0, -1), (-1, 0)]

        for new_position in neighbors:
            neighbor_position = (
                current_node.position[0] + new_position[0],
                current_node.position[1] + new_position[1],
            )

            # Check if the neighbor is within the grid bounds and not an obstacle

            if (
                0 <= neighbor_position[0] < len(grid)
                and 0 <= neighbor_position[1] < len(grid[0])
                and grid[neighbor_position[0]][neighbor_position[1]] == 0
                and neighbor_position not in closed_set
            ):
                g_cost = current_node.g + 1

                h_cost = heuristic(neighbor_position, goal)

                neighbor_node = Node(neighbor_position, g_cost, h_cost)

                # Check if this neighbor is already in the open list

                if not any(
                    neighbor.position == neighbor_node.position and neighbor.g <= g_cost
                    for neighbor in open_list
                ):
                    heapq.heappush(open_list, neighbor_node)

    return None  # Return None if there is no path


# Example usage

grid = [
    [0, 0, 0, 0, 0],
    [0, 1, 1, 1, 0],
    [0, 0, 0, 0, 0],
    [0, 1, 1, 1, 0],
    [0, 0, 0, 0, 0],
]


start = (0, 0)  # Starting position

goal = (4, 4)  # Goal position


cost = a_star(start, goal, grid)

if cost is not None:
    print(f"Path found with cost: {cost}")

else:
    print("No path found.")
