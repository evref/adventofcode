def parse():
    data = []
    with open("input", 'r') as file:
        for line in file:
            data.append(line.strip())

    return data

def word_is_xmas(w: str):
    return w == "XMAS" or w == "SAMX"

# The idea is to go through the array from left to right and only to check possible xmas-spots that are to come. This should 
# avoid duplicate counting.
def solve(data):
    xmas_counter = 0
    # Go through entire matrix
    for line_idx, line in enumerate(data):
        for c_idx, c in enumerate(line):
            # Create condition flags beforehand, back and forward differ due to inclusiveness of lower bound and exclusiveness of upper bound
            enough_space_forward = c_idx+3 < len(line)
            enough_space_back = c_idx-3 >= 0
            enough_space_down = line_idx+3 < len(data)

            # Make sure that there is enough space left in front to include xmas
            if enough_space_forward:
                # Check characters in front (left to right)
                if word_is_xmas(line[c_idx:c_idx+4]):
                    xmas_counter += 1

            # Check characters in diagonal starting in current c (upper left to lower right)
            if enough_space_down and enough_space_forward:
                if word_is_xmas(data[line_idx][c_idx]+data[line_idx+1][c_idx+1]+data[line_idx+2][c_idx+2]+data[line_idx+3][c_idx+3]):
                    xmas_counter += 1

            # Check characters below (up to down)
            if enough_space_down:
                if word_is_xmas(data[line_idx][c_idx]+data[line_idx+1][c_idx]+data[line_idx+2][c_idx]+data[line_idx+3][c_idx]):
                    xmas_counter += 1
                
            # Make sure that there is enough space left for backwards diagonal
            if enough_space_back and enough_space_down:
                # Check characters in diagonal starting in current c (upper right to lower left)
                if word_is_xmas(data[line_idx][c_idx]+data[line_idx+1][c_idx-1]+data[line_idx+2][c_idx-2]+data[line_idx+3][c_idx-3]):
                    xmas_counter += 1

    return xmas_counter



data = parse()
solution = solve(data)

print(solution)