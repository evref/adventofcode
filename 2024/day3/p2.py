import numpy as np
import re

def parse():
    with open("input", 'r') as file:
        data = file.read()

    pattern = r"(?<=mul\()\d+,\d+(?=\))|do\(\)|don\'t\(\)"
    matches = re.findall(pattern, data)
    
    o = []
    isDo = True
    for match in matches:
        if match == 'do()':
            isDo = True
        elif match == "don't()":
            isDo = False
        else:
            if isDo:
                temp = list(map(int, match.split(",")))
                o.append(temp)

    return np.array(o)

def solve(data):
    mul = data[:,0] * data[:,1]
    sum = np.sum(mul)

    return sum

data = parse()
solution = solve(data)

print(solution)