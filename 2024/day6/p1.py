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

data = parse()
solution = solve1(data)

print(solution)