import math

def parse():
    data = []
    with open("input_mini", 'r') as file:
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
    
def print_path(data, y, x, dir_x, just_turned):
    if just_turned and dir_x != 0:
        return '|'
    elif just_turned and dir_x == 0:
        return '-'
    
    char_to_print = '|'
    if dir_x != 0:
        char_to_print = '-'
    if (data[y][x] == '|' and char_to_print == '-') or (data[y][x] == '-' and char_to_print == '|'):
        char_to_print = '+'

    return char_to_print

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
    last_turn_positions = []
    new_obstruction_count = 0
    just_turned = False

    while True:
        new_y = y + dir_y
        new_x = x + dir_x

        # If guard has exited
        if check_guard_pos_exit(data, new_y, new_x):
            data[y][x] = print_path(data, y, x, dir_x, just_turned)
            break
        
        # Get new direction if obstruction in front of guard
        if check_guard_pos_obstruction(data, new_y, new_x):
            dir_y, dir_x = new_dir(dir_x, dir_y)
            last_turn_positions.append((y, x))
            just_turned = True
        else: # Otherwise keep on walking and replace old pos with an 'X'
            # Check for single loops (rectangles) in last 3 turns
            if len(last_turn_positions) == 3:
                if (y == last_turn_positions[0][0] and x == last_turn_positions[2][1]) or (y == last_turn_positions[2][0] and x == last_turn_positions[0][1]):
                    y_mid, x_mid = last_turn_positions[1]
                    y_first, x_first = last_turn_positions[0]
                    y_last, x_last = last_turn_positions[2]
                    if y_mid == y_first or y_mid == y_last:
                        if y_mid == y_first and x_mid == x_last:
                            new_obstruction_count += 1
                            print(f"Mid: {last_turn_positions[1]}")
                            print(f"First: {last_turn_positions[0]}")
                            print(f"Last: {last_turn_positions[2]}")
                            print()
                        elif y_mid == y_last and x_mid == x_first:
                            new_obstruction_count += 1
                            print(f"Mid: {last_turn_positions[1]}")
                            print(f"First: {last_turn_positions[0]}")
                            print(f"Last: {last_turn_positions[2]}")
                            print()
            
            # Make sure last_turn_positions contains only last 3 turns
            if len(last_turn_positions) > 3:
                last_turn_positions.pop(0)

            sim_y, sim_x = y, x
            
            while True:
                sim_dir_y, sim_dir_x = new_dir(dir_x, dir_y)
                sim_new_y, sim_new_x = sim_y+sim_dir_y, sim_x+sim_dir_x

                if check_guard_pos_exit(data, sim_new_y, sim_new_x):
                    break
                elif check_guard_pos_obstruction(data, sim_new_y, sim_new_x):
                    break
                
                if (data[sim_new_y][sim_new_x] == '|' and sim_dir_x == 0) or (data[sim_new_y][sim_new_x] == '-' and sim_dir_x != 0):
                    new_obstruction_count += 1
                    break

                sim_y, sim_x = sim_new_y, sim_new_x
            

            data[y][x] = print_path(data, y, x, dir_x, just_turned)
            just_turned = False

            x, y = new_x, new_y

    for line in data:
        s = ""
        for c in line:
            s += c
        print(s)
    return new_obstruction_count

data = parse()
solution = solve2(data)

print(solution)