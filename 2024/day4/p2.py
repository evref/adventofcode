def parse():
    data = []
    with open("input", 'r') as file:
        for line in file:
            data.append(line.strip())

    return data

def words_are_mas(w1: str, w2: str):
    return (w1 == "MAS" or w1 == "SAM") and (w2 == "MAS" or w2 == "SAM")

# The idea is to go through the array from left to right and only to check possible xmas-spots that are to come. This should 
# avoid duplicate counting.
def solve(data):
    mas_counter = 0
    # Go through entire matrix
    for line_idx, line in enumerate(data):
        for c_idx, c in enumerate(line):
            # Create condition flags beforehand, back and forward differ due to inclusiveness of lower bound and exclusiveness of upper bound
            enough_space_forward = c_idx+2 < len(line)
            enough_space_down = line_idx+2 < len(data)

            if enough_space_forward and enough_space_down:
                w1 = data[line_idx][c_idx]+data[line_idx+1][c_idx+1]+data[line_idx+2][c_idx+2]
                w2 = data[line_idx+2][c_idx]+data[line_idx+1][c_idx+1]+data[line_idx][c_idx+2]
                if words_are_mas(w1, w2):
                    mas_counter += 1

    return mas_counter



data = parse()
solution = solve(data)

print(solution)