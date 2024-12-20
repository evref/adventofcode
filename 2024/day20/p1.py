import numpy as np


class node:
    def __init__(self, value: int, type: str, pos: tuple[int, int]):
        self.val = value
        self.type = type
        self.pos = pos


def parse(path: str):
    data = []
    with open(path, 'r') as file:
        for line_idx, line in enumerate(file):
            data.append([])
            for c_idx, c in enumerate(line):
                if c != '\n':
                    data[line_idx].append(c)
                if c == 'S':
                    start_pos = (c_idx, line_idx)

    if start_pos == (-1,-1):
        print("ERROR: NO START SYMBOL FOUND")
        return -1

    return (data, start_pos)

def label_paths(matrix, start_pos):
    dim_x, dim_y = len(matrix), len(matrix[0])
    path = []
    path_dict = {}
    cur_pos = start_pos
    path.append(node(0, "start", start_pos))
    path_dict[start_pos] = node(0, "start", start_pos)
    end_loop = False
    while True:
        offsets_to_check = [(0,1), (0,-1), (1,0), (-1,0)]
        # Check out of bounds x-axis <0
        if cur_pos[0]-1 < 0:
            offsets_to_check.remove((-1,0))
        # Check out of bounds x-axis after dim_x
        if cur_pos[0]+1 >= dim_x:
            offsets_to_check.remove((1,0))
        # Check oob y-axis <0
        if cur_pos[1]-1 < 0:
            offsets_to_check.remove((0,-1))
        # Check oob y-axis after dim_y
        if cur_pos[1]+1 >= dim_y:
            offsets_to_check.remove((0,1))

        # Check for paths in none of these positions
        for offsets in offsets_to_check:
            new_pos = (cur_pos[0]+offsets[0], cur_pos[1]+offsets[1])
            # If next pos is path pos
            if matrix[new_pos[1]][new_pos[0]] == '.':
                matrix[new_pos[1]][new_pos[0]] = str(len(path))
                path.append(node(len(path), "path", new_pos))
                path_dict[new_pos] = node(len(path), "path", new_pos)
                cur_pos = new_pos
                break
            # If next pos is end pos
            elif matrix[new_pos[1]][new_pos[0]] == 'E':
                path.append(node(len(path), "end", new_pos))
                path_dict[new_pos] = node(len(path), "path", new_pos)
                end_loop = True
                break
        
        if end_loop:
            break
    """
    DEBUG PRINT FOR NEW MATRIX (lite sämst, printar på arrayformat atm)
    with open("input_mini_mod", 'w') as file:
        for line in matrix:
            print(line)
            buffer = ""
            for c in matrix:
                buffer += str(c)
            buffer += '\n'

        file.write(buffer)
    """
    
    return (path, path_dict)

def is_out_of_bounds(pos: tuple[int, int], dim_x: int, dim_y: int):
    return pos[0] < 0 or pos[0] >= dim_x or pos[1] < 0 or pos[1] >= dim_y

def solve1(matrix, path, path_dict, cheat_save_limit):
    dim_x, dim_y = len(matrix[0]), len(matrix)
    cheat_count = 0
    for step in path:
        offsets_to_check = [(0,2), (0,3), (-1,2), (1,2), (-2,1), (-1,1), (1,1), (1,2), (-3,0), (-2,0), (2,0), (3,0), (-2,-1), (-1,-1), (1,-1), (2,-1), (-1,-2), (0,-2), (1,-2), (0,-3)]
        for offset in offsets_to_check:
            new_pos = (step.pos[0]+offset[0], step.pos[1]+offset[1])
            if not is_out_of_bounds(new_pos, dim_x, dim_y):
                if matrix[new_pos[1]][new_pos[0]] != '#':
                    dist = 3

                    # Reduce dist if offst can be reached with two steps
                    if (abs(offset[0]) == 1 and abs(offset[1]) == 1) or (abs(offset[0]) == 2 and abs(offset[1]) == 0) or (abs(offset[0]) == 0 and abs(offset[1]) == 1):
                        dist -= 1

                    # If cheat path is good enough, add to cheat count
                    if path_dict[new_pos].val > step.val + dist + cheat_save_limit:
                        cheat_count += 1

    return cheat_count



data, start_pos = parse("input")
matrix_counted_path, path_dict = label_paths(data, start_pos)
solution = solve1(data, matrix_counted_path, path_dict, 100)

print(solution)