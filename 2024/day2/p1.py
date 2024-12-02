import numpy as np

with open('input', 'r') as file:
    data = [list(map(int, line.split())) for line in file]

num_safe_reports = 0
for row in data:
    row = np.array(row)
    res = row[1:] - row[:-1]
    safe = True
    for i in range(len(res)):
        if abs(res[i]) > 3:
            safe = False
            break
    
    if safe and (np.all(res > 0) or np.all(res < 0)):
        num_safe_reports += 1

print(num_safe_reports)