import math

def parse():
    data = []
    with open("input", 'r') as file:
        for line_idx, line in enumerate(file):
            data.append([])
            line = line.strip()
            for c_idx in range(len(line)):
                data[line_idx].append(line[c_idx])
    
    return data

def find_guard(data):
    for line_idx in range(len(data)):
        for c_idx in range(len(data[line_idx])):
            if data[line_idx][c_idx] == '^' or data[line_idx][c_idx] == '<' or data[line_idx][c_idx] == '>' or data[line_idx][c_idx] == 'v':
                dir = (-1,0)
                if data[line_idx][c_idx] == '<':
                    dir = (0,-1)
                elif data[line_idx][c_idx] == '>':
                    dir = (0,1)
                elif data[line_idx][c_idx] == 'v':
                    dir = (1,0)
                return ((line_idx, c_idx), dir)
            
def check_guard_pos_exit(data, guard_pos_y, guard_pos_x):
    return guard_pos_x < 0 or guard_pos_x >= len(data[0]) or guard_pos_y < 0 or guard_pos_y >= len(data)

def check_guard_pos_obstruction(data, guard_pos_y, guard_pos_x):
    return data[guard_pos_y][guard_pos_x] == '#'

def new_dir(dir_x, dir_y):
    if dir_y == -1 and dir_x == 0:
        return (0,1)
    elif dir_y == 0 and dir_x == 1:
        return (1,0)
    elif dir_y == 1 and dir_x == 0:
        return (0,-1)
    else:
        return (-1,0)

def count_x(data):
    count = 0
    for line in data:
        for c in line:
            if c == 'X':
                count += 1

    return count

def solve1(data):
    guard_pos, guard_dir = find_guard(data)
    y, x = guard_pos
    dir_y, dir_x = guard_dir

    while True:
        new_y = y + dir_y
        new_x = x + dir_x

        # If guard has exited
        if check_guard_pos_exit(data, new_y, new_x):
            data[y][x] = 'X'
            return count_x(data)
        
        # Get new direction if obstruction in front of guard
        if check_guard_pos_obstruction(data, new_y, new_x):
            dir_y, dir_x = new_dir(dir_x, dir_y)
        else: # Otherwise keep on walking and replace old pos with an 'X'
            data[y][x] = 'X'
            x, y = new_x, new_y

def solve2(data):
    guard_pos, guard_dir = find_guard(data)
    y, x = guard_pos
    dir_y, dir_x = guard_dir
    turn_positions = []

    while True:
        new_y = y + dir_y
        new_x = x + dir_x

        # If guard has exited
        if check_guard_pos_exit(data, new_y, new_x):
            data[y][x] = 'X'
            break
        
        # Get new direction if obstruction in front of guard
        if check_guard_pos_obstruction(data, new_y, new_x):
            dir_y, dir_x = new_dir(dir_x, dir_y)
            turn_positions.append((len(turn_positions), y, x))
        else: # Otherwise keep on walking and replace old pos with an 'X'
            data[y][x] = 'X'
            x, y = new_x, new_y

    # Exhaustive search / Brute force search for the three edges of a rectangle, a loop is found if the three edges of a rectangle are found
    # and the fourth edge == 'X' in data.
    count = 0
    mid_points = []
    for mid_pos in turn_positions:
        _, mid_y, mid_x = mid_pos
        for edge_pos_1 in turn_positions:
            if mid_pos == edge_pos_1 or mid_points.__contains__(edge_pos_1):
                continue
            _, edge_1_y, edge_1_x = edge_pos_1
            # Check if edges are next on same x or same y
            if mid_y == edge_1_y or mid_x == edge_1_x:
                y_is_same = mid_y == edge_1_y
                for edge_pos_2 in turn_positions:
                    if edge_pos_2 == mid_pos or edge_pos_2 == edge_pos_1 or mid_points.__contains__(edge_pos_2):
                        continue
                    _, edge_2_y, edge_2_x = edge_pos_2
                    # If y values were same now check x values
                    if y_is_same:
                        if mid_x == edge_2_x and data[edge_2_y][edge_1_x] == 'X':
                            count += 1
                            print(f"Mid point: {mid_pos}")
                            print(f"Edge point 1: {edge_pos_1}")
                            print(f"Edge point 2: {edge_pos_2}")
                            print()
                            mid_points.append(mid_pos)
                    else: # If x values were same now check y values
                        if mid_y == edge_2_y and data[edge_1_y][edge_2_x] == 'X':
                            count += 1
                            print(f"Mid point: {mid_pos}")
                            print(f"Edge point 1: {edge_pos_1}")
                            print(f"Edge point 2: {edge_pos_2}")
                            print()
                            mid_points.append(mid_pos)
                        
    # Take into account that each rectangle will generate two permutations as we don't save which edges we've saved
    return round(count / 2)

data = parse()
solution = solve2(data)

print(solution)