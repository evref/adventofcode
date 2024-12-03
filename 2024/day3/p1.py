import numpy as np
import re

def parse():
    with open("input", 'r') as file:
        data = file.read()

    pattern = r"(?<=mul\()\d+,\d+(?=\))"
    matches = re.findall(pattern, data)
    
    o = []
    for match in matches:
        temp = list(map(int, match.split(",")))
        o.append(temp)

    return np.array(o)

def solve1(data):
    mul = data[:,0] * data[:,1]
    sum = np.sum(mul)

    return sum

data = parse()
solution = solve1(data)

print(solution)